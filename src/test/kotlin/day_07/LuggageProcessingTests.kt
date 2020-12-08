package day_07

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LuggageProcessingTests {

    @Test
    fun `Shiny Blue Bag Should Hold Pale Blue, Wavy Fuchsia and Pale Salmon Bags`(){
        val rule = "shiny purple bags contain 2 pale blue bags, 1 wavy fuchsia bag, 5 pale salmon bags."
        val luggageProcessor = LuggageProcessor();
        luggageProcessor.addBagRule(rule);
        assertAll("bags contained",
            { assertTrue(luggageProcessor.canBagFitInBag("shiny purple","pale blue")) },
            { assertTrue(luggageProcessor.canBagFitInBag("shiny purple","pale salmon")) },
            { assertTrue(luggageProcessor.canBagFitInBag("shiny purple","wavy fuchsia")) }
        )
    }

    @Test
    fun `Shiny Blue Bag Should Hold Drab Tan Bag In Pale Blue Bag`(){
        val firstRule = "shiny purple bags contain 2 pale blue bags, 1 wavy fuchsia bag, 5 pale salmon bags."
        val secondRule = "pale blue bags contain 1 drab tan bag, 3 dark chartreuse bags," +
                                " 2 mirrored gold bags, 3 muted turquoise bags."
        val luggageProcessor = LuggageProcessor();
        luggageProcessor.addBagRule(firstRule);
        luggageProcessor.addBagRule(secondRule);
        assertTrue(luggageProcessor.canBagFitInBag("shiny purple","drab tan"))
    }
}