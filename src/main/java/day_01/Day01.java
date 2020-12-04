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
}