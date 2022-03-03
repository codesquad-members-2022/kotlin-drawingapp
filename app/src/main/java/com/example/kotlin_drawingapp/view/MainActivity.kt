package com.example.kotlin_drawingapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_drawingapp.R
import com.example.kotlin_drawingapp.presenter.MainPresenter
import com.example.kotlin_drawingapp.shapemodel.Rectangle

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainContract.View {
    private var imageBorder: ImageView? = null
    private lateinit var presenter: MainPresenter
    private lateinit var mainLayout: FrameLayout
    private var selectRectangle: ImageView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.image_container)
        presenter = MainPresenter(this, this, resources.displayMetrics.density)

        val btnGenerateRectangle = findViewById<Button>(R.id.btn_generate_rect)
        btnGenerateRectangle.setOnClickListener {
            mainLayout.addView(presenter.create())
        }

        mainLayout.setOnTouchListener { _, motionEvent ->
            mainLayout.removeView(imageBorder)
            imageBorder = null
            presenter.select(motionEvent.x, motionEvent.y)
            true
        }

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.max = 10
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress > 0) {
                    selectRectangle?.apply {
                        presenter.setAlpha(this, progress)
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun select(border: ImageView?, rectImage: ImageView?, rect: Rectangle?) {
        imageBorder = border
        selectRectangle = rectImage
        imageBorder?.let {
            val tvColor = findViewById<TextView>(R.id.tv_background_color)
            mainLayout.addView(imageBorder)
            if (rect != null) {
                tvColor.text = String.format("%X", rect.rgb.getRgb())
                val seekBar = findViewById<SeekBar>(R.id.seekBar)
                seekBar.progress = rect.alpha
            }
        }
    }
}