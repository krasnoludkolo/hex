package game

import io.vavr.collection.HashMap
import io.vavr.collection.List
import io.vavr.collection.Map
import io.vavr.collection.Set

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty(),
    val piecesMap: Map<Point, Piece> = HashMap.empty(),
    val activePlayer: Player = RedPlayer,
    val history: List<Move> = List.empty()
) {

    fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: Success(performMove(move))
    }

    private fun performMove(move: Move): Board {
        return when (move) {
            is NormalMove -> performNormalMove(move)
            is SwitchMove -> performSwitchMove()
        }.copy(
            history = history.append(move)
        )
    }

    private fun performNormalMove(move: NormalMove): Board = this.copy(
        piecesMap = piecesMap.put(move.point, move.player.getPiece()),
        activePlayer = activePlayer.nextPlayer()

    )

    private fun performSwitchMove(): Board {
        val firstPiecePoint = piecesMap.keySet().first()
        return this.copy(
            piecesMap = HashMap.empty<Point, Piece>().put(firstPiecePoint, BluePiece),
            activePlayer = activePlayer.nextPlayer()
        )
    }

    private fun validateMove(move: Move): ErrorMove? = when (move) {
        is NormalMove -> validateNormalMove(move)
        is SwitchMove -> validateSwitchMove()
    }

    private fun validateNormalMove(move: NormalMove): ErrorMove? {
        return when {
            move.player != activePlayer -> {
                ErrorMove.wrongTurn()
            }
            piecesMap.containsKey(move.point) -> {
                ErrorMove.takenPlace()
            }
            else -> null
        }
    }

    private fun validateSwitchMove(): ErrorMove? {
        return if (piecesMap.keySet().size() == 1) {
            null
        } else {
            ErrorMove.switchAfterFirstTurn()
        }
    }
}

