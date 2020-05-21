package game.engine

import game.Board
import game.Move
import game.MoveResult
import game.Success
import io.vavr.collection.List

interface Game {
    fun makeMove(move: Move): MoveResult
    fun getHistory(): List<Move>
    fun getBoard(): Board

    fun Game.toSuccess(): Success = Success(this)
}

