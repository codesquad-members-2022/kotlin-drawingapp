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
import com.codesquad_han.kotlin_drawingapp.model.property.BackgroundColor
import com.codesquad_han.kotlin_drawingapp.model.property.Point
import com.codesquad_han.kotlin_drawingapp.model.property.Size
import com.codesquad_han.kotlin_drawingapp.model.property.Transparency

class RectangleDrawingView : View {

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var selectedNormalRectangle: BaseRectangle? = null
    private var tempNormalRectangle: BaseRectangle? = null
    private var rectangleList = mutableListOf<BaseRectangle>()

    private var paint = Paint()
    private var tempPaint = Paint()
    private var strokePaint = Paint()
    private lateinit var clickListener: RectangleViewClickInterface

    fun setClickListener(listener: RectangleViewClickInterface) {
        this.clickListener = listener
    }

    override fun onDraw(canvas: Canvas) {
        rectangleList.forEach { rectangle ->

            rectangle.drawRectangle(context, canvas)

            // 선택된 사각형 있으면 윤곽선 그리기
            selectedNormalRectangle?.let {
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
        tempNormalRectangle?.let { tempRectangle ->
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
                if (selectedNormalRectangle != null) {
                    if (checkTwoPoint(PointF(event.getX(0), event.getY(0)))) {
                        if (tempNormalRectangle == null) {
                            tempNormalRectangle = selectedNormalRectangle!!.getTempRectangle()
                            invalidate()
                        }

                        tempNormalRectangle!!.point.x =
                            (event.getX(0) - (tempNormalRectangle!!.size.width / 2)).toInt()
                        tempNormalRectangle!!.point.y =
                            (event.getY(0) - (tempNormalRectangle!!.size.height / 2)).toInt()
                        // 임시뷰 움직이는 동안 좌표 변화 보여주기
                        clickListener.updatePointText(tempNormalRectangle!!.point.x, tempNormalRectangle!!.point.y)
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (tempNormalRectangle != null) {
                    var newX = tempNormalRectangle!!.point.x
                    var newY = tempNormalRectangle!!.point.y
                    tempNormalRectangle = null

                    // 임시 사각형 뷰로 선택 사각형 위치 이동
                    clickListener.updateSelectedRectanglePoint(selectedNormalRectangle!!.id, newX, newY)
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
    fun drawRectangle(updatedRectangleList: MutableList<BaseRectangle>) {
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
                selectedNormalRectangle = rectangleList[i]
                selected = true

                selectedNormalRectangle?.let { // 액티비티에서 선택한 사각형 색상, 투명도 나타내기
                    clickListener.clickDrawingView(
                        getColorStr(it),
                        it.transparency.transparency,
                        true,
                        it.id,
                        it.point.x, it.point.y,
                        it.size.width, it.size.height
                    )
                }
                break
            }
        }

        if (!selected) {
            selectedNormalRectangle = null
            clickListener.clickDrawingView(
                "", -1, false, "",
                0, 0, 0, 0
            )
        }

        invalidate()
    }

    fun checkTwoPoint(point: PointF): Boolean {
        if (tempNormalRectangle == null) {
            return (point.x >= selectedNormalRectangle!!.point.x &&
                    point.x <= selectedNormalRectangle!!.point.x + selectedNormalRectangle!!.size.width &&
                    point.y >= selectedNormalRectangle!!.point.y &&
                    point.y <= selectedNormalRectangle!!.point.y + selectedNormalRectangle!!.size.height)
        } else {
            return (point.x >= tempNormalRectangle!!.point.x &&
                    point.x <= tempNormalRectangle!!.point.x + tempNormalRectangle!!.size.width &&
                    point.y >= tempNormalRectangle!!.point.y &&
                    point.y <= tempNormalRectangle!!.point.y + tempNormalRectangle!!.size.height)
        }
    }

    fun getColorStr(selectedRectangle: BaseRectangle): String {
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