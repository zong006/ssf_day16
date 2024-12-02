package vttp.ssf_day16.model;

public class Carpark {
    private String id;
    private String name;
    private String category;
    private String weekdayRate1;
    private String weekdayRate2;
    private String saturdayRate;
    private String sundayPhRate;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getWeekdayRate1() {
        return weekdayRate1;
    }
    public void setWeekdayRate1(String weekdayRate1) {
        this.weekdayRate1 = weekdayRate1;
    }
    public String getWeekdayRate2() {
        return weekdayRate2;
    }
    public void setWeekdayRate2(String weekdayRate2) {
        this.weekdayRate2 = weekdayRate2;
    }
    public String getSaturdayRate() {
        return saturdayRate;
    }
    public void setSaturdayRate(String saturdayRate) {
        this.saturdayRate = saturdayRate;
    }
    public String getSundayPhRate() {
        return sundayPhRate;
    }
    public void setSundayPhRate(String sundayPhRate) {
        this.sundayPhRate = sundayPhRate;
    }

    
}
