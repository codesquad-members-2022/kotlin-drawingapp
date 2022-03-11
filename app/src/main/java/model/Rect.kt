package model

import androidx.lifecycle.MutableLiveData

open class Rect(
    val rectId: String,
    val point: MutableLiveData<Point>,
    val size: MutableLiveData<Size>,
    var backGroundColor: MutableLiveData<BackGroundColor>,
    var opacity: MutableLiveData<Int>
) {

    override fun toString(): String {
        return "${this.rectId}, $point $size $backGroundColor Alpha: $opacity"
    }
}