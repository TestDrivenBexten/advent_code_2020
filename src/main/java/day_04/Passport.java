
public class Passport {
    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private int height;
    private String hairColor;
    private String eyeColor;
    private String passportId;
    private int countryId;

    private Passport(int birthYear, int issueYear,
            int expirationYear, int height,
            String hairColor, String eyeColor,
            String passportId, int countryId){
        this.birthYear = birthYear;
        this.issueYear = issueYear;
        this.expirationYear = expirationYear;
        this.height = height;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.passportId = passportId;
        this.countryId = countryId;
    }

    public boolean isValid(){
        boolean areYearsValid = this.birthYear > 0 && this.issueYear > 0
            && this.expirationYear > 0;
        boolean arePhysicalTraitsValid = this.height > 0
            && this.hairColor != null && !this.eyeColor.isEmpty();
        return areYearsValid && arePhysicalTraitsValid && !this.passportId.isEmpty();
    }

    public static Passport readPassportFromBatch(String passportBatch){
        int inputBirthYear = 0;
        int inputIssueYear = 0;
        int inputExpirationYear = 0;
        int inputHeight = 0;
        String inputHairColor = "";
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
                    inputHeight = Integer.parseInt(height);
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
        return new Passport(inputBirthYear, inputIssueYear,
            inputExpirationYear, inputHeight,
            inputHairColor, inputEyeColor,
            inputPassportId,inputCountryId);
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

    public int getHeight(){
        return height;
    }

    public String getHairColor(){
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