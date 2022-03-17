package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.LinearLayout

class ItemList(private val itemLayout: LinearLayout) {

    private val itemList = arrayListOf<Item>()

    fun addNewItem(newRectType: String, icon: Drawable, context: Context) {
        val newItem = Item(newRectType, icon, context)
        itemList.add(newItem)
        itemLayout.addView((newItem), 1)
    }

    private fun removeItem(index: Int) {
        itemLayout.removeView(itemList[index])
    }

    fun getItemIndex(): Int {
        return 0
    }

    fun changePosition(index: Int) {
        removeItem(index)
    }

}