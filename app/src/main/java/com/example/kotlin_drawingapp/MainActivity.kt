package com.example.kotlin_drawingapp

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.Point
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Size
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_drawingapp.customview.NumberUpDownView
import com.example.kotlin_drawingapp.databinding.ActivityMainBinding
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.draw.DrawObject
import com.example.kotlin_drawingapp.model.draw.DrawView
import com.example.kotlin_drawingapp.model.source.DrawingRepository
import com.example.kotlin_drawingapp.model.source.memory.PlaneDataSource

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(DrawingRepository(PlaneDataSource()), this)
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

        binding.btnGenerateImage.setOnClickListener {
            selectImageResult.launch(galleryIntent)
        }
    }

    private fun setViewListener() {
        binding.btnGenerateRect.setOnClickListener {
            presenter.createRectangle()
        }

        binding.btnGenerateTextview?.setOnClickListener {
            presenter.createTextView()
        }

        binding.drawView.setOnDrawViewTouchListener(object : DrawView.OnDrawViewTouchListener {
            override fun onClick(point: PointF) {
                presenter.selectDrawObject(point.x, point.y)
            }
        })

        binding.drawView.setOnDrawViewMoveEventListener(object : DrawView.OnDrawViewMoveEventListener {
            override fun onUpdate(point: Point) {
                binding.numberUpDownX.setText(point.x.toString())
                binding.numberUpDownY.setText(point.y.toString())
            }
        })

        binding.drawView.setOnDrawViewUpdateListener(object : DrawView.OnDrawViewPointUpdateListener{
            override fun onUpdate(target: DrawObject, point: Point) {
                presenter.modifyDrawObjectProperty(target, point, target.currentSize)
            }
        })

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress > 0) {
                    presenter.setCurrentSelectedDrawObjectAlpha(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.numberUpDownX.setOnChangedValueListener(object : NumberUpDownView.OnChangedValueListener {
            override fun onChaged(value: Int) {
                binding.drawView.currentSelectedDrawObject?.let {
                    presenter.modifyDrawObjectProperty(it, Point(value, it.currentPoint.y), it.currentSize)
                }
            }
        })

        binding.numberUpDownY.setOnChangedValueListener(object : NumberUpDownView.OnChangedValueListener {
            override fun onChaged(value: Int) {
                binding.drawView.currentSelectedDrawObject?.let {
                    presenter.modifyDrawObjectProperty(it, Point(it.currentPoint.x, value), it.currentSize)
                }
            }
        })

        binding.numberUpDownWidth.setOnChangedValueListener(object : NumberUpDownView.OnChangedValueListener {
            override fun onChaged(value: Int) {
                binding.drawView.currentSelectedDrawObject?.let {
                    presenter.modifyDrawObjectProperty(it, it.currentPoint, Size(value, it.currentSize.height))
                }
            }
        })

        binding.numberUpDownHeight.setOnChangedValueListener(object : NumberUpDownView.OnChangedValueListener {
            override fun onChaged(value: Int) {
                binding.drawView.currentSelectedDrawObject?.let {
                    presenter.modifyDrawObjectProperty(it, it.currentPoint, Size(it.currentSize.width, value))
                }
            }
        })
    }

    override fun showDrawObject(drawObject: List<DrawObject>) {
        binding.drawView.draw(drawObject)
    }

    override fun showDrawObjectInfo(color: Color, alpha: Int, point: Point, size: Size) {
        binding.tvBackgroundColor.text = String.format("%X", color.getRgb())
        binding.seekBar.progress = alpha
        binding.numberUpDownX.setText(point.x.toString())
        binding.numberUpDownY.setText(point.y.toString())
        binding.numberUpDownWidth.setText(size.width.toString())
        binding.numberUpDownHeight.setText(size.height.toString())
    }

    override fun setCurrentSelectedDrawObject(drawObject: DrawObject?) {
        binding.drawView.currentSelectedDrawObject = drawObject
    }
}