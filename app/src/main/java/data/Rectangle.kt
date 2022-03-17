package data

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF


/* data 쪽에서는 android.graphics 를 불러오지 않는것이 맞지만 클래스 생성 대신 직관적인 사용을 위해 일단 사용하였습니다 */
interface Rectangle {

    val id: String

    var rectangle: RectF

    var paint: Paint?

    fun draw(canvas: Canvas?)
}