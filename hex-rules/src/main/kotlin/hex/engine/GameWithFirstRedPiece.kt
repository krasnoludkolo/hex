package hex.engine

import hex.*
import io.vavr.collection.HashMap
import io.vavr.collection.List

internal class GameWithFirstRedPiece(
    private val board: Board,
    private val piecePoint: Point
) : Game {

    override fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: successMove(move)
    }

    private fun validateMove(move: Move): ErrorMove? {
        return when (move) {
            is SwitchMove -> null
            is NormalMove -> when {
                move.player == RedPlayer -> ErrorMove.wrongTurn()
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

    private fun makeNormalMove(move: NormalMove): GameAfterFirstMove {
        val newBoard = board.putPiece(move)
        val newHistory = getHistory().append(move)
        return GameAfterFirstMove(newBoard, RedPlayer, newHistory)
    }

    private fun makeSwitchMove(move: Move): GameAfterFirstMove {
        val newBoard = board.switch()
        val newHistory = getHistory().append(move)
        return GameAfterFirstMove(newBoard, RedPlayer, newHistory)
    }

    private fun Board.switch(): Board {
        val firstPiecePoint = board.piecesMap.keySet().first()
        return this.copy(
            piecesMap = HashMap.empty<Point, Piece>().put(firstPiecePoint, BluePiece)
        )
    }

    override fun getHistory(): List<Move> {
        return List.of(NormalMove(RedPlayer, piecePoint))
    }

    override fun getStatus(): GameStatus = GameStatus.ongoing(board, RedPlayer)
}

