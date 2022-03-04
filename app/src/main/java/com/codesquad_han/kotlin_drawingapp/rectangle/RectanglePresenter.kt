package com.codesquad_han.kotlin_drawingapp.rectangle

import com.codesquad_han.kotlin_drawingapp.data.RectangleRepository

class RectanglePresenter(
    val rectangleRepository: RectangleRepository,
    val rectangleView: RectangleContract.View
) :
    RectangleContract.Presenter {

    init {
        rectangleView.presenter = this
    }

    override fun start() {
        addRectangle()
    }

    override fun addRectangle() {  // 사각형 추가만 하기
        rectangleRepository.addRectangle()
    }

    override fun getRectangleList() { // 사각형 추가, 투명도 갱신한 리스트 전달
        rectangleView.showRectangle(rectangleRepository.getRectangleList())
    }

    override fun updateTransparency(id: String, transparency: Int) {
        rectangleRepository.updateTransparency(id, transparency)
    }


}