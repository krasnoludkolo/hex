package hex

sealed class Move

data class NormalMove(
    val hexPlayer: HexPlayer,
    val point: Point
) : Move() {
    companion object {
        fun red(point: Point) = NormalMove(RedHexPlayer, point)
        fun blue(point: Point) = NormalMove(BlueHexPlayer, point)
    }
}

object SwitchMove : Move()
