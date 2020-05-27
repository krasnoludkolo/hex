package main

import hex.*
import random.RandomBot

fun main() {
    val red = RandomBot("red", RedHexPlayer)
    val blue = RandomBot("blue", BlueHexPlayer)

    val (_, winner, history) = playGame(red, blue, 3)

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

}