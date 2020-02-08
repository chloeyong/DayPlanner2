import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Task extends TimeTaker{

    private String name;
    private int totalDuration;

    //array of int minutes of how long each interval is
    private ArrayList<Integer> intervals;

    private MyDateTime deadline;
    private int urgency;
    private String description;
    private HashMap<String, Boolean> checklist;
    private String category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public ArrayList<Integer> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<Integer> intervals) {
        this.intervals = intervals;
    }

    public MyDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(MyDateTime deadline) {
        this.deadline = deadline;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Boolean> getChecklist() {
        return checklist;
    }

    public void setChecklist(HashMap<String, Boolean> checklist) {
        this.checklist = checklist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
