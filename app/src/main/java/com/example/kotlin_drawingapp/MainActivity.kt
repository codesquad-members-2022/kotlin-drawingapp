package com.example.kotlin_drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val squareModelFactory = SquareModelFactory()
        Log.d("Rect1", squareModelFactory.createLogMessage())
        Log.d("Rect2", squareModelFactory.createLogMessage())
        Log.d("Rect3", squareModelFactory.createLogMessage())
        Log.d("Rect4", squareModelFactory.createLogMessage())
    }
}