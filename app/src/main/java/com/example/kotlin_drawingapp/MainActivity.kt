package com.example.kotlin_drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_drawingapp.databinding.ActivityMainBinding
import com.example.kotlin_drawingapp.square.ID
import com.example.kotlin_drawingapp.square.SquareFactory

class MainActivity : AppCompatActivity(), Listener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Rect1", SquareFactory.createSquare().toString())
        Log.d("Rect2", SquareFactory.createSquare().toString())
        Log.d("Rect3", SquareFactory.createSquare().toString())
        Log.d("Rect4", SquareFactory.createSquare().toString())

        val customView = CustomView(this, this)
        binding.constraintLayout.addView(customView)
    }

    override fun check(flag: Boolean, color: Int){
        return if (flag) {
            binding.textView4.text = "터치확인"
            val hexString = String.format("#%06X", color)
            binding.textView2.text = hexString
        } else {
            binding.textView4.text = "터치 불가능"
        }
    }
}