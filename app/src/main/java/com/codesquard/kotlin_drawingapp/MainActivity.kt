package com.codesquard.kotlin_drawingapp

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowMetrics
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

        RectangleViewModelFactory(this).createView(first)
        RectangleViewModelFactory(this).createView(second)
        RectangleViewModelFactory(this).createView(third)
        RectangleViewModelFactory(this).createView(fourth)
    }

}