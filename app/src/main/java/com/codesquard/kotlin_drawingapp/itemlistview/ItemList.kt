package com.codesquard.kotlin_drawingapp.itemlistview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

class ItemList(private val listener: ItemListListener, private val itemLayout: LinearLayout) {

    private val itemList = arrayListOf<Item>()

    private var selectedItem: Item? = null

    fun addNewItem(rectId: String, rectType: String, order: Int, icon: Drawable, context: Context) {
        val newItem = Item(rectId, rectType, order, icon, context)
        itemList.add(newItem)
        itemLayout.addView((newItem), 1)
        newItem.setOnCustomClickListener {
            onItemLongClick(newItem)
        }
    }

    private fun removeItem(selectedItem: Item) {
        itemLayout.removeView(selectedItem)
        itemList.remove(selectedItem)
    }

    private fun changePosition(index: Int) {
        val temp = itemList[index]
        val changedIndex = index + 1
        removeItem(temp)
        itemList.add(changedIndex, temp)
        itemLayout.addView(temp, itemList.size - changedIndex)
    }

    private fun onItemClick(item: Item) {
        unSelectItem()
        selectItem(item)
        listener.onSelectItem(item.getRectId())
    }

    private fun onItemLongClick(item: Item) {
        selectItem(item)
        val index = getSelectedItemIndex()
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

    private fun getSelectedItemIndex(): Int {
        var index = 0
        selectedItem?.let {
            index = itemList.indexOf(it)
        }
        return index
    }

    private fun selectItem(item: Item) {
        unSelectItem()
        selectedItem = item
        item.changeSelectedItemBackgroundColor()
    }

    fun selectItem(itemId: String) {
        unSelectItem()
        selectedItem.let {
            itemList.forEach {
                if (it.getRectId() == itemId) {
                    selectedItem = it
                    it.changeSelectedItemBackgroundColor()
                    return@let
                }
            }
        }
    }

    fun unSelectItem() {
        selectedItem?.let {
            it.isSelected = false
            it.changeUnSelectedItemBackgroundColor()
        }
    }

}