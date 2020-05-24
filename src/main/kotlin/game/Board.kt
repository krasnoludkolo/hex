package game

import io.vavr.collection.HashMap
import io.vavr.collection.HashSet
import io.vavr.collection.Map
import io.vavr.collection.Set
import io.vavr.kotlin.getOrNull

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty(),
    val piecesMap: Map<Point, Piece> = HashMap.empty(),
    val upWalls: Set<Cell> = adjacencyMap.keySet().filter { it is UpWallCell },
    val downWalls: Set<Cell> = adjacencyMap.keySet().filter { it is DownWallCell },
    val leftWalls: Set<Cell> = adjacencyMap.keySet().filter { it is LeftWallCell },
    val rightWalls: Set<Cell> = adjacencyMap.keySet().filter { it is RightWallCell }
) {

    fun putPiece(point: Point, piece: Piece) = copy(
        piecesMap = piecesMap.put(point, piece)
    )

    fun putPiece(move: NormalMove) = putPiece(move.point, move.player.getPiece())

    fun hasEnded(): Boolean = isVerticalConnection() || hasHorizontalConnection()

    private fun isVerticalConnection(): Boolean = hasConnection(upWalls, downWalls, BluePiece)

    private fun hasHorizontalConnection(): Boolean = hasConnection(leftWalls, rightWalls, RedPiece)

    private fun hasConnection(startWall: Set<Cell>, endWall: Set<Cell>, playerPiece: Piece): Boolean {

        fun dfs(trace: Set<Cell>, current: Cell): Boolean {
            val next = adjacencyMap
                .get(current)
                .get()
                .removeAll(trace)
            return if (next.any { endWall.contains(it) }) {
                true
            } else {
                next
                    .filterIsInstance<BoardCell>()
                    .filter { piecesMap.getOrNull(it.point) == playerPiece }
                    .any { dfs(trace.add(it), it) }
            }
        }

        return startWall.any { dfs(HashSet.of(it), it) }
    }

}

