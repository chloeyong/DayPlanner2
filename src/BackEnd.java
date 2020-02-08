import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public interface BackEnd {

    public Day getDay(Date date);

    public ArrayList<Day> getDays();

    public void addTask(Task task);

//    public Task

}
