import android.content.Context
import android.graphics.Bitmap
import model.Plane
import view.MainContract
import view.RectView

class MainPresenter(
    private val view: MainContract.View,
    private val context: Context
) : MainContract.Presenter {

    private val plane = Plane(context)
    override fun selectRectangle(xPos: Float, yPos: Float) {
        plane.getCustomRectangleByPosition(xPos, yPos)
            ?.let { view.displaySelectedRectAttribute(it) }

    }

    override fun changeColor(rectView: RectView) {
        if (rectView.photoId == "") {
            plane.changeColor(rectView.rectId)
        }
    }

    override fun changeOpacity(rectView: RectView, opacity: Int) {
        plane.changeOpacity(rectView.rectId, opacity)
    }

    override fun createRectanglePaint() {
        view.drawRectangle(plane.createRectanglePaint())
    }

    override fun createPhotoPaint(image: Bitmap) {
        view.drawPhoto(plane.createPhotoPaint(image))
    }

}