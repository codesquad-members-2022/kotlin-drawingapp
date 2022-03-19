package com.codesquad_han.kotlin_drawingapp.rectangle

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.codesquad_han.kotlin_drawingapp.R
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle

class RectangleDrawingView : View {

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    private var selectedRectangle: BaseRectangle? = null
    private var tempRectangle: BaseRectangle? = null
    private var rectangleList = mutableListOf<BaseRectangle>()

    private var strokePaint = Paint()
    private lateinit var clickListener: RectangleViewClickInterface

    fun setClickListener(listener: RectangleViewClickInterface) {
        this.clickListener = listener
    }

    override fun onDraw(canvas: Canvas) {
        rectangleList.forEach { rectangle ->

            rectangle.drawRectangle(context, canvas, false)

            // 선택된 사각형 있으면 윤곽선 그리기
            if (rectangle.selected) {
                selectedRectangle = rectangle

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

            }

            Log.d("AppTest", "RectangleDrawingView/ ${rectangle.toString()}")
        }

        // 임시뷰 사각형 그리기
        tempRectangle?.let {
            it.drawRectangle(context, canvas, true)
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
                if (selectedRectangle != null) {
                    if (checkTwoPoint(PointF(event.getX(0), event.getY(0)))) {
                        if (tempRectangle == null) {
                            tempRectangle = selectedRectangle!!.getTempRectangle()
                            invalidate()
                        }

                        tempRectangle!!.point.x =
                            (event.getX(0) - (tempRectangle!!.size.width / 2)).toInt()
                        tempRectangle!!.point.y =
                            (event.getY(0) - (tempRectangle!!.size.height / 2)).toInt()
                        // 임시뷰 움직이는 동안 좌표 변화 보여주기
                        clickListener.updatePointText(
                            tempRectangle!!.point.x,
                            tempRectangle!!.point.y
                        )
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (tempRectangle != null) {
                    var newX = tempRectangle!!.point.x
                    var newY = tempRectangle!!.point.y
                    tempRectangle = null

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
                selectedRectangle = rectangleList[i]
                selected = true

                selectedRectangle?.let { // 액티비티에서 선택한 사각형 색상, 투명도 나타내기
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
            selectedRectangle = null
            clickListener.clickDrawingView(
                "", -1, false, "",
                0, 0, 0, 0
            )
        }

        invalidate()
    }

    fun checkTwoPoint(point: PointF): Boolean {
        if (tempRectangle == null) {
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