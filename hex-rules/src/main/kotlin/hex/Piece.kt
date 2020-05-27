package hex

sealed class Piece {
    fun getOpposite() = if (this is RedPiece) BluePiece else RedPiece
}

object RedPiece : Piece()
object BluePiece : Piece()