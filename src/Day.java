import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Day {

    /**
     * Task and interval number (which interval you're on)
     * This is going to have tasks where the name for each repeated (continued task) is
     * mutated to reflect its interval.
     *
     *
     * <Newly generated TimeTaker,length of this whole (sub) timetaker of original task >
     */
    private ArrayList<Tuple<TimeTaker, Integer>> timeTakerAndIntervalLength;
    //Signifies if time is allocated <Time,Time taker in that time slot>
    private HashMap<Integer, TimeTaker> availability;
    private MyDateTime date;

    public Day(){
        timeTakerAndIntervalLength = new ArrayList<Tuple<TimeTaker, Integer>>();
    }

    public MyDateTime getDate() {
        return date;
    }

    public void setDate(MyDateTime date) {
        this.date = date;
    }

    public void addTimeInterval(Tuple<TimeTaker, Integer> ti, int startTime){
        timeTakerAndIntervalLength.add(ti);
        //todo this needs testing with some dummy data!! get on it scrub!
        int currentTime = startTime;
        for(int i = 0;i < ti.getX().getIntervals().get(ti.getY());i++){
            availability.put(currentTime,ti.getX());
            currentTime = incrementMins(currentTime);
        }
    }

    private int incrementMins(int mins){
        if(mins < 1439)
            return mins++;
        else return 0;
    }


}
