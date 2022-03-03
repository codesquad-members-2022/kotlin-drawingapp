package com.codesquad_han.kotlin_drawingapp.rectangle

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import com.codesquad_han.kotlin_drawingapp.R
import com.codesquad_han.kotlin_drawingapp.databinding.ActivityRectangleBinding
import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.RectangleFactory
import com.codesquad_han.kotlin_drawingapp.model.RectangleImageviewData

class RectangleActivity : AppCompatActivity(), RectangleContract.View {

    private lateinit var binding: ActivityRectangleBinding

    private lateinit var rectangleFactory: RectangleFactory

    override lateinit var presenter: RectangleContract.Presenter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRectangleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var widthMinus = ConvertDPtoPX(this, 150)

        Log.d("AppTest", "${this.window.decorView.height}")


        binding.frameLayoutDraw!!.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.frameLayoutDraw!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.frameLayoutDraw!!.width - widthMinus
                val height = binding.frameLayoutDraw!!.height
                rectangleFactory = RectangleFactory(width, height)
                Log.d("AppTest", "width:$width, height:$height")

                initPresenter(rectangleFactory)
            }
        })

        binding.frameLayoutDraw!!.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                Log.d("AppTest", "view.x : ${view.x}, view.y : ${view.y}")
                Log.d("AppTest", "event.x : ${motionEvent.x}, event.y : ${motionEvent.y}")
                Log.d(
                    "AppTest",
                    "event.rawX : ${motionEvent.rawX}, event.rawY : ${motionEvent.rawY}"
                )
            }
            true
        }

        //setBtnMakeRectangle()
        setBtnMakeRectangle2()

    }

    // presenter 초기화
    fun initPresenter(rectangleFactory: RectangleFactory) {
        presenter = RectanglePresenter(Plane(rectangleFactory), this)
    }

    fun setBtnMakeRectangle() {
        binding.btnGenerateRectangle!!.setOnClickListener {
            makeRectangle(4)
        }
    }

    fun makeRectangle(num: Int) {
        (1..num).forEach {
            Log.d("AppTest", "Rect$it ${rectangleFactory.generateRectangle().toString()}")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("AppTest", "$this/ onResume")
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    fun setBtnMakeRectangle2(){
        binding.btnGenerateRectangle?.setOnClickListener {
            presenter.start()
        }
    }

    // 만든 사각형 정보 가져와서 이미지 뷰 동적으로 생성하기
    override fun showRectangle(width: Int, height: Int, x:Int, y:Int, colorStr: String) {
        val dynamicImageView = ImageView(this)
        val layoutParams = LinearLayout.LayoutParams(ConvertDPtoPX(this, width), ConvertDPtoPX(this, height))
        dynamicImageView.layoutParams = layoutParams
        dynamicImageView.setBackgroundResource(R.drawable.stroke1)

        var gradientDrawable = dynamicImageView.background as GradientDrawable
        //gradientDrawable.setColor(ContextCompat.getColor(this, R.color.purple_200))
        gradientDrawable.setColor(Color.parseColor("#$colorStr"))

        dynamicImageView.x = x.toFloat() // 사각형 왼쪽 상단 좌표
        dynamicImageView.y = y.toFloat()

        presenter.saveImageView(dynamicImageView)
        dynamicImageView.setOnClickListener {
            presenter.selectRectangleImageView(dynamicImageView)
        }

        binding.frameLayoutDraw!!.addView(dynamicImageView)
    }

    fun ConvertDPtoPX(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    override fun showSelectedRectangle(rectangleList: ArrayList<RectangleImageviewData>) {
        rectangleList.forEach {
            var gradientDrawable = it.imageView?.background as GradientDrawable
            if(it.selected){
                gradientDrawable.setStroke(5, Color.RED)
            }
            else{
                gradientDrawable.setStroke(5, null)
            }
        }
    }

}