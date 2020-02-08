public class RestTime extends TimeTaker {
    private int duration;
    private int startTime;


    public RestTime(int startTime, int duration){
        this.duration = duration;
        this.startTime = startTime;
    }
    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
