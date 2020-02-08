import java.util.ArrayList;
import java.util.Calendar;

public class Options {
    private int drinkReminders;
    //<Time of Rest, Duration>
    private ArrayList<Tuple<Calendar, Integer>> restTimes;

    public int getDrinkReminders() {
        return drinkReminders;
    }

    public void setDrinkReminders(int drinkReminders) {
        this.drinkReminders = drinkReminders;
    }

    public ArrayList<Tuple<Calendar, Integer>>  getRestTimes() {
        return restTimes;
    }

    public void setRestTimes(ArrayList<Tuple<Calendar, Integer>> restTimes) {
        this.restTimes = restTimes;
    }
}
