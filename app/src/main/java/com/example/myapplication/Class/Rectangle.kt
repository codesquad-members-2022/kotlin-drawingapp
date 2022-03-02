package com.example.myapplication.Class

class Rectangle {
    private val numberFactory = NumberFactory()

    val size: Size = Size(150, 120)
    val id: String = numberFactory.getIdValue()
    val point: Point =
        Point(numberFactory.getRandomXPointNumber(), numberFactory.getRandomYPointNumber())
    val color: Color = Color(
        numberFactory.getRandomRgbNumber(),
        numberFactory.getRandomRgbNumber(),
        numberFactory.getRandomRgbNumber()
    )
    val alpha: Float = numberFactory.getRandomAlphaFloat()
    fun getNewColor():Color{
        return Color(numberFactory.getRandomRgbNumber(),
            numberFactory.getRandomRgbNumber(),
            numberFactory.getRandomRgbNumber()
        )
    }
}