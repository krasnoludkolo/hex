package game

sealed class Move

data class NormalMove(
    val player: Player,
    val point: Point
) : Move() {
    companion object {
        fun red(point: Point) = NormalMove(RedPlayer, point)
        fun blue(point: Point) = NormalMove(BluePlayer, point)
    }
}

object SwitchMove : Move()
