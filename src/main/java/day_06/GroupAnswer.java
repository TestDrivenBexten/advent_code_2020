package day_06;

import java.util.HashSet;
import java.util.Set;

public class GroupAnswer {
    public static int calculateGroupAnswerCount(String groupAnswer){
        char[] answerArray = groupAnswer.replaceAll("\n", "").toCharArray();
        Set<Character> uniqueAnswerSet = new HashSet<Character>();
        for(char answer: answerArray){
            uniqueAnswerSet.add(answer);
        }
        return uniqueAnswerSet.size();
    }
}