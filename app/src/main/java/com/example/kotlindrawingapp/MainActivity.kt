package com.example.kotlindrawingapp

import android.Manifest
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindrawingapp.presenter.Contract
import com.example.kotlindrawingapp.presenter.Presenter
import com.example.kotlindrawingapp.repository.FigureRepository

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Presenter
    private lateinit var colorTextView: TextView
    private lateinit var alpha: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var customView: CustomCanvas
    private lateinit var squareButton: Button
    private lateinit var pictureButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        squareButton = findViewById(R.id.btn_square)
        pictureButton = findViewById(R.id.btn_picture)
        customView = findViewById(R.id.container_canvas)
        colorTextView = findViewById(R.id.tv_color)
        seekBar = findViewById(R.id.seekBar_alpha)
        alpha = findViewById(R.id.tv_alpha)
        val repository = FigureRepository()
        presenter = Presenter(this, repository)

        presenter.plane.observe(this) {
            customView.drawRectangle(it)
        }

        presenter.selectedSquare.observe(this) {
            colorTextView.text = it?.rgb?.decimalToHex() ?: "NONE"
            seekBar.progress = it?.alpha?.alpha ?: 0
            alpha.text = seekBar.progress.toString()
        }

        squareButton.setOnClickListener {
            presenter.loadRectangle()
        }

        pictureButton.setOnClickListener {
            albumPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.editRectangleAlpha(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
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
}