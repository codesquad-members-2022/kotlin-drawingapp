package presenter

import Plane
import android.content.Context
import android.widget.ImageView
import view.MainContract

class MainPresenter(
    private val view: MainContract.View,
    private val context: Context,
    private val density: Float
) : MainContract.Presenter {

    private val plane= Plane(context,density)
    override fun createRectangle(): ImageView {
        return plane.create(RectFactory.makeRect())
    }

    override fun selectRectangle(xPos: Float, yPos: Float) {
        val selectedRectangle = plane.getRectangleByPosition(xPos, yPos)
        selectedRectangle?.let {
            val selectedRectangleView = plane.getRectangleView(selectedRectangle)
            val imageBorder = plane.makeImageBorder(xPos, yPos)
            view.showSelected(selectedRectangle, selectedRectangleView, imageBorder)
        }
    }

}

