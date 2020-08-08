package hex

import hex.status.OngoingGameStatus

interface Player {

    fun makeMove(status: OngoingGameStatus): Move
    fun getName(): String

}