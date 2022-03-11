package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap

interface Contract {

    interface View {

    }

    interface Presenter {
        fun loadRectangle()

        fun loadPicture(bitmap: Bitmap)

        fun editRectangleAlpha(alpha: Int)
    }
}