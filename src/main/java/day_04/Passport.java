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

    private Passport(){}

    public static Passport readPassportFromBatch(String passportBatch){
        String[] passportFieldArray = passportBatch.split("\\s");
        return new Passport();
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