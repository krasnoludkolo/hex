package game.engine

import game.*
import io.vavr.collection.List

data class GameAfterFirstMove(
    private val board: Board,
    private val activePlayer: Player,
    private val history: List<Move> = List.empty()
) : Game {

    override fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: performMove(move as NormalMove).toSuccess()
    }

    private fun performMove(move: NormalMove): Game {
        return performNormalMove(move).addToHistory(move)
    }

    private fun addToHistory(move: Move) = this.copy(history = history.append(move))

    private fun performNormalMove(move: NormalMove): GameAfterFirstMove = this.copy(
        board = board.putPiece(move.point, move.player.getPiece()),
        activePlayer = activePlayer.nextPlayer()
    )

    private fun validateMove(move: Move): ErrorMove? = when (move) {
        is NormalMove -> validateNormalMove(move)
        is SwitchMove -> ErrorMove.switchInWrongTurn()
    }

    private fun validateNormalMove(move: NormalMove): ErrorMove? {
        return when {
            move.player != activePlayer -> {
                ErrorMove.wrongTurn()
            }
            board.piecesMap.containsKey(move.point) -> {
                ErrorMove.takenPlace()
            }
            else -> null
        }
    }

    override fun getHistory(): List<Move> = history

    override fun getBoard(): Board = board

}