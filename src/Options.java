import java.util.ArrayList;
import java.util.Calendar;

public class Options {
    private int drinkReminders;

    private int shortRest;
    //<Time of Rest, Duration>
    private ArrayList<Tuple<MyDateTime, Integer>> restTimes;

    public int getDrinkReminders() {
        return drinkReminders;
    }

    public void setDrinkReminders(int drinkReminders) {
        this.drinkReminders = drinkReminders;
    }

    public ArrayList<Tuple<MyDateTime, Integer>>  getRestTimes() {
        return restTimes;
    }

    public void setRestTimes(ArrayList<Tuple<MyDateTime, Integer>> restTimes) {
        this.restTimes = restTimes;
    }
}
