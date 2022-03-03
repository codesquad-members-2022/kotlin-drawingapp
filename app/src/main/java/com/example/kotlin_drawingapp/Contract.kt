package com.example.kotlin_drawingapp

import android.graphics.Rect

interface Contract {
    interface View {
        fun showInfo(flag: Boolean, color: Int)
    }
    interface Presenter {
        fun makeRect(left: Int, right: Int, top: Int, bottom: Int): Rect {
            return Rect(left, right, top, bottom)
        }
    }
}