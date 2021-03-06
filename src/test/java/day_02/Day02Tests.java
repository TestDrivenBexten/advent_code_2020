package day_02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PuzzleInputReader;

class Day02Tests {

  @Test
  @DisplayName("Password \"abcde\" should be valid for 1-3 'a' characters")
  void Abcde_Test() {
    var isValid = Day02.isPasswordValid(1, 3, 'a', "abcde");
    assertTrue(isValid);
  }

  @Test
  @DisplayName("Password \"cdefg\" should not be valid for 1-3 'b' characters")
  void Cdefg_Test() {
    var isValid = Day02.isPasswordValid(1, 3, 'b', "cdefg");
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Password \"ccccccccc\" should be valid for 2-9 'c' characters")
  void Multiple_C_Test() {
    var isValid = Day02.isPasswordValid(2, 9, 'c', "ccccccccc");
    assertTrue(isValid);
  }

  @Test
  @DisplayName("Should count 614 valid passwords from puzzle input")
  void Print_To_Console_P1() throws Exception {
    int count = 0;
    Pattern r = Pattern.compile("(\\d+)-(\\d+)\\s([a-z]):\\s([a-z]+)");

    var path = Paths.get("src/test/java/day_02/day_02_input.txt");
    List<String> passwordList = PuzzleInputReader.readStringListFromFile(path);
    var passwordIterator = passwordList.listIterator();
    while (passwordIterator.hasNext()) {
      String rawPassword = passwordIterator.next();
      Matcher m = r.matcher(rawPassword);
      if (m.find()) {
        int min = Integer.parseInt(m.group(1));
        int max = Integer.parseInt(m.group(2));
        char character = m.group(3).charAt(0);
        String password = m.group(4);

        var isValid = Day02.isPasswordValid(min, max, character, password);
        if (isValid) {
          count++;
        }
      }
    }
    assertEquals(614, count);
  }

  @Test
  @DisplayName("New password \"abcde\" should be valid for 1-3 'a' characters")
  void Second_Policy_Abcde_Test() {
    var isValid = Day02.isSecondPasswordValid(1, 3, 'a', "abcde");
    assertTrue(isValid);
  }

  @Test
  @DisplayName("New password \"cdefg\" should not be valid for 1-3 'b' characters")
  void Second_Policy_Cdefg_Test() {
    var isValid = Day02.isSecondPasswordValid(1, 3, 'b', "cdefg");
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Password \"ccccccccc\" should not be valid for 2-9 'c' characters")
  void Second_Policy_Multiple_C_Test() {
    var isValid = Day02.isSecondPasswordValid(2, 9, 'c', "ccccccccc");
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Should count 354 valid passwords from puzzle input")
  void Print_To_Console_P2() throws Exception {
    int count = 0;
    Pattern r = Pattern.compile("(\\d+)-(\\d+)\\s([a-z]):\\s([a-z]+)");

    var path = Paths.get("src/test/java/day_02/day_02_input.txt");
    List<String> passwordList = PuzzleInputReader.readStringListFromFile(path);
    var passwordIterator = passwordList.listIterator();
    while (passwordIterator.hasNext()) {
      String rawPassword = passwordIterator.next();
      Matcher m = r.matcher(rawPassword);
      if (m.find()) {
        int min = Integer.parseInt(m.group(1));
        int max = Integer.parseInt(m.group(2));
        char character = m.group(3).charAt(0);
        String password = m.group(4);

        var isValid = Day02.isSecondPasswordValid(min, max, character, password);
        if (isValid) {
          count++;
        }
      }
    }
    assertEquals(354, count);
  }
}
