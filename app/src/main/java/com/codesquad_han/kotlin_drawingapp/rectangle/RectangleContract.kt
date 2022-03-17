package com.codesquad_han.kotlin_drawingapp.rectangle

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.codesquad_han.kotlin_drawingapp.BasePresenter
import com.codesquad_han.kotlin_drawingapp.BaseView
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle

interface RectangleContract {

    interface View : BaseView<Presenter> {
        fun showRectangle(updatedNormalRectangleList: MutableList<BaseRectangle>)   // 추가된 사각형을 포함한 리스트 받아와서 새로 그리기

        fun showPointX(newX: Int)

        fun showPointY(newY: Int)

        fun showSizeW(newW: Int)

        fun showSizeH(newH: Int)
    }

    interface Presenter : BasePresenter {
        var liveNormalRectangleList: MutableLiveData<MutableList<BaseRectangle>>

        fun addRectangle() // 일반 사각형 생성

        fun addTextRectangle() // 텍스트 사각형 생성

        fun addImageRectangle(imageUri: Uri?) // 이미지 사각형 생성

        fun getRectangleList()

        fun updateTransparency(id: String, transparency: Int) // 선택한 사각형 투명도 변경

        fun updateImageUri(id: String, imageUri: Uri?) // 선택한 사각형 이미지 uri 업데이트

        fun updateSelectedRectanglePoint(id: String, newX: Int, newY: Int) // 선택한 사각형 드래그 후 위치값 업데이트

        fun updatePointX(value: Int, id: String)

        fun updatePointY(value: Int, id: String)

        fun updateSizeWidth(value: Int, id: String)

        fun updateSizeHeight(value: Int, id: String)

    }
}