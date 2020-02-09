import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Day {

    public ArrayList<Tuple<TimeTaker, Integer>> getTimeTakerAndIntervalLength() {
        return timeTakerAndIntervalLength;
    }

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

    public Day(MyDateTime date){
        timeTakerAndIntervalLength = new ArrayList<Tuple<TimeTaker, Integer>>();
        this.availability = new HashMap<>();
        setDate(date);
    }

    public MyDateTime getDate() {
        return date;
    }

    public void setDate(MyDateTime date) {
        this.date = date;
    }

    public boolean addTimeInterval(Tuple<TimeTaker, Integer> ti, int startTime){
//        int nextAvail = getNextAvailability();
//        for(int x = nextAvail; x<=nextAvail+ )
        if( ti.getX() instanceof Task){
            System.out.println("Doing a task");
        }
        timeTakerAndIntervalLength.add(ti);
        //todo this needs testing with some dummy data!! get on it scrub!
        int currentTime = startTime;
        int size =  ti.getX().getIntervals().get(ti.getY());
        for(int i = 0;i < size;i++){
            this.availability.put(currentTime,ti.getX());
            currentTime = incrementMins(currentTime);
            if(!(ti.getX() instanceof  RestTime)) {
                ti.getX().getIntervals().remove(i);
            }else{
                return true;
            }
            if(ti.getX().getIntervals().size() == 0){
                return true;
            }

        }
        return false;
    }

    private int incrementMins(int mins){
        if(mins < 1439)
            return mins++;
        else return 0;
    }

    public HashMap<Integer, TimeTaker> getAvailability(){
        return availability;
    }

    public int getNextAvailability(){
        for (int i = 0; i < 1441; i++){
            if(availability.get(i) == null){
                return i;
            }
        }
        return -1;
    }

    public int nextPossibleInterval(TimeTaker timeTaker) {
        if(timeTaker.getIntervals().size()==0){
            return 0;
        }
        for (int interval : timeTaker.getIntervals()) {
            if (getNextAvailability() + interval <= 1440 && getNextAvailability() != -1) {
                return timeTaker.getIntervals().get(interval);
            }
        }
        return -1;
    }


}
