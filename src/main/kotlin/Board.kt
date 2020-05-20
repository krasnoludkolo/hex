import io.vavr.collection.HashMap
import io.vavr.collection.Map
import io.vavr.collection.Set

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty(),
    val piecesMap: Map<Point, Piece> = HashMap.empty(),
    val activePlayer: Player = RedPlayer
) {

    val left: List<LeftWallCell> = adjacencyMap.values().filterIsInstance<LeftWallCell>()
    val right: List<RightWallCell> = adjacencyMap.values().filterIsInstance<RightWallCell>()
    val down: List<DownWallCell> = adjacencyMap.values().filterIsInstance<DownWallCell>()
    val up: List<UpWallCell> = adjacencyMap.values().filterIsInstance<UpWallCell>()

    fun makeMove(move: Move): MoveResult {
        return validateMove(move) ?: Success(performMove(move))
    }

    private fun performMove(move: Move): Board {
        return this.copy(
            piecesMap = piecesMap.put(move.point, move.player.getPiece()),
            activePlayer = activePlayer.nextPlayer()
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
