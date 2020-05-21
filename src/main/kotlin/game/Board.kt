package game

import io.vavr.collection.HashMap
import io.vavr.collection.Map
import io.vavr.collection.Set

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty(),
    val piecesMap: Map<Point, Piece> = HashMap.empty()
) {

    fun putPiece(point: Point, piece: Piece) = copy(
        piecesMap = piecesMap.put(point, piece)
    )

    fun putPiece(move: NormalMove) = putPiece(move.point, move.player.getPiece())

}

