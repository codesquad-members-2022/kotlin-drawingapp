package com.codesquard.kotlin_drawingapp.itemlistview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.google.android.material.textview.MaterialTextView

class Item(
    private val rectId: String,
    private val rectType: String,
    private val icon: Drawable,
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val textView = MaterialTextView(context)

    init {
        setDefaultLayoutAttr()
        setDefaultTextView()
        this.addView(textView)
    }

    private fun setDefaultLayoutAttr() {
    }

    private fun setDefaultTextView() {
        val defaultAttr = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        defaultAttr.marginStart = dpToPx(10f)
        textView.text = rectType
        textView.textSize = dpToPx(8f).toFloat()
        textView.layoutParams = defaultAttr
        textView.setPadding(0, 0, dpToPx(10f), 0)
        textView.setCompoundDrawables(icon, null, null, null)
        textView.compoundDrawablePadding = dpToPx(20f)
        textView.gravity = Gravity.CENTER_VERTICAL
    }

    private fun dpToPx(dp: Float): Int {
        val resources = this.resources
        val metrics = resources.displayMetrics
        val density = metrics.density
        return (dp * density).toInt()
    }

    fun setOnSelectedItemBackgroundColor() {
        this.setBackgroundColor(Color.parseColor("#9E90EC"))
    }

    fun setOnUnSelectedItemBackgroundColor() {
        this.setBackgroundColor(Color.parseColor("#E9E6FA"))
    }

    fun getRectId() = rectId
}