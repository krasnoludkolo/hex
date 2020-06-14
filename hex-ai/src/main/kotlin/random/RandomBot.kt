package random

import hex.*

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