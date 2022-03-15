package com.codesquad_han.kotlin_drawingapp.model

import android.content.Context
import android.graphics.Canvas
import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.property.BackgroundColor
import com.codesquad_han.kotlin_drawingapp.model.property.Point
import com.codesquad_han.kotlin_drawingapp.model.property.Size
import com.codesquad_han.kotlin_drawingapp.model.property.Transparency

interface BaseRectangle{
    val id: String
    var point: Point
    var size: Size
    var backgroundColor: BackgroundColor
    var transparency: Transparency
    var imageUri: Uri?

    fun drawRectangle(context: Context, canvas: Canvas)

    fun getTempRectangle() : BaseRectangle
}