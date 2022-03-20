package com.codesquard.kotlin_drawingapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.codesquard.kotlin_drawingapp.*
import com.codesquard.kotlin_drawingapp.itemlistview.Command
import com.codesquard.kotlin_drawingapp.itemlistview.ItemList
import com.codesquard.kotlin_drawingapp.itemlistview.ItemListListener
import com.codesquard.kotlin_drawingapp.model.Rectangle
import com.codesquard.kotlin_drawingapp.presenter.TaskContract
import com.codesquard.kotlin_drawingapp.presenter.TaskPresenter
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), TaskContract.TaskView, ItemListListener {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var itemListLayout: LinearLayout
    private lateinit var customView: CustomView
    private lateinit var tempView: TemporaryView
    private lateinit var normalRectCreateBtn: Button
    private lateinit var backgroundBtn: Button
    private lateinit var photoRectCreateBtn: Button
    private lateinit var textRectCreateBtn: Button
    private lateinit var positionXBtn: Button
    private lateinit var positionYBtn: Button
    private lateinit var sizeWBtn: Button
    private lateinit var sizeHBtn: Button
    private lateinit var alphaSlider: Slider
    private lateinit var presenter: TaskContract.Presenter
    private lateinit var itemList: ItemList
    private lateinit var rectIcon: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.main_layout)
        itemListLayout = findViewById(R.id.layout_layer)
        customView = findViewById(R.id.custom_view)
        tempView = findViewById(R.id.temporary_view)
        backgroundBtn = findViewById(R.id.btn_background)
        normalRectCreateBtn = findViewById(R.id.btn_normal_rect)
        photoRectCreateBtn = findViewById(R.id.btn_photo_rect)
        textRectCreateBtn = findViewById(R.id.btn_text_rect)
        alphaSlider = findViewById(R.id.slider_alpha)
        positionXBtn = findViewById(R.id.btn_position_x)
        positionYBtn = findViewById(R.id.btn_position_y)
        sizeWBtn = findViewById(R.id.btn_size_w)
        sizeHBtn = findViewById(R.id.btn_size_h)
        presenter = TaskPresenter(this)
        itemList = ItemList(this, itemListLayout)

        val getPhoto = registerIntentToGetPhotoAsBitmap()
        val requestPermissionLauncher = registerPermission(getPhoto)

        onClickNormalRectBtn()
        onClickPhotoRectBtn(requestPermissionLauncher)
        onClickTextRectBtn()
        onTouchBtnToChangeSize()
        onTouchBtnToChangePosition()
        setCustomViewTouchEvent()
        measureCustomViewSize()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchBtnToChangeSize() {
        sizeWBtn.setOnTouchListener { v: View, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> presenter.changeSize(event.x, event.y, true)
                else -> v.performClick()
            }
            true
        }
        sizeHBtn.setOnTouchListener { v: View, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> presenter.changeSize(event.x, event.y, false)
                else -> v.performClick()
            }
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchBtnToChangePosition() {
        positionXBtn.setOnTouchListener { v: View, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> presenter.changePosition(event.x, event.y, true)
                else -> v.performClick()
            }
            true
        }
        positionYBtn.setOnTouchListener { v: View, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> presenter.changePosition(event.x, event.y, false)
                else -> v.performClick()
            }
            true
        }
    }

    private fun registerIntentToGetPhotoAsBitmap() =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val imageUri = it.data?.data ?: throw IllegalAccessError("사진을 선택해야 합니다.")
                val photo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            this.contentResolver,
                            imageUri
                        )
                    )
                } else {
                    MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                }
                presenter.addNewRectangle(photo)
            } else {
                Snackbar.make(customView, "사진을 불러오지 못하였습니다", Snackbar.LENGTH_SHORT).show()
            }
        }

    private fun registerPermission(photo: ActivityResultLauncher<Intent>): ActivityResultLauncher<String> {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    val intent = Intent(ACTION_GET_CONTENT).apply {
                        data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        type = "image/*"
                    }
                    photo.launch(intent)
                } else {
                    Snackbar.make(
                        customView,
                        "사진을 추가하기 위해서는 미디어 권한을 승인하시기 바랍니다",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        return requestPermissionLauncher
    }

    private fun onClickTextRectBtn() {
        textRectCreateBtn.setOnClickListener {
            setRectIcon(textRectCreateBtn)
            presenter.createNewTextRectangle()
        }
    }

    private fun onClickPhotoRectBtn(requestPermissionLauncher: ActivityResultLauncher<String>) {
        photoRectCreateBtn.setOnClickListener {
            setRectIcon(photoRectCreateBtn)
            requestPermissionLauncher.launch("android.permission.ACCESS_MEDIA_LOCATION")
        }
    }

    private fun onClickColorBtn() {
        backgroundBtn.setOnClickListener {
            presenter.changeColor()
        }
    }

    private fun onSlideAlpha() {
        alphaSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                presenter.changeAlphaValue(slider.value)
            }
        })
    }

    private fun onClickNormalRectBtn() {
        normalRectCreateBtn.setOnClickListener {
            setRectIcon(normalRectCreateBtn)
            presenter.addNewRectangle()
        }
    }

    override fun onChangeRectOrder(command: Command) {
        val rectList = customView.getRectList()
        val selectedRect = customView.getSelectedRect()
        command.runChangeRectangleFunction(rectList, selectedRect)
        customView.invalidate()
    }

    override fun showRectangle(newRect: Rectangle) {
        itemList.addNewItem(newRect.id, newRect.type, newRect.createdOrder, rectIcon, this)
        customView.addNewRect(newRect)
        customView.invalidate()
    }

    override fun showSelectedRectangle(selectedRect: Rectangle?) {
        customView.invalidate()
        selectedRect?.run {
            itemList.selectItem(selectedRect.id)
        }
    }

    override fun showRectColor(color: String) {
        backgroundBtn.text = color
    }

    override fun showRectAlpha(alpha: Float) {
        alphaSlider.value = alpha
    }

    override fun showEnabledColor(boolean: Boolean) {
        backgroundBtn.isEnabled = boolean
    }

    override fun showDraggingRectangle(tempRect: Rectangle?) {
        tempView.setTempRect(tempRect)
        tempView.invalidate()
    }

    private fun showDraggedRectangle() {
        tempView.setTempRect(null)
        tempView.invalidate()
        customView.invalidate()
    }

    override fun enableRectColorBtnAndSlider() {
        onClickColorBtn()
        onSlideAlpha()
    }

    override fun showRectSize(w: String, h: String) {
        sizeWBtn.text = "W   $w"
        sizeHBtn.text = "H   $h"
        if (w.isNotEmpty()) {
            val width = pxToDp(w.toFloat())
            val height = pxToDp(h.toFloat())
            sizeWBtn.text = "W   $width"
            sizeHBtn.text = "H   $height"
        }
    }

    override fun showRectPosition(x: String, y: String) {
        positionXBtn.text = "X   $x"
        positionYBtn.text = "Y   $y"
    }

    override fun measureTextSize(textRect: Rectangle) {
        val textSize = customView.getTextSize(textRect)
        presenter.addNewTextRectangle(textRect, textSize)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setCustomViewTouchEvent() {
        customView.setOnTouchListener { _, event ->
            val x = event?.x ?: 0f
            val y = event?.y ?: 0f

            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    itemList.unSelectItem()
                    presenter.selectRectangle(x, y)
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    presenter.dragRectangle(x, y)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    showDraggedRectangle()
                    true
                }
                else -> false
            }
        }
    }

    private fun getInitRectSize(width: Float, height: Float) =
        arrayOf(dpToPx(width), dpToPx(height))

    private fun measureCustomViewSize() {
        customView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rectMaxPoint = customView.getViewSize()
                presenter.setInitRectSizeAndMaxPoint(getInitRectSize(150f, 120f), rectMaxPoint)
                customView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun dpToPx(dp: Float): Int {
        val resources = this.resources
        val metrics = resources.displayMetrics
        val density = metrics.density
        return (dp * density).toInt()
    }

    private fun pxToDp(px: Float): Int {
        val resources = this.resources
        val metrics = resources.displayMetrics
        val density = metrics.density
        return (px / density).toInt()
    }

    private fun setRectIcon(btn: Button) {
        rectIcon = btn.compoundDrawables[1]
    }

    override fun onSelectItem(rectId: String) {
        presenter.selectRectangle(rectId)
    }
}


