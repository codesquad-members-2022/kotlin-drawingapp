package com.example.kotlindrawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.kotlindrawingapp.square.Alpha
import com.example.kotlindrawingapp.square.Point
import com.example.kotlindrawingapp.square.RGB
import com.example.kotlindrawingapp.square.Size

class MainActivity : AppCompatActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_square)
        button.setOnClickListener {
            val square1 = SquareFactory.createSquare(
                Point(10, 200),
                Size(),
                RGB(245, 0, 245),
                Alpha(9)
            )
            val square2 = SquareFactory.createSquare(
                Point(110, 180),
                Size(),
                RGB(43, 124, 95),
                Alpha(5)
            )
            val square3 = SquareFactory.createSquare(
                Point(590, 90),
                Size(),
                RGB(98, 244, 15),
                Alpha(7)
            )
            val square4 = SquareFactory.createSquare(
                Point(330, 450),
                Size(),
                RGB(125, 39, 99),
                Alpha(1)
            )
            Log.d(TAG, "$square1")
            Log.d(TAG, "$square2")
            Log.d(TAG, "$square3")
            Log.d(TAG, "$square4")
        }
    }

    companion object {
        private const val TAG = "RECTANGLE"
    }
}