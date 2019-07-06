package com.iamnagaraj.nyethack

import java.lang.Exception
import java.lang.IllegalStateException

fun main() {
    //val name = "Madrigal"
    Game.play()
}


private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()})" + "(Blessed:${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.healthPoints}")
}

object Game {
    val player = Player("Madrigal")
    var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"),Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer")
        player.catFireball()
    }

    fun play() {
        while (true) {
            //play NyetHack
            println("Application Version: ${Constants.APP_VERSION}")
            println("Developer Name: ${Constants.DEVELOPER_NAME}")
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus(player)
            println(">Enter your command")
            println(GameInput(readLine()).processCommand())
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })
        fun processCommand() = when (command.toLowerCase().also { println(command.toLowerCase()) }) {
            "move"-> move(argument)
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move(directionInput:String)=
        try{
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosistion = direction.updateCoordinate(player.currentPosition)
            if(!newPosistion.isInBounds){
                throw IllegalStateException("$direction is out of bounds")
            }
            val newRoom = worldMap[newPosistion.y][newPosistion.x]
            player.currentPosition = newPosistion
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"

        }catch (e:Exception){
            "Invalid direction: $directionInput"
        }
}

