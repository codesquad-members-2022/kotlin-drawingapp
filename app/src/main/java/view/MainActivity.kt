package view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.app.R

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customView = findViewById<DrawObjects>(R.id.custom_canvas)
        val button = findViewById<Button>(R.id.make_square_button)
        val removeButton = findViewById<Button>(R.id.remove_square_button)
        val rectangleCount = findViewById<TextView>(R.id.textview_rectangle_count)
        val rectangleColorButton = findViewById<Button>(R.id.textview_background_color_change)
        val photoLoader = findViewById<Button>(R.id.insert_photo_button)
        val rectangleLeftText = findViewById<TextView>(R.id.rectangle_left)
        val rectangleTopText = findViewById<TextView>(R.id.rectangle_top)
        val rectangleRightText = findViewById<TextView>(R.id.rectangle_right)
        val rectangleBottomText = findViewById<TextView>(R.id.rectangle_bottom)
        var bitmapImage: MutableList<Bitmap> = mutableListOf()

        customView.customListener = object : CustomListener {
            override fun isClicked(numbers: String) {
                rectangleColorButton.text = numbers
            }
        }

        customView.locationListener =  object : WidthAndHeightListener {
            override fun showWidthAndHeight(numbers: String) {
                val metrics = numbers.split(',')
                val left = metrics[0]
                val top = metrics[1]
                val right = metrics[2]
                val bottom = metrics[3]
                rectangleLeftText.text = left
                rectangleTopText.text = top
                rectangleRightText.text = right
                rectangleBottomText.text = bottom
            }
        }

        button.setOnClickListener {
            customView.makeRectangle()
            rectangleCount.text = "사각형수: ${customView.countRectangles()}"
        }

        removeButton.setOnClickListener {
            customView.removeRectangleAndStrokes()
            rectangleColorButton.text = "clear"
            rectangleCount.text = "사각형수: ${customView.countRectangles()}"
        }

        rectangleColorButton.setOnClickListener {
            customView.changeRectangleColor()
        }

        val startForResult =
            registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
                val bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, image))
                bitmapImage.add(bitmap)
                customView.saveAndGetImage(bitmapImage)
                println("main: count : ${bitmapImage.count()}")
            }

        photoLoader.setOnClickListener {
            startForResult.launch("image/*")
        }
    }
}

