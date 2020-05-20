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
        return this.copy(
            piecesMap = piecesMap.put(move.point, move.player.getPiece()),
            activePlayer = activePlayer.nextPlayer(),
            history = history.append(move)
        )
    }

    private fun validateMove(move: Move): ErrorMove? {
        return when {
            move.player != activePlayer -> {
                ErrorMove.wrongTurn()
            }
            piecesMap.containsKey(move.point) -> {
                ErrorMove.takenPlace()
            }
            else -> {
                null
            }
        }
    }

}
