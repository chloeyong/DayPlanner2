import java.lang.reflect.Array;
import java.util.ArrayList;

public class MockFrontEnd implements FrontEnd {


    private Options options;
    /***
     * Need to create:
     *
     * ArrayList of Tasks
     *      Some of these need Deadlines
     *      Others need to be rests or whatever
     *      good luck
     *
     * Create options
     *      Look through everything that they have and just create some yeah
     *      easy right?
     *
     *
     * @return
     */

    ArrayList<Task> tasks = new ArrayList<Task>();
    ArrayList<RestTime> restTimes = new ArrayList<RestTime>();
    ArrayList<Integer> intervals1 = new ArrayList<>();
    ArrayList<Integer> intervals2 = new ArrayList<>();
    ArrayList<Integer> intervals3 = new ArrayList<>();

    public void populateIntervals(){
        intervals1.add(0, 20);
        intervals1.add(1,40);
        intervals1.add(2,60);
        intervals2.add(0,200);
        intervals2.add(1,200);
        intervals2.add(2, 140);
        intervals3.add(0, 200);
        intervals3.add(1, 120);
    }


    public MockFrontEnd(){

        tasks.add(new Task("task1", 2, "Just a task brooo", "??",120));
        tasks.add(new Task("task2",2, "Who even cares anymore","you're still here?", 120));
        tasks.add(new Task("Finish this bloody thing", new MyDateTime(20200209, 540),100, "If you're reading this it works more than I thought it would", "fuck off",420));
        tasks.add(new Task("task4", new MyDateTime(20200209, 640),4, "description", "Work", 80));
        tasks.add(new Task("task5", 5, "big description, guess what, urgency is a LIE", "sleep?",320));
//        tasks.get(0).setIntervals(new ArrayList<Integer>(new Integer()[]
        tasks.get(0).setIntervals(intervals1);
        tasks.get(2).setIntervals(intervals2);
        tasks.get(4).setIntervals(intervals3);

        restTimes.add(new RestTime(1320, 480));
        this.options = new Options(10, 5);


//        tasks.add()
    }



    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public ArrayList<RestTime> getRestTimes() {
        return restTimes;
    }

    @Override
    public Options getOptions() {
        return options;
    }
}
