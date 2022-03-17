package com.example.kotlindrawingapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.kotlindrawingapp.R

class LayerCustomView(
    context: Context,
    name: String,
    imageDrawable: Int,
    listener: PopUpListener,
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

        this.setOnLongClickListener {
            val popupMenu = PopupMenu(context, it)
            popupMenu.menuInflater.inflate(R.menu.popup, popupMenu.menu)

            //TODO - 메뉴 별 기능추가하기
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.popup_send_to_back -> {
                        listener.sendToBack(this)
                    }
                    R.id.popup_send_back -> {
                        listener.sendBack(this)
                    }
                    R.id.popup_send_front -> {
                        listener.sendFront(this)
                    }
                    R.id.popup_send_to_front -> {
                        listener.sendToFront(this)
                    }
                }
                true
            }
            popupMenu.show()
            true
        }
    }


}