import game.*
import io.vavr.collection.List
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class HistoryTest {

    @Test
    fun `history should contains all moves`() {
        val move1 = red(1, 1)
        val move2 = blue(0, 1)
        val move3 = NormalMove.red(Point(2, 1))
        val moves = List.of(move1, move2, move3)

        val game = moves
            .fold(GameCreator.createGame(3)) { board, move ->
                (board.makeMove(move) as Success).game
            }

        assertTrue { game.getHistory().containsAll(moves) }
    }

    @Test
    fun `history should contains all moves with switch`() {
        val move1 = NormalMove.red(Point(1, 1))
        val move2 = SwitchMove
        val move3 = NormalMove.red(Point(2, 1))
        val move4 = blue(0, 1)
        val moves = List.of(move1, move2, move3, move4)

        val game = moves
            .fold(GameCreator.createGame(3)) { board, move ->
                (board.makeMove(move) as Success).game
            }

        assertTrue { game.getHistory().containsAll(moves) }
    }

}
