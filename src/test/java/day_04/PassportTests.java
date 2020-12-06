package day_04;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.PuzzleInputReader;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

class PassportTests {
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
            () -> assertEquals("183cm",passport.getHeight()),
            () -> assertEquals("#fffffd",passport.getHairColor()),
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
       
    @Test
    @DisplayName("Should count x valid passports from batch")
    void Batch_Valid_Passport_Test() throws Exception{
        int validPassportCount = 0;
        var path = Paths.get("src/test/java/day_04/batch_input.txt");
        List<String> lineList = PuzzleInputReader.readStringListFromFile(path);

        String batchPassport = "";
        var lineIterator = lineList.iterator();
        while(lineIterator.hasNext()){
            var line = lineIterator.next();
            if(line.isEmpty()){
                var passport = Passport.readPassportFromBatch(batchPassport);
                if(passport.isValid()){
                    validPassportCount++;
                }
                batchPassport = "";
            }else{
                batchPassport += line + "\n";
            }
        }

        assertEquals(190,validPassportCount);
    }

   @ParameterizedTest
   @MethodSource("invalidPassportProvider")
   @DisplayName("Passport should be invalid with incorrect fields")
   void Passport_Should_Be_Invalid_With_Field_Validation(String batchInput){
        var passport = Passport.readPassportFromBatch(batchInput);
        assertFalse(passport.isValidWithFields());
   }

   @ParameterizedTest
   @MethodSource("validPassportProvider")
   @DisplayName("Passport should be valid with correct fields")
   void Passport_Should_Be_Valid_With_Field_Validation(String batchInput){
        var passport = Passport.readPassportFromBatch(batchInput);
        assertTrue(passport.isValidWithFields());
   }

   static Stream<String> invalidPassportProvider() {
       return Stream.of(
           "eyr:1972 cid:100\n"
            + "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
        "iyr:2019\n" + "hcl:#602927 eyr:1967 hgt:170cm\n" +
       "ecl:grn pid:012533040 byr:1946",
       "hcl:dab227 iyr:2012\n" +
       "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
       
       "hgt:59cm ecl:zzz\n" +
       "eyr:2038 hcl:74454a iyr:2023\n" +
       "pid:3556412378 byr:2007");
   }

   static Stream<String> validPassportProvider(){
       return Stream.of(
           "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
       "hcl:#623a2f",
       "eyr:2029 ecl:blu cid:129 byr:1989\n"+
       "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
       "hcl:#888785\n" +
       "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
       "pid:545766238 ecl:hzl\n" +
       "eyr:2022",
       "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
       );
   }
}
