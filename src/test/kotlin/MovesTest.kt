import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MovesTest {

    @Test
    fun `should place red piece`() {
        val board = BoardCreator.createBoard(3)
        val movePoint = Point(1, 1)
        val move = Move.red(movePoint)

        val moveResult = board.makeMove(move)

        assertTrue { moveResult is Success }
        assertTrue { (moveResult as Success).board.getPieceAt(movePoint) == RedPiece }
    }

    @Test
    fun `should not place blue piece as first`() {
        val board = BoardCreator.createBoard(3)
        val movePoint = Point(1, 1)
        val move = Move.blue(movePoint)

        val moveResult = board.makeMove(move)

        assertTrue { moveResult is ErrorMove }
        assertTrue { (moveResult as ErrorMove).e == ErrorMove.Error.WRONG_TURN }
    }

    @Test
    fun `cannot place piece on occupied space`() {
        val movePoint = Point(1, 1)
        val move = Move.red(movePoint)
        val (board) = BoardCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(Move.blue(movePoint))

        assertTrue { moveResult is ErrorMove }
        assertTrue { (moveResult as ErrorMove).e == ErrorMove.Error.TAKEN_PLACE }
    }

    @Test
    fun `should blue place as second move`() {
        val movePoint = Point(1, 1)
        val move = Move.red(movePoint)
        val blueMove = Move.blue(Point(1, 0))
        val (board) = BoardCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(blueMove)

        assertTrue { moveResult is Success }
        assertTrue { (moveResult as Success).board.getPieceAt(blueMove.point) == BluePiece }
    }

}