import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.*;

public class ChummiesMagicMaker implements BackEnd {
    private ArrayList<Day> days;
    private FrontEnd frontEnd;
    private MyDateTime earliestStart;

    public ChummiesMagicMaker(FrontEnd frontEnd, MyDateTime startTime){
        this.earliestStart = startTime;
        this.frontEnd = frontEnd;
    }

    private void generateDays(){
        for(int i = 0; i<100; i++){
            Day day;
            if(i==0){
                day = new Day(earliestStart);
                days.add(day);
            }else{
                MyDateTime geneDay = new MyDateTime(earliestStart.getDate(), earliestStart.getMin() );
                day = new Day(geneDay);
                days.add(day);
            }
            ArrayList<RestTime> restTimes = frontEnd.getRestTimes();
            for (RestTime rest : restTimes){
                Tuple tuple = new Tuple(rest, 0);
                day.addTimeInterval(tuple,rest.getStartTime());
            }
        }
        Day day = days.get(0);

        ArrayList<Task> tasks = frontEnd.getTasks();
        ArrayList<Task> tasksWithDeads = new ArrayList<>();
        ArrayList<Task> tasksForWhenever = new ArrayList<>();
        for (Task task : tasks){
            if (!task.getDeadline().equals(null)){
                tasksWithDeads.add(task);
            }else{
                tasksForWhenever.add(task);
            }
        }
        Collections.sort(tasksWithDeads, new DeadlineComparitor());
        //todo gotta order the deads list list by deads my head hurts


        for(int i = 1;i<=largestNumberOfIntervals();i++){
            for (Task task : tasksWithDeads){
                if (task.getIntervals().size()>=i){

                    //todo decipher the true nature of intervals, what are they really?
                }
                //todo allocate time for the tasks too
                //Same as rest only working backwards to make sure that everything fits in a given constraint
            }
            //todo for tasks without deadlines has to be done too!!
        }
    }

    private int largestNumberOfIntervals(){
        int largest = 1;
        for (Task task: frontEnd.getTasks()){
            if (task.getIntervals().size()<largest){
                largest=task.getIntervals().size();
            }
        }
        return largest;
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

    public void addTask(Task task, MyDateTime startTime){
        earliestStart = startTime;

    }
}
