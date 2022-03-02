import model.Plane
import android.content.Context
import android.widget.TextView
import model.RectFactory
import model.RectView
import view.MainContract

class MainPresenter(
    private val view: MainContract.View,
    private val context: Context
) : MainContract.Presenter {

    private val plane = Plane(context)

    override fun selectRectangle(xPos: Float, yPos: Float) {
        val selectedRectangle = plane.getCustomRectangleByPosition(xPos, yPos)
        val selectedRectangleView= plane.getCustomRectangleViewByPosition(xPos,yPos)
        view.drawBorder(selectedRectangleView)
        selectedRectangle?.let {
            view.displayAttribute(selectedRectangle)
        }
    }

    override fun changeColor(rectView: RectView, colorValueView: TextView) {
        val randomColor = plane.changeColor(rectView)
        view.showColorChange(rectView, randomColor, colorValueView)
    }

    override fun changeOpacity(rectView: RectView, opacity: Int) {
        plane.changeOpacity(rectView, opacity)
        view.showOpacityChange(rectView, opacity)
    }

    override fun createRectanglePaint() {
        val rectPaint = plane.createRectanglePaint(RectFactory.makeRect())
        view.drawRectangle(rectPaint)
    }

}