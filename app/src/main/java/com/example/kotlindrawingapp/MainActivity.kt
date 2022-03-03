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
    private lateinit var container: CustomCanvas
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_square)
        container = findViewById(R.id.container_canvas)
        colorTextView = findViewById(R.id.tv_color)
        seekBar = findViewById(R.id.seekBar_alpha)
        val repository = SquareRepository()
        presenter = Presenter(this, repository, container)

        button.setOnClickListener {
            presenter.drawRectangle()
        }
    }

    override fun updateBoard(color: String, alpha: Int) {
        colorTextView.text = color
        seekBar.progress = alpha
    }
}