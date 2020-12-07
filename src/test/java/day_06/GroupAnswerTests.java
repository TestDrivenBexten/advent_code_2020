package day_06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PuzzleInputReader;

class GroupAnswerTests {
  @Test
  @DisplayName("Should count x answers from groups")
  void Sum_Group_Answer_Test() throws Exception {
    var path = Paths.get("src/test/java/day_06/group_answer_input.txt");
    List<String> answerList = PuzzleInputReader.readStringListFromFile(path);

    int totalCount = 0;
    String groupAnswer = "";
    for (String answer : answerList) {
      if (answer.isEmpty()) {
        int groupCount = GroupAnswer.calculateGroupAnswerCount(groupAnswer);
        totalCount += groupCount;
        groupAnswer = "";
      } else {
        groupAnswer += answer;
      }
    }

    assertEquals(6587, totalCount);
  }

  @Test
  @DisplayName("Should count x unanimous answers from groups")
  void Sum_Group_Unanimous_Answer_Test() throws Exception {
    var path = Paths.get("src/test/java/day_06/group_answer_input.txt");
    List<String> answerList = PuzzleInputReader.readStringListFromFile(path);

    int totalCount = 0;
    String groupAnswer = "";
    for (String answer : answerList) {
      if (answer.isEmpty()) {
        int groupCount = GroupAnswer.calculateUnanimousGroupAnswerCount(groupAnswer);
        totalCount += groupCount;
        groupAnswer = "";
      } else {
        groupAnswer += answer + "\n";
      }
    }

    assertEquals(3235, totalCount);
  }
}
