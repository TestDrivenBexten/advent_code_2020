
public class Day02 {
    public static boolean isPasswordValid(int min, int max, char character, String password){
        int characterCount = password.chars()
            .reduce(0,
                (a, b) -> a + ((char) b == character ? 1 : 0));
        return min <= characterCount && characterCount <= max;
    }
}