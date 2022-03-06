package com.example.kotlindrawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.example.kotlindrawingapp.presenter.Contract
import com.example.kotlindrawingapp.presenter.Presenter
import com.example.kotlindrawingapp.repository.SquareRepository

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Presenter
    private lateinit var colorTextView: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var customView: CustomCanvas
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_square)
        customView = findViewById(R.id.container_canvas)
        colorTextView = findViewById(R.id.tv_color)
        seekBar = findViewById(R.id.seekBar_alpha)
        val repository = SquareRepository()
        presenter = Presenter(this, repository)

        presenter.plane.observe(this) {
            customView.drawRectangle(it)
        }

        presenter.selectedSquare.observe(this) {
            colorTextView.text = it?.rgb?.decimalToHex() ?: "NONE"
            seekBar.progress = it?.alpha?.alpha ?: 0
        }

        button.setOnClickListener {
            presenter.loadRectangle()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.editRectangleAlpha(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}