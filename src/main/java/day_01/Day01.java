import java.util.List;

class Day01 {
    public static int productOfDaysAddingTo2020(List<Integer> list){
        int hey = list.stream().filter(x -> x == 299).
            findFirst().orElse(0);
        return hey;
    }
}