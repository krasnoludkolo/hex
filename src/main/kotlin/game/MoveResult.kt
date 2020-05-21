package game

import game.engine.Game

sealed class MoveResult
data class Success(val game: Game) : MoveResult()
data class ErrorMove(val e: Error) : MoveResult() {
    enum class Error {
        WRONG_TURN, TAKEN_PLACE, SWITCH_IN_WRONG_TURN
    }

    companion object {
        fun wrongTurn() = ErrorMove(Error.WRONG_TURN)
        fun takenPlace() = ErrorMove(Error.TAKEN_PLACE)
        fun switchInWrongTurn() = ErrorMove(Error.SWITCH_IN_WRONG_TURN)
    }
}