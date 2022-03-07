package Presenter

import Data.Rectangle

interface GenerateRectangle {
    fun makeRandomRect(): Rectangle
    fun makeRectangleStrokesList(rectangle: Rectangle): Rectangle
}