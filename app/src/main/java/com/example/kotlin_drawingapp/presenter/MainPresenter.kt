package com.example.kotlin_drawingapp.presenter

import android.content.Context
import android.widget.ImageView
import com.example.kotlin_drawingapp.model.Plane
import com.example.kotlin_drawingapp.model.RectangleFactory
import com.example.kotlin_drawingapp.view.MainContract

class MainPresenter(
    private val view: MainContract.View,
    private val context: Context,
    private val density: Float
    ) : MainContract.Presenter {

    private val plane: Plane = Plane(context, density)
    init {
        plane.rectList.observeForever {
            view.update(plane.getLastCreateImage())
        }
    }
    override fun create() {
        plane.create(RectangleFactory.create())
    }

    override fun select(x: Float, y: Float) {
        val border: ImageView? = plane.getSelectRectangleBorder(x, y)
        val rectImage = plane.getRectangleImageByPosition(x, y)
        val rect = plane.getRectangleByPosition(x, y)

        rect?.let {
            view.select(border, rectImage, rect)
        }
    }

    fun setAlpha(rect: ImageView, alpha: Int) {
        plane.setRectangleAlpha(rect, alpha)
    }
}
