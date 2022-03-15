package com.example.kotlindrawingapp.view

import android.Manifest
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindrawingapp.R
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.presenter.Contract
import com.example.kotlindrawingapp.presenter.Presenter
import com.example.kotlindrawingapp.repository.FigureRepository

class MainActivity : AppCompatActivity(), Contract.View, Movable {

    private lateinit var presenter: Presenter
    private lateinit var colorTextView: TextView
    private lateinit var alpha: TextView
    private lateinit var x: TextView
    private lateinit var y: TextView
    private lateinit var width: TextView
    private lateinit var height: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var customView: CustomCanvas
    private lateinit var squareButton: Button
    private lateinit var pictureButton: Button
    private lateinit var textButton: Button
    private lateinit var xUpButton: ImageButton
    private lateinit var xDownButton: ImageButton
    private lateinit var yUpButton: ImageButton
    private lateinit var yDownButton: ImageButton
    private lateinit var widthUpButton: ImageButton
    private lateinit var widthDownButton: ImageButton
    private lateinit var heightUpButton: ImageButton
    private lateinit var heightDownButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        val repository = FigureRepository()
        presenter = Presenter(this, repository)
        customView.setListener(this)

        presenter.plane.observe(this) {
            customView.drawRectangle(it)
        }

        presenter.selectedSquare.observe(this) {
            it?.let {
                colorTextView.text = it.rgb?.decimalToHex() ?: "NONE"
                seekBar.progress = it.alpha?.alpha
                alpha.text = seekBar.progress.toString()
                x.text = it.point.x.toString()
                y.text = it.point.y.toString()
                width.text = it.size.width.toString()
                height.text = it.size.height.toString()
            }
        }

        squareButton.setOnClickListener { presenter.loadFigure() }
        pictureButton.setOnClickListener { albumPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE) }
        textButton.setOnClickListener {
            presenter.loadRandomText(object : Sizeable {
                override fun getWidthAndHeight(text: String) {
                    val size = customView.getWidthAndHeight(text)
                    presenter.loadText(size, text)
                }
            })
        }
        onCoordinateEvent()
        onSizeEvent()
        onSeekBarEvent()
    }

    private fun onSeekBarEvent() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.editRectangleAlpha(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun onSizeEvent() {
        widthUpButton.setOnClickListener {
            val newWidth = width.text.toString().toInt() + 1
            presenter.editFigureWidth(newWidth)
        }

        widthDownButton.setOnClickListener {
            val newWidth = width.text.toString().toInt() - 1
            if (newWidth >= 1) {
                presenter.editFigureWidth(newWidth)
            }
        }

        heightUpButton.setOnClickListener {
            val newHeight = height.text.toString().toInt() + 1
            presenter.editFigureHeight(newHeight)
        }

        heightDownButton.setOnClickListener {
            val newHeight = height.text.toString().toInt() - 1
            if (newHeight >= 1) {
                presenter.editFigureHeight(newHeight)
            }
        }
    }

    private fun onCoordinateEvent() {
        xUpButton.setOnClickListener {
            val pointX = x.text.toString().toFloat() + 1
            presenter.editFigurePointX(pointX)
        }

        xDownButton.setOnClickListener {
            val pointX = x.text.toString().toFloat() - 1
            if (pointX >= 1) {
                presenter.editFigurePointX(pointX)
            }
        }

        yUpButton.setOnClickListener {
            val pointY = y.text.toString().toFloat() + 1
            presenter.editFigurePointY(pointY)
        }

        yDownButton.setOnClickListener {
            val pointY = y.text.toString().toFloat() - 1
            if (pointY >= 1) {
                presenter.editFigurePointY(pointY)
            }
        }
    }

    private fun initialize() {
        squareButton = findViewById(R.id.btn_square)
        pictureButton = findViewById(R.id.btn_picture)
        textButton = findViewById(R.id.btn_text)
        customView = findViewById(R.id.container_canvas)
        colorTextView = findViewById(R.id.tv_color)
        seekBar = findViewById(R.id.seekBar_alpha)
        alpha = findViewById(R.id.tv_alpha)
        xUpButton = findViewById(R.id.btn_x_up)
        xDownButton = findViewById(R.id.btn_x_down)
        yUpButton = findViewById(R.id.btn_y_up)
        yDownButton = findViewById(R.id.btn_y_down)
        widthUpButton = findViewById(R.id.btn_width_up)
        widthDownButton = findViewById(R.id.btn_width_down)
        heightUpButton = findViewById(R.id.btn_height_up)
        heightDownButton = findViewById(R.id.btn_height_down)
        x = findViewById(R.id.tv_x)
        y = findViewById(R.id.tv_y)
        width = findViewById(R.id.tv_width)
        height = findViewById(R.id.tv_height)
    }

    private val getAlbum =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val uri = it.data?.data
                uri?.let {
                    val bitmap = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                        MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                    } else {
                        val source = ImageDecoder.createSource(this.contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    }
                    presenter.loadPicture(bitmap)
                }
            }
        }

    private val albumPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                val intent = Intent()
                    .apply {
                        action = Intent.ACTION_PICK
                        type = "image/*"
                    }
                getAlbum.launch(Intent.createChooser(intent, "사진 고르기"))
            } else {
                Toast.makeText(this, "취소!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun move(x: Float, y: Float, figure: Figure, selectedFigure: Figure) {
        presenter.editFigurePoint(x, y)
        presenter.removeFigure(selectedFigure)
        presenter.loadFigure(figure)
    }

    override fun move(tempX: Float, tempY: Float) {
        x.text = tempX.toString()
        y.text = tempY.toString()
    }
}