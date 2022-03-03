package com.example.kotlin_drawingapp

class SquareModelFactory {
    private val squareModel = SquareModel()
    private val firstIdSeparate = squareModel.createSeparatedId()
    private val secondIdSeparate = squareModel.createSeparatedId()
    private val thirdIdSeparate = squareModel.createSeparatedId()
    private val arrayOfIdSeparate = arrayOf(firstIdSeparate ,secondIdSeparate ,thirdIdSeparate)
    private val idResult = arrayOfIdSeparate.joinToString("-")

    fun createSquareObject(): Array<String>  {
        return arrayOf(
            idResult,
            squareModel.createSize()[0],
            squareModel.createSize()[1],
            squareModel.createPoint()[0],
            squareModel.createPoint()[1],
            squareModel.createRGB()[0],
            squareModel.createRGB()[1],
            squareModel.createRGB()[2],
            squareModel.createAlpha())
    }

    fun createLogMessage(): String {
        return "(${createSquareObject()[0]}), " +
                "X: ${createSquareObject()[1]}, " +
                "Y: ${createSquareObject()[2]}, " +
                "W ${createSquareObject()[3]}, " +
                "H: ${createSquareObject()[4]}, " +
                "R: ${createSquareObject()[5]}, " +
                "G: ${createSquareObject()[6]}, " +
                "B: ${createSquareObject()[7]}, " +
                "Alpha: ${createSquareObject()[8]} "
    }
}