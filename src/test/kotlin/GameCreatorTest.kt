import game.BoardCell
import game.GameCreator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class GameCreatorTest {

    @Test
    fun `should create edges from up wall`() {
        val board = GameCreator.createBoard(3).getBoard()

        assertTrue { board.hasEdgeWithUp(0 to 0, 0) }
        assertTrue { board.hasEdgeWithUp(0 to 0, 1) }
        assertTrue { board.hasEdgeWithUp(0 to 1, 1) }
        assertTrue { board.hasEdgeWithUp(0 to 1, 2) }
        assertTrue { board.hasEdgeWithUp(0 to 1, 2) }
        assertTrue { board.hasEdgeWithUp(0 to 2, 3) }
        assertFalse { board.hasEdgeWithUp(0 to 3, 3) }

    }

    @Test
    fun `should create edges from down wall`() {
        val board = GameCreator.createBoard(3).getBoard()

        assertTrue { board.hasEdgeWithDown(2 to 0, 0) }
        assertTrue { board.hasEdgeWithDown(2 to 0, 1) }
        assertTrue { board.hasEdgeWithDown(2 to 1, 1) }
        assertTrue { board.hasEdgeWithDown(2 to 2, 2) }
        assertTrue { board.hasEdgeWithDown(2 to 1, 2) }
        assertTrue { board.hasEdgeWithDown(2 to 2, 3) }
        assertFalse { board.hasEdgeWithDown(2 to 3, 3) }
    }

    @Test
    fun `should create edges from left wall`() {
        val board = GameCreator.createBoard(3).getBoard()

        assertTrue { board.hasEdgeWithLeft(0 to 0, 0) }
        assertTrue { board.hasEdgeWithLeft(0 to 0, 1) }
        assertTrue { board.hasEdgeWithLeft(1 to 0, 1) }
        assertTrue { board.hasEdgeWithLeft(1 to 0, 2) }
        assertTrue { board.hasEdgeWithLeft(2 to 0, 2) }
        assertTrue { board.hasEdgeWithLeft(2 to 0, 3) }
        assertFalse { board.hasEdgeWithDown(3 to 0, 3) }
    }

    @Test
    fun `should create edges from right wall`() {
        val board = GameCreator.createBoard(3).getBoard()

        assertTrue { board.hasEdgeWithRight(0 to 2, 0) }
        assertTrue { board.hasEdgeWithRight(0 to 2, 1) }
        assertTrue { board.hasEdgeWithRight(1 to 2, 1) }
        assertTrue { board.hasEdgeWithRight(1 to 2, 2) }
        assertTrue { board.hasEdgeWithRight(2 to 2, 2) }
        assertTrue { board.hasEdgeWithRight(2 to 2, 3) }
        assertFalse { board.hasEdgeWithRight(3 to 2, 3) }
    }

    @Test
    fun `should create edges from central cell`() {
        val board = GameCreator.createBoard(3).getBoard()

        assertTrue { board.hasEdge(1 to 1, 0 to 0) }
        assertTrue { board.hasEdge(1 to 1, 0 to 1) }
        assertTrue { board.hasEdge(1 to 1, 1 to 0) }
        assertTrue { board.hasEdge(1 to 1, 1 to 2) }
        assertTrue { board.hasEdge(1 to 1, 2 to 1) }
        assertTrue { board.hasEdge(1 to 1, 2 to 2) }
        assertFalse { board.hasEdge(1 to 1, 1 to 1) }
    }

    @Test
    fun `should create edges from cell next to wall`() {
        val board = GameCreator.createBoard(3).getBoard()

        assertTrue { board.hasEdge(0 to 1, 0 to 0) }
        assertTrue { board.hasEdge(0 to 1, 1 to 1) }
        assertTrue { board.hasEdge(0 to 1, 1 to 2) }
        assertTrue { board.hasEdge(0 to 1, 0 to 2) }
        assertTrue { board.hasEdgeWithUp(0 to 1, 1) }
        assertTrue { board.hasEdgeWithUp(0 to 1, 2) }

        assertTrue { board.adjacencyMap[BoardCell(0 to 1)].get().size() == 6 }
    }


}