import game.*
import io.vavr.collection.List
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

internal class MovesTest {

    @Test
    fun `should place red piece`() {
        val board = GameCreator.createBoard(3)
        val movePoint = Point(1, 1)
        val move = NormalMove.red(movePoint)

        val moveResult = board.makeMove(move)

        if (moveResult is Success) {
            val status = moveResult.game.getStatus()
            if (status is OngoingGameStatus)
                assertTrue { status.board.getPieceAt(movePoint) == RedPiece }
        } else {
            fail()
        }

    }

    @Test
    fun `should not place blue piece as first`() {
        val board = GameCreator.createBoard(3)
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
        val (board) = GameCreator
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
        val (board) = GameCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(blueMove)

        if (moveResult is Success) {
            val status = moveResult.game.getStatus()
            if (status is OngoingGameStatus)
                assertTrue { status.board.getPieceAt(blueMove.point) == BluePiece }
        } else {
            fail()
        }
    }

    @Test
    fun `should blue switch colors as first move`() {
        val redMovePoint = Point(1, 1)
        val move = NormalMove.red(redMovePoint)
        val (board) = GameCreator
            .createBoard(3)
            .makeMove(move) as Success

        val moveResult = board.makeMove(SwitchMove)

        if (moveResult is Success) {
            val status = moveResult.game.getStatus()
            if (status is OngoingGameStatus)
                assertTrue { status.board.getPieceAt(redMovePoint) == BluePiece }
        } else {
            fail()
        }
    }

    @Test
    fun `should not switch colors if not first move`() {
        val move1 = NormalMove.red(Point(1, 1))
        val move2 = NormalMove.blue(Point(0, 1))
        val move3 = NormalMove.red(Point(2, 1))
        val moves = List.of(move1, move2, move3)

        val board = moves
            .fold(GameCreator.createBoard(3)) { board, move ->
                (board.makeMove(move) as Success).game
            }

        val moveResult = board.makeMove(SwitchMove)

        assertTrue { moveResult is ErrorMove }
        assertTrue { (moveResult as ErrorMove).e == ErrorMove.Error.SWITCH_IN_WRONG_TURN }
    }

}