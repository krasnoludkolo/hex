sealed class Player {
    fun nextPlayer() = if (this is RedPlayer) BluePlayer else RedPlayer
    fun getPiece() = if (this is RedPlayer) RedPiece else BluePiece
}

object RedPlayer : Player()
object BluePlayer : Player()

data class Move(
    val player: Player,
    val point: Point
) {
    companion object {
        fun red(point: Point) = Move(RedPlayer, point)
        fun blue(point: Point) = Move(BluePlayer, point)
    }
}


sealed class MoveResult

data class Success(val board: Board) : MoveResult()

data class ErrorMove(val e: Error) : MoveResult() {
    enum class Error {
        WRONG_TURN, TAKEN_PLACE
    }

    companion object {
        fun wrongTurn() = ErrorMove(Error.WRONG_TURN)
        fun takenPlace() = ErrorMove(Error.TAKEN_PLACE)
    }
}