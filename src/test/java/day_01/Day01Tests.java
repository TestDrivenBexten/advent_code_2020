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
}