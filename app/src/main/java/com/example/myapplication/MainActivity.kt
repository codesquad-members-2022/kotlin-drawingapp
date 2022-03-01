package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Class.Rectangle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rectangle = Array(4) { Rectangle() }
        for (i in 0..3) {
            Log.d(
                "Rect${i + 1}",
                "Rect${i + 1} (${rectangle[i].id}), X:${rectangle[i].point.x},Y:${rectangle[i].point.y}, W${rectangle[i].size.width}, H${rectangle[i].size.height}, " +
                        "R${rectangle[i].color.r}, G${rectangle[i].color.g}, B${rectangle[i].color.b}, Alpha:${rectangle[i].alpha}"
            )
        }
    }
}