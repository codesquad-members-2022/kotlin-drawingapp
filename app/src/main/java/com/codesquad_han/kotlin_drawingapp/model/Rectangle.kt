package com.codesquad_han.kotlin_drawingapp.model

import android.net.Uri


class Rectangle(
    id: String,
    point: Point,
    size: Size,
    backgroundColor: BackgroundColor,
    transparency: Transparency,
    imageUri: Uri?
) {
    val id = id
    val point = point
    val size = size
    val backgroundColor = backgroundColor
    val transparency = transparency
    var imageUri = imageUri

    override fun toString(): String {
        return "($id), X:${point.x},Y:${point.y}, W:${size.width},H:${size.height}, R:${backgroundColor.r},G:${backgroundColor.g},${backgroundColor.b}, " +
                "Alpha:${transparency.transparency}, Image Uri : ${imageUri} ."
    }


}