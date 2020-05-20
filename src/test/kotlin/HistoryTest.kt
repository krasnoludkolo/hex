import io.vavr.collection.List
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class HistoryTest {

    @Test
    fun `history should contains all moves`() {
        val move1 = Move.red(Point(1, 1))
        val move2 = Move.blue(Point(0, 1))
        val move3 = Move.red(Point(2, 1))
        val moves = List.of(move1, move2, move3)

        val board = moves
            .fold(BoardCreator.createBoard(3)) { board, move ->
                (board.makeMove(move) as Success).board
            }

        assertTrue { board.history.containsAll(moves) }
    }

}
