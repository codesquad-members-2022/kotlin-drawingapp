package com.codesquad_han.kotlin_drawingapp.rectangle

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import com.codesquad_han.kotlin_drawingapp.R
import com.codesquad_han.kotlin_drawingapp.data.RectangleRepositoryImpl
import com.codesquad_han.kotlin_drawingapp.databinding.ActivityRectangleBinding
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle
import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.RectangleFactory
import com.google.android.material.snackbar.Snackbar

class RectangleActivity : AppCompatActivity(), RectangleContract.View, RectangleViewClickInterface {

    private lateinit var binding: ActivityRectangleBinding

    private lateinit var rectangleFactory: RectangleFactory

    override lateinit var presenter: RectangleContract.Presenter

    private var RECTANGLE_WIDTH = 0
    private var RECTANGLE_HEIGHT = 0

    private lateinit var SELECTED_RECTANGLE_ID: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRectangleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RECTANGLE_WIDTH = ConvertDPtoPX(this, 150)
        RECTANGLE_HEIGHT = ConvertDPtoPX(this, 120)
        Log.d("AppTest", "${this.window.decorView.height}")
        Log.d("AppTest", "MainActivity/ ${RECTANGLE_WIDTH}, ${RECTANGLE_HEIGHT}")


        binding.rectangleDrawingView?.let {
            it.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val width = binding.rectangleDrawingView!!.width - RECTANGLE_WIDTH
                    val height = binding.rectangleDrawingView!!.height - RECTANGLE_HEIGHT

                    rectangleFactory = RectangleFactory(width, height, getString(R.string.TestText).split(' '))
                    Log.d("AppTest", "width:$width, height:$height")

                    initPresenter(rectangleFactory)

                    // 리스너 해제
                    it.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }

        // 커스텀뷰 에서 터치이벤트 전달 & 테두리 페인트 초기화
        binding.rectangleDrawingView?.let {
            it.drawingViewInit()
            it.setClickListener(this)
        }

        setBtnMakeNormalRectangle()
        setBtnMakeTextRectangle()
        setTransparencySeekBar()
        setBtnGallery()

        setPointXchange()
        setPointYchange()
        setSizeWchange()
        setSizeHchange()
    }

    // presenter 초기화 및 livedata 옵저버 등록
    fun initPresenter(rectangleFactory: RectangleFactory) {
        presenter = RectanglePresenter(RectangleRepositoryImpl(Plane(rectangleFactory)), this)
        presenter.liveNormalRectangleList.observe(this) {
            showRectangle(it)
        }
    }

    fun setBtnMakeNormalRectangle() {
        binding.btnGenerateRectangle?.setOnClickListener {
            presenter.start()
        }
    }

    fun setBtnMakeTextRectangle(){
        binding.btnGenerateTextRectangle?.setOnClickListener {
            presenter.addTextRectangle()
        }
    }

    fun setPointXchange(){
        binding.ivPointXup?.let {
            it.setOnClickListener {
                presenter.updatePointX(1, SELECTED_RECTANGLE_ID)
            }
        }
        binding.ivPointXdown?.let {
            it.setOnClickListener {
                presenter.updatePointX(-1, SELECTED_RECTANGLE_ID)
            }
        }
    }

    fun setPointYchange(){
        binding.ivPointYup?.let {
            it.setOnClickListener {
                presenter.updatePointY(1, SELECTED_RECTANGLE_ID)
            }
        }
        binding.ivPointYdown?.let {
            it.setOnClickListener {
                presenter.updatePointY(-1, SELECTED_RECTANGLE_ID)
            }
        }
    }

    fun setSizeWchange(){
        binding.ivSizeWup?.let {
            it.setOnClickListener {
                presenter.updateSizeWidth(1, SELECTED_RECTANGLE_ID)
            }
        }
        binding.ivSizeWdown?.let {
            it.setOnClickListener {
                presenter.updateSizeWidth(-1, SELECTED_RECTANGLE_ID)
            }
        }
    }

    fun setSizeHchange(){
        binding.ivSizeHup?.let {
            it.setOnClickListener {
                presenter.updateSizeHeight(1, SELECTED_RECTANGLE_ID)
            }
        }
        binding.ivSizeHdown?.let {
            it.setOnClickListener {
                presenter.updateSizeHeight(-1, SELECTED_RECTANGLE_ID)
            }
        }
    }

    // 만든 사각형 커스텀 뷰에 추가로 그리기
    override fun showRectangle(updatedNormalRectangleList: MutableList<BaseRectangle>) {
        Log.d("AppTest", "update rectangle list size : ${updatedNormalRectangleList.size}")
        binding.rectangleDrawingView?.let {
            it.drawRectangle(updatedNormalRectangleList)
        }
    }

    override fun showPointX(newX: Int) {
        binding.tvPointX?.let{
            it.text = newX.toString()
        }
    }

    override fun showPointY(newY: Int) {
        binding.tvPointY?.let{
            it.text = newY.toString()
        }
    }

    override fun showSizeW(newW: Int) {
        binding.tvSizeW?.let{
            it.text = newW.toString()
        }
    }

    override fun showSizeH(newH: Int) {
        binding.tvSizeH?.let{
            it.text = newH.toString()
        }
    }


    ///////////////
    override fun clickDrawingView(color: String, alpha: Int, selected: Boolean, id: String, x: Int, y: Int, width: Int, height: Int) { // 커스텀 뷰 터치 시 호출
        if (selected) {
            binding.constraintLayoutControl?.let {
                it.visibility = View.VISIBLE
            }
            binding.tvBackgroundColor?.let {
                it.text = color
            }
            binding.seekBarTransparency?.let {
                it.progress = alpha
            }
            binding.tvPointX?.let {
                it.text = x.toString()
            }
            binding.tvPointY?.let {
                it.text = y.toString()
            }
            binding.tvSizeW?.let {
                it.text = width.toString()
            }
            binding.tvSizeH?.let {
                it.text = height.toString()
            }

            // id 값을 활용해 현재 선택된 사각형 투명도 데이터 업데이트 후 뷰에 반영시키기
            SELECTED_RECTANGLE_ID = id
        } else {
            binding.constraintLayoutControl?.let {
                it.visibility = View.INVISIBLE
            }
        }
    }

    override fun updateSelectedRectanglePoint(id: String, newX: Int, newY: Int) {
        SELECTED_RECTANGLE_ID = id
        presenter.updateSelectedRectanglePoint(id, newX, newY)
    }

    override fun updatePointText(x: Int, y: Int) {
        binding.tvPointX?.let {
            it.text = x.toString()
        }
        binding.tvPointY?.let {
            it.text = y.toString()
        }
    }

    fun ConvertDPtoPX(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    fun setTransparencySeekBar() {
        binding.seekBarTransparency?.let {
            it.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                }

                override fun onStartTrackingTouch(seekbar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekbar: SeekBar) {
                    presenter.updateTransparency(SELECTED_RECTANGLE_ID, seekbar.progress)
                }
            })
        }
    }


    fun setBtnGallery() {
        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    Log.d("AppTest", "RectangleActivity/ data : ${it.data?.data}")
                    // uri 전달하기!!!!
                    presenter.addImageRectangle(it.data?.data)
                } else {
                    Snackbar.make(binding.root, "사진 불러오기 취소", Snackbar.LENGTH_SHORT).show()
                }
            }

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    getContent.launch(Intent.createChooser(intent, "Gallery"))
                } else {
                    Snackbar.make(binding.root, "갤러리 접근 권한이 승인되지 않았습니다", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

        binding.btnOpenGallery?.let {
            it.setOnClickListener {
                // 갤러리 열고 uri 가져오기 구현하기
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

}