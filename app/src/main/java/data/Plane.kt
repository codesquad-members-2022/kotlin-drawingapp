package data

import android.graphics.Bitmap

class Plane {
    fun makeRandomRegularRectangle() {
        val rectangle = RegularRectangle.makeRectangle(
            MakeRandomNumber.makeRandomID(),
            MakeRandomNumber.makeRandomWidthAndHeight(),
            MakeRandomNumber.makeRandomRGBA()
        )
        Repository.addRectangle(rectangle)
    }

    fun getRandomRegularRectangle(): MutableList<RegularRectangle> {
        return Repository.rectangles
    }

    fun getRectNumber(): Int {
        return Repository.rectangles.count()
    }

    fun getRectangleStrokesList(): MutableList<StrokeRectangle> {
        return Repository.rectangleStrokes
    }

    fun setStrokesListClear() {
        Repository.removeStroke()
    }

    fun changeRectangleColorOrNull(rectangle: RegularRectangle): MutableList<RegularRectangle>? {
        return Repository.changeRectangleColor(rectangle)
    }

    fun setImageRect(bitmap: Bitmap) {
        val imageRectangle = ImageRectangle.makeRectangle(
            MakeRandomNumber.makeRandomID(),
            bitmap,
            MakeRandomNumber.makeRandomWidthAndHeight()
        )
        Repository.addImageRect(imageRectangle)
    }

    fun getImageRect(): MutableList<ImageRectangle> {
        return Repository.imagesRectangles
    }

    fun lookupRectangleIsOnThePointer(x: Float?, y: Float?): Boolean {
        Repository.imagesRectangles.forEach { imageRectangle ->
            if (imageRectangle.isOnTheRect(x, y)) {
                Repository.recentClickedImageRectangle = imageRectangle
                Repository.recentlyClicked = imageRectangle
                return true
            }
        }
        Repository.rectangles.forEach { regularRectangle ->
            if (regularRectangle.isOnTheRect(x, y)) {
                Repository.recentClickedRectangle = regularRectangle
                Repository.recentlyClicked = regularRectangle
                return true
            }
        }
        return false
    }

    fun getRecentClickedRectangle(): RegularRectangle? {
        return Repository.recentClickedRectangle
    }

    fun getRecentClickedImageRectangle(): ImageRectangle? {
        return Repository.recentClickedImageRectangle
    }

    fun getRecentlyClickedObject(): Rectangle? {
        return Repository.recentlyClicked
    }


    //TODO strokes can be unlimited -> need to make a cap for limited numbers of strokes manufacturing
    fun makeStrokeRectangle() {
        Repository.imagesRectangles.forEach { imageRectangle ->
            if(imageRectangle.clicked) {
                Repository.addStroke(
                    StrokeRectangle.makeRectangle(
                        "Stroke${MakeRandomNumber.makeRandomID()}",
                        imageRectangle.rectangle
                    )
                )
            }
        }

        Repository.rectangles.forEach { rectangle ->
            if(rectangle.clicked) {
                Repository.addStroke(
                    StrokeRectangle.makeRectangle(
                        "Stroke${MakeRandomNumber.makeRandomID()}",
                        rectangle.rectangle
                    )
                )
            }
        }
    }

    fun clearClickedList() {
        Repository.imagesRectangles.forEach { imageRectangle ->
            imageRectangle.clicked = false
        }

        Repository.rectangles.forEach { rectangle ->
            rectangle.clicked = false
        }
    }

}