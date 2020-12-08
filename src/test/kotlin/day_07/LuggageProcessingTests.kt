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
}