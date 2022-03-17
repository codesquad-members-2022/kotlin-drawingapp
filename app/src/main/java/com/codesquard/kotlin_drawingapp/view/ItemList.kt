package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.LinearLayout

class ItemList(private val listener: ItemListListener, private val itemLayout: LinearLayout) {

    private val itemList = arrayListOf<Item>()

    fun addNewItem(rectId: String, rectType: String, icon: Drawable, context: Context) {
        val newItem = Item(rectId, rectType, icon, context)
        setOnItemClickListener(newItem)
        itemList.add(newItem)
        itemLayout.addView((newItem), 1)
    }

    private fun removeItem(index: Int) {
        itemLayout.removeView(itemList[index])
    }

    private fun setOnItemClickListener(item: Item) {
        item.setOnClickListener {
            unSelectItem()
            selectItem(item)
            listener.onSelectItem(item.getRectId())
        }
    }

    private fun selectItem(item: Item) {
        item.isSelected = true
        item.setOnSelectedItemBackgroundColor()
    }

    fun selectItem(rectIndex: Int) {
        val item = itemList[rectIndex]
        item.isSelected = true
        item.setOnSelectedItemBackgroundColor()
    }

    fun unSelectItem() {
        itemList.forEach {
            it.isSelected = false
            it.setOnUnSelectedItemBackgroundColor()
        }
    }

}