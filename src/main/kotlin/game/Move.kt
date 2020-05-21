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