package com.codesquad_han.kotlin_drawingapp.rectangle

import android.widget.ImageView
import com.codesquad_han.kotlin_drawingapp.BasePresenter
import com.codesquad_han.kotlin_drawingapp.BaseView
import com.codesquad_han.kotlin_drawingapp.model.RectangleImageviewData

interface RectangleContract {

    interface View : BaseView<Presenter> {
        fun showRectangle(width:Int, height: Int, x:Int, y:Int, colorStr: String)   // 사각형 정보 받아와서 이미지뷰 만들기

        fun showSelectedRectangle(rectangleList : ArrayList<RectangleImageviewData>)
    }

    interface Presenter : BasePresenter {
        fun generateRectangle() // 사각형 생성

        fun saveImageView(imageView: ImageView) // 사각형 정보를 바탕으로 생성한 이미지뷰를 저장

        fun selectRectangleImageView(imageView: ImageView)

        fun getRectangleImageViewList(rectangleList : ArrayList<RectangleImageviewData>)
    }
}