package com.iamnagaraj.nyethack

const val MAX_EXPERIENCE:Int = 5000
fun main() {
    val playerName = "Estragon"
    var experiencePoints =5
    var hasSteed= false
    var pubName = "Unicor\'s Horn"
    var publicanName = ""
    var gold = 50
    val drink = listOf<String>("mead","wine","LaCroix")
    experiencePoints+=5
    println(experiencePoints)
    println(playerName)
    if(!hasSteed)
        println("The player does not have a steed yet.")
    println(playerName.reversed())
}