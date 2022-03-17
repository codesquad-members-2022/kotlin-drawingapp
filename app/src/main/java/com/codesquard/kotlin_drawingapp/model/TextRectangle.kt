package com.codesquard.kotlin_drawingapp.model

class TextRectangle : Rectangle {

    override lateinit var id: String

    override lateinit var point: Array<Float>

    override lateinit var size: Array<Int>

    override lateinit var color: Array<Int>

    override var isSelected: Boolean = false

    override var alphaValue: Int = 0

    private val textList = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas tellus rutrum tellus pellentesque eu. Viverra justo nec ultrices dui sapien eget mi proin sed. Vel pretium lectus quam id leo. Molestie at elementum eu facilisis sed odio morbi quis commodo. Risus at ultrices mi tempus imperdiet nulla malesuada. In est ante in nibh mauris cursus mattis molestie a. Venenatis urna cursus eget nunc. Eget velit aliquet sagittis id consectetur purus ut. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Consequat nisl vel pretium lectus quam id. Nisl vel pretium lectus quam id leo in vitae turpis. Purus faucibus ornare suspendisse sed. Amet mauris commodo quis imperdiet.
    """.trimIndent().split(" ")

    private var textValue = ""

    fun getTextList() = textList

    fun getText() = textValue

    fun setText(index: Int) {
        (index..index + 4).forEach {
            if (it < textList.size) {
                textValue += textList[it] + " "
            }
        }
        textValue = textValue.trim()
    }

}