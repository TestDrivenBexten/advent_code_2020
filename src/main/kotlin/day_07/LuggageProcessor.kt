package day_07

import java.util.regex.Pattern

data class Bag(val name: String)

class LuggageProcessor {
    private val bagList = mutableListOf<Bag>()

    private val regex = "(\\w+ \\w+) bags contain (.+)."
    private val childRegex = "(\\d+)\\s(\\w+\\s\\w+)\\sbags?"

    fun addBagRule(bagRule: String){
        val r = Pattern.compile(regex)
        val m = r.matcher(bagRule)
        if(!m.matches()){
            return
        }

        val bagName = m.group(1);
        addBag(bagName)

        val childBagString = m.group(2);
        val childStringList = childBagString.split(",")
        childStringList.map { childString ->
            val pattern = Pattern.compile(childRegex)
            val matcher = pattern.matcher(childString.trim())
            println(childString)
            if(!matcher.matches()){
                return
            }
            val quantity = matcher.group(1).toInt()
            val childName = matcher.group(2)
            addChildBag(childName,quantity)
        }
    }

    fun canBagFitInBag(parentBag: String, childBag: String): Boolean{
        return false;
    }

    private fun addBag(bagName: String){
        if(!hasBagByName(bagName)){
            val newBag = Bag(bagName)
            bagList.add(newBag)
        }
    }

    private fun addChildBag(childBagName: String, childQuantity: Int){
        addBag(childBagName)
    }

    private fun hasBagByName(bagName: String): Boolean{
        val bag = bagList.find { bag -> bag.name == bagName }
        return bag !== null
    }
}