package com.example.kotlindrawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlindrawingapp.presenter.Contract
import com.example.kotlindrawingapp.presenter.Presenter

class MainActivity : AppCompatActivity(), Contract.View {

    lateinit var button: Button
    lateinit var canvasLayout: ConstraintLayout
    lateinit var customCanvas: CustomCanvas
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_square)
        canvasLayout = findViewById(R.id.container_canvas)
        presenter = Presenter(this)

        button.setOnClickListener {
            customCanvas = CustomCanvas(this)
            canvasLayout.addView(customCanvas)
            presenter.drawRectangle(customCanvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
                val x = event.x
                val y = event.y
                presenter.selectRectangle(x, y)
            }
        }
        return true
    }
}