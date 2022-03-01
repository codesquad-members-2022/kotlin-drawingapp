package com.codesquad.kotlin_drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import presenter.MainPresenter
import view.MainContract

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainLayout: FrameLayout
    private lateinit var presenter:MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter= MainPresenter(this,this, resources.displayMetrics.density)
        mainLayout= findViewById(R.id.image_container)
        val btnMakeRectangle= findViewById<Button>(R.id.btn_addRectangle)
        btnMakeRectangle.setOnClickListener {
            mainLayout.addView(presenter.createRectangle())
        }
    }

}