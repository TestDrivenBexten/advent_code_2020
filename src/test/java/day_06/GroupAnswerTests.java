package day_06;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import util.PuzzleInputReader;

class GroupAnswerTests {

    @ParameterizedTest
    @MethodSource("groupAnswerCountProvider")
    @DisplayName("Check group answer counts")
    void Group_Answer_Count_Tests(String groupAnswer, int expectedCount){
        int actualCount = GroupAnswer.calculateGroupAnswerCount(groupAnswer);
        assertEquals(expectedCount, actualCount);
    }

    static Stream<Arguments> groupAnswerCountProvider(){
        return Stream.of(
            arguments("abc",3),
            arguments("a\nb\nc",3),
            arguments("ab\nac",3),
            arguments("a\na\na\na",1),
            arguments("b",1)
        );
    }

   @Test
   @DisplayName("Should count x answers from groups")
   void Sum_Group_Answer_Test() throws Exception{
       var path = Paths.get("src/test/java/day_06/group_answer_input.txt");
       List<String> answerList = PuzzleInputReader.readStringListFromFile(path);

       int totalCount = 0;
       String groupAnswer = "";
       for(String answer: answerList){
           if(answer.isEmpty()){
            int groupCount = GroupAnswer.calculateGroupAnswerCount(groupAnswer);
            totalCount += groupCount;
            groupAnswer = "";
           } else {
               groupAnswer += answer;
           }
       }

       assertEquals(6587,totalCount);
   }

   @ParameterizedTest
   @MethodSource("groupUnanimousAnswerCountProvider")
   @DisplayName("Check group unanimous answer counts")
   void Group_Unanimous_Answer_Count_Tests(String groupAnswer, int expectedCount){
       int actualCount = GroupAnswer.calculateUnanimousGroupAnswerCount(groupAnswer);
       assertEquals(expectedCount, actualCount);
   }

   static Stream<Arguments> groupUnanimousAnswerCountProvider(){
       return Stream.of(
           arguments("abc",3),
           arguments("a\nb\nc",0),
           arguments("ab\nac",1),
           arguments("a\na\na\na",1),
           arguments("b",1)
       );
   }
}