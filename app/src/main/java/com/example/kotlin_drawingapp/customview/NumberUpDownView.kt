package com.example.kotlin_drawingapp.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlin_drawingapp.R

class NumberUpDownView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var textValue: TextView
    private var textTitle: TextView
    private var btnUp: ImageButton
    private var btnDown: ImageButton

    interface OnChangedValueListener {
        fun onChaged(value: Int)
    }

    private var setOnChangedValueListener: OnChangedValueListener? = null
    fun setOnChangedValueListener(listener: OnChangedValueListener) {
        setOnChangedValueListener = listener
    }

    init {
        inflate(context, R.layout.numberupdown_view, this)
        textTitle = findViewById(R.id.titleValue)
        textValue = findViewById(R.id.textValue)
        btnUp = findViewById(R.id.btn_up)
        btnDown = findViewById(R.id.btn_down)

        btnUp.setOnClickListener {
            up()
        }

        btnDown.setOnClickListener {
            down()
        }

        getAttrs(attrs)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberUpDownView).apply {
            textTitle.text = getText(R.styleable.NumberUpDownView_titleValue)
            textValue.text = getInt(R.styleable.NumberUpDownView_textValue, 0).toString()
        }

        typedArray.recycle()
    }

    fun setText(value: String) {
        textValue.text = value
    }

    fun getText(): Int {
        if (textValue.text.isEmpty()) {
            return 0
        }
        setOnChangedValueListener?.onChaged(textValue.text.toString().toInt())
        return textValue.text.toString().toInt()
    }

    fun setTitle(value: String) {
        textTitle.text = value
    }

    fun getTitle(): String {
        return textTitle.text.toString()
    }

    private fun up(number: Int = 1) {
        val num = getText() + number
        setText(num.toString())
    }

    private fun down(number: Int = 1) {
        val num = getText() - number
        setText(num.toString())
    }
}