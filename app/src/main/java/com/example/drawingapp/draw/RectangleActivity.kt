package com.example.drawingapp.draw

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.Contract
import com.example.drawingapp.R
import com.example.drawingapp.data.Rectangle
import com.example.drawingapp.data.RectangleRepository
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class RectangleActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Contract.Presenter
    private lateinit var draw: RectangleDraw

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
        draw = findViewById(R.id.draw_rectangle)
        presenter = RectanglePresenter(this, RectangleRepository())
//        presenter.onClickLog()
//        presenter.onClickLog()
//        presenter.onClickLog()
//        presenter.onClickLog()
        val drawButton: Button = findViewById(R.id.btn_make_rectangle)
        drawButton.text = "사각형"
        drawButton.setOnClickListener() {
//            presenter.onClickLog()
            presenter.setPlane()
            presenter.onClickDraw()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val count = draw.onClickRectangleIndex()
        setColorText(count)
        return true
    }

    override fun setColorText(count: Int) = when (count != -1) {
        true -> {
            var color: Button = findViewById(R.id.tv_background_color)
            color.text = "#${draw.getColor(count)}"
        }

        false -> {
            var color: Button = findViewById(R.id.tv_background_color)
            color.text = ""
        }
    }

    override fun getDrawMessage(message: String) {
        Logger.i(message)
    }

    override fun drawRectangle(rectangle: Rectangle) {
        draw.drawRectangle(rectangle)
        Logger.d(rectangle.toString())
    }

}