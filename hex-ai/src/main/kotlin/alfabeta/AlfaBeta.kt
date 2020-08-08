package alfabeta

import alfabeta.evaluator.BoardEvaluator
import hex.*
import hex.status.OngoingGameStatus
import io.vavr.collection.Set

class AlfaBeta constructor(
    private val name: String,
    private val depth: Int,
    private val hexPlayer: HexPlayer,
    private val evaluator: BoardEvaluator

) : Player {

    override fun makeMove(status: OngoingGameStatus): Move {
        return max(status.board, depth)
    }

    private fun min(board: Board, currentDepth: Int): NormalMove {
        if (currentDepth == 0) {
            return generateAllOponentPossibleMoves(board)
                .map { MoveWithEvaluation(it, evaluator.evaluate(board.putPiece(it))) }
                .sortedBy { it.evaluation }
                .first().move
        } else {
            val oponentMove = max(board, currentDepth - 1)
            val boardAfterMove = board.putPiece(oponentMove)
            return generateAllOponentPossibleMoves(boardAfterMove)
                .map { MoveWithEvaluation(it, evaluator.evaluate(board.putPiece(it))) }
                .sortedBy { it.evaluation }
                .first().move
        }
    }

    private fun max(board: Board, currentDepth: Int): NormalMove {
        if (currentDepth == 0) {
            return generateAllPossibleMoves(board)
                .map { MoveWithEvaluation(it, evaluator.evaluate(board.putPiece(it))) }
                .sortedByDescending { it.evaluation }
                .first().move
        } else {
            val oponentMove = min(board, currentDepth - 1)
            val boardAfterMove = board.putPiece(oponentMove)
            return generateAllPossibleMoves(boardAfterMove)
                .map { MoveWithEvaluation(it, evaluator.evaluate(board.putPiece(it))) }
                .sortedByDescending { it.evaluation }
                .first().move
        }
    }

    private fun generateAllPossibleMoves(board: Board): Set<NormalMove> {
        return board
            .emptyPlaces
            .map { NormalMove(hexPlayer, it.point) }
    }

    private fun generateAllOponentPossibleMoves(board: Board): Set<NormalMove> {
        return board
            .emptyPlaces
            .map { NormalMove(hexPlayer.nextPlayer(), it.point) }
    }


    override fun getName() = name

    data class MoveWithEvaluation(val move: NormalMove, val evaluation: Double)

}