package model

import androidx.lifecycle.MutableLiveData

open class Rect(
    val rectId: String,
    var point: MutableLiveData<Point>,
    var size: MutableLiveData<Size>,
    var backGroundColor: MutableLiveData<BackGroundColor>,
    var opacity: MutableLiveData<Int>
) {

    override fun toString(): String {
        return "${this.rectId}, $point $size $backGroundColor Alpha: $opacity"
    }
}