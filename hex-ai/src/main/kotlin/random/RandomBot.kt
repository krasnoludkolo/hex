package random

import hex.HexPlayer
import hex.Move
import hex.NormalMove
import hex.Player
import hex.status.OngoingGameStatus

class RandomBot(
    private val name: String,
    private val hexPlayer: HexPlayer
) : Player {

    override fun makeMove(status: OngoingGameStatus): Move {
        val possiblePlaces = status
            .board
            .emptyPlaces

        val cell = possiblePlaces
            .shuffled()
            .first()

        return NormalMove(hexPlayer, cell.point)
    }

    override fun getName() = name
}