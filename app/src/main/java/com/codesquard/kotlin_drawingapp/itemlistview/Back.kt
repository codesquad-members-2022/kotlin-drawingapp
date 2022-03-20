package com.codesquard.kotlin_drawingapp.itemlistview

import android.widget.LinearLayout
import com.codesquard.kotlin_drawingapp.model.Rectangle

class Back : Command {

    override fun runChangeLayoutFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val changedIndex = 0
        changeItemOrderInList(itemList, selectedItem, changedIndex)
        changeItemOrderInLayout(itemLayout, selectedItem, itemList.size)
    }

    override fun runChangeRectangleFunction(
        rectList: ArrayList<Rectangle>,
        selectedRect: Rectangle
    ) {
        val changedIndex = 0
        changeRectOrderInList(rectList, selectedRect, changedIndex)
    }

}