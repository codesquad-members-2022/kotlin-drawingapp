package com.example.kotlin_drawingapp

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.draw.DrawObject
import com.example.kotlin_drawingapp.model.draw.DrawView
import com.example.kotlin_drawingapp.model.source.DrawingRepository
import com.example.kotlin_drawingapp.model.source.memory.PlaneDataSource

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var drawView: DrawView
    private lateinit var tvRgb: TextView
    private lateinit var seekBarAlpha: SeekBar
    private lateinit var btnGenerateRectangle: Button
    private lateinit var btnGenerateImage: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        setViewListener()
        setGalleryIntentForBtnImage()
    }

    private fun setGalleryIntentForBtnImage() {
        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_PICK
        val selectImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.data
                data?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        presenter.createImage(ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, it)))
                    } else {
                        presenter.createImage(MediaStore.Images.Media.getBitmap(contentResolver, it))
                    }
                }
            }
        }

        btnGenerateImage.setOnClickListener {
            selectImageResult.launch(galleryIntent)
        }
    }

    private fun bindView() {
        presenter = MainPresenter(DrawingRepository(PlaneDataSource()), this)
        drawView = findViewById(R.id.drawView)
        tvRgb = findViewById(R.id.tv_background_color)
        seekBarAlpha = findViewById(R.id.seekBar)
        btnGenerateRectangle = findViewById(R.id.btn_generate_rect)
        btnGenerateImage = findViewById(R.id.btn_generate_image)
    }

    private fun setViewListener() {
        btnGenerateRectangle.setOnClickListener {
            presenter.createRectangle()
        }

        drawView.setOnDrawViewTouchListener(object : DrawView.OnDrawViewTouchListener {
            override fun onClick(point: PointF) {
                presenter.selectDrawObject(point.x, point.y)
            }
        })

        seekBarAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress > 0) {
                    presenter.setCurrentSelectedDrawObjectAlpha(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun showDrawObject(drawObject: List<DrawObject>) {
        drawView.draw(drawObject)
    }

    override fun showDrawObjectInfo(color: Color, alpha: Int) {
        tvRgb.text = String.format("%X", color.getRgb())
        seekBarAlpha.progress = alpha
    }
}