package com.codesquard.kotlin_drawingapp.itemlistview

import android.widget.LinearLayout

interface Command {

    fun runFunction(itemList: ArrayList<Item>, selectedItem: Item, itemLayout: LinearLayout)

    fun changeItemOrderInList(itemList: ArrayList<Item>, selectedItem: Item, index: Int) {
        itemList.remove(selectedItem)
        itemList.add(index, selectedItem)
    }

    fun changeItemOrderInLayout(itemLayout: LinearLayout, selectedItem: Item, index: Int) {
        itemLayout.removeView(selectedItem)
        itemLayout.addView(selectedItem, index)
    }

}

class Forward : Command {

    override fun runFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val index = itemList.indexOf(selectedItem)
        if (index < itemList.size - 1) {
            val changedIndex = index + 1
            changeItemOrderInList(itemList, selectedItem, changedIndex)
            changeItemOrderInLayout(itemLayout, selectedItem, itemList.size - changedIndex)
        }
    }
}

class Backward : Command {

    override fun runFunction(
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

}

class Front : Command {

    override fun runFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val changedIndex = itemList.size - 1
        changeItemOrderInList(itemList, selectedItem, changedIndex)
        changeItemOrderInLayout(itemLayout, selectedItem, 1)

    }

}

class Back : Command {

    override fun runFunction(
        itemList: ArrayList<Item>,
        selectedItem: Item,
        itemLayout: LinearLayout
    ) {
        val changedIndex = 0
        changeItemOrderInList(itemList, selectedItem, changedIndex)
        changeItemOrderInLayout(itemLayout, selectedItem, itemList.size)
    }
}


