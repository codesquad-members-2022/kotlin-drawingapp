package view

import MainPresenter
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.codesquad.kotlin_drawingapp.R
import model.*
import model.Point
import model.Rect


private const val REQUEST_CODE = 1000

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mainLayout: FrameLayout
    private lateinit var presenter: MainPresenter
    private var selectedCustomRectangleView: RectView? = null
    private lateinit var tvRgbValue: TextView
    private lateinit var editTvWidth: EditText
    private lateinit var editTvHeight: EditText
    private lateinit var editTvXpos: EditText
    private lateinit var editTvYpos: EditText
    private lateinit var layer: LinearLayout
    private var squareIndex = 1
    private var textIndex = 1
    private var photoIndex = 1
    private var rectCount = 0
    private var customRectInfoViewList: ArrayList<CustomRectInfoView> = ArrayList(100)
    private lateinit var customLayerView: CustomRectInfoView
    private val backgroundObserver = Observer<BackGroundColor> { colorValue ->
        selectedCustomRectangleView?.changeColor(colorValue)
        tvRgbValue.text = colorValue.getRGBHexValue()
    }
    private val opacityObserver = Observer<Int> { opacity ->
        selectedCustomRectangleView?.changeOpacity(opacity)
    }
    private val customRectangleViewList: ArrayList<RectView> = arrayListOf()
    private var selectedRectangle: Rect? = null
    private val sizeObserver = Observer<Size> { size ->
        selectedCustomRectangleView?.changeSize(size.width, size.height)
        editTvWidth.setText("${size.width}")
        editTvHeight.setText("${size.height}")

    }
    private val posObserver = Observer<Point> { point ->
        selectedCustomRectangleView?.changePos(point.xPos.toFloat(), point.yPos.toFloat())
        editTvXpos.setText("${point.xPos}")
        editTvYpos.setText("${point.yPos}")
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, this)
        mainLayout = findViewById(R.id.image_container)
        layer = findViewById(R.id.linear_list)
        val btnMakeRectangle = findViewById<Button>(R.id.btn_addRectangle)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        val btnMakePhotoView = findViewById<Button>(R.id.btn_addPhoto)
        val btnMakeSentenceView = findViewById<Button>(R.id.btn_addSentence)
        tvRgbValue = findViewById<TextView>(R.id.tv_rgb_value)
        val btnWidthUp = findViewById<Button>(R.id.btn_width_up)
        val btnWidthDown = findViewById<Button>(R.id.btn_width_down)
        val btnHeightUp = findViewById<Button>(R.id.btn_height_up)
        val btnHeightDown = findViewById<Button>(R.id.btn_height_down)
        val btnXposUp = findViewById<Button>(R.id.btn_xPos_up)
        val btnYposUp = findViewById<Button>(R.id.btn_yPos_up)
        val btnXposDown = findViewById<Button>(R.id.btn_xPos_down)
        val btnYposDown = findViewById<Button>(R.id.btn_yPos_down)
        editTvWidth = findViewById<EditText>(R.id.editText_width)
        editTvHeight = findViewById<EditText>(R.id.editText_height)
        editTvXpos = findViewById<EditText>(R.id.editText_xPos)
        editTvYpos = findViewById<EditText>(R.id.editText_yPos)
        tvRgbValue = findViewById<TextView>(R.id.tv_rgb_value)
        btnMakeRectangle.setOnClickListener {
            customLayerView = CustomRectInfoView(this, "Rect $squareIndex")
            squareIndex++
            layer.addView(customLayerView)
            customRectInfoViewList.add(customLayerView)
            presenter.createRectanglePaint()
            customLayerView.setOnClickListener { view ->
                view as CustomRectInfoView
                customRectangleViewList.map { it.eraseBorder() }
                customRectInfoViewList.map { it.resetColor() }
                view.setBackgroundColor(Color.GRAY)
                selectedRectangle?.opacity?.removeObserver(opacityObserver)
                selectedRectangle?.backGroundColor?.removeObserver(backgroundObserver)
                presenter.selectRectangleByList(view.rectId)
            }
            customLayerView.setOnLongClickListener { view ->
                view as CustomRectInfoView
                val popupMenu = PopupMenu(this, view)
                val mInflater = this.menuInflater
                mInflater.inflate(R.menu.list_longclick_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    val indexOfSelectedItem = customRectInfoViewList.indexOf(view)
                    customRectInfoViewList.map { originView ->
                        layer.removeView(originView)
                    }
                    when (it.itemId) {

                        R.id.item_move_back -> {
                            if (indexOfSelectedItem >= 1) {
                                val temp = customRectInfoViewList[indexOfSelectedItem - 1]
                                customRectInfoViewList[indexOfSelectedItem - 1] = view
                                customRectInfoViewList[indexOfSelectedItem] = temp
                            }
                        }
                        R.id.item_move_front -> {
                            if (indexOfSelectedItem + 1 < customRectInfoViewList.size) {
                                val temp = customRectInfoViewList[indexOfSelectedItem + 1]
                                customRectInfoViewList[indexOfSelectedItem + 1] = view
                                customRectInfoViewList[indexOfSelectedItem] = temp
                            }
                        }
                        R.id.item_move_head -> {
                            val temp: ArrayList<CustomRectInfoView> = ArrayList(100)
                            customRectInfoViewList.remove(view)
                            temp.add(view)
                            customRectInfoViewList.map { info ->
                                temp.add(info)
                            }
                            this.customRectInfoViewList = temp
                        }
                        R.id.item_move_tail -> {
                            val temp: ArrayList<CustomRectInfoView> = ArrayList(100)
                            customRectInfoViewList.remove(view)
                            customRectInfoViewList.map { info ->
                                temp.add(info)
                            }
                            temp.add(view)
                            this.customRectInfoViewList = temp
                        }

                    }
                    customRectInfoViewList.map { modifyView ->
                        layer.addView(modifyView)
                    }
                    true
                }
                popupMenu.show()
                true
            }


        }

        btnMakePhotoView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                when {
                    ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        getPhotoFromGallery()
                    }
                    else -> {
                        requestPermissions(
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_CODE
                        )
                    }
                }
            }

        }

        btnMakeSentenceView.setOnClickListener {
            customLayerView = CustomRectInfoView(this, "Text $textIndex")
            textIndex++
            layer.addView(customLayerView)
            customRectInfoViewList.add(customLayerView)
            presenter.createSentencePaint()
            customLayerView.setOnClickListener { view ->
                view as CustomRectInfoView
                customRectangleViewList.map { it.eraseBorder() }
                customRectInfoViewList.map { it.resetColor() }
                view.setBackgroundColor(Color.GRAY)
                selectedRectangle?.opacity?.removeObserver(opacityObserver)
                selectedRectangle?.backGroundColor?.removeObserver(backgroundObserver)
                presenter.selectRectangleByList(view.rectId)
            }
            customLayerView.setOnLongClickListener { view ->
                view as CustomRectInfoView
                val popupMenu = PopupMenu(this, view)
                val mInflater = this.menuInflater
                mInflater.inflate(R.menu.list_longclick_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    val indexOfSelectedItem = customRectInfoViewList.indexOf(view)
                    customRectInfoViewList.map { originView ->
                        layer.removeView(originView)
                    }
                    when (it.itemId) {

                        R.id.item_move_back -> {
                            if (indexOfSelectedItem >= 1) {
                                val temp = customRectInfoViewList[indexOfSelectedItem - 1]
                                customRectInfoViewList[indexOfSelectedItem - 1] = view
                                customRectInfoViewList[indexOfSelectedItem] = temp
                            }
                        }
                        R.id.item_move_front -> {
                            if (indexOfSelectedItem + 1 < customRectInfoViewList.size) {
                                val temp = customRectInfoViewList[indexOfSelectedItem + 1]
                                customRectInfoViewList[indexOfSelectedItem + 1] = view
                                customRectInfoViewList[indexOfSelectedItem] = temp
                            }
                        }
                        R.id.item_move_head -> {
                            val temp: ArrayList<CustomRectInfoView> = ArrayList(100)
                            customRectInfoViewList.remove(view)
                            temp.add(view)
                            customRectInfoViewList.map { info ->
                                temp.add(info)
                            }
                            this.customRectInfoViewList = temp
                        }
                        R.id.item_move_tail -> {
                            val temp: ArrayList<CustomRectInfoView> = ArrayList(100)
                            customRectInfoViewList.remove(view)
                            customRectInfoViewList.map { info ->
                                temp.add(info)
                            }
                            temp.add(view)
                            this.customRectInfoViewList = temp
                        }

                    }
                    customRectInfoViewList.map { modifyView ->
                        layer.addView(modifyView)
                    }
                    true
                }
                popupMenu.show()
                true
            }


        }
        mainLayout.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                customRectangleViewList.map { it.eraseBorder() }
                selectedRectangle?.opacity?.removeObserver(opacityObserver)
                selectedRectangle?.backGroundColor?.removeObserver(backgroundObserver)
                presenter.selectRectangle(motionEvent.x, motionEvent.y)
            }
            false
        }


        tvRgbValue.setOnClickListener {
            selectedCustomRectangleView?.let { selectedView ->
                presenter.changeColor(
                    selectedView
                )
            }
        }

        opacitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, opacity: Int, fromUser: Boolean) {
                selectedCustomRectangleView?.let { presenter.changeOpacity(it, opacity) }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        btnHeightDown.setOnClickListener {
            selectedCustomRectangleView?.let {
                if (it.rectHeight != 1) {
                    presenter.changeSize(it, "height", -1)
                }
            }
        }

        btnWidthDown.setOnClickListener {
            selectedCustomRectangleView?.let {
                if (it.rectWidth != 1) {
                    presenter.changeSize(it, "width", -1)
                }
            }
        }

        btnWidthUp.setOnClickListener {
            selectedCustomRectangleView?.let {
                presenter.changeSize(it, "width", 1)
            }
        }

        btnHeightUp.setOnClickListener {
            selectedCustomRectangleView?.let {
                presenter.changeSize(it, "height", 1)
            }
        }

        btnXposUp.setOnClickListener {
            selectedCustomRectangleView?.let {
                presenter.changeXpos(it, 1)
            }
        }
        btnXposDown.setOnClickListener {
            selectedCustomRectangleView?.let {
                if (it.left.toInt() != 1) {
                    presenter.changeXpos(it, -1)
                }
            }
        }

        btnYposUp.setOnClickListener {
            selectedCustomRectangleView?.let {
                presenter.changeYPos(it, 1)
            }
        }

        btnYposDown.setOnClickListener {
            selectedCustomRectangleView?.let {
                if (it.top.toInt() != 1) {
                    presenter.changeYPos(it, -1)
                }
            }
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    getPhotoFromGallery()
                } else {
                    showContextPopupPermission()
                }
                return
            }
        }
    }


    private fun showContextPopupPermission() {
        AlertDialog.Builder(this).setTitle("권한이 필요합니다")
            .setMessage("사진을 불러오기 위해 권한설정이 필요합니다")
            .setPositiveButton("설정하러가기") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .setData(Uri.parse("package:$packageName"));
                startActivity(intent)
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

                        customLayerView = CustomRectInfoView(this, "Photo $photoIndex")
                        photoIndex++
                        layer.addView(customLayerView)
                        customRectInfoViewList.add(customLayerView)
                        presenter.createPhotoPaint(bitmap)
                        customLayerView.setOnClickListener { view ->
                            view as CustomRectInfoView
                            customRectangleViewList.map { it.eraseBorder() }
                            customRectInfoViewList.map { it.resetColor() }
                            view.setBackgroundColor(Color.GRAY)
                            selectedRectangle?.opacity?.removeObserver(opacityObserver)
                            selectedRectangle?.backGroundColor?.removeObserver(backgroundObserver)

                            presenter.selectRectangleByList(view.rectId)
                        }
                        customLayerView.setOnLongClickListener { view ->
                            view as CustomRectInfoView
                            val popupMenu = PopupMenu(this, view)
                            val mInflater = this.menuInflater
                            mInflater.inflate(R.menu.list_longclick_menu, popupMenu.menu)
                            popupMenu.setOnMenuItemClickListener {
                                val indexOfSelectedItem = customRectInfoViewList.indexOf(view)
                                customRectInfoViewList.map { originView ->
                                    layer.removeView(originView)
                                }
                                when (it.itemId) {

                                    R.id.item_move_back -> {
                                        if (indexOfSelectedItem >= 1) {
                                            val temp =
                                                customRectInfoViewList[indexOfSelectedItem - 1]
                                            customRectInfoViewList[indexOfSelectedItem - 1] = view
                                            customRectInfoViewList[indexOfSelectedItem] = temp
                                        }
                                    }
                                    R.id.item_move_front -> {
                                        if (indexOfSelectedItem + 1 < customRectInfoViewList.size) {
                                            val temp =
                                                customRectInfoViewList[indexOfSelectedItem + 1]
                                            customRectInfoViewList[indexOfSelectedItem + 1] = view
                                            customRectInfoViewList[indexOfSelectedItem] = temp
                                        }
                                    }
                                    R.id.item_move_head -> {
                                        val temp: ArrayList<CustomRectInfoView> = ArrayList(100)
                                        customRectInfoViewList.remove(view)
                                        temp.add(view)
                                        customRectInfoViewList.map { info ->
                                            temp.add(info)
                                        }
                                        this.customRectInfoViewList = temp
                                    }
                                    R.id.item_move_tail -> {
                                        val temp: ArrayList<CustomRectInfoView> = ArrayList(100)
                                        customRectInfoViewList.remove(view)
                                        customRectInfoViewList.map { info ->
                                            temp.add(info)
                                        }
                                        temp.add(view)
                                        this.customRectInfoViewList = temp
                                    }

                                }
                                customRectInfoViewList.map { modifyView ->
                                    layer.addView(modifyView)
                                }
                                true
                            }
                            popupMenu.show()
                            true
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }


    @SuppressLint("ClickableViewAccessibility")
    override fun displaySelectedRectAttribute(rect: Rect) {
        this.selectedCustomRectangleView =
            customRectangleViewList.find { it.rectId == rect.rectId }
        this.selectedRectangle = rect

        selectedCustomRectangleView?.drawBorder()
        val rgbValueTextView = findViewById<TextView>(R.id.tv_rgb_value)
        val opacitySeekBar = findViewById<SeekBar>(R.id.seekbar_opacity)
        val editTvWidth = findViewById<EditText>(R.id.editText_width)
        val editTvHeight = findViewById<EditText>(R.id.editText_height)
        val editTvXpos = findViewById<EditText>(R.id.editText_xPos)
        val editTvYpos = findViewById<EditText>(R.id.editText_yPos)
        var tempView = RectView(this)
        editTvHeight.setText("${rect.size.value?.height}")
        editTvWidth.setText("${rect.size.value?.width}")
        editTvXpos.setText("${rect.point.value?.xPos}")
        editTvYpos.setText("${rect.point.value?.yPos}")
        tempView.isVisible = false
        if (selectedCustomRectangleView?.photoId == "" && selectedCustomRectangleView?.text == "") {
            rgbValueTextView.text = rect.backGroundColor.value?.getRGBHexValue()
            rect.opacity.value?.let {
                opacitySeekBar.progress = it
            }
            rect.backGroundColor.observe(this, backgroundObserver)
            rect.opacity.observe(this, opacityObserver)

            tempView.drawRectangle(rect)
        } else if (selectedCustomRectangleView?.text != "") {
            rgbValueTextView.text = "No Color"
            rect.opacity.value?.let {
                opacitySeekBar.progress = it
            }
            rect.opacity.observe(this, opacityObserver)
            tempView.drawSentence(rect as Sentence)
        } else {
            rgbValueTextView.text = "No Color"
            rect.opacity.value?.let {
                opacitySeekBar.progress = it
            }
            rect.opacity.observe(this, opacityObserver)
            selectedCustomRectangleView?.bitmap?.let {
                tempView.drawPhoto(it, rect as Photo)
            }
        }
        rect.point.observe(this, posObserver)
        rect.size.observe(this, sizeObserver)

        tempView.changeOpacity(5)
        mainLayout.addView(tempView)
        selectedCustomRectangleView?.setOnTouchListener { view, motionEvent ->
            tempView.isVisible = true
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                selectedCustomRectangleView?.onTouch(motionEvent, tempView)
                tempView.invalidate()
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                selectedCustomRectangleView?.let {
                    it.changePos(motionEvent.x, motionEvent.y)
                    mainLayout.removeView(tempView)
                    presenter.changePosition(it)
                }
            }

            true
        }

    }

    override fun drawRectangle(rect: Rect) {
        val rectView = RectView(this)
        rectView.drawRectangle(rect)
        mainLayout.addView(rectView)
        customRectangleViewList.add(rectView)
        customRectInfoViewList[rectCount].rectId = rect.rectId
        customRectInfoViewList[rectCount].image.setImageBitmap(rectView.getBitmapFromView())
        rectCount++
    }

    override fun drawPhoto(photo: Photo) {
        val rectView = RectView(this)
        val image = BitmapFactory.decodeByteArray(photo.imageInfo, 0, photo.imageInfo.size)
        rectView.drawPhoto(image, photo)
        mainLayout.addView(rectView)
        customRectangleViewList.add(rectView)
        customRectInfoViewList[rectCount].rectId = photo.rectId
        customRectInfoViewList[rectCount].image.setImageBitmap(rectView.getThumbnailPhoto())
        rectCount++
    }

    override fun drawSentence(sentence: Sentence) {
        val rectView = RectView(this)
        rectView.drawSentence(sentence)
        mainLayout.addView(rectView)
        customRectangleViewList.add(rectView)
        customRectInfoViewList[rectCount].rectId = sentence.rectId
        customRectInfoViewList[rectCount].image.setImageBitmap(rectView.getBitmapFromView())
        rectCount++
    }

    override fun redrawRectangle(rect: Rect) {
        mainLayout.removeView(selectedCustomRectangleView)
        customRectangleViewList.remove(selectedCustomRectangleView)
        val rectView = RectView(this)
        rectView.drawRectangle(rect)
        selectedCustomRectangleView = rectView
        mainLayout.addView(rectView)
        customRectangleViewList.add(rectView)
    }

    override fun redrawPhoto(photo: Photo) {
        mainLayout.removeView(selectedCustomRectangleView)
        customRectangleViewList.remove(selectedCustomRectangleView)
        val rectView = RectView(this)
        selectedCustomRectangleView?.bitmap?.let {
            rectView.drawPhoto(it, photo)
        }
        selectedCustomRectangleView = rectView
        mainLayout.addView(rectView)
        customRectangleViewList.add(rectView)
    }

    override fun redrawSentence(sentence: Sentence) {
        mainLayout.removeView(selectedCustomRectangleView)
        customRectangleViewList.remove(selectedCustomRectangleView)
        val rectView = RectView(this)
        rectView.drawSentence(sentence)
        selectedCustomRectangleView = rectView
        mainLayout.addView(rectView)
        customRectangleViewList.add(rectView)
    }

}

