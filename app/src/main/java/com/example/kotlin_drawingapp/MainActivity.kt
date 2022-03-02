package com.example.kotlin_drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_drawingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Listener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val squareModelFactory = SquareModelFactory()
        Log.d("Rect1", squareModelFactory.createLogMessage())
        Log.d("Rect2", squareModelFactory.createLogMessage())
        Log.d("Rect3", squareModelFactory.createLogMessage())
        Log.d("Rect4", squareModelFactory.createLogMessage())

        val customView = CustomView(this, this)
        binding.frameLayout.addView(customView)
    }

    override fun check(flag: Boolean) {
        if (flag) binding.textView4.text = "터치확인"
        else binding.textView4.text = "터치 불가능"
    }
}