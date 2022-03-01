package com.codesquard.kotlin_drawingapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val first = findViewById<TextView>(R.id.first_view)
        val second = findViewById<TextView>(R.id.second_view)
        val third = findViewById<TextView>(R.id.third_view)
        val fourth = findViewById<TextView>(R.id.fourth_view)

        createView(first)
        createView(second)
        createView(third)
        createView(fourth)
    }

    private fun createView(view: View) {
        val rectangle = RectangleViewModelFactory(this).getInstance()
        view.setBackgroundColor(
            Color.rgb(
                rectangle.getColor()[0],
                rectangle.getColor()[1],
                rectangle.getColor()[2]
            )
        )
        view.alpha = rectangle.getAlpha()
        view.x = rectangle.getPoint()[0].toFloat()
        view.y = rectangle.getPoint()[1].toFloat()
        view.layoutParams.width = rectangle.getSize()[0]
        view.layoutParams.height = rectangle.getSize()[1]
    }

}