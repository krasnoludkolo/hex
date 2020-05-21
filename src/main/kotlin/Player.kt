sealed class Player {
    fun nextPlayer() = if (this is RedPlayer) BluePlayer else RedPlayer
    fun getPiece() = if (this is RedPlayer) RedPiece else BluePiece
}

object RedPlayer : Player()
object BluePlayer : Player()