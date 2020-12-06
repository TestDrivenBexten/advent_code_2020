package day_06;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
}