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

    private Passport(String eyeColor){
        this.eyeColor = eyeColor;
    }

    public static Passport readPassportFromBatch(String passportBatch){
        int inputBirthYear;
        int inputIssueYear;
        int inputExpirationYear;
        int inputHeightInCentimeters;
        Color inputHairColor;
        String inputEyeColor = "";
        String inputPassportId = "";
        int inputCountryId;

        String[] passportFieldArray = passportBatch.split("\\s");
        for(String passportField: passportFieldArray){
            String passportKey = passportField.substring(0,3);
            String passportValue = passportField.substring(4);
            if(passportKey.equals("ecl")){
                inputEyeColor = passportValue;
            }
        }
        return new Passport(inputEyeColor);
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