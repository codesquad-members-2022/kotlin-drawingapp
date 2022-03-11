package model

import androidx.lifecycle.MutableLiveData

class RectFactory {

    companion object {
        fun makeRect(): Rect {
            return makeNewRectType()
        }

        fun makePhoto():Photo{
            return makeNewPhoto()
        }

        private fun makeNewRectType(): Rect {
            return Rect(
                randomRectId(),
                MutableLiveData(makeRandomPoint()),
                MutableLiveData(makeSize()),
                MutableLiveData(makeRandomRGB()),
                MutableLiveData(makeRandomOpacity())
            )

        }

        private fun makeNewPhoto():Photo{
            return Photo(
                randomPhotoId(),
                randomRectId(),
                MutableLiveData(makeRandomPoint()),
                MutableLiveData(makeSize()),
                MutableLiveData(makeRandomRGB()),
                MutableLiveData(makeRandomOpacity())
            )
        }

        private fun makeSize(): Size {
            val width = 150
            val height = 120
            return Size(width, height)
        }

        private fun makeRandomPoint(): Point {
            val xPos = (1..2048).random()
            val yPos = (1..1536).random()
            return Point(xPos, yPos)
        }

        private fun makeRandomOpacity(): Int {
            return (1..10).random()
        }

        private fun makeRandomRGB(): BackGroundColor {
            val redValue = (0..255).random()
            val blueValue = (0..255).random()
            val greenValue = (0..255).random()
            return BackGroundColor(redValue, blueValue, greenValue)
        }

        private fun randomRectId(): String {
            val targetLength = 3
            return "${getRandomString(targetLength)}-$${getRandomString(targetLength)}-${
                getRandomString(
                    targetLength
                )
            }"
        }

        private fun randomPhotoId(): String {
            val targetLength = 4
            return "${getRandomString(targetLength)}-$${getRandomString(targetLength)}-${
                getRandomString(
                    targetLength
                )
            }"
        }

        private fun getRandomString(length: Int): String {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
        }
    }
}