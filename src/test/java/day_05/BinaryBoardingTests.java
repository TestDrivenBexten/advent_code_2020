package day_05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.PuzzleInputReader;

class BinaryBoardingTests {
  @ParameterizedTest
  @MethodSource("binaryBoardingSeatIdProvider")
  @DisplayName("Binary boarding spec should return seat ID")
  void Binary_Boarding_Should_Have_Correct_Seat_ID(String spec, int expectedSeatId) {
    var binaryBoarding = BinaryBoarding.convertFromSpecification(spec);
    int actualSeatId = binaryBoarding.getSeatId();
    assertEquals(expectedSeatId, actualSeatId);
  }

  static Stream<Arguments> binaryBoardingSeatIdProvider() {
    return Stream.of(
        arguments("BFFFBBFRRR", 567), arguments("FFFBBBFRRR", 119), arguments("BBFFBBFRLL", 820));
  }

  @Test
  @DisplayName("Should find maximum seat ID from boarding list")
  void Find_Maximum_Seat_ID_Test() throws Exception {
    var path = Paths.get("src/test/java/day_05/boarding_input.txt");
    List<String> boardingList = PuzzleInputReader.readStringListFromFile(path);

    int maximumSeatId = -1;
    var boardingIterator = boardingList.iterator();
    while (boardingIterator.hasNext()) {
      String boardingSpec = boardingIterator.next();
      var binaryBoarding = BinaryBoarding.convertFromSpecification(boardingSpec);
      int seatId = binaryBoarding.getSeatId();
      maximumSeatId = maximumSeatId < binaryBoarding.getSeatId() ? seatId : maximumSeatId;
    }

    assertEquals(906, maximumSeatId);
  }

  @Test
  @DisplayName("Should find missing seat ID from boarding list")
  void Find_Missing_Seat_ID_Test() throws Exception {
    var path = Paths.get("src/test/java/day_05/boarding_input.txt");
    List<String> boardingList = PuzzleInputReader.readStringListFromFile(path);

    List<BinaryBoarding> binaryList =
        boardingList.stream()
            .map(BinaryBoarding::convertFromSpecification)
            .sorted(Comparator.comparingInt(BinaryBoarding::getSeatId))
            .collect(Collectors.toList());

    int missingSeatId = 0;
    for (int k = 0; k < binaryList.size() - 1; k++) {
      int firstSeatId = binaryList.get(k).getSeatId();
      int secondSeatId = binaryList.get(k + 1).getSeatId();
      if (secondSeatId == firstSeatId + 2) {
        missingSeatId = firstSeatId + 1;
        break;
      }
    }

    assertEquals(519, missingSeatId);
  }
}
