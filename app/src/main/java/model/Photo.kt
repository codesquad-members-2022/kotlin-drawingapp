package model

import androidx.lifecycle.MutableLiveData

class Photo(
    rectId: String,
    point: Point,
    size: Size,
    backGroundColor: MutableLiveData<BackGroundColor>,
    imageOpacity: MutableLiveData<Int> ,
) : Rect(rectId,point,size,backGroundColor,imageOpacity){
    lateinit var imageInfo:ByteArray
}