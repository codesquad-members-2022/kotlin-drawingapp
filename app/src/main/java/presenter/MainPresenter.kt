import android.content.Context
import android.graphics.Bitmap
import model.Photo
import model.Plane
import model.Sentence
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

    override fun changePosition(rectView: RectView) {

        plane.changePosition(rectView.rectId, rectView.left.toInt(), rectView.top.toInt())?.let{
            if(rectView.photoId==""&&rectView.text==""){
                view.redrawRectangle(it)
            }
            else if(rectView.text!=""){
                view.redrawSentence(it as Sentence)
            }
            else{
                view.redrawPhoto(it as Photo)
            }
        }
    }

    override fun changeXpos(rectView: RectView, value: Int) {
        plane.changePosition(rectView.rectId, (rectView.left+value).toInt(),  rectView.top.toInt())
    }

    override fun changeYPos(rectView: RectView, value: Int) {
        plane.changePosition(rectView.rectId, (rectView.left).toInt(),  (rectView.top+value).toInt())
    }

    override fun changeSize(rectView: RectView, mode: String, value: Int) {
        when(mode){
            "width"->{
                plane.changeSize(rectView.rectId, rectView.rectWidth+value, rectView.rectHeight )
            }
            "height"->{
                plane.changeSize(rectView.rectId, rectView.rectWidth, rectView.rectHeight+value)
            }
        }
    }

    override fun createRectanglePaint() {
        view.drawRectangle(plane.createRectanglePaint())
    }

    override fun createPhotoPaint(image: Bitmap) {
        view.drawPhoto(plane.createPhotoPaint(image))
    }

    override fun createSentencePaint() {
        view.drawSentence(plane.createTextPaint())
    }

}