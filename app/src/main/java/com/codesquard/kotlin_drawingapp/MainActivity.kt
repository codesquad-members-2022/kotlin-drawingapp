package com.codesquard.kotlin_drawingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), TaskContract.TaskView {
    private lateinit var firstBtn: Button
    private lateinit var mainLayout: ConstraintLayout
    lateinit var presenter: TaskContract.Presenter
    lateinit var customView: CustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.main_layout)
        customView = findViewById(R.id.customView)
        presenter = TaskPresenter(this)
        onClickButton()
    }

    private fun onClickButton() {
        firstBtn = findViewById(R.id.create_btn)
        firstBtn.setOnClickListener {
            presenter.addNewRectangle()
        }
    }

    override fun showRectangle(newRect: Rectangle) {
        customView.addNewRect((newRect))
        customView.invalidate()
    }

}


