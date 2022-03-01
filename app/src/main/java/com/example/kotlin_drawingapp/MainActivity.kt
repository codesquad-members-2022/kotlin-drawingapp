package com.example.kotlin_drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val squareModelFactory = SquareModelFactory()
        Log.d("Rect1", "(${squareModelFactory.createSquareObject()[0]}), " +
                "X: ${squareModelFactory.createSquareObject()[1]}, " +
                "Y: ${squareModelFactory.createSquareObject()[2]}, " +
                "W ${squareModelFactory.createSquareObject()[3]}, " +
                "H: ${squareModelFactory.createSquareObject()[4]}, " +
                "R: ${squareModelFactory.createSquareObject()[5]}, " +
                "G: ${squareModelFactory.createSquareObject()[6]}, " +
                "B: ${squareModelFactory.createSquareObject()[7]}, " +
                "Alpha: ${squareModelFactory.createSquareObject()[8]} ")

        Log.d("Rect2", "(${squareModelFactory.createSquareObject()[0]}), " +
                "X: ${squareModelFactory.createSquareObject()[1]}, " +
                "Y: ${squareModelFactory.createSquareObject()[2]}, " +
                "W ${squareModelFactory.createSquareObject()[3]}, " +
                "H: ${squareModelFactory.createSquareObject()[4]}, " +
                "R: ${squareModelFactory.createSquareObject()[5]}, " +
                "G: ${squareModelFactory.createSquareObject()[6]}, " +
                "B: ${squareModelFactory.createSquareObject()[7]}, " +
                "Alpha: ${squareModelFactory.createSquareObject()[8]} ")

        Log.d("Rect3", "(${squareModelFactory.createSquareObject()[0]}), " +
                "X: ${squareModelFactory.createSquareObject()[1]}, " +
                "Y: ${squareModelFactory.createSquareObject()[2]}, " +
                "W ${squareModelFactory.createSquareObject()[3]}, " +
                "H: ${squareModelFactory.createSquareObject()[4]}, " +
                "R: ${squareModelFactory.createSquareObject()[5]}, " +
                "G: ${squareModelFactory.createSquareObject()[6]}, " +
                "B: ${squareModelFactory.createSquareObject()[7]}, " +
                "Alpha: ${squareModelFactory.createSquareObject()[8]} ")

        Log.d("Rect4", "(${squareModelFactory.createSquareObject()[0]}), " +
                "X: ${squareModelFactory.createSquareObject()[1]}, " +
                "Y: ${squareModelFactory.createSquareObject()[2]}, " +
                "W ${squareModelFactory.createSquareObject()[3]}, " +
                "H: ${squareModelFactory.createSquareObject()[4]}, " +
                "R: ${squareModelFactory.createSquareObject()[5]}, " +
                "G: ${squareModelFactory.createSquareObject()[6]}, " +
                "B: ${squareModelFactory.createSquareObject()[7]}, " +
                "Alpha: ${squareModelFactory.createSquareObject()[8]} ")
    }
}