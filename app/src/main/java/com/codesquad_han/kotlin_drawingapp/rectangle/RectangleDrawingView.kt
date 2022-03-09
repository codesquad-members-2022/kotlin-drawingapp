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
import com.codesquad_han.kotlin_drawingapp.model.*
import com.codesquad_han.kotlin_drawingapp.model.Point

class RectangleDrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var selectedRectangle: Rectangle? = null
    private var tempRectangle: Rectangle? = null
    private var rectangleList = mutableListOf<Rectangle>()

    private var isDoubleTouchExist = false  // 현재 두 손가락 동시에

    private var paint = Paint()
    private var tempPaint = Paint()
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

        // 임시뷰 사각형 그리기
        tempRectangle?.let { tempRectangle ->
            tempPaint.setColor(
                Color.argb(
                    5 * 255 / 10,
                    tempRectangle.backgroundColor.r,
                    tempRectangle.backgroundColor.g,
                    tempRectangle.backgroundColor.b
                )
            )

            if (tempRectangle.imageUri == null) {
                canvas.drawRect(
                    tempRectangle.point.x.toFloat(),
                    tempRectangle.point.y.toFloat(),
                    (tempRectangle.point.x + tempRectangle.size.width).toFloat(),
                    (tempRectangle.point.y + tempRectangle.size.height).toFloat(),
                    tempPaint
                )
            }

            tempRectangle.imageUri?.let {
                var bitmap: Bitmap
                if (Build.VERSION.SDK_INT < 29) { //
                    bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }

                var imagePaint = Paint()
                imagePaint.alpha = 5 * 255 / 10
                canvas.drawBitmap(
                    bitmap, null, Rect(
                        tempRectangle.point.x,
                        tempRectangle.point.y,
                        (tempRectangle.point.x + tempRectangle.size.width),
                        (tempRectangle.point.y + tempRectangle.size.height)
                    ), imagePaint
                )
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentPoint = PointF(event.x, event.y)
        var pointCount = event.pointerCount

        // 클릭 리스너 사용해서 액티비티 연결하기
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                checkTouchPoint(currentPoint.x, currentPoint.y)
            }
            MotionEvent.ACTION_MOVE -> {
                if (pointCount == 2 && selectedRectangle != null) {
                    if (checkTwoPoint(PointF(event.getX(0), event.getY(0))) &&
                        checkTwoPoint(PointF(event.getX(1), event.getY(1)))
                    ) {
                        if(!isDoubleTouchExist){
                            isDoubleTouchExist = true
                            tempRectangle = Rectangle(
                                selectedRectangle!!.id,
                                Point(selectedRectangle!!.point.x, selectedRectangle!!.point.y),
                                Size(selectedRectangle!!.size.width, selectedRectangle!!.size.height),
                                BackgroundColor(selectedRectangle!!.backgroundColor.r,
                                                selectedRectangle!!.backgroundColor.g,
                                                selectedRectangle!!.backgroundColor.b),
                                Transparency(5),
                                selectedRectangle!!.imageUri
                            )
                            invalidate()
                        }

                        tempRectangle!!.point.x = (event.getX(0) - (tempRectangle!!.size.width / 4 * 3)).toInt()
                        tempRectangle!!.point.y = (event.getY(0) - (tempRectangle!!.size.height / 3)).toInt()
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (isDoubleTouchExist) {
                    var newX = tempRectangle!!.point.x
                    var newY = tempRectangle!!.point.y
                    tempRectangle = null
                    isDoubleTouchExist = false

                     // 임시 사각형 뷰로 선택 사각형 위치 이동
                    clickListener.updateSelectedRectanglePoint(selectedRectangle!!.id, newX, newY)
                }
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

    fun checkTwoPoint(point: PointF): Boolean {
        if (!isDoubleTouchExist) {
            return (point.x >= selectedRectangle!!.point.x &&
                    point.x <= selectedRectangle!!.point.x + selectedRectangle!!.size.width &&
                    point.y >= selectedRectangle!!.point.y &&
                    point.y <= selectedRectangle!!.point.y + selectedRectangle!!.size.height)
        } else {
            return (point.x >= tempRectangle!!.point.x &&
                    point.x <= tempRectangle!!.point.x + tempRectangle!!.size.width &&
                    point.y >= tempRectangle!!.point.y &&
                    point.y <= tempRectangle!!.point.y + tempRectangle!!.size.height)
        }
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