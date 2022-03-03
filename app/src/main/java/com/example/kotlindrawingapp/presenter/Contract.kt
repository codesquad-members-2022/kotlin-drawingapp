package com.example.kotlindrawingapp.presenter

import com.example.kotlindrawingapp.CustomCanvas

interface Contract {

    interface View {
        fun updateBoard(color: String, alpha: Int)
    }

    interface Presenter {
        fun drawRectangle(canvas: CustomCanvas)

        fun selectRectangle(x: Float, y: Float)
    }
}