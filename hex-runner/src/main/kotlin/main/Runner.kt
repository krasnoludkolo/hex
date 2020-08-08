package main

import HexUI
import alfabeta.AlfaBeta
import alfabeta.evaluator.LongestPathEvaluator
import hex.*
import random.RandomBot

fun main() {
    val red = AlfaBeta("red", 3, RedHexPlayer, LongestPathEvaluator())
    val blue = RandomBot("blue", BlueHexPlayer)

    val (board, winner, history) = playGame(red, blue, 5)

    println("Winner: $winner")
    history
        .map {
            when (it) {
                is NormalMove -> "(${it.point.x}:${it.point.y})"
                is SwitchMove -> "S"
            }
        }
        .forEach {
            println(it)
        }

    HexUI.consoleUI().drawBoard(board)
}