import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Task extends TimeTaker{

    private String name;
    private MyDateTime deadline;
    private int urgency;
    private String description;
    private HashMap<String, Boolean> checklist;
    private String category;
    private int duration;

    public Task(String name, int urgency, String description, String category, int duration) {
        this.name = name;
        this.urgency = urgency;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.setIntervals(new ArrayList<>(duration));
    }

    public Task(String name, MyDateTime deadline, int urgency, String description, String category, int duration) {
        this.name = name;
        this.deadline = deadline;
        this.urgency = urgency;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.setIntervals(new ArrayList<>(duration));
    }


    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
