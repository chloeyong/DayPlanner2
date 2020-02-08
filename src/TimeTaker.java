import java.util.ArrayList;

public abstract class TimeTaker {
    private int duration;
    //array of int minutes of how long each interval is
    private ArrayList<Integer> intervals;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Integer> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<Integer> intervals) {
        this.intervals = intervals;
    }
}
