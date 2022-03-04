package model

import androidx.lifecycle.MutableLiveData

open class Rect(
    private val rectId: String,
    val point: Point,
    val size: Size,
    var backGroundColor: MutableLiveData<BackGroundColor>,
    var opacity: MutableLiveData<Int>
) {

    override fun toString(): String {
        return "${this.rectId}, $point $size $backGroundColor Alpha: $opacity"
    }
}