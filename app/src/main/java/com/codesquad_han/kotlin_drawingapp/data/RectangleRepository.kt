package com.codesquad_han.kotlin_drawingapp.data

import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle

interface RectangleRepository {

    fun addNormalRectangle()

    fun addTextRectangle()

    fun addImageRectangle(imageUri: Uri?)

    fun getRectangleList() : MutableList<BaseRectangle>

    fun updateTransparency(id: String, transparency: Int)

    fun updateImageUri(id: String, imageUri: Uri?)

    fun updateSelctedRectanglePoint(id: String, newX: Int, newY: Int)

    fun updatePointX(value: Int, id: String) : Int

    fun updatePointY(value: Int, id: String) : Int

    fun updateSizeWidth(value: Int, id: String) : Int

    fun updateSizeHeight(value: Int, id: String) : Int

    fun updateSelectedState(id: String, isSelectedExist: Boolean)
}