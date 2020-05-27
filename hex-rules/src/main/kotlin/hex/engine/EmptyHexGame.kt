package hex.engine

import hex.*
import io.vavr.collection.List

internal class EmptyHexGame(
    private val board: Board
) : HexGame {

    override fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: successMove(move as NormalMove)
    }

    private fun validateMove(move: Move): ErrorMove? {
        return when (move) {
            is SwitchMove -> ErrorMove.switchInWrongTurn()
            is NormalMove -> when {
                move.hexPlayer != RedHexPlayer -> ErrorMove.wrongTurn()
                else -> null
            }
        }
    }

    private fun successMove(move: NormalMove): Success {
        return Success(HexGameWithFirstRedPiece(board.putPiece(move), move.point))
    }

    override fun getHistory(): List<Move> = List.empty()

    override fun getStatus(): GameStatus = GameStatus.ongoing(board, RedHexPlayer)
}