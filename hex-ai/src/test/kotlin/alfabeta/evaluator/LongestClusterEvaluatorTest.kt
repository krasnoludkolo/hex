package alfabeta.evaluator

import hex.GameCreator
import hex.status.OngoingGameStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class LongestClusterEvaluatorTest {

    @Test
    fun `should evaluate board after first move`() {
        val evaluator = LongestClusterEvaluator()
        val gameStatus = GameCreator
            .createGame(3)
            .makeMoves(
                red(1, 1)
            ).getStatus() as OngoingGameStatus

        val evaluation = evaluator.evaluate(gameStatus.board)

        assertTrue { evaluation == 1.toDouble() }
    }

    @Test
    fun `should evaluate board after first moves`() {
        val evaluator = LongestClusterEvaluator()
        val gameStatus = GameCreator
            .createGame(3)
            .makeMoves(
                red(1, 1),
                blue(0, 0)
            ).getStatus() as OngoingGameStatus

        val evaluation = evaluator.evaluate(gameStatus.board)

        assertTrue { evaluation == 0.toDouble() }
    }

    @Test
    fun `should calculate longest path`() {
        val evaluator = LongestClusterEvaluator()
        val gameStatus = GameCreator
            .createGame(3)
            .makeMoves(
                red(1, 1),
                blue(0, 0),
                red(1, 0)
            ).getStatus() as OngoingGameStatus

        val evaluation = evaluator.evaluate(gameStatus.board)

        assertEquals(1.toDouble(), evaluation)
    }

}