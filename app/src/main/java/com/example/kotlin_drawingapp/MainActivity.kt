package com.example.kotlin_drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_drawingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val customView = Canvas(this, this)
            binding.constraintLayout.addView(customView)
        }
    }

    override fun showInfo(flag: Boolean, color: Int) {
        return if (flag) {
            binding.textView4.text = "터치확인"
            val hexString = String.format("#%06X", color)
            binding.textView2.text = hexString
        } else {
            binding.textView4.text = "터치 불가능"
        }
    }
}