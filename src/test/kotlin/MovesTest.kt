import game.*
import io.vavr.collection.List
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MovesTest {

    @Test
    fun `should place red piece`() {
        val board = BoardCreator.createBoard(3)
        val movePoint = Point(1, 1)
        val move = NormalMove.red(movePoint)

        val moveResult = board.makeMove(move)

        assertTrue { moveResult is Success }
        assertTrue { (moveResult as Success).board.getPieceAt(movePoint) == RedPiece }
    }

    @Test
    fun `should not place blue piece as first`() {
        val board = BoardCreator.createBoard(3)
        val movePoint = Point(1, 1)
        val move = NormalMove.blue(movePoint)

        val moveResult = board.makeMove(move)

        assertTrue { moveResult is ErrorMove }
        assertTrue { (moveResult as ErrorMove).e == ErrorMove.Error.WRONG_TURN }
    }

    @Test
    fun `cannot place piece on occupied space`() {
        val movePoint = Point(1, 1)
        val move = NormalMove.red(movePoint)
        val (board) = BoardCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(NormalMove.blue(movePoint))

        assertTrue { moveResult is ErrorMove }
        assertTrue { (moveResult as ErrorMove).e == ErrorMove.Error.TAKEN_PLACE }
    }

    @Test
    fun `should blue place as second move`() {
        val movePoint = Point(1, 1)
        val move = NormalMove.red(movePoint)
        val blueMove = NormalMove.blue(Point(1, 0))
        val (board) = BoardCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(blueMove)

        assertTrue { moveResult is Success }
        assertTrue { (moveResult as Success).board.getPieceAt(blueMove.point) == BluePiece }
    }

    @Test
    fun `should blue switch colors as first move`() {
        val redMovePoint = Point(1, 1)
        val move = NormalMove.red(redMovePoint)
        val (board) = BoardCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(SwitchMove)

        assertTrue { moveResult is Success }
        assertTrue { (moveResult as Success).board.getPieceAt(redMovePoint) == BluePiece }
    }

    @Test
    fun `should not switch colors if not first move`() {
        val move1 = NormalMove.red(Point(1, 1))
        val move2 = NormalMove.blue(Point(0, 1))
        val move3 = NormalMove.red(Point(2, 1))
        val moves = List.of(move1, move2, move3)

        val board = moves
            .fold(BoardCreator.createBoard(3)) { board, move ->
                (board.makeMove(move) as Success).board
            }

        val moveResult = board.makeMove(SwitchMove)

        assertTrue { moveResult is ErrorMove }
        assertTrue { (moveResult as ErrorMove).e == ErrorMove.Error.SWITCH_AFTER_FIRST_TURN }
    }

}