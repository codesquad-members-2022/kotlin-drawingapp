package com.example.kotlin_drawingapp.model

import android.graphics.Point
import android.util.Size
import com.example.kotlin_drawingapp.shapemodel.Color
import com.example.kotlin_drawingapp.shapemodel.Rectangle
import kotlin.random.Random

class RectangleFactory {
    companion object {
        fun create(): Rectangle {
            return Rectangle(randomId(), randomSize(), randomPoint(), randomColor(), randomAlpha())
        }

        private fun randomId(): String {
            return "${Random.nextInt(100, 999)}-" +
                    "${Random.nextInt(100, 999)}-" +
                    "${Random.nextInt(100, 999)}"
        }

        private fun randomSize(): Size {
            return Size(Random.nextInt(1, 500), Random.nextInt(1, 500))
        }

        private fun randomPoint(): Point {
            return Point(Random.nextInt(1, 500), Random.nextInt(1, 500))
        }

        private fun randomColor(): Color {
            return Color(Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
        }

        private fun randomAlpha(): Int {
            return Random.nextInt(1, 10)
        }
    }
}