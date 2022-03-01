package view

import Rect
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import com.codesquad.kotlin_drawingapp.R
import presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainLayout: FrameLayout
    private lateinit var presenter:MainPresenter
    private var selectedBorder: ImageView? = null
    private var selectedRectangleView:ImageView?=null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter= MainPresenter(this,this, resources.displayMetrics.density)
        mainLayout= findViewById(R.id.image_container)
        val btnMakeRectangle= findViewById<Button>(R.id.btn_addRectangle)
        btnMakeRectangle.setOnClickListener {
            mainLayout.addView(presenter.createRectangle())
        }

        mainLayout.setOnTouchListener { _, motionEvent ->
            mainLayout.removeView(selectedBorder)
            selectedBorder=null
            presenter.selectRectangle(motionEvent.x, motionEvent.y)
            true
        }
    }

    override fun showSelected(rect: Rect, rectView: ImageView?, border: ImageView?) {
        selectedBorder=border
        selectedRectangleView=rectView
        mainLayout.addView(border)
    }

}