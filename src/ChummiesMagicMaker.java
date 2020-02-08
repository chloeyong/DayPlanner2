import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChummiesMagicMaker implements BackEnd {
    ArrayList<Day> days;
    FrontEnd frontEnd;
    public ChummiesMagicMaker(FrontEnd frontEnd){
        this.frontEnd = frontEnd;
    }

    private void generateDays(){

    }

    private int getTotalWorkTime(){
        int total = 0;
        return total;
    }

    private int getRestTimePerDay(){
        int total = 0;
        for(Tuple<Calendar, Integer> restTime : frontEnd.getOptions().getRestTimes()){
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
