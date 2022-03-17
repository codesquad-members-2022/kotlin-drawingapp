package view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.codesquad.kotlin_drawingapp.R


class CustomRectInfoView(
    context: Context,
    name: String,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    var rectId=""
    val photoId=""
    init {
        LayoutInflater.from(context).inflate(R.layout.customview_layer, this, true)
        val text: TextView = findViewById(R.id.tv_name)
        val image: ImageView = findViewById(R.id.iv_figure)
        text.text = name
        this.setBackgroundColor(Color.WHITE)
    }

    fun selected(){
        this.setBackgroundColor(Color.GRAY)
    }

    fun resetColor(){
        this.setBackgroundColor(Color.WHITE)
    }

}