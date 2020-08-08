package hex

sealed class Move

data class NormalMove(
    val hexPlayer: HexPlayer,
    val point: Point
) : Move() {

    override fun toString(): String {
        return point.toString()
    }

    companion object {
        fun red(point: Point) = NormalMove(RedHexPlayer, point)
        fun blue(point: Point) = NormalMove(BlueHexPlayer, point)
    }
}

object SwitchMove : Move() {
    override fun toString(): String {
        return "S"
    }
}
