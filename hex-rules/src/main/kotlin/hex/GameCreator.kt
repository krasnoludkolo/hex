package hex

import hex.engine.EmptyHexGame
import hex.engine.HexGame
import io.vavr.collection.List
import io.vavr.kotlin.pair
import java.lang.Integer.max
import kotlin.math.min

object GameCreator {

    fun createGame(size: Int): HexGame {
        if (size < 3) throw IllegalArgumentException("Ok, So Basically Im Very Smol")
        return EmptyHexGame(
            BoardBuilder(size)
                .addUpWall(size)
                .addDownWall(size)
                .addLeftWall(size)
                .addRightWall(size)
                .addBoard(size)
                .build()
        )
    }

    private fun BoardBuilder.addBoard(size: Int): BoardBuilder {
        val list = List.range(0, size)
            .crossProduct()
            .map { it.pair() }
        return list
            .fold(this) { acc, p ->
                val cell = BoardCell(p.first to p.second)
                acc
                    .addEdge(
                        cell,
                        BoardCell(max(0, cell.point.x - 1) to max(0, cell.point.y - 1))
                    )
                    .addEdge(cell, BoardCell(cell.point.x to max(0, cell.point.y - 1)))
                    .addEdge(cell, BoardCell(max(0, cell.point.x - 1) to cell.point.y))
            }
    }

    private fun BoardBuilder.addUpWall(size: Int): BoardBuilder {
        return List.range(0, size + 1)
            .map { UpWallCell(it) }
            .fold(this) { acc, cell ->
                acc
                    .addEdge(cell, BoardCell(0 to min(cell.n, size - 1)))
                    .addEdge(cell, BoardCell(0 to max(0, cell.n - 1)))
            }
    }

    private fun BoardBuilder.addDownWall(size: Int): BoardBuilder {
        return List.range(0, size + 1)
            .map { DownWallCell(it) }
            .fold(this) { acc, cell ->
                acc
                    .addEdge(cell, BoardCell(size - 1 to min(cell.n, size - 1)))
                    .addEdge(cell, BoardCell(size - 1 to max(0, cell.n - 1)))
            }
    }

    private fun BoardBuilder.addLeftWall(size: Int): BoardBuilder {
        return List.range(0, size + 1)
            .map { LeftWallCell(it) }
            .fold(this) { acc, cell ->
                acc
                    .addEdge(cell, BoardCell(min(cell.n, size - 1) to 0))
                    .addEdge(cell, BoardCell(max(0, cell.n - 1) to 0))
            }
    }

    private fun BoardBuilder.addRightWall(size: Int): BoardBuilder {
        return List.range(0, size + 1)
            .map { RightWallCell(it) }
            .fold(this) { acc, cell ->
                acc
                    .addEdge(cell, BoardCell(min(cell.n, size - 1) to size - 1))
                    .addEdge(cell, BoardCell(max(0, cell.n - 1) to size - 1))
            }
    }


}