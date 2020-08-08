package hex.engine

import hex.Move
import hex.MoveResult
import hex.Success
import hex.status.GameHistory
import hex.status.GameStatus

interface HexGame {
    fun makeMove(move: Move): MoveResult
    fun getHistory(): GameHistory
    fun getStatus(): GameStatus

    fun HexGame.toSuccess(): Success = Success(this)
}

