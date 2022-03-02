package com.codesquard.kotlin_drawingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), TaskContract.TaskView {
    private lateinit var firstBtn: Button
    private lateinit var mainLayout: ConstraintLayout
    lateinit var presenter: TaskContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.main_layout)
        presenter = TaskPresenter(this)
        onClickButon()
    }

    private fun onClickButon() {
        firstBtn = findViewById(R.id.create_btn)
        firstBtn.setOnClickListener {
            showRectangle()
        }
    }

    override fun showRectangle() {
        val canvas = presenter.onCreateView(this)
        mainLayout.addView(canvas)
    }

}


