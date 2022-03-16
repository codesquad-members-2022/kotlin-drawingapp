package com.example.kotlindrawingapp.view

interface Movable {
    fun move(x: Float, y: Float)

    fun moveTemporary(x: Float, y: Float)
}