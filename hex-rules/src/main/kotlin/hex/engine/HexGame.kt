package hex.engine

import hex.GameStatus
import hex.Move
import hex.MoveResult
import hex.Success
import io.vavr.collection.List

interface HexGame {
    fun makeMove(move: Move): MoveResult
    fun getHistory(): List<Move>
    fun getStatus(): GameStatus

    fun HexGame.toSuccess(): Success = Success(this)
}

