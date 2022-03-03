package com.example.kotlindrawingapp.presenter

interface Contract {

    interface View {
        fun updateBoard(color: String, alpha: Int)
    }

    interface Presenter {
        fun drawRectangle()

        fun loadBoard(index: Int)
    }
}