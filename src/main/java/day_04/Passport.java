import java.awt.Color;

public class Passport {
    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private int heightInCentimeters;
    private Color hairColor;
    private String eyeColor;
    private String passportId;
    private int countryId;

    private Passport(int birthYear, int issueYear,
            int expirationYear, int heightInCentimeters,
            String eyeColor, int countryId){
        this.birthYear = birthYear;
        this.issueYear = issueYear;
        this.expirationYear = expirationYear;
        this.heightInCentimeters = heightInCentimeters;
        this.eyeColor = eyeColor;
        this.countryId = countryId;
    }

    public static Passport readPassportFromBatch(String passportBatch){
        int inputBirthYear = 0;
        int inputIssueYear = 0;
        int inputExpirationYear = 0;
        int inputHeightInCentimeters = 0;
        Color inputHairColor;
        String inputEyeColor = "";
        String inputPassportId = "";
        int inputCountryId = 0;

        String[] passportFieldArray = passportBatch.split("\\s");
        for(String passportField: passportFieldArray){
            String passportKey = passportField.substring(0,3);
            String passportValue = passportField.substring(4);
            switch(passportKey){
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
                    String height = passportValue.replaceAll("[a-z]","");
                    inputHeightInCentimeters = Integer.parseInt(height);
                    break;
                case "ecl":
                    inputEyeColor = passportValue;
                    break;
                case "cid":
                    inputCountryId = Integer.parseInt(passportValue);
                    break;
            }
        }
        return new Passport(inputBirthYear, inputIssueYear,
            inputExpirationYear, inputHeightInCentimeters,
            inputEyeColor, inputCountryId);
    }

    /* Getters */
    public int getBirthYear(){
        return birthYear;
    }

    public int getIssueYear(){
        return issueYear;
    }

    public int getExpirationYear(){
        return expirationYear;
    }

    public int getHeightInCentimeters(){
        return heightInCentimeters;
    }

    public Color getHairColor(){
        return hairColor;
    }

    public String getEyeColor(){
        return eyeColor;
    }

    public String getPassportId(){
        return passportId;
    }

    public int getCountryId(){
        return countryId;
    }
}