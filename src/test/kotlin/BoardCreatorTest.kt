import io.vavr.collection.List
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BoardCreatorTest{

    @Test
    fun `should add up wall`(){
        val board = BoardCreator.createBoard(3)

        assertTrue {
            board.adjacencyMap[UpWallCell(1)].isDefined &&
            board.adjacencyMap[UpWallCell(2)].isDefined &&
            board.adjacencyMap[UpWallCell(3)].isDefined &&
            board.adjacencyMap[UpWallCell(4)].isDefined
        }
    }

    @Test
    fun `should create edges from up wall`(){
        val board = BoardCreator.createBoard(3)

        assertTrue {
            board.adjacencyMap[UpWallCell(1)].get().contains(BoardCell(1 to 1)) &&
            board.adjacencyMap[UpWallCell(2)].get().containsAll(List.of(BoardCell(1 to 1), BoardCell(1 to 2))) &&
            board.adjacencyMap[UpWallCell(3)].get().containsAll(List.of(BoardCell(1 to 2), BoardCell(1 to 3))) &&
            board.adjacencyMap[UpWallCell(4)].get().contains(BoardCell(1 to 3))
        }
    }

    @Test
    fun `should create edges from down wall`(){
        val board = BoardCreator.createBoard(3)

        assertTrue {
            board.adjacencyMap[DownWallCell(1)].get().contains(BoardCell(3 to 1)) &&
            board.adjacencyMap[DownWallCell(2)].get().containsAll(List.of(BoardCell(3 to 1), BoardCell(3 to 2))) &&
            board.adjacencyMap[DownWallCell(3)].get().containsAll(List.of(BoardCell(3 to 2), BoardCell(3 to 3))) &&
            board.adjacencyMap[DownWallCell(4)].get().contains(BoardCell(3 to 3))
        }
    }

    @Test
    fun `should create edges from left wall`(){
        val board = BoardCreator.createBoard(3)

        assertTrue {
            board.adjacencyMap[LeftWallCell(1)].get().contains(BoardCell(1 to 1)) &&
            board.adjacencyMap[LeftWallCell(2)].get().containsAll(List.of(BoardCell(1 to 1), BoardCell(2 to 1))) &&
            board.adjacencyMap[LeftWallCell(3)].get().containsAll(List.of(BoardCell(2 to 1), BoardCell(3 to 1))) &&
            board.adjacencyMap[LeftWallCell(4)].get().contains(BoardCell(3 to 1))
        }
    }

    @Test
    fun `should create edges from right wall`(){
        val board = BoardCreator.createBoard(3)

        assertTrue {
            board.adjacencyMap[RightWallCell(1)].get().contains(BoardCell(1 to 3)) &&
            board.adjacencyMap[RightWallCell(2)].get().containsAll(List.of(BoardCell(1 to 3), BoardCell(1 to 3))) &&
            board.adjacencyMap[RightWallCell(3)].get().containsAll(List.of(BoardCell(2 to 3), BoardCell(3 to 3))) &&
            board.adjacencyMap[RightWallCell(4)].get().contains(BoardCell(3 to 3))
        }
    }


}