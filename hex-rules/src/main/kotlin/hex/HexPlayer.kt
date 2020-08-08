package hex

sealed class HexPlayer {
    fun nextPlayer() = if (this is RedHexPlayer) BlueHexPlayer else RedHexPlayer
    fun getPiece() = if (this is RedHexPlayer) RedPiece else BluePiece
}

object RedHexPlayer : HexPlayer() {
    override fun toString(): String {
        return "RED"
    }
}

object BlueHexPlayer : HexPlayer() {
    override fun toString(): String {
        return "BLUE"
    }
}