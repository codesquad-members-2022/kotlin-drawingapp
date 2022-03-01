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

}

