package com.codesquad_han.kotlin_drawingapp.rectangle

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.codesquad_han.kotlin_drawingapp.data.RectangleRepository
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle

class RectanglePresenter(
    val rectangleRepository: RectangleRepository,
    val rectangleView: RectangleContract.View
) :
    RectangleContract.Presenter {

    override var liveNormalRectangleList = MutableLiveData<MutableList<BaseRectangle>>()

    init {
        rectangleView.presenter = this
    }

    override fun start() {
        addRectangle()
    }

    override fun addRectangle() {  // 일반 사각형(이미지 삽입 기능 포함) 추가 후 라이브데이터 갱신
        rectangleRepository.addNormalRectangle()
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun addTextRectangle() { // 텍스트 기반 사각형 추가 후 라이브데이터 갱신
        rectangleRepository.addTextRectangle()
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun addImageRectangle(imageUri: Uri?) { // 갤러링서 선태한 사진 기반 사각형 추가 후 라이브데이터 갱신
        rectangleRepository.addImageRectangle(imageUri)
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updateTransparency(id: String, transparency: Int) { // 선택된 사각형 투명도 데이터 변경 후 라이브데이터 갱신
        rectangleRepository.updateTransparency(id, transparency)
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updateImageUri(id: String, imageUri: Uri?) { // 선택한 사각형에 갤러리에서 선택한 이미지 삽입 시 데이터 변경 후 라이브데이터 갱신
        rectangleRepository.updateImageUri(id, imageUri)
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updateSelectedRectanglePoint(id: String, newX: Int, newY: Int) {
        rectangleRepository.updateSelctedRectanglePoint(id, newX, newY)
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updatePointX(value: Int, id: String) {
        rectangleView.showPointX(rectangleRepository.updatePointX(value, id))
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updatePointY(value: Int, id: String) {
        rectangleView.showPointY(rectangleRepository.updatePointY(value, id))
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updateSizeWidth(value: Int, id: String) {
        rectangleView.showSizeW(rectangleRepository.updateSizeWidth(value, id))
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    override fun updateSizeHeight(value: Int, id: String) {
        rectangleView.showSizeH(rectangleRepository.updateSizeHeight(value, id))
        liveNormalRectangleList.value = rectangleRepository.getRectangleList()
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun getRectangleList() { // 사각형 추가, 투명도 갱신한 리스트 전달, 라이브데이터 도입 전에 변화된 사각형 리스트를 가져오기 위한 함수
        rectangleView.showRectangle(rectangleRepository.getRectangleList())
    }
}