import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BoardTest {

    @Test
    fun `should create add edge`() {
        val board = Board.BoardBuilder()
            .addEdge(BoardCell(1 to 1), BoardCell(1 to 2))
            .build()

        assertTrue {
            board.adjacencyMap[BoardCell(1 to 1)].get().contains(BoardCell(1 to 2)) &&
                    board.adjacencyMap[BoardCell(1 to 2)].get().contains(BoardCell(1 to 1))
        }


    }

    @Test
    fun `should add edges from one vertex to many`() {
        val board = Board.BoardBuilder()
            .addEdge(BoardCell(1 to 1), BoardCell(1 to 2))
            .addEdge(BoardCell(1 to 1), BoardCell(1 to 3))
            .build()

        assertTrue {
            board.adjacencyMap[BoardCell(1 to 1)].get().contains(BoardCell(1 to 2)) &&
                    board.adjacencyMap[BoardCell(1 to 1)].get().contains(BoardCell(1 to 3))
        }
    }

    @Test
    fun `should create edges from wall cell to board cell`() {
        val board = Board.BoardBuilder()
            .addEdge(BoardCell(1 to 1), BoardCell(1 to 2))
            .addEdge(UpWallCell(1), BoardCell(1 to 1))
            .addEdge(UpWallCell(2), BoardCell(1 to 1))
            .addEdge(UpWallCell(2), BoardCell(1 to 2))
            .build()

        assertTrue {
            board.adjacencyMap[BoardCell(1 to 1)].get().contains(BoardCell(1 to 2)) &&
                    board.adjacencyMap[BoardCell(1 to 1)].get().contains(UpWallCell(1)) &&
                    board.adjacencyMap[BoardCell(1 to 2)].get().contains(UpWallCell(2)) &&
                    board.adjacencyMap[BoardCell(1 to 2)].get().contains(BoardCell(1 to 1))
        }
    }

}