package view

import MainPresenter
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.codesquad.kotlin_drawingapp.R
import model.BackGroundColor
import model.Rect
import model.RectView


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainLayout: FrameLayout
    private lateinit var presenter: MainPresenter
    private var selectedCustomRectangleView: RectView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, this)
        mainLayout = findViewById(R.id.image_container)
        val btnMakeRectangle = findViewById<Button>(R.id.btn_addRectangle)
        val tvRGBValue = findViewById<TextView>(R.id.tv_rgb_value)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        btnMakeRectangle.setOnClickListener {
            presenter.createRectanglePaint()
        }

        mainLayout.setOnTouchListener { _, motionEvent ->
            if (selectedCustomRectangleView == null) {
                presenter.selectRectangle(motionEvent.x, motionEvent.y)
            } else {
                selectedCustomRectangleView?.let {
                    it.eraseBorder()
                    presenter.selectRectangle(motionEvent.x, motionEvent.y)
                }
            }
            true
        }


        tvRGBValue.setOnClickListener {
            selectedCustomRectangleView?.let { selectedView ->
                presenter.changeColor(selectedView, tvRGBValue)
            }
        }

        opacitySeekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar?, opacity: Int, fromUser: Boolean) {
                selectedCustomRectangleView?.let { presenter.changeOpacity(it, opacity) }
            }

            override fun onStartTrackingTouch(seekBar:SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }
    override fun drawRectangle(rectView: RectView) {
        mainLayout.addView(rectView)

    }

    override fun drawBorder(rectView: RectView?) {
        this.selectedCustomRectangleView = rectView
        rectView?.drawBorder()
    }

    override fun displayAttribute(rect: Rect) {
        val rgbValueTextView = findViewById<TextView>(R.id.tv_rgb_value)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        rgbValueTextView.text = rect.backGroundColor.getRGBHexValue()
        opacitySeekBar.progress = rect.opacity
    }

    override fun showColorChange(
        rectView: RectView,
        color: BackGroundColor,
        colorValueView: TextView
    ) {
        rectView.colorChange(color)
        colorValueView.text = color.getRGBHexValue()

    }

    override fun showOpacityChange(rectView: RectView, opacity: Int) {
        rectView.opacityChange(opacity)
    }

}