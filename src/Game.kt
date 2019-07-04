fun main() {
    //val name = "Madrigal"

    val player = Player()
    player.catFireball(5)
    val auraColor = player.auraColor()
    val healthStatus = player.formatHealthStatus()
    printPlayerStatus(auraColor,player.isBlessed,player.name,healthStatus)
}



private fun printPlayerStatus(auraColor:String,isBlesed:Boolean,name:String,healthStatus:String){
    println("(Aura: $auraColor)" + "(Blessed:${if (isBlesed) "YES" else "NO"})")
    println("$name $healthStatus")
}

