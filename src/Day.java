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

        timeTakerAndIntervalLength.add(ti);
        int currentTime = startTime;
//        System.out.println(ti.getX().getName()+"  "+ti.getX().getIntervals());
//        System.out.println(availability.toString());
        int size;
        //todo work out what was wrong with this later and tell my off but I want to fix other things rn
        try {
            size = ti.getX().getIntervals().get(ti.getY());
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
//        System.out.println(size);
        for(int i = 0;i < size;i++) {
//            System.out.println(availability.get(currentTime));
            this.availability.put(currentTime, ti.getX());
//            System.out.println(availability.get(currentTime));

            if( ti.getX() instanceof Task){
//              System.out.println("Doing "+ti.getX().getName());
//                System.out.println(currentTime);
            }
            currentTime = incrementMins(currentTime);
            if(currentTime<1439){
                currentTime++;
            }else{
                currentTime=0;
            }


//            System.out.println(currentTime);
//            System.out.println(availability);
        }
            if(!(ti.getX() instanceof  RestTime)) {
                //todo work out whatever was meant to be happening here
//                System.out.println(ti.getX().getIntervals());
                ti.getX().getIntervals().remove(ti.getX().getIntervals().indexOf(size));
            }else{
//                System.out.println("Rest");
                return true;
            }
            if(ti.getX().getIntervals().size() == 0){
                System.out.println("Emptied");
                return true;
            }

//        }
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
                System.out.println("Null??");
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
//                System.out.println(timeTaker.getIntervals());
//                System.out.println(interval);
                return timeTaker.getIntervals().indexOf(interval);
            }
        }
        return -1;
    }


}
