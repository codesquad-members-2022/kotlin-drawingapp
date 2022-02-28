package com.codesquad_han.kotlin_drawingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.codesquad_han.kotlin_drawingapp.model.RectangleFactory

class MainActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var constraintLayoutDraw : ConstraintLayout
    private lateinit var btnMakeRectangle: Button

    private lateinit var rectangleFactory: RectangleFactory

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("AppTest", "${this.window.decorView.height}")

        constraintLayoutDraw = findViewById(R.id.constrainLayoutDraw)
        constraintLayoutDraw.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                constraintLayoutDraw.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = constraintLayoutDraw.width
                val height = constraintLayoutDraw.height
                rectangleFactory = RectangleFactory(width, height)
                Log.d("AppTest", "width:$width, height:$height")
            }
        })

        constraintLayoutDraw.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN){
                Log.d("AppTest", "view.x : ${view.x}, view.y : ${view.y}")
                Log.d("AppTest", "event.x : ${motionEvent.x}, event.y : ${motionEvent.y}")
                Log.d("AppTest", "event.rawX : ${motionEvent.rawX}, event.rawY : ${motionEvent.rawY}")
            }
            true
        }
        setBtnMakeRectangle()
    }

    fun setBtnMakeRectangle(){
        btnMakeRectangle = findViewById(R.id.btnGenerateRectangle)
        btnMakeRectangle.setOnClickListener {
            makeRectangle(4)
        }
    }

    fun makeRectangle(num: Int){
        (1..num).forEach {
            Log.d("AppTest","Rect$it ${rectangleFactory.generateRectangle().toString()}")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("AppTest", "$this/ onResume")
    }

}