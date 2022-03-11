package com.example.kotlin_drawingapp.model

import android.graphics.Point
import android.util.Size
import kotlin.random.Random

open class Factory {
    companion object {
        fun randomId(): String {
            return "${Random.nextInt(100, 999)}-" +
                    "${Random.nextInt(100, 999)}-" +
                    "${Random.nextInt(100, 999)}"
        }

        fun randomSize(): Size {
            return Size(Random.nextInt(1, 500), Random.nextInt(1, 500))
        }

        fun randomPoint(): Point {
            return Point(Random.nextInt(1, 500), Random.nextInt(1, 500))
        }

        fun randomColor(): Color {
            return Color(
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
        }

        fun randomAlpha(): Int {
            return Random.nextInt(1, 10)
        }
    }
}
