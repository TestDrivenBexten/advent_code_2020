import java.util.List;

class Day01 {
    public static int productOfDaysAddingTo2020(List<Integer> list){
        var listIterator = list.listIterator();
        while(listIterator.hasNext()){
            var firstNum = listIterator.next();
            int secondNum = list.stream().
                filter(x -> x + firstNum == 2020).
                findFirst().
                orElse(0);
            if(secondNum != 0){
                return firstNum * secondNum;
            }
        }
        return 0;
    }

    public static int productOf3DaysAddingTo2020(List<Integer> list){
        var firstNumIterator = list.listIterator();
        while(firstNumIterator.hasNext()){
            var firstNum = firstNumIterator.next();

            var secondNumIterator = list.listIterator();
            while(secondNumIterator.hasNext()){
                var secondNum = secondNumIterator.next();
                int thirdNum = list.stream().
                    filter(x -> x + firstNum + secondNum == 2020).
                    findFirst().
                    orElse(0);
                if(thirdNum != 0){
                    return firstNum * secondNum * thirdNum;
                }
            }
        }
        return 0;
    }
}