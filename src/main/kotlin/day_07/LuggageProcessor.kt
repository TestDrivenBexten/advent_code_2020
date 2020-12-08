package day_07

import java.util.regex.Pattern

data class Bag(val name: String, val bagCapacityList: MutableList<BagCapacity>)
data class BagCapacity(val bag: Bag, val quantity: Int)

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
        val parentBag = addBag(bagName)

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
            parentBag?.let { addChildBag(it,childName,quantity) }
        }
    }

    fun canBagFitInBag(parentBagName: String, childBagName: String): Boolean{
        val parentBag = bagList.find { bag -> bag.name == parentBagName }
        val childBag = bagList.find { bag -> bag.name == childBagName }
        val capacity = parentBag?.let { searchForBagCapacity(it,childBag) }
        return capacity !== null
    }

    fun bagColorList(): MutableList<Bag> {
        return bagList
    }

    private fun searchForBagCapacity(parentBag: Bag, childBag: Bag?): BagCapacity?{
        val bagCapacity = parentBag.bagCapacityList.find { bagCapacity -> bagCapacity.bag == childBag }
        if (bagCapacity === null){
            val bagCapacityList = parentBag.bagCapacityList.map {
                capacity -> searchForBagCapacity(capacity.bag,childBag)
            }
            return bagCapacityList.find { it !== null }
        }else {
            return bagCapacity
        }
    }

    private fun addBag(bagName: String): Bag? {
        if(!hasBagByName(bagName)){
            val newBag = Bag(bagName, mutableListOf())
            bagList.add(newBag)
            return newBag
        } else {
            return bagList.find { bag -> bag.name == bagName }
        }
    }

    private fun addChildBag(parentBag: Bag, childBagName: String, childQuantity: Int){
        val childBag = addBag(childBagName)
        val bagCapacity = childBag?.let { BagCapacity(it,childQuantity) }
        bagCapacity?.let { parentBag.bagCapacityList.add(it) }
    }

    private fun hasBagByName(bagName: String): Boolean{
        val bag = bagList.find { bag -> bag.name == bagName }
        return bag !== null
    }
}