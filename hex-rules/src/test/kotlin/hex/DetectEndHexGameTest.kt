package hex

import hex.status.EndedGameStatus
import hex.status.OngoingGameStatus
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DetectEndHexGameTest {

    @Test
    fun `should not detect ongoing game end`() {
        val game = GameCreator.createGame(3)
            .makeMoves(
                red(1, 1)
            )

        val status = game.getStatus()

        assertTrue { status is OngoingGameStatus }
    }

    @Test
    fun `should detect game end and show red winner`() {
        val game = GameCreator.createGame(3)
            .makeMoves(
                red(1, 1),
                blue(1, 0),
                red(0, 0),
                blue(2, 0),
                red(2, 2)
            )

        val status = game.getStatus()

        assertTrue { status is EndedGameStatus }
        assertTrue { (status as EndedGameStatus).winner == RedHexPlayer }
    }

    @Test
    fun `should detect game end and show blue winner`() {
        val game = GameCreator.createGame(3)
            .makeMoves(
                red(1, 1),
                switch(),
                red(0, 1),
                blue(2, 2),
                red(2, 1),
                blue(0, 0)
            )

        val status = game.getStatus()

        assertTrue { status is EndedGameStatus }
        assertTrue { (status as EndedGameStatus).winner == BlueHexPlayer }
    }

}