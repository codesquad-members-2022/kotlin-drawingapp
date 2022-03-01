package com.example.myapplication.Class

class Rectangle {
    private val numberFactory = NumberFactory()

    val size: Size = Size(150, 120)
    val id: String = numberFactory.getIdValue()
    val point: Point =
        Point(numberFactory.getRandomPointNumber(), numberFactory.getRandomPointNumber())
    val color: Color = Color(
        numberFactory.getRandomRgbNumber(),
        numberFactory.getRandomRgbNumber(),
        numberFactory.getRandomRgbNumber()
    )
    val alpha: Int = numberFactory.getRandomAlphaNumber()
}