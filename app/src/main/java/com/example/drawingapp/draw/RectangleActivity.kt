package com.example.drawingapp.draw

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.drawingapp.Contract
import com.example.drawingapp.R
import com.example.drawingapp.data.RectangleRepository
import com.example.drawingapp.data.attribute.Picture
import com.example.drawingapp.data.attribute.Rectangle
import com.example.drawingapp.util.showSnackBar
import com.google.android.material.slider.Slider
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class RectangleActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Contract.Presenter
    private lateinit var draw: RectangleDraw
    private lateinit var slider: Slider
    private val rectangleColor = mutableListOf<String>()

    @SuppressLint("Range")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
        draw = findViewById(R.id.draw_rectangle)
        presenter = RectanglePresenter(this, RectangleRepository())
//        presenter.onClickLog()
//        presenter.onClickLog()
//        presenter.onClickLog()
//        presenter.onClickLog()
        val getContent = selectPicture()
        val drawButton: Button = findViewById(R.id.btn_make_rectangle)
        val picButton: Button = findViewById(R.id.add_pic_btn)
        picButton.text = "사진 추가"
        drawButton.text = "사각형"

        picButton.setOnClickListener {
            getContent.launch("image/*")
        }

        drawButton.setOnClickListener() {
//            presenter.onClickLog()
            presenter.setPlane()
            presenter.drawRectangle()
        }

        slider = findViewById(R.id.slider_invisible)

        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {

            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
                if (slider.value < 10F) {
                    slider.value = 10F
                }
                if (draw.getClickRectangle() == -1) {
                    findViewById<ConstraintLayout>(R.id.container).showSnackBar("선택된 사각형이 없습니다.")
                    slider.value = 1F
                    return
                }
                val alpha = slider.value / 10
                changeAlpha(draw.getClickRectangle(), alpha.toInt())
                setAlpha(draw.getClickRectangle(), alpha.toInt())
            }
        })
        val plane = presenter.plane()
        plane.list.observe(this) {
            draw.invalidate()
        }
    }

    private fun selectPicture() = registerForActivityResult(ActivityResultContracts.GetContent()) {
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, it))
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, it)
        }
        val scaleBitmap = Bitmap.createScaledBitmap(bitmap, 150, 120, true)
        presenter.setPlane(scaleBitmap)
        presenter.drawPicture()
    }

    override fun changeAlpha(index: Int, alpha: Int) {
        draw.changeAlpha(index, alpha)
    }

    override fun setAlpha(index: Int, value: Int) {
        presenter.setAlpha(index, value)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val count = draw.getClickRectangle()
        setColorText(count)
        val pointF = PointF(event!!.x, event.y - 176F)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                onTouchRectangle(pointF)
            }
            MotionEvent.ACTION_MOVE -> {
                val length = event.historySize

                if (length != 0 && draw.getClickRectangle() != -1) {

                    val x = pointF.x - event.getHistoricalX(0)
                    val y = pointF.y - (event.getHistoricalY(0) - 176)
                    dragViewFactory(x.toInt(), y.toInt())
                }
            }
            MotionEvent.ACTION_UP -> {
                draw.performClick()
            }
        }
        draw.invalidate()
        return true
    }

    private fun dragViewFactory(x: Int, y: Int) = when (rectangleColor[draw.getClickRectangle()] == "image") {
        true -> setPicture(x, y)
        else -> setRectXY(x, y)
    }

    private fun setRectXY(x: Int, y: Int) {
        val typeList = draw.setXY(x, y)
        presenter.setPlaneXY(typeList)
    }

    private fun setPicture(x: Int, y: Int) {
        draw.setXY(x, y)
    }

    override fun onTouchRectangle(pointF: PointF) {
        val count = draw.findRectangle(pointF)
        if (count == -1) {
            draw.setStrokeClean()
            presenter.resetClick()
            return
        }
        presenter.setClick(draw.getClickRectangle())
        slider.value =
            presenter.getAlpha(draw.getClickRectangle())?.times(10F) ?: throw IllegalArgumentException("stub!")
    }


    @SuppressLint("SetTextI18n")
    override fun setColorText(count: Int) = when (count != -1) {
        true -> {
            val color: Button = findViewById(R.id.tv_background_color)
            color.text = "#${rectangleColor[count]}"
        }

        false -> {
            val color: Button = findViewById(R.id.tv_background_color)
            color.text = ""
        }
    }

    override fun getDrawMessage(message: String) {
        Logger.i(message)
    }

    override fun drawRectangle(rectangle: Rectangle) {
        draw.setDrawType(rectangle)
        setPaints(rectangle)
        rectangleColor.add(setColor(rectangle.color))
    }

    override fun drawPicture(picture: Picture) {
        draw.setDrawType(picture)
        setPaints(picture)
        rectangleColor.add("image")

    }

    private fun setPaints(rectangle: Rectangle) {
        val paint = Paint()

        paint.color = Color.argb(
            rectangle.alpha * 25,
            rectangle.color.red,
            rectangle.color.green,
            rectangle.color.blue
        )

        paint.style = Paint.Style.FILL
        draw.setPaints(paint)
    }

    private fun setPaints(picture: Picture) {
        val paint = Paint()

        paint.alpha = picture.alpha * 25

        draw.setPaints(paint)
    }

    private fun setColor(color: com.example.drawingapp.data.attribute.Color) =
        color.red.toString(16) +
                color.blue.toString(16) +
                color.green.toString(16)

}
