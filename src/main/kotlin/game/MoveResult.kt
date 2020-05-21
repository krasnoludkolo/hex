package game

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