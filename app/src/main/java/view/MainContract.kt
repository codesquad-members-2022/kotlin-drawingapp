package view

import Rect
import android.widget.ImageView

interface MainContract {
    interface View{
    }

    interface Presenter{
        fun createRectangle():ImageView
    }
}