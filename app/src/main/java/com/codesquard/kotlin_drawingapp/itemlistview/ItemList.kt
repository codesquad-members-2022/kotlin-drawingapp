package com.codesquard.kotlin_drawingapp.itemlistview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

class ItemList(private val listener: ItemListListener, private val itemLayout: LinearLayout) {

    private val itemList = arrayListOf<Item>()

    fun addNewItem(rectId: String, rectType: String, order: Int, icon: Drawable, context: Context) {
        val newItem = Item(rectId, rectType, order, icon, context)
        newItem.setOnCustomClickListener {
            onItemLongClick(newItem)
        }
        itemList.add(newItem)
        itemLayout.addView((newItem), 1)
    }

    private fun removeItem(index: Int) {
        itemLayout.removeView(itemList[index])
        itemList.removeAt(index)
    }

    private fun changePosition(index: Int) {
        val temp = itemList[index]
        removeItem(index)
        itemList.add(index, temp)
        itemLayout.addView(temp, itemList.size - index - 1)
    }

    private fun onItemClick(item: Item) {
        Log.d("클릭", "됨?")
        unSelectItem()
        selectItem(item)
        listener.onSelectItem(item.getRectId())
    }

    private fun onItemLongClick(item: Item) {
        Log.d("롱클릭", "됨?")
        val index = getIndex(item)
        changePosition(index)
    }

    /*@SuppressLint("ClickableViewAccessibility")
    private fun onItemClickListener(item: Item) {
        item.setOnTouchListener { _, motionEvent ->
            val longPressTime = 2000L
            if (item.isPressed && motionEvent.action == MotionEvent.ACTION_UP) {
                val eventDuration = motionEvent.eventTime - motionEvent.downTime
                if (eventDuration > longPressTime) {
                    onItemLongClick(item)
                } else {
                    onItemClick(item)
                }
            }
            false
        }
    }*/

    private fun Item.setOnCustomClickListener(listener: () -> Unit) {
        setOnTouchListener(object : View.OnTouchListener {

            private val longClickDuration = 2000L
            private val handler = Handler(Looper.getMainLooper())

            override fun onTouch(v: View, event: MotionEvent?): Boolean {
                onItemClick(v as Item)
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed({ listener.invoke() }, longClickDuration)
                } else if (event?.action == MotionEvent.ACTION_UP) {
                    handler.removeCallbacksAndMessages(null)
                }
                return true
            }
        })
    }

    private fun getIndex(orderedItem: Item): Int {
        return itemList.indexOf(orderedItem)
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