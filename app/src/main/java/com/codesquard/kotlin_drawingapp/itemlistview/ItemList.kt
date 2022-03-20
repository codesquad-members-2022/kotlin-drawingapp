package com.codesquard.kotlin_drawingapp.itemlistview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class ItemList(private val listener: ItemListListener, private val itemLayout: LinearLayout) {

    private val itemList = arrayListOf<Item>()

    private var selectedItem: Item? = null

    fun addNewItem(rectId: String, rectType: String, order: Int, icon: Drawable, context: Context) {
        val newItem = Item(rectId, rectType, order, icon, context)
        itemList.add(newItem)
        itemLayout.addView((newItem), 1)
        newItem.setOnCustomClickListener {
            onItemLongClick(context, itemList, newItem, itemLayout)
        }
    }

    private fun onItemClick(item: Item) {
        selectItem(item)
        listener.onSelectItem(item.getRectId())
    }

    private fun onItemLongClick(
        context: Context,
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        showDialog(context, itemList, selectedItem, itemLayout)
    }

    private fun showDialog(
        context: Context,
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val singleItems = arrayOf("맨 앞으로 보내기", "앞으로 보내기", "뒤로 보내기", "맨 뒤로 보내기")
        var checkedItem = 0

        MaterialAlertDialogBuilder(context)
            .setTitle("사각")
            .setNegativeButton("취소") { dialog, which ->
                Snackbar.make(itemLayout, "기능 실행을 취소하였습니다", Snackbar.LENGTH_SHORT).show()
            }.setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                checkedItem = which
            }.setPositiveButton("실행") { dialog, which ->
                Snackbar.make(
                    itemLayout,
                    "${singleItems[checkedItem]}를 실행했습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
                var command: Command? = null
                when (checkedItem) {
                    0 -> command = Front()
                    1 -> command = Forward()
                    2 -> command = Backward()
                    3 -> command = Back()
                }
                command?.runFunction(itemList, selectedItem, itemLayout)
            }
            .show()
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