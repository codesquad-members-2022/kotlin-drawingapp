package com.example.kotlindrawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlindrawingapp.square.*

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var customCanvas: CustomCanvas
    val squares = listOf<Square>(
        SquareFactory.createSquare(Point(10, 200), Size(), RGB(245, 0, 245), Alpha(9)),
        SquareFactory.createSquare(Point(110, 180), Size(), RGB(43, 124, 95), Alpha(5)),
        SquareFactory.createSquare(Point(590, 90), Size(), RGB(98, 244, 15), Alpha(7)),
        SquareFactory.createSquare(Point(330, 450), Size(), RGB(125, 39, 99), Alpha(1))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_square)
        customCanvas = findViewById(R.id.canvas)

        var index = 0
        button.setOnClickListener {
            customCanvas.drawRectangle(squares[index++])
        }
    }

    companion object {
        private const val TAG = "RECTANGLE"
    }
}