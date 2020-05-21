package game.engine

import game.*
import io.vavr.collection.List

class EmptyGame(
    private val board: Board
) : Game {

    override fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: successMove(move as NormalMove)
    }

    private fun validateMove(move: Move): ErrorMove? {
        return when (move) {
            is SwitchMove -> ErrorMove.switchInWrongTurn()
            is NormalMove -> when {
                move.player != RedPlayer -> ErrorMove.wrongTurn()
                else -> null
            }
        }
    }

    private fun successMove(move: NormalMove): Success {
        return Success(GameWithFirstRedPiece(board.putPiece(move), move.point))
    }

    override fun getHistory(): List<Move> = List.empty()

    override fun getBoard(): Board = board
}