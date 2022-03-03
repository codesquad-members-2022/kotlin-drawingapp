package com.example.kotlin_drawingapp

interface SquareModelFrame {
    fun createSeparatedId(): String
    fun createSize(): Array<String>
    fun createPoint(): Array<String>
    fun createRGB(): Array<String>
    fun createAlpha(): String
}