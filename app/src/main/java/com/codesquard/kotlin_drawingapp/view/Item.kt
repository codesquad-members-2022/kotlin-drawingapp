package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.google.android.material.textview.MaterialTextView

class Item(
    val icon: Drawable,
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
        val defaultAttr = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        defaultAttr.marginStart = dpToPx(10f)
        this.layoutParams = defaultAttr
    }

    private fun setDefaultTextView() {
        textView.text = "실험"
        textView.textSize = dpToPx(8f).toFloat()
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
}