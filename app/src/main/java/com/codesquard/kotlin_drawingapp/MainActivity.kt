package com.codesquard.kotlin_drawingapp

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity(), TaskContract.TaskView {

    private lateinit var firstBtn: Button
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var backgroundBtn: Button
    private lateinit var alphaSlider: Slider
    private lateinit var presenter: TaskContract.Presenter
    private lateinit var customView: CustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.main_layout)
        customView = findViewById(R.id.customView)
        backgroundBtn = findViewById(R.id.button_background)
        alphaSlider = findViewById(R.id.slider_alpha)
        presenter = TaskPresenter(this)

        onClickRectBtn()
    }

    private fun onSlideAlpha() {
        alphaSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                presenter.changeAlphaValue(slider.value)
            }
        })
    }

    private fun onClickRectBtn() {
        firstBtn = findViewById(R.id.create_btn)
        firstBtn.setOnClickListener {
            presenter.addNewRectangle()
        }
    }

    override fun showRectangle(newRect: Rectangle) {
        customView.addNewRect(newRect)
        customView.invalidate()
    }

    override fun showSelectedRectangle(index: Int) {
        customView.setSelectedRect(index)
        showRectAlpha()
        showRectColor()
        customView.invalidate()
    }

    private fun showRectColor() {
        val color = customView.getRectColor()
        backgroundBtn.text = color
    }

    private fun showRectAlpha() {
        val alpha = customView.getRectAlpha()
        alphaSlider.value = alpha
    }

    override fun updateRect() {
        onSlideAlpha()
    }

    override fun showUpdatedRect() {
        customView.invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: 0f
        val y = event?.y ?: 0f

        if (event?.action == MotionEvent.ACTION_DOWN) {
            presenter.selectRectangle(x, y)
        }

        return super.onTouchEvent(event)
    }

}


