package com.example.kotlin_drawingapp.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_drawingapp.R
import com.example.kotlin_drawingapp.model.draw.DrawObject

class CustomLayerTextView private constructor(context: Context)
    : androidx.appcompat.widget.AppCompatTextView(context) {

    private lateinit var property: DrawObject
    constructor(context: Context, drawObject: DrawObject): this(context) {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80)
        when(drawObject) {
            is DrawObject.Rectangle -> {
                text = "사각형"
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_crop_square_24, 0, 0, 0)
            }
            is DrawObject.Image -> {
                text = "이미지"
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_image_24, 0, 0, 0)
            }
            is DrawObject.CustomText -> {
                text = "텍스트"
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_text_format_24, 0, 0, 0)
            }
        }
        background = ResourcesCompat.getDrawable(resources, R.drawable.layout_border, null)
        gravity = Gravity.CENTER_VERTICAL
        setPadding(4, 0, 0, 0)
        property = drawObject
    }

    interface OnClickLayerTextViewListener {
        fun onClick(drawObject: DrawObject)
    }
    private var clickLayerTextViewListener: OnClickLayerTextViewListener? = null
    fun setOnClickLayerTextViewListener(listener: OnClickLayerTextViewListener) {
        clickLayerTextViewListener = listener
    }

    override fun onDraw(canvas: Canvas?) {
        if (property.selected) {
            setBackgroundColor(Color.GRAY)
        }

        super.onDraw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                property.apply {
                    clickLayerTextViewListener?.onClick(this)
                }
            }
        }

        return super.onTouchEvent(event)
    }
}