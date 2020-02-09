import java.util.ArrayList;
import java.util.Calendar;

public class Options {
    private int drinkReminders;

    public Options(int drinkReminders, int shortRest) {
        this.drinkReminders = drinkReminders;
        this.shortRest = shortRest;
    }

    private int shortRest;
    //<Time of Rest, Duration>
    private ArrayList<Tuple<MyDateTime, Integer>> restTimes;

    public int getDrinkReminders() {
        return drinkReminders;
    }

    public void setDrinkReminders(int drinkReminders) {
        this.drinkReminders = drinkReminders;
    }

}
