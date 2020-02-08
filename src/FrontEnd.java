import java.sql.Time;
import java.util.ArrayList;

public interface FrontEnd {


    public ArrayList<Task> getTasks();

    public ArrayList<RestTime> getRestTimes();

    public Options getOptions();



}
