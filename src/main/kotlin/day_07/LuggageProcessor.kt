package day_07

data class Bag(val name: String)

class LuggageProcessor {
    val bagList = mutableListOf<Bag>();

    fun addBagRule(bagRule: String){

    }

    fun canBagFitInBag(parentBag: String, childBag: String): Boolean{
        return false;
    }
}