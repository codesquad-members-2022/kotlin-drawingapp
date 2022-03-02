package com.codesquard.kotlin_drawingapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), TaskContract.TaskView {
    private lateinit var firstView: TextView
    private lateinit var secondView: TextView
    private lateinit var thirdView: TextView
    private lateinit var fourthView: TextView
    private lateinit var firstBtn: Button
    override lateinit var viewList: ArrayList<View>
    private val presenter: TaskContract.Presenter = TaskPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstView = findViewById<TextView>(R.id.first_view)
        secondView = findViewById<TextView>(R.id.second_view)
        thirdView = findViewById<TextView>(R.id.third_view)
        fourthView = findViewById<TextView>(R.id.fourth_view)
        firstBtn = findViewById(R.id.create_btn)
        viewList = arrayListOf(firstView, secondView, thirdView, fourthView)

        firstBtn.setOnClickListener {
            showRectangle()
        }
    }

    override fun showRectangle() {
        if (viewList.size != 0) {
            createView(viewList[0], presenter.onCreateView())
            viewList.removeAt(0)
        }
    }

    private fun createView(view: View, rectangle: RectangleViewModel) {
        view.setBackgroundColor(
            Color.rgb(
                rectangle.getColor()[0],
                rectangle.getColor()[1],
                rectangle.getColor()[2]
            )
        )
        view.alpha = rectangle.getAlpha()
        view.x = rectangle.getPoint()[0].toFloat()
        view.y = rectangle.getPoint()[1].toFloat()
        view.layoutParams.width = rectangle.getSize()[0]
        view.layoutParams.height = rectangle.getSize()[1]
    }

}