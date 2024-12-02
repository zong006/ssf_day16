package vttp.ssf_day16.model;

public class DayTemperature {
    private int low;
    private int high;
    
    public DayTemperature(int low, int high) {
        this.low = low;
        this.high = high;
    }
    public int getLow() {
        return low;
    }
    public void setLow(int low) {
        this.low = low;
    }
    public int getHigh() {
        return high;
    }
    public void setHigh(int high) {
        this.high = high;
    }

    public String getRange(){
        String range = Integer.toString(this.high - this.low);
        return "Today's temperature range is "+range;
    }

}
