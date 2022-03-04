package com.codesquad_han.kotlin_drawingapp.rectangle

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import com.codesquad_han.kotlin_drawingapp.data.RectangleRepositoryImpl
import com.codesquad_han.kotlin_drawingapp.databinding.ActivityRectangleBinding
import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.Rectangle
import com.codesquad_han.kotlin_drawingapp.model.RectangleFactory
import com.google.android.material.slider.Slider

class RectangleActivity : AppCompatActivity(), RectangleContract.View, RectangleViewClickInterface {

    private lateinit var binding: ActivityRectangleBinding

    private lateinit var rectangleFactory: RectangleFactory

    override lateinit var presenter: RectangleContract.Presenter

    private var RECTANGLE_WIDTH = 0
    private var RECTANGLE_HEIGHT = 0

    private lateinit var SELECTED_RECTANGLE_ID: String

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
                    val width = binding.rectangleDrawingView!!.width - RECTANGLE_WIDTH
                    val height = binding.rectangleDrawingView!!.height - RECTANGLE_HEIGHT

                    rectangleFactory = RectangleFactory(width, height)
                    Log.d("AppTest", "width:$width, height:$height")

                    initPresenter(rectangleFactory)

                    // 리스너 해제
                    it.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }

        // 커스텀뷰 에서 터치이벤트 전달 & 테두리 페인트 초기화
        binding.rectangleDrawingView?.let {
            it.drawingViewInit()
            it.setClickListener(this)
        }

        setBtnMakeRectangle()
        setTransparencySlider()

    }

    // presenter 초기화 및 livedata 옵저버 등록
    fun initPresenter(rectangleFactory: RectangleFactory) {
        presenter = RectanglePresenter(RectangleRepositoryImpl(Plane(rectangleFactory)), this)
        presenter.liveRectangleList.observe(this) {
            showRectangle(it)
        }
    }

    fun setBtnMakeRectangle() {
        binding.btnGenerateRectangle?.setOnClickListener {
            presenter.start()
        }
    }

    // 만든 사각형 커스텀 뷰에 추가로 그리기
    override fun showRectangle(updatedRectangleList: MutableList<Rectangle>) {
        Log.d("AppTest", "update rectangle list size : ${updatedRectangleList.size}")
        binding.rectangleDrawingView?.let {
            it.drawRectangle(updatedRectangleList)
        }
    }


    override fun clickDrawingView(color: String, alpha: Int, selected: Boolean, id: String) {
        if (selected) {
            binding.constraintLayoutControl?.let {
                it.visibility = View.VISIBLE
            }
            binding.tvBackgroundColor?.let {
                it.text = color
            }
            binding.sliderTransparency?.let {
                it.value = alpha.toFloat()
            }

            // id 값을 활용해 현재 선택된 사각형 투명도 데이터 업데이트 후 뷰에 반영시키기
            SELECTED_RECTANGLE_ID = id
        } else {
            binding.constraintLayoutControl?.let {
                it.visibility = View.INVISIBLE
            }
        }
    }

    fun ConvertDPtoPX(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    fun setTransparencySlider() {
        binding.sliderTransparency?.let {
            it.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                @SuppressLint("RestrictedApi")
                override fun onStartTrackingTouch(slider: Slider) {

                }

                @SuppressLint("RestrictedApi")
                override fun onStopTrackingTouch(slider: Slider) {
                    presenter.updateTransparency(SELECTED_RECTANGLE_ID, slider.value.toInt())
                }

            })
        }
    }

}