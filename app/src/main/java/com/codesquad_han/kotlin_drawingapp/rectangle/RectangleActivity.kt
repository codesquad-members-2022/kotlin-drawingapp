package com.codesquad_han.kotlin_drawingapp.rectangle

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import com.codesquad_han.kotlin_drawingapp.data.RectangleRepositoryImpl
import com.codesquad_han.kotlin_drawingapp.databinding.ActivityRectangleBinding
import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.Rectangle
import com.codesquad_han.kotlin_drawingapp.model.RectangleFactory

class RectangleActivity : AppCompatActivity(), RectangleContract.View, RectangleViewClickInterface {

    private lateinit var binding: ActivityRectangleBinding

    private lateinit var rectangleFactory: RectangleFactory

    override lateinit var presenter: RectangleContract.Presenter

    private var RECTANGLE_WIDTH = 0
    private var RECTANGLE_HEIGHT = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRectangleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RECTANGLE_WIDTH = ConvertDPtoPX(this, 150)
        RECTANGLE_HEIGHT = ConvertDPtoPX(this, 120)
        Log.d("AppTest", "${this.window.decorView.height}")
        Log.d("AppTest", "MainActivity/ ${RECTANGLE_WIDTH}, ${RECTANGLE_HEIGHT}")


        binding.rectangleDrawingView?.let {
            it.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.rectangleDrawingView!!.viewTreeObserver.removeOnGlobalLayoutListener(
                        this
                    )
                    val width = binding.rectangleDrawingView!!.width - RECTANGLE_WIDTH
                    val height = binding.rectangleDrawingView!!.height - RECTANGLE_HEIGHT

                    rectangleFactory = RectangleFactory(width, height)
                    Log.d("AppTest", "width:$width, height:$height")

                    initPresenter(rectangleFactory)
                }
            })
        }

        // 커스텀뷰 에서 터치이벤트 전달 & 테두리 페인트 초기화
        binding.rectangleDrawingView?.let {
            it.drawingViewInit()
            it.setClickListener(this)
        }

        setBtnMakeRectangle()
    }

    // presenter 초기화
    fun initPresenter(rectangleFactory: RectangleFactory) {
        presenter = RectanglePresenter(RectangleRepositoryImpl(Plane(rectangleFactory)), this)
    }

    fun setBtnMakeRectangle() {
        binding.btnGenerateRectangle?.setOnClickListener {
            presenter.start()
            presenter.getRectangleList()
        }
    }

    // 만든 사각형 커스텀 뷰에 추가로 그리기
    override fun showRectangle(updatedRectangleList : MutableList<Rectangle>) {
        Log.d("AppTest", "update rectangle list size : ${updatedRectangleList.size}")
        binding.rectangleDrawingView?.drawRectangle(updatedRectangleList)
    }

    override fun showSelectedRectangle() {

    }

    override fun clickDrawingView() {
        // 드로잉 커스텀뷰 부분 터치 시

    }

    fun ConvertDPtoPX(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

}