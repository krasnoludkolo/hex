package hex

interface Player {

    fun makeMove(status: OngoingGameStatus): Move
    fun getName(): String

}