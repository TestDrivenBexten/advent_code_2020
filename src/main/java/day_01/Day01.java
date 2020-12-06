package day_01;

import java.util.List;

public class Day01 {
    public static int productOfDaysAddingTo2020(List<Integer> list){
        for (Integer firstNum : list) {
            int secondNum = list.stream().
                    filter(x -> x + firstNum == 2020).
                    findFirst().
                    orElse(0);
            if (secondNum != 0) {
                return firstNum * secondNum;
            }
        }
        return 0;
    }

    public static int productOf3DaysAddingTo2020(List<Integer> list){
        for (Integer firstNum : list) {
            for (Integer secondNum : list) {
                int thirdNum = list.stream().
                        filter(x -> x + firstNum + secondNum == 2020).
                        findFirst().
                        orElse(0);
                if (thirdNum != 0) {
                    return firstNum * secondNum * thirdNum;
                }
            }
        }
        return 0;
    }
}