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
    private val customRectangleViewList: ArrayList<RectView> = arrayListOf()
    override fun selectRectangle(xPos: Float, yPos: Float) : RectView? {
        customRectangleViewList.map{
            it.eraseBorder()
        }
        val selectedRectangle = plane.getCustomRectangleByPosition(xPos, yPos)
        val selectedRectangleView = customRectangleViewList.find{it.rectId==selectedRectangle?.rectId }
        selectedRectangleView?.let{rectView->
            rectView.drawBorder()
            selectedRectangle?.let {
                view.displayRectAttribute(rectView.backGroundRGB, rectView.alpha, selectedRectangle)
            }
        }

        return selectedRectangleView

    }

    override fun changeColor(rectView: RectView) {
        if(rectView.photoId=="") {
            plane.changeColor(rectView.rectId)
        }
    }

    override fun changeOpacity(rectView: RectView, opacity: Int) {
        plane.changeOpacity(rectView.rectId, opacity)
    }

    override fun createRectanglePaint(): RectView {
        val rectView = RectView(context)
        val rect = plane.createRectanglePaint()
        rectView.drawRectangle(rect)
        customRectangleViewList.add(rectView)
        return rectView
    }

    override fun createPhotoPaint(image: Bitmap):RectView {
        val photo = plane.createPhotoPaint(image)
        val rectView = RectView(context)
        rectView.drawPhoto(image,photo)
        customRectangleViewList.add(rectView)
        return rectView
    }

}