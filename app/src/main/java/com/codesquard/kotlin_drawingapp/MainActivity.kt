package com.codesquard.kotlin_drawingapp

import android.os.Bundle
import android.util.Log
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

        val firstView = RectangleViewModelFactory(this)
        firstView.setRectangleViewAttr()
        val secondView = RectangleViewModelFactory(this)
        secondView.setRectangleViewAttr()
        val thirdView = RectangleViewModelFactory(this)
        thirdView.setRectangleViewAttr()
        val fourthView = RectangleViewModelFactory(this)
        fourthView.setRectangleViewAttr()

        Log.d("Rect1", firstView.printInfo())
        Log.d("Rect2", secondView.printInfo())
        Log.d("Rect3", thirdView.printInfo())
        Log.d("Rect4", fourthView.printInfo())
    }

}