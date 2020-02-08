public class MyDateTime {
    //for example 08/12/2020 will be 20201208
    private int date;
    private int min;

    public MyDateTime(int date, int min){
        this.date=date;
        this.min = min;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
