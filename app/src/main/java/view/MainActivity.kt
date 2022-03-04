package view

import MainPresenter
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.codesquad.kotlin_drawingapp.R
import model.BackGroundColor
import model.Rect


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainLayout: FrameLayout
    private lateinit var presenter: MainPresenter
    private var selectedCustomRectangleView: RectView? = null
    private lateinit var tvRgbValue: TextView
    private val backgroundObserver = Observer<BackGroundColor> { colorValue ->
        selectedCustomRectangleView?.colorChange(colorValue)
        tvRgbValue.text = colorValue.getRGBHexValue()
    }
    private val opacityObserver = Observer<Int> { opacity ->
        selectedCustomRectangleView?.opacityChange(opacity)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, this)
        mainLayout = findViewById(R.id.image_container)
        val btnMakeRectangle = findViewById<Button>(R.id.btn_addRectangle)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        val btnMakePhotoView = findViewById<Button>(R.id.btn_addPhoto)
        tvRgbValue = findViewById<TextView>(R.id.tv_rgb_value)

        btnMakeRectangle.setOnClickListener {
            mainLayout.addView(presenter.createRectanglePaint())
        }

        btnMakePhotoView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    getPhotoFromGallery()
                } else {
                    if(shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        showContextPopupPermission()
                    }
                    else{
                        requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
                    }
                }
            } else {
                getPhotoFromGallery()
            }
        }



        mainLayout.setOnTouchListener { _, motionEvent ->
            this.selectedCustomRectangleView =
                presenter.selectRectangle(motionEvent.x, motionEvent.y)
            true
        }

        tvRgbValue.setOnClickListener {
            selectedCustomRectangleView?.let { selectedView -> presenter.changeColor(selectedView) }
        }

        opacitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, opacity: Int, fromUser: Boolean) {
                selectedCustomRectangleView?.let { presenter.changeOpacity(it, opacity) }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    private fun showContextPopupPermission() {
        AlertDialog.Builder(this).setTitle("권한이 필요합니다")
            .setMessage("사진을 불러오기 위해 권한이 필요합니다")
            .setPositiveButton("동의하기") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1000
                    )
                }
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()

    }

    private fun getPhotoFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        getPhoto.launch(intent)
    }

    private val getPhoto: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK && it.data != null) {
                val currentImageUri = it.data?.data
                lateinit var bitmap: Bitmap
                try {
                    currentImageUri?.let {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                this.contentResolver,
                                currentImageUri
                            )
                        } else {
                            val source =
                                ImageDecoder.createSource(this.contentResolver, currentImageUri)
                            bitmap = ImageDecoder.decodeBitmap(source)
                        }
                        mainLayout.addView(presenter.createPhotoPaint(bitmap))
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }


    override fun displayRectAttribute(rgbInfo: String, opacity: Int, rect: Rect) {
        val rgbValueTextView = findViewById<TextView>(R.id.tv_rgb_value)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        rgbValueTextView.text = rgbInfo
        opacitySeekBar.progress = (opacity / 25.5).toInt()
        rect.backGroundColor.observe(this, backgroundObserver)
        rect.opacity.observe(this, opacityObserver)
    }


}
