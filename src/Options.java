import java.util.ArrayList;
import java.util.Calendar;

public class Options {
    private int sleepTime;
    private int drinkReminders;
    private ArrayList<Calendar> restTimes;

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getDrinkReminders() {
        return drinkReminders;
    }

    public void setDrinkReminders(int drinkReminders) {
        this.drinkReminders = drinkReminders;
    }

    public ArrayList<Calendar> getRestTimes() {
        return restTimes;
    }

    public void setRestTimes(ArrayList<Calendar> restTimes) {
        this.restTimes = restTimes;
    }
}
