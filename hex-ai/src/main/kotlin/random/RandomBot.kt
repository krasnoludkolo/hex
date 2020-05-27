package random

import hex.*

class RandomBot(
    private val name: String,
    private val hexPlayer: HexPlayer
) : Player {

    override fun makeMove(status: OngoingGameStatus): Move {
        val cell = status
            .board
            .adjacencyMap
            .keySet()
            .filterIsInstance<BoardCell>()
            .filter { !status.board.piecesMap.keySet().contains(it.point) }
            .shuffled()
            .first()
        return NormalMove(hexPlayer, cell.point)
    }

    override fun getName() = name
}