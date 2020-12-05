import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.awt.Color;

public class PassportTests {
    @Test
    @DisplayName("Read complete passport from batch")
    void Load_Passport_From_Batch_Test(){
        String batchInput = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" + 
                            "byr:1937 iyr:2017 cid:147 hgt:183cm";
        var passport = Passport.readPassportFromBatch(batchInput);
        assertAll("passport fields should be loaded",
            () -> assertEquals(1937,passport.getBirthYear()),
            () -> assertEquals(2017,passport.getIssueYear()),
            () -> assertEquals(2020,passport.getExpirationYear()),
            () -> assertEquals(183,passport.getHeightInCentimeters()),
            () -> assertEquals(Color.decode("#fffffd"),passport.getHairColor()),
            () -> assertEquals("gry",passport.getEyeColor()),
            () -> assertEquals("860033327",passport.getPassportId()),
            () -> assertEquals(147,passport.getCountryId())
        );
    }
}