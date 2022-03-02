package view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.codesquad.kotlin_drawingapp.R
import model.BackGroundColor
import model.Rect
import presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainLayout: FrameLayout
    private lateinit var presenter: MainPresenter
    private var selectedBorder: ImageView? = null
    private var selectedRectangleView: ImageView? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, this, resources.displayMetrics.density)
        mainLayout = findViewById(R.id.image_container)
        val btnMakeRectangle = findViewById<Button>(R.id.btn_addRectangle)
        val tvRGBValue = findViewById<TextView>(R.id.tv_rgb_value)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        btnMakeRectangle.setOnClickListener {
            mainLayout.addView(presenter.createRectangle())
        }

        mainLayout.setOnTouchListener { _, motionEvent ->
            mainLayout.removeView(selectedBorder)
            selectedBorder = null
            selectedRectangleView = null
            presenter.selectRectangle(motionEvent.x, motionEvent.y)
            true
        }
        tvRGBValue.setOnClickListener {
            selectedRectangleView?.let { selectedView ->
                presenter.changeColor(
                    selectedView,
                    tvRGBValue
                )
            }
        }
        opacitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                opacitySeekBar: SeekBar?,
                opacity: Int,
                fromUser: Boolean
            ) {
                selectedRectangleView.apply {
                    selectedRectangleView?.let { presenter.changeOpacity(it, opacity) }
                }
            }

            override fun onStartTrackingTouch(opacitySeekBar: SeekBar?) {}

            override fun onStopTrackingTouch(opacitySeekBar: SeekBar?) {}
        })
    }

    override fun showSelected(rect: Rect, rectView: ImageView?, border: ImageView?) {
        selectedBorder = border
        selectedRectangleView = rectView
        rectView?.let {
            val rgbValueTextView = findViewById<TextView>(R.id.tv_rgb_value)
            val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
            rgbValueTextView.text = rect.backGroundColor.getRGBHexValue()
            opacitySeekBar.progress = rect.opacity
            mainLayout.addView(border)
        }
    }

    override fun showColorChange(
        rectView: ImageView,
        color: BackGroundColor,
        colorValueView: TextView
    ) {
        rectView.setBackgroundColor(color.getBackGroundColor())
        colorValueView.text = color.getRGBHexValue()

    }

    override fun showOpacityChange(rectView: ImageView, opacity: Int) {
        rectView.alpha = (opacity/10.0).toFloat()
    }

}