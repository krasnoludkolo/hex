package console

import HexUI
import hex.Board
import hex.Piece
import hex.RedPiece
import hex.status.FullGameTurn
import hex.status.GameHistory
import hex.status.HalfGameTurn

internal class ConsoleUI : HexUI {

    private val EMPTY = 0
    private val RED = 1
    private val BLUE = 2

    override fun drawBoard(board: Board) {
        val emptyBoard = List(board.boardSize) { List(board.boardSize) { EMPTY }.toMutableList() }.toMutableList()
        //TODO immutable
        board.piecesMap.forEach { emptyBoard[it._1.y][it._1.x] = it._2.toCode() }
        emptyBoard
            .forEachIndexed { index, list -> drawRow(list, board.boardSize, index) }
    }

    override fun drawHistory(gameHistory: GameHistory) {
        gameHistory
            .turns
            .mapIndexed { index, it ->
                when (it) {
                    is FullGameTurn -> "$index. ${it.redMove} ${it.blueMove}"
                    is HalfGameTurn -> "$index. ${it.redMove}"
                }
            }
            .forEach {
                println(it)
            }
    }

    private fun drawRow(row: List<Int>, size: Int, rowIndex: Int) {
        println(
            emptySpaces(size - rowIndex) + row.fold("", { acc, i -> acc + codeToSign(i) + " " })
        )
    }

    private fun codeToSign(i: Int): String {
        return when (i) {
            RED -> "X"
            BLUE -> "0"
            else -> "*"
        }
    }

    private fun emptySpaces(i: Int): String {
        return List(i) { " " }.joinToString(separator = "") { it }
    }

    private fun Piece.toCode(): Int {
        return if (this == RedPiece) RED else BLUE
    }
}

