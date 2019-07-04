import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"
var patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fensworth", "Baggins")
var menuList = File("data/tavern-menu-items.txt").readText().split("\n")
var uniquePatrons = mutableSetOf<String>()
val patronGold = mutableMapOf<String, Double>()
fun main() {
    initialize()
    /*patronList.forEachIndexed() { index,patron ->
        println("Good evening, $patron - you're #${index+1} in the line")
        placeOrder(patron,menuList.shuffled().first())
    }
    menuList.forEachIndexed{index,data->
        println("$index $data")
    }*/

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons.plusAssign(name)
    }
    println(uniquePatrons)
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }
    var orderCount = 0
    while (orderCount <= 9) {
        if(uniquePatrons.count()>0) {
            placeOrder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
            orderCount++
        }else{
            println("Nobody in the list")
            break
        }
    }
    displayPatronBalance()
}

fun initialize() {
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards")
    } else {
        println("The tavern master says Eli isn't here")
    }
    if (patronList.containsAll(listOf("Mordoc", "Sophie"))) {
        println("The tavern master says: Yea they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }
}

fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    println("$patronName speaks with $tavernMaster about their order")
    println(message)
    if(performPurchase(price = price.toDouble(), patronName = patronName)) {
        val phrase = if (name == "Dragon's Breath") {
            "$patronName claims:  ${toDragonSpeak("Ah Delicious $name")}"
        } else {
            "$patronName says: Thanks for the $name"
        }
        println(phrase)
    }else{
        println("Patron $name booted from NyetHack")
        patronGold.remove(patronName)
        uniquePatrons.remove(patronName)
    }
}

private fun toDragonSpeak(phrase: String) = phrase.replace(Regex("[aAeEiIoOuU]")) {
    when (it.value) {
        "a" -> "4"
        "A" -> "4"
        "e" -> "3"
        "E" -> "3"
        "i" -> "1"
        "I" -> "1"
        "o" -> "0"
        "O" -> "0"
        "u" -> "|_|"
        "U" -> "|_|"
        else -> it.value
    }
}

fun performPurchase(price: Double, patronName: String): Boolean {
    val balance = patronGold.getValue(patronName) - price
    return if (balance > 0) {
        patronGold[patronName] = balance
        true
    } else {
        //patronGold.remove(patronName)
        false
    }
}

fun displayPatronBalance() {
    patronGold.forEach { (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}