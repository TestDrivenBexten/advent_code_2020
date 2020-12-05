import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            () -> assertEquals(183,passport.getHeight()),
            () -> assertEquals(Color.decode("#fffffd"),passport.getHairColor()),
            () -> assertEquals("gry",passport.getEyeColor()),
            () -> assertEquals("860033327",passport.getPassportId()),
            () -> assertEquals(147,passport.getCountryId())
        );
    }

    @Test
    @DisplayName("Passport with all eight fields should be valid")
    void Passport_With_All_Test(){
        String batchInput = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                            "byr:1937 iyr:2017 cid:147 hgt:183cm";
        var passport = Passport.readPassportFromBatch(batchInput);
        assertTrue(passport.isValid());
    }

    @Test
    @DisplayName("Passport with height missing should not be valid")
    void Passport_With_Missing_Height_Test(){
        String batchInput = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                            "hcl:#cfa07d byr:1929";
        var passport = Passport.readPassportFromBatch(batchInput);
        assertFalse(passport.isValid());
    }
    
    @Test
    @DisplayName("Passport with country ID missing should be valid")
    void Passport_With_Missing_Country_Id_Test(){
        String batchInput = "hcl:#ae17e1 iyr:2013\n" +
                            "eyr:2024\n" +
                            "ecl:brn pid:760753108 byr:1931\n" +
                            "hgt:179cm";
        var passport = Passport.readPassportFromBatch(batchInput);
        assertTrue(passport.isValid());
    }
   
    @Test
    @DisplayName("Passport with country ID and birth year missing should not be valid")
    void Passport_With_Missing_Country_Id_And_Birth_Year_Test(){
        String batchInput = "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                            "iyr:2011 ecl:brn hgt:59in";
        var passport = Passport.readPassportFromBatch(batchInput);
        assertFalse(passport.isValid());
    }
}