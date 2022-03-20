package com.codesquard.kotlin_drawingapp.itemlistview

import android.widget.LinearLayout
import com.codesquard.kotlin_drawingapp.model.Rectangle

class Backward : Command {

    override fun runChangeLayoutFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val index = itemList.indexOf(selectedItem)
        if (index > 0) {
            val changedIndex = index - 1
            changeItemOrderInList(itemList, selectedItem, changedIndex)
            changeItemOrderInLayout(itemLayout, selectedItem, itemList.size - changedIndex)
        }
    }

    override fun runChangeRectangleFunction(
        rectList: ArrayList<Rectangle>,
        selectedRect: Rectangle
    ) {
        val index = rectList.indexOf(selectedRect)
        if (index > 0) {
            val changedIndex = index - 1
            changeRectOrderInList(rectList, selectedRect, changedIndex)
        }
    }
}