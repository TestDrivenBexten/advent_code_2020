package day_07

import java.util.regex.Pattern

data class Bag(val name: String, val bagCapacityList: MutableList<BagCapacity>)
data class BagCapacity(val name: String, val quantity: Int)

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
            if(!matcher.matches()){
                return
            }
            val quantity = matcher.group(1).toInt()
            val childName = matcher.group(2)
            addChildBag(bagName,childName,quantity)
        }
    }

    fun canBagFitInBag(parentBag: String, childBag: String): Boolean{
        val bag = bagList.find { bag -> bag.name == parentBag }
        val capacity = bag?.bagCapacityList?.find { bagCapacity -> bagCapacity.name == childBag }
        return capacity?.quantity!! > 0
    }

    private fun addBag(bagName: String){
        if(!hasBagByName(bagName)){
            val newBag = Bag(bagName, mutableListOf())
            bagList.add(newBag)
        }
    }

    private fun addChildBag(parentBagName: String, childBagName: String, childQuantity: Int){
        addBag(childBagName)
        val bagCapacity = BagCapacity(childBagName,childQuantity)
        val parentBag = bagList.find { bag -> bag.name == parentBagName }
        parentBag?.bagCapacityList?.add(bagCapacity)
    }

    private fun hasBagByName(bagName: String): Boolean{
        val bag = bagList.find { bag -> bag.name == bagName }
        return bag !== null
    }
}