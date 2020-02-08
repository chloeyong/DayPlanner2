import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChummiesMagicMaker implements BackEnd {
    private ArrayList<Day> days;
    private FrontEnd frontEnd;


    public ChummiesMagicMaker(FrontEnd frontEnd){
        this.frontEnd = frontEnd;
    }

    private void generateDays(){
        ArrayList<RestTime> restTimes = frontEnd.getRestTimes();
        for (RestTime rest : restTimes){
            rest.getStartTime();
            //todo get start times, use them to allocate the availability and shit in the day, then note the
            //rest as such in the day in tuple form or whatever.
        }
        ArrayList<Task> tasks = frontEnd.getTasks();
        for (Task task : tasks){
            //todo allocate time for the tasks too
            //Same as rest only working backwards to make sure that everything fits in a given constraint
        }
    }

    private void allocateRestTimes(Day day){
        for(Tuple<MyDateTime, Integer> restTimes:frontEnd.getOptions().getRestTimes()){

            //todo, method is probably deprecated, work out usefullness and then delete or whatever is neccesary.
        }
    }

    private int getNumOfDays(){
        return (getTotalWorkTime() + getRestTimePerDay()) / 24;
    }

    private int getTotalWorkTime(){
        int total = 0;
        for(Task task:frontEnd.getTasks()){
//            total += task.getTotalDuration();
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
