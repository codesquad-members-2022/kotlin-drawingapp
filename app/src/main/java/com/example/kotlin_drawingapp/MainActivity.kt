package com.example.kotlin_drawingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.kotlin_drawingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            binding.constraintLayout.removeAllViewsInLayout()
            val customView = Canvas(this, this)
            binding.constraintLayout.addView(customView)
        }

        binding.seekBar.setOnSeekBarChangeListener(seekBarListener)
    }

    val seekBarProgress = mutableListOf<Int>()
    private val seekBarListener = @SuppressLint("AppCompatCustomView")
    object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            seekBarProgress.add(progress)
        }
        override fun onStartTrackingTouch(p0: SeekBar?) {
        }
        override fun onStopTrackingTouch(p0: SeekBar?) {
        }
    }

    override fun showInfo(flag: Boolean, color: Int) {
        return when {
            flag -> {
                val hexString = String.format("#%06X", color)
                binding.textView2.text = hexString
            }
            else -> binding.textView2.text = ""
        }
    }
}