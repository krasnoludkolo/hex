package hex.engine

import hex.*
import io.vavr.collection.List

internal data class HexGameAfterFirstMove(
    private val board: Board,
    private val activeHexPlayer: HexPlayer,
    private val history: List<Move> = List.empty()
) : HexGame {

    override fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: performMove(move as NormalMove).toSuccess()
    }

    private fun performMove(move: NormalMove): HexGame {
        return performNormalMove(move)
            .addToHistory(move)
            .checkIfGameHasEnded()
    }

    private fun checkIfGameHasEnded(): HexGame = if (board.hasEnded()) generateEndGame() else this

    private fun generateEndGame() = EndedHexGame(board, history, activeHexPlayer.nextPlayer())

    private fun addToHistory(move: Move) = this.copy(history = history.append(move))

    private fun performNormalMove(move: NormalMove): HexGameAfterFirstMove = this.copy(
        board = board.putPiece(move.point, move.hexPlayer.getPiece()),
        activeHexPlayer = activeHexPlayer.nextPlayer()
    )

    private fun validateMove(move: Move): ErrorMove? = when (move) {
        is NormalMove -> validateNormalMove(move)
        is SwitchMove -> ErrorMove.switchInWrongTurn()
    }

    private fun validateNormalMove(move: NormalMove): ErrorMove? {
        return when {
            move.hexPlayer != activeHexPlayer -> {
                ErrorMove.wrongTurn()
            }
            board.piecesMap.containsKey(move.point) -> {
                ErrorMove.takenPlace()
            }
            else -> null
        }
    }

    override fun getHistory(): List<Move> = history

    override fun getStatus(): GameStatus = GameStatus.ongoing(board, activeHexPlayer)

}