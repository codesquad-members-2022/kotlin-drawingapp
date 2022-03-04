package com.example.kotlin_drawingapp

import android.graphics.PointF
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.RectangleBorder

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var drawView: DrawView
    private lateinit var tvRgb: TextView
    private lateinit var seekBarAlpha: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        drawView = findViewById(R.id.drawView)
        tvRgb = findViewById(R.id.tv_background_color)
        seekBarAlpha = findViewById(R.id.seekBar)
        val btnGenerateRectangle = findViewById<Button>(R.id.btn_generate_rect)
        btnGenerateRectangle.setOnClickListener {
            presenter.drawRectangle()
        }

        drawView.setOnRectangleClickListener(object : DrawView.SetOnDrawViewTouchListener {
            override fun onClick(point: PointF) {
                presenter.selectRectangle(point.x, point.y)
            }
        })
    }

    override fun showRectangle(rectangles: List<Rectangle>) {
        drawView.drawRectangle(rectangles)
    }

    override fun showRectangleInfo(color: Color, alpha: Int) {
        tvRgb.text = String.format("%X", color.getRgb())
        seekBarAlpha.progress = alpha
    }

    override fun showRectangleBorder(rectangleBorderList: List<RectangleBorder>) {
        drawView.drawRectangleBorder(rectangleBorderList)
    }
}