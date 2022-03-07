package com.codesquad_han.kotlin_drawingapp.rectangle

import android.content.Context
import android.graphics.*
import android.os.Build
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.codesquad_han.kotlin_drawingapp.R
import com.codesquad_han.kotlin_drawingapp.model.Rectangle

class RectangleDrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var selectedRectangle: Rectangle? = null
    private var rectangleList = mutableListOf<Rectangle>()

    private var paint = Paint()
    private var strokePaint = Paint()
    private lateinit var clickListener: RectangleViewClickInterface

    fun setClickListener(listener: RectangleViewClickInterface) {
        this.clickListener = listener
    }

    override fun onDraw(canvas: Canvas) {
        rectangleList.forEach { rectangle ->
            paint.setColor(
                Color.argb(
                    rectangle.transparency.transparency * 255 / 10,
                    rectangle.backgroundColor.r,
                    rectangle.backgroundColor.g,
                    rectangle.backgroundColor.b
                )
            )

            if (rectangle.imageUri == null) {
                canvas.drawRect(
                    rectangle.point.x.toFloat(),
                    rectangle.point.y.toFloat(),
                    (rectangle.point.x + rectangle.size.width).toFloat(),
                    (rectangle.point.y + rectangle.size.height).toFloat(),
                    paint
                )
            }

            // 사각형에 할당된 이미지 uri가 있다면 그리도록 한다
            rectangle.imageUri?.let {
                var bitmap: Bitmap
                if (Build.VERSION.SDK_INT < 29) { //
                    bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }

                var imagePaint = Paint()
                imagePaint.alpha = rectangle.transparency.transparency * 255 / 10
                canvas.drawBitmap(
                    bitmap, null, Rect(
                        rectangle.point.x,
                        rectangle.point.y,
                        (rectangle.point.x + rectangle.size.width),
                        (rectangle.point.y + rectangle.size.height)
                    ), imagePaint
                )
            }

            selectedRectangle?.let {
                if (rectangle.id == it.id) {
                    canvas.drawRect(
                        rectangle.point.x.toFloat(),
                        rectangle.point.y.toFloat(),
                        (rectangle.point.x + rectangle.size.width).toFloat(),
                        (rectangle.point.y + rectangle.size.height).toFloat(),
                        strokePaint
                    )
                }
            }

            Log.d("AppTest", "RectangleDrawingView/ ${rectangle.toString()}")
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentPoint = PointF(event.x, event.y)

        // 클릭 리스너 사용해서 액티비티 연결하기
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                checkTouchPoint(currentPoint.x, currentPoint.y)
            }
        }

        return true
    }

    fun drawingViewInit() {
        strokePaint.strokeWidth = 10f
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = ContextCompat.getColor(context, R.color.selected)
    }

    // 사각형 추가 혹은 선택된 사각형 투명도 변경 시 호출
    fun drawRectangle(updatedRectangleList: MutableList<Rectangle>) {
        rectangleList = updatedRectangleList
        invalidate()
    }

    // 선택 좌표에 사각형이 여러 개 있는 경우 가장 최근에 생성된 사각형을 선택
    fun checkTouchPoint(touchX: Float, touchY: Float) {
        var selected = false
        for (i in rectangleList.size - 1 downTo 0) {
            if (touchX >= rectangleList[i].point.x
                && touchX <= rectangleList[i].point.x + rectangleList[i].size.width
                && touchY >= rectangleList[i].point.y
                && touchY <= rectangleList[i].point.y + rectangleList[i].size.height
            ) {
                selectedRectangle = rectangleList[i]
                selected = true

                selectedRectangle?.let { // 액티비티에서 선택한 사각형 색상, 투명도 나타내기
                    clickListener.clickDrawingView(
                        getColorStr(it),
                        it.transparency.transparency,
                        true,
                        it.id
                    )
                }
                break
            }
        }

        if (!selected) {
            selectedRectangle = null
            clickListener.clickDrawingView("", -1, false, "")
        }

        invalidate()
    }

    fun getColorStr(selectedRectangle: Rectangle): String {
        var red = Integer.toHexString(selectedRectangle.backgroundColor.r)
        var green = Integer.toHexString(selectedRectangle.backgroundColor.g)
        var blue = Integer.toHexString(selectedRectangle.backgroundColor.b)

        if (red.length == 1) red = "0" + red
        if (green.length == 1) green = "0" + green
        if (blue.length == 1) blue = "0" + blue

        Log.d("AppTest", "selected rectangle color : #${red}${green}${blue}")
        return "#${red}${green}${blue}"
    }

}