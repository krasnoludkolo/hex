package hex

sealed class HexPlayer {
    fun nextPlayer() = if (this is RedHexPlayer) BlueHexPlayer else RedHexPlayer
    fun getPiece() = if (this is RedHexPlayer) RedPiece else BluePiece
}

object RedHexPlayer : HexPlayer()
object BlueHexPlayer : HexPlayer()