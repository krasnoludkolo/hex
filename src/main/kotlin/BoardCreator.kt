import io.vavr.collection.List
import java.lang.Integer.max

typealias Builder = Board.BoardBuilder

typealias WallVertexCreator = (acc: Builder, Cell) -> Builder
typealias WallCreator = (size: Int) -> WallVertexCreator


object BoardCreator {

    fun createBoard(size: Int): Board {
        return Builder()
            .addWall(size, { n -> UpWallCell(n) }, addUp)
            .addWall(size, { n -> DownWallCell(n) }, addDown)
            .addWall(size, { n -> LeftWallCell(n) }, addLeft)
            .addWall(size, { n -> RightWallCell(n) }, addRight)
            .build()
    }

    private fun Builder.addWall(
        size: Int,
        cellCreator: (n: Int) -> Cell,
        vertexCreator: WallCreator
    ): Builder {
        return List.range(0, size + 1)
            .map { cellCreator.invoke(it + 1) }
            .fold(this, vertexCreator.invoke(size))
    }

    private val addUp: WallCreator = { size ->
        { acc, cell: Cell -> acc
                .addEdge(cell, BoardCell(1 to (cell as UpWallCell).n))
                .addEdge(cell, BoardCell(1 to max(1, cell.n - 1)))
        }
    }

    private val addDown: WallCreator = { size ->
        { acc, cell -> acc
                .addEdge(cell, BoardCell(size to (cell as DownWallCell).n))
                .addEdge(cell, BoardCell(size to max(1, cell.n - 1)))
        }
    }

    private val addLeft: WallCreator = { size ->
        { acc, cell -> acc
                .addEdge(cell, BoardCell((cell as LeftWallCell).n to 1))
                .addEdge(cell, BoardCell(max(1, cell.n - 1) to 1))
        }
    }

    private val addRight: WallCreator = { size ->
        { acc, cell -> acc
                .addEdge(cell, BoardCell((cell as RightWallCell).n to size))
                .addEdge(cell, BoardCell(max(1, cell.n - 1) to size))
        }
    }

}