import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class Day01Tests {
    @Test
    @DisplayName("List with 1721 and 299 should return 514579")
    void First_Product_Test(){
        var expenseList = List.of(1721,979,366,299,675,1456);
        var result = Day01.productOfDaysAddingTo2020(expenseList);
        assertEquals(514579,result);
    }

    @Test
    @DisplayName("List with 100 and 1920 should return 192000")
    void Second_Product_Test(){
        var expenseList = List.of(1721,100,1920,1456);
        var result = Day01.productOfDaysAddingTo2020(expenseList);
        assertEquals(192000,result);
    }

    @Test
    void Print_Part_1_Answer_To_Console() throws Exception{
        var path = Paths.get("src/test/java/day_01/day_01_input.txt");
        List<Integer> intList = PuzzleInputReader.readIntListFromFile(path);
        var result = Day01.productOfDaysAddingTo2020(intList);
        System.out.println(result);
    }

    @Test
    @DisplayName("List with 979, 366, 675 should return 241861950")
    void Three_Expense_Product_Test(){
        var expenseList = List.of(1721,979,366,299,675,1456);
        var result = Day01.productOf3DaysAddingTo2020(expenseList);
        assertEquals(241861950,result);
    }

    @Test
    void Print_Part_2_Answer_To_Console() throws Exception{
        var path = Paths.get("src/test/java/day_01/day_01_input.txt");
        List<Integer> intList = PuzzleInputReader.readIntListFromFile(path);
        var result = Day01.productOf3DaysAddingTo2020(intList);
        System.out.println(result);
    }
}