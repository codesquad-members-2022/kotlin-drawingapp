package com.codesquard.kotlin_drawingapp.itemlistview

interface ItemListListener {

    fun onSelectItem(rectId: String)

    fun onChangeRectOrder(command: Command)

}