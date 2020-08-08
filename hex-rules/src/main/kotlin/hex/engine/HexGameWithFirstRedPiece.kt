package hex.engine

import hex.*
import hex.status.GameHistory
import hex.status.GameStatus
import io.vavr.collection.HashMap

internal class HexGameWithFirstRedPiece(
    private val board: Board,
    private val piecePoint: Point
) : HexGame {

    override fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: successMove(move)
    }

    private fun validateMove(move: Move): ErrorMove? {
        return when (move) {
            is SwitchMove -> null
            is NormalMove -> when {
                move.hexPlayer == RedHexPlayer -> ErrorMove.wrongTurn()
                move.point == piecePoint -> ErrorMove.takenPlace()
                else -> null
            }
        }
    }

    private fun successMove(move: Move): Success {
        return when (move) {
            is NormalMove -> makeNormalMove(move)
            is SwitchMove -> makeSwitchMove(move)
        }.toSuccess()
    }

    private fun makeNormalMove(move: NormalMove): HexGameAfterFirstMove {
        val newBoard = board.putPiece(move)
        val newHistory = getHistory().addMove(move)
        return HexGameAfterFirstMove(newBoard, RedHexPlayer, newHistory)
    }

    private fun makeSwitchMove(move: Move): HexGameAfterFirstMove {
        val newBoard = board.switch()
        val newHistory = getHistory().addMove(move)
        return HexGameAfterFirstMove(newBoard, RedHexPlayer, newHistory)
    }

    private fun Board.switch(): Board {
        val firstPiecePoint = board.piecesMap.keySet().first()
        return this.copy(
            piecesMap = HashMap.empty<Point, Piece>().put(firstPiecePoint, BluePiece)
        )
    }

    override fun getHistory(): GameHistory {
        return GameHistory.withMove(NormalMove(RedHexPlayer, piecePoint))
    }

    override fun getStatus(): GameStatus = GameStatus.ongoing(board, RedHexPlayer)
}

