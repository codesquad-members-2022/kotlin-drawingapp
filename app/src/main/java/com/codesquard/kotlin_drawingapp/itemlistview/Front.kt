package com.codesquard.kotlin_drawingapp.itemlistview

import android.widget.LinearLayout
import com.codesquard.kotlin_drawingapp.model.Rectangle

class Front : Command {

    override fun runChangeLayoutFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val changedIndex = itemList.size - 1
        changeItemOrderInList(itemList, selectedItem, changedIndex)
        changeItemOrderInLayout(itemLayout, selectedItem, 1)
    }

    override fun runChangeRectangleFunction(
        rectList: ArrayList<Rectangle>,
        selectedRect: Rectangle
    ) {
        val changedIndex = rectList.size - 1
        changeRectOrderInList(rectList, selectedRect, changedIndex)
    }

}