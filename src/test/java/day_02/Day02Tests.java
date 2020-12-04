
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class Day02Tests {

    @Test
    @DisplayName("Password \"abcde\" should be valid for 1-3 'a' characters")
    void Abcde_Test(){
        var isValid = Day02.isPasswordValid(1,3,'a',"abcde");
        assertTrue(isValid);
    }

    @Test
    @DisplayName("Password \"cdefg\" should not be valid for 1-3 'b' characters")
    void Cdefg_Test(){
        var isValid = Day02.isPasswordValid(1,3,'b',"cdefg");
        assertFalse(isValid);
    }

    @Test
    @DisplayName("Password \"ccccccccc\" should be valid for 2-9 'c' characters")
    void Multiple_C_Test(){
        var isValid = Day02.isPasswordValid(2,9,'c',"ccccccccc");
        assertTrue(isValid);
    }
}