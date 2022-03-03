package com.example.kotlin_drawingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.Rectangle

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var drawView: DrawView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        drawView = findViewById(R.id.drawView)
        val btnGenerateRectangle = findViewById<Button>(R.id.btn_generate_rect)
        btnGenerateRectangle.setOnClickListener {
            presenter.drawRectangle()
        }
    }

    override fun showRectangle(rectangles: List<Rectangle>) {
        drawView.drawRectangle(rectangles)
    }

    override fun showRectangleInfo(color: Color, alpha: Int) {

    }

    override fun showRectangleBorder(border: Rectangle?) {

    }

    override fun setRectangleAlpha(index: Int, alpha: Int) {

    }
}