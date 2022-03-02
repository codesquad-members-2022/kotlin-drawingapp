import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.codesquad.kotlin_drawingapp.R
import model.BackGroundColor
import model.Rect
import kotlin.math.roundToInt


private data class BindingRect(val imageView: ImageView, val rect: Rect)

class Plane(private val context: Context, private val density:Float) {
    private val rectangleList:ArrayList<BindingRect>  = arrayListOf()

    fun create(rect: Rect):ImageView{
        val imageView= ImageView(context)
        val param= FrameLayout.LayoutParams(
            convertDpToPx(rect.size.width),  convertDpToPx(rect.size.height)
        )
        param.setMargins(convertDpToPx(rect.point.xPos), convertDpToPx(rect.point.yPos),0,0)
        imageView.layoutParams = param
        imageView.alpha= (rect.getOpacity()/10.0).toFloat()
        imageView.setBackgroundColor(rect.backGroundColor.getBackGroundColor())
        imageView.id= ViewCompat.generateViewId()
        rectangleList.add(BindingRect(imageView,rect))
        return imageView
    }

    private fun convertDpToPx(dp: Int): Int {
        return (dp * density).roundToInt()
    }

    private fun convertPxToDp(px: Float): Int {
        return (px / density).roundToInt()
    }

    fun getRectCount():Int{
        return rectangleList.size
    }

    fun getRectangleByPosition(x:Float,y:Float): Rect?{
        val indexOfRectangle= checkIsInRectangleArea(x,y)
        return if(indexOfRectangle==-1){
            null
        } else{
            rectangleList[indexOfRectangle].rect
        }
    }


    fun getRectangleView(rect: Rect): ImageView? {
        return rectangleList.find{it.rect==rect}?.imageView
    }


    fun checkIsInRectangleArea(x:Float, y:Float): Int {
        val xPos= convertPxToDp(x)
        val yPos= convertPxToDp(y)
        rectangleList.map{
            if(it.rect.point.xPos<=xPos && it.rect.point.xPos+it.rect.size.width>=xPos){
                if(it.rect.point.yPos<=yPos && it.rect.point.yPos+ it.rect.size.height>=yPos){
                    return rectangleList.indexOf(it)
                }
            }
        }
        return -1
    }

    fun makeImageBorder(x:Float, y:Float):ImageView?{
        val selectedRect= getRectangleByPosition(x,y)
        return selectedRect?.let {
            val imageView= ImageView(context)
            val param= FrameLayout.LayoutParams(
                convertDpToPx(selectedRect.size.width),  convertDpToPx(selectedRect.size.height)
            )
            param.setMargins(convertDpToPx(selectedRect.point.xPos), convertDpToPx(selectedRect.point.yPos),0,0)
            imageView.layoutParams = param
            imageView.setBackgroundResource(R.drawable.imageview_border)
            return imageView
        }?:null

    }

    fun getRectByIndex(index:Int): Rect {
        return rectangleList[index].rect
    }

    fun changeColor(rectView:ImageView):BackGroundColor{
        val randomColor= BackGroundColor((0..255).random(), (0..255).random(), (0..255).random())
        rectangleList.find { it.imageView==rectView }?.rect?.backGroundColor=randomColor
        return randomColor
    }

    fun changeOpacity(rectView: ImageView, opacity:Int){
        rectangleList.find { it.imageView==rectView }?.rect?.setOpacity(opacity)

    }

}