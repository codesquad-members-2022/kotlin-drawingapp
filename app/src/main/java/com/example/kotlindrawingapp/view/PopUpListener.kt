package com.example.kotlindrawingapp.view

interface PopUpListener {

    fun sendToBack(layerCustomView: LayerCustomView)

    fun sendBack(layerCustomView: LayerCustomView)

    fun sendFront(layerCustomView: LayerCustomView)

    fun sendToFront(layerCustomView: LayerCustomView)

}