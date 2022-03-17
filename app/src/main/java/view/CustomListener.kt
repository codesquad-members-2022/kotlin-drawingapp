package view

import android.graphics.Bitmap

/* MainActivity 와 DrawObjects 간의 통신을 위한 커스텀 리스너 */
interface CustomListener {
    fun isClicked(numbers: String)
}

interface WidthAndHeightListener {
    fun showWidthAndHeight(numbers: String)
}