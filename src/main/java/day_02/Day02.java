
public class Day02 {
    public static boolean isPasswordValid(int min, int max, char character, String password){
        int characterCount = password.chars()
            .reduce(0,
                (a, b) -> a + ((char) b == character ? 1 : 0));
        return min <= characterCount && characterCount <= max;
    }

    public static boolean isSecondPasswordValid(int min, int max, char character, String password){
        boolean firstCharMatched = password.charAt(min -1) == character;
        boolean secondCharMatched = password.charAt(max -1) == character;
        return firstCharMatched ^ secondCharMatched;
    }
}