package com.codesquad_han.kotlin_drawingapp.model

class RectangleFactory(x : Int, y:Int) {
    private var x = x
    private var y = y

    fun generateRectangle() : Rectangle{
        val id = getRandomString(3) + "-" + getRandomString(3) + "-" + getRandomString(3)

        return Rectangle(id, Point((0..x).random(), (0..y).random()), Size(150, 120),
                            BackgroundColor((0..255).random(), (0..255).random(), (0..255).random()), Transparency((1..10).random())
        )
    }


    fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}