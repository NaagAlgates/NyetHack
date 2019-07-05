package com.iamnagaraj.nyethack

import java.lang.Exception

fun main() {
    var swordJuggling:Int?=null
    val isJugglingProficient = (1..3).shuffled().last()==3
    if(isJugglingProficient){
        swordJuggling = 2;
    }
    try {
        proficiencyCheck(swordJuggling)
        swordJuggling = swordJuggling!!.plus(1)

        println("You juggle $swordJuggling swords")
    }catch (e:Exception){
        println(e)
    }
}

fun proficiencyCheck(swordJuggling:Int?){
   /* swordJuggling?:throw IllegalStateException("com.iamnagaraj.nyethack.Player cannot juggle swords")*/
//    swordJuggling?:throw UnSkilledSwordJugglerException()
    checkNotNull(swordJuggling,{"com.iamnagaraj.nyethack.Player cannot juggle swords"})
}