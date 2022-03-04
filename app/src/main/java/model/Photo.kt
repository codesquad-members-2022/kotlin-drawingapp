package model

import androidx.lifecycle.MutableLiveData

class Photo(
    val photoId:String,
    rectId: String,
    point: Point,
    size: Size,
    backGroundColor: MutableLiveData<BackGroundColor>,
    opacity: MutableLiveData<Int> ,
) : Rect(rectId,point,size,backGroundColor,opacity){
    lateinit var imageInfo:ByteArray

}