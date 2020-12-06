package util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInputReader {
  public static List<Integer> readIntListFromFile(Path path) throws Exception {
    var intList = new ArrayList<Integer>();
    List<String> lines = Files.readAllLines(path);
    for (String line : lines) {
      intList.add(Integer.parseInt(line));
    }
    return intList;
  }

  public static List<String> readStringListFromFile(Path path) throws Exception {
    return Files.readAllLines(path);
  }
}
