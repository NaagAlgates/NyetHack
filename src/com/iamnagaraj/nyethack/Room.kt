package com.iamnagaraj.nyethack

open class Room(val name: String) {
    protected open val dangerLevel = 5

    init {
        require(name.isNotEmpty()) { "Name cannot be blank" }
    }

    fun description() = "Room: $name" + " Danger Level: $dangerLevel"
    open fun load() = "Nothing much to see here"
}

class TownSquare : Room("Town Square") {
    override
    fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"
    override
    val dangerLevel  = super.dangerLevel-1
    private var bellSound = "GOWNG"
    private fun ringBell()="The bell tower announces your arrival. $bellSound"
}