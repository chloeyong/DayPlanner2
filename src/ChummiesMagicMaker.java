import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChummiesMagicMaker implements BackEnd {
    private ArrayList<Day> days;
    private FrontEnd frontEnd;

    //Signifies if time is allocated
    private HashMap<Integer, Boolean> availability;

    public ChummiesMagicMaker(FrontEnd frontEnd){
        this.frontEnd = frontEnd;
    }

    private void generateDays(){
        ArrayList<Task> tasks = frontEnd.getTasks();
    }

    private void allocateRestTimes(Day day){
        for(Tuple<MyDateTime, Integer> restTimes:frontEnd.getOptions().getRestTimes()){
        }
    }

    private int getNumOfDays(){
        return (getTotalWorkTime() + getRestTimePerDay()) / 24;
    }

    private int getTotalWorkTime(){
        int total = 0;
        for(Task task:frontEnd.getTasks()){
            total += task.getTotalDuration();
        }
        return total;
    }

    private int getRestTimePerDay(){
        int total = 0;
        for(Tuple<MyDateTime, Integer> restTime : frontEnd.getOptions().getRestTimes()){
          total += restTime.getY();
        }
        return total;
    }

    @Override
    public Day getDay(Date date) {
        for(Day day:days){
            if(day.getDate().equals(date));
            return day;
        }
        return null;
    }

    @Override
    public ArrayList<Day> getDays() {
        return days;
    }

    private void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public void addTask(Task task){

    }
}
