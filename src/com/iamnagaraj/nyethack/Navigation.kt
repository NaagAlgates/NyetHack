package com.iamnagaraj.nyethack

data class Coordinate(val x: Int, val y: Int) {
    val isInBounds = x >= 0 && y >= 0

    operator fun plus(other:Coordinate) = Coordinate(x+other.x,y+other.y)
}

enum class Direction(private val coordinate: Coordinate) {
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    WEST(Coordinate(0, 1)),
    SOUTH(Coordinate(-1, 0));

    fun updateCoordinate(playerCoordinate: Coordinate) =
       coordinate+playerCoordinate
}