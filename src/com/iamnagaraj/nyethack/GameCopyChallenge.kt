package com.iamnagaraj.nyethack

fun main() {
    val name = "Mandrigal"
    val healthPoints = 89
    val isBlessed = true
    val isImmortal = false
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()
    val statusFormatString = "(HP) (A) -> H"
    val auraColor = if (auraVisible) when (karma) {
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        else -> "GREEN"
    } else "NONE"
    val healthStatus = formatHealthStatus(healthPoints, isBlessed)
    println("(Aura: $auraColor) (Blessed: ${if (isBlessed) "YES" else "NO"})")
    //println("Aura: $auraColor Blessed: ${if(isBlessed) "YES" else "NO"}")
    println(statusFormatString
        .replace("HP", "HP: $healthPoints")
        .replace("A", "Aura: $auraColor")
        .replace("H", healthStatus))
    println("$name $healthStatus")
}

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean): String {
    val healthStatus = when (healthPoints) {
        100 -> "is in excellent condition"
        in 90..99 -> "has a few scratches"
        in 75..89 -> if (isBlessed) "has some minor wounds but is healing quite quickly!"
        else "has some minor wounds"
        in 15..74 -> "looks pretty hurt"
        else -> "is in awful condition"
    }
    return healthStatus
}