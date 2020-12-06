package day_05;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BinaryBoardingTests {
    @ParameterizedTest
    @MethodSource("binaryBoardingSeatIdProvider")
    @DisplayName("Binary boarding spec should return seat ID")
    void Binary_Boarding_Should_Have_Correct_Seat_ID(String spec, int expectedSeatId){
        var binaryBoarding = BinaryBoarding.convertFromSpecification(spec);
        int actualSeatId = binaryBoarding.getSeatId();
        assertEquals(expectedSeatId,actualSeatId);
    }

    static Stream<Arguments> binaryBoardingSeatIdProvider(){
        return Stream.of(
            arguments("BFFFBBFRRR",567),
            arguments("FFFBBBFRRR",119),
            arguments("BBFFBBFRLL",820)
        );
    }
}
