import io.vavr.collection.List
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BoardCreatorTest {

    @Test
    fun `should add up wall`() {
        val board = BoardCreator.createBoard(3)

        assertTrue { board.adjacencyMap[UpWallCell(1)].isDefined }
        assertTrue { board.adjacencyMap[UpWallCell(2)].isDefined }
        assertTrue { board.adjacencyMap[UpWallCell(3)].isDefined }
        assertTrue { board.adjacencyMap[UpWallCell(4)].isDefined }
    }

    @Test
    fun `should create edges from up wall`() {
        val board = BoardCreator.createBoard(3)

        assertTrue { board.hasEdgeWithUp(1 to 1,1) }
        assertTrue { board.hasEdgeWithUp(1 to 1,2) }
        assertTrue { board.hasEdgeWithUp(1 to 2,2) }
        assertTrue { board.hasEdgeWithUp(1 to 2,3) }
        assertTrue { board.hasEdgeWithUp(1 to 2,3) }
        assertTrue { board.hasEdgeWithUp(1 to 3,4) }

    }

    @Test
    fun `should create edges from down wall`() {
        val board = BoardCreator.createBoard(3)

        assertTrue { board.hasEdgeWithDown(3 to 1,1) }
        assertTrue { board.hasEdgeWithDown(3 to 1,2) }
        assertTrue { board.hasEdgeWithDown(3 to 2,2) }
        assertTrue { board.hasEdgeWithDown(3 to 3,3) }
        assertTrue { board.hasEdgeWithDown(3 to 2,3) }
        assertTrue { board.hasEdgeWithDown(3 to 3,4) }
    }

    @Test
    fun `should create edges from left wall`() {
        val board = BoardCreator.createBoard(3)

        assertTrue { board.hasEdgeWithLeft(1 to 1,1) }
        assertTrue { board.hasEdgeWithLeft(1 to 1,2) }
        assertTrue { board.hasEdgeWithLeft(2 to 1,2) }
        assertTrue { board.hasEdgeWithLeft(2 to 1,3) }
        assertTrue { board.hasEdgeWithLeft(3 to 1,3) }
        assertTrue { board.hasEdgeWithLeft(3 to 1,4) }
    }

    @Test
    fun `should create edges from right wall`() {
        val board = BoardCreator.createBoard(3)

        assertTrue { board.hasEdgeWithRight(1 to 3,1) }
        assertTrue { board.hasEdgeWithRight(1 to 3,2) }
        assertTrue { board.hasEdgeWithRight(2 to 3,2) }
        assertTrue { board.hasEdgeWithRight(2 to 3,3) }
        assertTrue { board.hasEdgeWithRight(3 to 3,3) }
        assertTrue { board.hasEdgeWithRight(3 to 3,4) }
    }


}