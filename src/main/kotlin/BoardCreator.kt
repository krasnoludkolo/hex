import io.vavr.collection.List
import java.lang.Integer.max

object BoardCreator {

    fun createBoard(size: Int): Board {
        return Board.BoardBuilder()
            .addUpWall(size)
            .addDownWall(size)
            .addLeftWall(size)
            .addRightWall(size)
            .build()
    }

    private fun Board.BoardBuilder.addUpWall(size: Int): Board.BoardBuilder {
        return List.range(0, size + 1)
            .map { UpWallCell(it + 1) }
            .fold(this){acc, cell ->
                acc
                    .addEdge(cell,BoardCell(1 to cell.n))
                    .addEdge(cell, BoardCell(1 to max(1,cell.n-1)))
            }
    }

    private fun Board.BoardBuilder.addDownWall(size: Int): Board.BoardBuilder {
        return List.range(0, size + 1)
            .map { DownWallCell(it + 1) }
            .fold(this){acc, cell ->
                acc
                    .addEdge(cell,BoardCell(size to cell.n))
                    .addEdge(cell, BoardCell(size to max(1,cell.n-1)))
            }
    }

    private fun Board.BoardBuilder.addLeftWall(size: Int): Board.BoardBuilder {
        return List.range(0, size + 1)
            .map { LeftWallCell(it + 1) }
            .fold(this){acc, cell ->
                acc
                    .addEdge(cell,BoardCell(cell.n to 1))
                    .addEdge(cell, BoardCell(max(1,cell.n-1) to 1))
            }
    }

    private fun Board.BoardBuilder.addRightWall(size: Int): Board.BoardBuilder {
        return List.range(0, size + 1)
            .map { RightWallCell(it + 1) }
            .fold(this){acc, cell ->
                acc
                    .addEdge(cell,BoardCell(cell.n to size))
                    .addEdge(cell, BoardCell(max(1,cell.n-1) to size))
            }
    }

}