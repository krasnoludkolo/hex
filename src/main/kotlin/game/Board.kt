package game

import io.vavr.collection.HashMap
import io.vavr.collection.HashSet
import io.vavr.collection.Map
import io.vavr.collection.Set
import io.vavr.kotlin.getOrNull

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty(),
    val piecesMap: Map<Point, Piece> = HashMap.empty()
) {

    fun putPiece(point: Point, piece: Piece) = copy(
        piecesMap = piecesMap.put(point, piece)
    )

    fun putPiece(move: NormalMove) = putPiece(move.point, move.player.getPiece())

    fun hasEnded(): Boolean {
        return isVerticalConnection() || isHorizontalConnection()
    }

    private fun isHorizontalConnection(): Boolean {
        val leftWalls = adjacencyMap.keySet().filterIsInstance<LeftWallCell>()

        fun dfsRed(trace: Set<Cell>, current: Cell): Boolean {
            val next = adjacencyMap
                .get(current)
                .get()
                .removeAll(trace)
            return if (next.any { it is RightWallCell }) {
                true
            } else {
                next
                    .filterIsInstance<BoardCell>()
                    .filter { piecesMap.getOrNull(it.point) is RedPiece }
                    .any { dfsRed(trace.add(it), it) }
            }
        }

        return leftWalls.any { dfsRed(HashSet.of(it), it) }
    }

    private fun isVerticalConnection(): Boolean {
        val upWalls = adjacencyMap.keySet().filterIsInstance<UpWallCell>()

        fun dfsBlue(trace: Set<Cell>, current: Cell): Boolean {
            val next = adjacencyMap
                .get(current)
                .get()
                .removeAll(trace)
            return if (next.any { it is DownWallCell }) {
                true
            } else {
                next
                    .filterIsInstance<BoardCell>()
                    .filter { piecesMap.getOrNull(it.point) is BluePiece }
                    .any { dfsBlue(trace.add(it), it) }
            }
        }

        return upWalls.any { dfsBlue(HashSet.of(it), it) }
    }

}

