package com.example.kotlindrawingapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.kotlindrawingapp.R

class LayerCustomView(
    context: Context,
    name: String,
    imageDrawable: Int,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        this.background = ContextCompat.getDrawable(context, R.drawable.custom_layer)
        LayoutInflater.from(context).inflate(R.layout.customview_layer, this, true)

        val text: TextView = findViewById(R.id.tv_name)
        val image: ImageView = findViewById(R.id.iv_figure)
        text.text = name
        image.setImageResource(imageDrawable)
    }


}