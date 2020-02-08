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
    private Tuple<TimeTaker, Integer> timeIntervals;
    private MyDateTime date;

    public MyDateTime getDate() {
        return date;
    }

    public void setDate(MyDateTime date) {
        this.date = date;
    }

    public void addTimeInterval(Tuple<TimeTaker, Integer> ti){

    }


}
