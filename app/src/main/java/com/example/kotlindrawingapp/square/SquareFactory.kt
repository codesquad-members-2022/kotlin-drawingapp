import com.example.kotlindrawingapp.square.*

object SquareFactory {

    fun createSquare(point: Point, size: Size, rgb: RGB, alpha: Alpha): Square {
        return Square(ID(ID.generateID()), point, size, rgb, alpha)
    }
}