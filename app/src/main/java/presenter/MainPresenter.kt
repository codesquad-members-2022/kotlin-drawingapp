import android.content.Context
import model.Plane
import view.MainContract
import view.RectView

class MainPresenter(
    private val view: MainContract.View,
    private val context: Context
) : MainContract.Presenter {

    private val plane = Plane(context)
    private val customRectangleViewList: ArrayList<RectView> = arrayListOf()
    override fun selectRectangle(xPos: Float, yPos: Float) {
        customRectangleViewList.map{
            it.eraseBorder()
        }
        val selectedRectangle = plane.getCustomRectangleByPosition(xPos, yPos)
        val selectedRectangleView = customRectangleViewList.find{it.rect==selectedRectangle}
        selectedRectangleView?.let{
            it.drawBorder()
            view.displayAttribute(it)
        }

    }

    override fun changeColor(rectView: RectView) {
        plane.changeColor(rectView.rect)
    }

    override fun changeOpacity(rectView: RectView, opacity: Int) {
        plane.changeOpacity(rectView.rect, opacity)
    }

    override fun createRectanglePaint(): RectView {
        val rectView = RectView(context)
        val rect = plane.createRectanglePaint()
        rectView.rect= rect
        rectView.drawRectangle()
        customRectangleViewList.add(rectView)
        return rectView
    }

}