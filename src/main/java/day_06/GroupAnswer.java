package day_06;

import java.util.HashSet;
import java.util.Set;

public class GroupAnswer {
    public static int calculateGroupAnswerCount(String groupAnswer){
        char[] answerArray = groupAnswer.replaceAll("\n", "").toCharArray();
        var uniqueAnswerSet = uniqueCharacterSet(answerArray);

        return uniqueAnswerSet.size();
    }

    public static int calculateUnanimousGroupAnswerCount(String groupAnswer){
        String[] personArray = groupAnswer.split("\n");

        char[] answerArray = groupAnswer.replaceAll("\n", "").toCharArray();
        var uniqueAnswerSet = uniqueCharacterSet(answerArray);

        int unanimousCount = 0;
        boolean isUnanimous;
        for(char answer: uniqueAnswerSet){
            isUnanimous = true;
            for(String person: personArray){
                isUnanimous &= person.indexOf(answer) != -1;
            }
            if(isUnanimous){
                unanimousCount++;
            }
        }

        return unanimousCount;
    }

    private static Set<Character> uniqueCharacterSet(char[] charArray){
        Set<Character> uniqueCharSet = new HashSet<Character>();
        for(char character: charArray){
            uniqueCharSet.add(character);
        }
        return uniqueCharSet;
    }
}