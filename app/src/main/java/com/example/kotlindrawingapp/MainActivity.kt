package com.example.kotlindrawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlindrawingapp.presenter.Contract
import com.example.kotlindrawingapp.presenter.Presenter

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var customCanvas: CustomCanvas
    private lateinit var presenter: Presenter
    private lateinit var colorTextView: TextView
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btn_square)
        val canvasLayout: ConstraintLayout = findViewById(R.id.container_canvas)
        colorTextView = findViewById(R.id.tv_color)
        seekBar = findViewById(R.id.seekBar_alpha)
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

    override fun updateBoard(color: String, alpha: Int) {
        colorTextView.text = color
        seekBar.progress = alpha
    }

}