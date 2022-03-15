package com.example.kotlindrawingapp.domain.figure.text

import com.example.kotlindrawingapp.domain.figure.*

data class Text(
    override val id: ID,
    override var point: Point,
    override var size: Size,
    override var rgb: RGB?,
    override var alpha: Alpha,
    var text: String
) : Figure() {

    override fun isSelected(touchPoint: Point): Boolean {
        return touchPoint.x >= point.x && touchPoint.y >= point.y - size.height
                && touchPoint.x <= point.x + size.width && touchPoint.y <= point.y
    }

    companion object {
        private const val BLANK = " "
        private const val text =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas tellus rutrum tellus pellentesque eu. Viverra justo nec ultrices dui sapien eget mi proin sed. Vel pretium lectus quam id leo. Molestie at elementum eu facilisis sed odio morbi quis commodo. Risus at ultrices mi tempus imperdiet nulla malesuada. In est ante in nibh mauris cursus mattis molestie a. Venenatis urna cursus eget nunc. Eget velit aliquet sagittis id consectetur purus ut. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Consequat nisl vel pretium lectus quam id. Nisl vel pretium lectus quam id leo in vitae turpis. Purus faucibus ornare suspendisse sed. Amet mauris commodo quis imperdiet."

        fun generateText(): String {
            val split = text.split(BLANK)
            val index = generateNumber(split)
            return split.subList(index, index + 5).joinToString(BLANK)
        }

        private fun generateNumber(list: List<String>): Int {
            return (0..list.size - 5).random()
        }

    }
}