import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.ViewCompat
import kotlin.math.roundToInt


private data class BindingRect(val imageView: ImageView, val rect:Rect)

class Plane(private val context: Context, private val density:Float) {
    private val rectangleList:ArrayList<BindingRect>  = arrayListOf()

    fun create(rect:Rect):ImageView{
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

    fun checkIsRectangleIn(xPos:Int, yPos:Int):Boolean{
        rectangleList.map{
            if(xPos in it.rect.point.xPos-75 ..it.rect.point.xPos+75 || yPos in it.rect.point.yPos-60 ..it.rect.point.yPos+60){
                return true
            }
        }
        return false
    }

}