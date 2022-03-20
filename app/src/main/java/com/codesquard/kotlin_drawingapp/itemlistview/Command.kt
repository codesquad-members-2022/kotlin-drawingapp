package com.codesquard.kotlin_drawingapp.itemlistview

import android.widget.LinearLayout
import com.codesquard.kotlin_drawingapp.model.Rectangle

interface Command {

    fun runChangeLayoutFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    )

    fun runChangeRectangleFunction(rectList: ArrayList<Rectangle>, selectedRect: Rectangle)

    fun changeItemOrderInList(itemList: ArrayList<Item>, selectedItem: Item, index: Int) {
        itemList.remove(selectedItem)
        itemList.add(index, selectedItem)
    }

    fun changeRectOrderInList(rectList: ArrayList<Rectangle>, selectedRect: Rectangle, index: Int) {
        rectList.remove(selectedRect)
        rectList.add(index, selectedRect)
    }

    fun changeItemOrderInLayout(itemLayout: LinearLayout, selectedItem: Item, index: Int) {
        itemLayout.removeView(selectedItem)
        itemLayout.addView(selectedItem, index)
    }

}







