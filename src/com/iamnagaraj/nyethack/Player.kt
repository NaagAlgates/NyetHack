package com.iamnagaraj.nyethack

import java.io.File

class Player(_name:String,var healthPoints:Int=100, val isBlessed:Boolean, var isImmortal:Boolean) {
    var name = _name
        get() = field.capitalize()+" of $homeTown"
    private set(value){
        field = value.trim()
    }
    private val homeTown by lazy { selectHomeTown() }

    private fun selectHomeTown() = File("data/towns.txt").readText().split("\n").shuffled().first()

    /*var healthPoints = _healthPoints
    var isBlessed = _isBlessed
    private val isImmortal =isImmortal*/
    fun catFireball(numFireballs: Int = 2) = println("A glass of Fireball springs into existence. (x$numFireballs)")

    fun formatHealthStatus()=
        when(healthPoints){
            100-> "is in excellent condition"
            in 90..99-> "ha few scratches"
            in 75..89->if(isBlessed){
                "has some minor wounds, but is healing quite quickly!"
            }else{
                "has some minor wounds."
            }
            in 15..74->"looks pretty hurt"
            else->"is in awful condition!"
        }

    fun auraColor():String{
        val auraVisible = isBlessed && healthPoints>50 || isImmortal
        var auraColor = if(auraVisible) "Green" else "None"
        return  auraColor
    }

    constructor(name:String):this(name,
        isBlessed = true,
        isImmortal = false)

    init{
        require(healthPoints>0) {"health point must be greater than zero."}
        require(name.isNotEmpty()) {"Name cannot be empty"}
    }
}