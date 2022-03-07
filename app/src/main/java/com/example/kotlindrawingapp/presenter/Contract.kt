package com.example.kotlindrawingapp.presenter

interface Contract {

    interface View {

    }

    interface Presenter {
        fun loadRectangle()

        fun editRectangleAlpha(alpha: Int)
    }
}