package com.example.kotlindrawingapp.view

import com.example.kotlindrawingapp.domain.figure.Figure

interface Movable {
    fun move(x: Float, y: Float, figure: Figure, selectedFigure: Figure)

    fun move(x: Float, y: Float)
}