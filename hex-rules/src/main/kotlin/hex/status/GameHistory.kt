package hex.status

import hex.Move

data class GameHistory(
    val turns: List<GameTurn>
) {
    fun addMove(move: Move): GameHistory {
        return when (val last = turns.last()) {
            is HalfGameTurn -> {
                copy(turns = turns.dropLast(1) + last.addBlueMove(move))
            }
            else -> {
                copy(turns = turns + HalfGameTurn(move))
            }
        }
    }

    companion object {
        fun withMove(move: Move) = GameHistory(listOf(HalfGameTurn(move)))
        fun empty() = GameHistory(emptyList())
    }
}

sealed class GameTurn

data class HalfGameTurn(
    val redMove: Move
) : GameTurn() {
    fun addBlueMove(move: Move) = FullGameTurn(redMove, move)
}

data class FullGameTurn(
    val redMove: Move,
    val blueMove: Move
) : GameTurn()