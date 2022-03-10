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
        var bitmapImages = mutableListOf<Bitmap>()

        customView.customListener = object : CustomListener {
            override fun isClicked(numbers: String) {
                rectangleColorButton.text = numbers
            }
        }

        button.setOnClickListener {
            customView.makeRectangle()
            rectangleCount.text = "사각형수: ${customView.countRectangles()}"
        }

        removeButton.setOnClickListener {
            customView.removeRectangleAndStrokes()
            rectangleCount.text = "사각형수: ${customView.countRectangles()}"
        }

        rectangleColorButton.setOnClickListener {
            customView.changeRectangleColor()
        }

        val startForResult =
            registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
                val bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, image))
                bitmapImages.add(bitmap)
            }

        photoLoader.setOnClickListener {
            startForResult.launch("image/*")
            customView.loadImages(bitmapImages)
        }
    }
}

