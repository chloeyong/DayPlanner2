import java.util.*;

public class ChummiesMagicMaker implements BackEnd {
    private ArrayList<Day> days;
    private FrontEnd frontEnd;
    private MyDateTime earliestStart;
    private int currentDay;

    public ChummiesMagicMaker(FrontEnd frontEnd, MyDateTime startTime){
        this.earliestStart = startTime;
        this.frontEnd = frontEnd;
        this.currentDay = 0;
        days= new ArrayList<>();
    }


    public static void main(String[] args){
        ChummiesMagicMaker magicMaker = new ChummiesMagicMaker(new MockFrontEnd(), new MyDateTime(20200206, 0));
        magicMaker.generateDays();
        Day thisDay = magicMaker.days.get(0);
        for(Tuple thing: thisDay.getTimeTakerAndIntervalLength()){
            if (thing.getX() instanceof Task){
                System.out.println(((Task) thing.getX()).getName());
            }else if(thing.getX() instanceof RestTime){
                System.out.println("Rest time");
            }

            //todo bugtest the generateDays() method

        }
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
//                System.out.println("Started again?");
                Tuple tuple = new Tuple(rest, 0);
                day.addTimeInterval(tuple,rest.getStartTime());
//                System.out.println("Made it to the endZone once");
            }
        }

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


        for(int i = 1;i<=largestNumberOfIntervals();i++){
            for (Task task : tasksWithDeads){
                if (task.getIntervals().size()>=i){
                    int nextAvail = days.get(currentDay).getNextAvailability();
                    int nextInterval = days.get(currentDay).nextPossibleInterval(task);
                    if(nextInterval!= -1) {
                        if(days.get(currentDay).addTimeInterval(new Tuple(task, nextInterval), nextAvail))
                            tasksWithDeads.remove(task);
//                        days.get(currentDay).addTimeInterval(new RestTime())
                    }
                    else{
                        currentDay++;
                    }
                }
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

//    private void allocateRestTimes(Day day){
//        for(Tuple<MyDateTime, Integer> restTimes:frontEnd.getOptions().getRestTimes()){
//
//            //todo, method is probably deprecated, work out usefullness and then delete or whatever is neccesary.
//        }
//    }

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
        ArrayList<RestTime> tobeTupled = frontEnd.getRestTimes();
        for(RestTime restTime : tobeTupled){
          total += restTime.getDuration();
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
