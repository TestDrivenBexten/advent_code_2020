
public class Day02 {
    public static boolean isPasswordValid(int min, int max, char character, String password){
        int characterCount = password.chars()
            .reduce(0,
                (a, b) -> a + ((char) b == character ? 1 : 0));
        System.out.println(password);
        System.out.println(characterCount);
        return min <= characterCount && characterCount <= max;
    }
}