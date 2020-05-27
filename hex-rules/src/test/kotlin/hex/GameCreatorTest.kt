package hex

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameCreatorTest {

    @Test
    fun `should create edges from up wall`() {
        val status = GameCreator.createGame(3).getStatus()
        if (status is OngoingGameStatus) {
            val board = status.board
            assertTrue { board.hasEdgeWithUp(0 to 0, 0) }
            assertTrue { board.hasEdgeWithUp(0 to 0, 1) }
            assertTrue { board.hasEdgeWithUp(0 to 1, 1) }
            assertTrue { board.hasEdgeWithUp(0 to 1, 2) }
            assertTrue { board.hasEdgeWithUp(0 to 1, 2) }
            assertTrue { board.hasEdgeWithUp(0 to 2, 3) }
            assertFalse { board.hasEdgeWithUp(0 to 3, 3) }
        } else {
            fail()
        }

    }

    @Test
    fun `should create edges from down wall`() {
        val status = GameCreator.createGame(3).getStatus()
        if (status is OngoingGameStatus) {
            val board = status.board
            assertTrue { board.hasEdgeWithDown(2 to 0, 0) }
            assertTrue { board.hasEdgeWithDown(2 to 0, 1) }
            assertTrue { board.hasEdgeWithDown(2 to 1, 1) }
            assertTrue { board.hasEdgeWithDown(2 to 2, 2) }
            assertTrue { board.hasEdgeWithDown(2 to 1, 2) }
            assertTrue { board.hasEdgeWithDown(2 to 2, 3) }
            assertFalse { board.hasEdgeWithDown(2 to 3, 3) }
        } else {
            fail()
        }
    }

    @Test
    fun `should create edges from left wall`() {
        val status = GameCreator.createGame(3).getStatus()
        if (status is OngoingGameStatus) {
            val board = status.board
            assertTrue { board.hasEdgeWithLeft(0 to 0, 0) }
            assertTrue { board.hasEdgeWithLeft(0 to 0, 1) }
            assertTrue { board.hasEdgeWithLeft(1 to 0, 1) }
            assertTrue { board.hasEdgeWithLeft(1 to 0, 2) }
            assertTrue { board.hasEdgeWithLeft(2 to 0, 2) }
            assertTrue { board.hasEdgeWithLeft(2 to 0, 3) }
            assertFalse { board.hasEdgeWithDown(3 to 0, 3) }
        } else {
            fail()
        }
    }

    @Test
    fun `should create edges from right wall`() {
        val status = GameCreator.createGame(3).getStatus()
        if (status is OngoingGameStatus) {
            val board = status.board

            assertTrue { board.hasEdgeWithRight(0 to 2, 0) }
            assertTrue { board.hasEdgeWithRight(0 to 2, 1) }
            assertTrue { board.hasEdgeWithRight(1 to 2, 1) }
            assertTrue { board.hasEdgeWithRight(1 to 2, 2) }
            assertTrue { board.hasEdgeWithRight(2 to 2, 2) }
            assertTrue { board.hasEdgeWithRight(2 to 2, 3) }
            assertFalse { board.hasEdgeWithRight(3 to 2, 3) }
        } else {
            fail()
        }
    }

    @Test
    fun `should create edges from central cell`() {
        val status = GameCreator.createGame(3).getStatus()
        if (status is OngoingGameStatus) {
            val board = status.board
            assertTrue { board.hasEdge(1 to 1, 0 to 0) }
            assertTrue { board.hasEdge(1 to 1, 0 to 1) }
            assertTrue { board.hasEdge(1 to 1, 1 to 0) }
            assertTrue { board.hasEdge(1 to 1, 1 to 2) }
            assertTrue { board.hasEdge(1 to 1, 2 to 1) }
            assertTrue { board.hasEdge(1 to 1, 2 to 2) }
            assertFalse { board.hasEdge(1 to 1, 1 to 1) }
        } else {
            fail()
        }
    }

    @Test
    fun `should create edges from cell next to wall`() {
        val status = GameCreator.createGame(3).getStatus()
        if (status is OngoingGameStatus) {
            val board = status.board
            assertTrue { board.hasEdge(0 to 1, 0 to 0) }
            assertTrue { board.hasEdge(0 to 1, 1 to 1) }
            assertTrue { board.hasEdge(0 to 1, 1 to 2) }
            assertTrue { board.hasEdge(0 to 1, 0 to 2) }
            assertTrue { board.hasEdgeWithUp(0 to 1, 1) }
            assertTrue { board.hasEdgeWithUp(0 to 1, 2) }

            assertTrue { board.adjacencyMap[BoardCell(0 to 1)].get().size() == 6 }
        } else {
            fail()
        }
    }

}