import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Day {

    /**
     * Task and interval number (which interval you're on)
     * This is going to have tasks where the name for each repeated (continued task) is
     * mutated to reflect its interval.
     */
    private Tuple<Task, Integer> taskIntervals;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Tuple<Task, Integer> getTaskIntervals() {
        return taskIntervals;
    }

    public void setTaskIntervals(Tuple<Task, Integer> taskIntervals) {
        this.taskIntervals = taskIntervals;
    }


}
