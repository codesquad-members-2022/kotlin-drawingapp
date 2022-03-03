package com.codesquad_han.kotlin_drawingapp.rectangle

import android.widget.ImageView
import com.codesquad_han.kotlin_drawingapp.BasePresenter
import com.codesquad_han.kotlin_drawingapp.BaseView
import com.codesquad_han.kotlin_drawingapp.model.Rectangle
import com.codesquad_han.kotlin_drawingapp.model.RectangleImageviewData

interface RectangleContract {

    interface View : BaseView<Presenter> {
        fun showRectangle(updatedRectangleList : MutableList<Rectangle>)   // 추가된 사각형을 포함한 리스트 받아와서 새로 그리기

        fun showSelectedRectangle()
    }

    interface Presenter : BasePresenter {
        fun addRectangle() // 사각형 생성

        fun getRectangleList()
    }
}