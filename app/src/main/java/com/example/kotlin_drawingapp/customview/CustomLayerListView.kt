package com.example.kotlin_drawingapp.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.kotlin_drawingapp.R
import com.example.kotlin_drawingapp.model.draw.DrawObject

class CustomLayerListView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    interface OnClickListItemListener {
        fun onClick(drawObject: DrawObject)
    }
    private var clickListItemListener: OnClickListItemListener? = null
    fun setOnClickListItemListener(listener: OnClickListItemListener) {
        clickListItemListener = listener
    }

    private var drawObjectList = listOf<DrawObject>()
    fun updateDrawObjectList(drawObject: List<DrawObject>) {
        drawObjectList = drawObject

        removeAllViews()
        drawObjectList.forEach {
            addView(createCustomLayerTextView(it))
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun createCustomLayerTextView(drawObject: DrawObject): CustomLayerTextView {
        val textView = CustomLayerTextView(context, drawObject)
        textView.setOnClickLayerTextViewListener(object : CustomLayerTextView.OnClickLayerTextViewListener {
            override fun onClick(drawObject: DrawObject) {
                clickListItemListener?.onClick(drawObject)
            }
        })
        return textView
    }
}