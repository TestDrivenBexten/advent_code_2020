import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

class PuzzleInputReader {
    public static List<Integer> readIntListFromFile(Path path)
            throws Exception {
        var intList = new ArrayList<Integer>();
        List<String> lines = Files.readAllLines(path);
        for(String line: lines){
            intList.add(Integer.parseInt(line));
        }
        return intList;
    }
}