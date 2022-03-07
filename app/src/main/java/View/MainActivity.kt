package View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customView = findViewById<DrawObjects>(R.id.custom_canvas)
        val button = findViewById<Button>(R.id.make_square_button)
        button.setOnClickListener {
            customView.makeRectangle()
        }
    }
}