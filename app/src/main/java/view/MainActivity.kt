package view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import model.RectFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rect1 = RectFactory.makeRect()
        val rect2 = RectFactory.makeRect()
        val rect3 = RectFactory.makeRect()
        val rect4 = RectFactory.makeRect()

        Log.d("Rect1", "$rect1")
        Log.d("Rect2", "$rect2")
        Log.d("Rect3", "$rect3")
        Log.d("Rect4", "$rect4")
    }
}