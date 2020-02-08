import java.util.Comparator;

public class DeadlineComparitor implements Comparator<Task> {
    public int compare(Task task1, Task task2){
        Integer forFUCKSAKE = task1.getDeadline().getSum();
        return forFUCKSAKE.compareTo(task2.getDeadline().getSum());
    }

}
