package hex

import hex.engine.HexGame

sealed class MoveResult
data class Success(val hexGame: HexGame) : MoveResult()
data class ErrorMove(val e: Error) : MoveResult() {
    enum class Error {
        WRONG_TURN,
        TAKEN_PLACE,
        SWITCH_IN_WRONG_TURN,
        ENDED_GAME
    }

    companion object {
        fun wrongTurn() = ErrorMove(Error.WRONG_TURN)
        fun takenPlace() = ErrorMove(Error.TAKEN_PLACE)
        fun endedGame() = ErrorMove(Error.ENDED_GAME)
        fun switchInWrongTurn() = ErrorMove(Error.SWITCH_IN_WRONG_TURN)
    }
}