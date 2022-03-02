package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import com.example.myapplication.Class.Rectangle
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rectangle = Array(4) { Rectangle() }
        for (i in 0..3) {
            Log.d(
                "Rect${i + 1}",
                "Rect${i + 1} (${rectangle[i].id}), X:${rectangle[i].point.x},Y:${rectangle[i].point.y}, W${rectangle[i].size.width}, H${rectangle[i].size.height}, " +
                        "R${rectangle[i].color.r}, G${rectangle[i].color.g}, B${rectangle[i].color.b}, Alpha:${rectangle[i].alpha}"
            )
        }

        val mainPresenter: MainPresenter = MainPresenter()

        binding.makeRectangle.setOnClickListener {
            val rectangle = Rectangle()
            mainPresenter.saveRectangle(rectangle)
            val view = ImageView(this)
            view.setBackgroundColor(
                Color.rgb(
                    rectangle.color.r,
                    rectangle.color.g,
                    rectangle.color.b
                )
            )
            view.alpha = rectangle.alpha
            view.tag = rectangle.id

            val displayMetrics = resources.displayMetrics
            val width = (rectangle.size.width * displayMetrics.density).roundToInt()
            val height = (rectangle.size.height * displayMetrics.density).roundToInt()
            val layoutParams = FrameLayout.LayoutParams(width, height)

            val left = (rectangle.point.x * displayMetrics.density).roundToInt()
            val top = (rectangle.point.y * displayMetrics.density).roundToInt()
            layoutParams.setMargins(left, top, 0, 0)

            view.layoutParams = layoutParams
            binding.frameLayout?.addView(view, 0)

            val rectangleImage = view.findViewWithTag<ImageView>(rectangle.id)
            rectangleImage.setOnClickListener {
                binding.backgroundButton.text = String.format(
                    "#%02x%02x%02x",
                    rectangle.color.r,
                    rectangle.color.g,
                    rectangle.color.b
                )
                binding.alphaValue?.text = (rectangle.alpha * 10).toString()
                // 변경한 테두리 .. 어떻게 다시 되돌리는지 ?
                rectangleImage.setBackgroundResource(R.drawable.shape_drawable)

                binding.backgroundButton.setOnClickListener {
                    val r = rectangle.getNewColor().r
                    val g = rectangle.getNewColor().g
                    val b = rectangle.getNewColor().b
                    rectangleImage.setBackgroundColor(Color.rgb(r,g,b))

                    binding.backgroundButton.text = String.format(
                        "#%02x%02x%02x",
                        r, g, b
                    )
                }
            }
        }
    }

    override fun getRectangle(index: Int): Rectangle {
        TODO("Not yet implemented")
    }
}