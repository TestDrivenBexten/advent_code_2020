package day_04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
  private int birthYear;
  private int issueYear;
  private int expirationYear;
  private String height;
  private String hairColor;
  private String eyeColor;
  private String passportId;
  private int countryId;

  private Passport(
      int birthYear,
      int issueYear,
      int expirationYear,
      String height,
      String hairColor,
      String eyeColor,
      String passportId,
      int countryId) {
    this.birthYear = birthYear;
    this.issueYear = issueYear;
    this.expirationYear = expirationYear;
    this.height = height;
    this.hairColor = hairColor;
    this.eyeColor = eyeColor;
    this.passportId = passportId;
    this.countryId = countryId;
  }

  public boolean isValid() {
    boolean areYearsValid = this.birthYear > 0 && this.issueYear > 0 && this.expirationYear > 0;
    boolean arePhysicalTraitsValid =
        !this.height.isEmpty() && !this.hairColor.isEmpty() && !this.eyeColor.isEmpty();
    return areYearsValid && arePhysicalTraitsValid && !this.passportId.isEmpty();
  }

  public boolean isValidWithFields() {
    boolean areYearsValid = isBirthYearValid() && isIssueYearValid() && isExpirationYearValid();
    boolean arePhysicalTraitsValid = isEyeColorValid() && isHairColorValid() && isHeightValid();
    return areYearsValid && arePhysicalTraitsValid && isPassportIdValid();
  }

  public static Passport readPassportFromBatch(String passportBatch) {
    int inputBirthYear = 0;
    int inputIssueYear = 0;
    int inputExpirationYear = 0;
    String inputHeight = "";
    String inputHairColor = "";
    String inputEyeColor = "";
    String inputPassportId = "";
    int inputCountryId = 0;

    String[] passportFieldArray = passportBatch.split("\\s");
    for (String passportField : passportFieldArray) {
      String passportKey = passportField.substring(0, 3);
      String passportValue = passportField.substring(4);
      switch (passportKey) {
        case "byr":
          inputBirthYear = Integer.parseInt(passportValue);
          break;
        case "iyr":
          inputIssueYear = Integer.parseInt(passportValue);
          break;
        case "eyr":
          inputExpirationYear = Integer.parseInt(passportValue);
          break;
        case "hgt":
          inputHeight = passportValue;
          break;
        case "hcl":
          inputHairColor = passportValue;
          break;
        case "ecl":
          inputEyeColor = passportValue;
          break;
        case "pid":
          inputPassportId = passportValue;
          break;
        case "cid":
          inputCountryId = Integer.parseInt(passportValue);
          break;
      }
    }
    return new Passport(
        inputBirthYear,
        inputIssueYear,
        inputExpirationYear,
        inputHeight,
        inputHairColor,
        inputEyeColor,
        inputPassportId,
        inputCountryId);
  }

  private boolean isEyeColorValid() {
    String[] eyeColorArray = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
    for (String eyeColor : eyeColorArray) {
      if (eyeColor.equals(this.eyeColor)) {
        return true;
      }
    }
    return false;
  }

  private boolean isBirthYearValid() {
    return 1920 <= this.birthYear && this.birthYear <= 2002;
  }

  private boolean isIssueYearValid() {
    return 2010 <= this.issueYear && this.issueYear <= 2020;
  }

  private boolean isExpirationYearValid() {
    return 2020 <= this.expirationYear && this.expirationYear <= 2030;
  }

  private boolean isHairColorValid() {
    Pattern r = Pattern.compile("#([a-f]|\\d){6}");
    Matcher m = r.matcher(this.hairColor);
    return m.matches();
  }

  private boolean isHeightValid() {
    Pattern r = Pattern.compile("(\\d+)(cm|in)");
    Matcher m = r.matcher(this.height);
    if (!m.matches()) {
      return false;
    }

    int height = Integer.parseInt(m.group(1));
    String unit = m.group(2);
    if (unit.equals("cm")) {
      return 150 <= height && height <= 193;
    } else {
      return 59 <= height && height <= 76;
    }
  }

  private boolean isPassportIdValid() {
    Pattern r = Pattern.compile("\\d{9}");
    Matcher m = r.matcher(this.passportId);
    return m.matches();
  }

  /* Getters */
  public int getBirthYear() {
    return birthYear;
  }

  public int getIssueYear() {
    return issueYear;
  }

  public int getExpirationYear() {
    return expirationYear;
  }

  public String getHeight() {
    return height;
  }

  public String getHairColor() {
    return hairColor;
  }

  public String getEyeColor() {
    return eyeColor;
  }

  public String getPassportId() {
    return passportId;
  }

  public int getCountryId() {
    return countryId;
  }
}
