package CUS1166Project.Models;

public class MissedMeal {
    int resId;
    String mealMissed;
    String dateMissed;
    boolean contacted;

    //constructor with all parameters for contacted missedMeal
    public MissedMeal(int resId, String mealMissed, String dateMissed, boolean contacted) {
        this.resId = resId;
        this.mealMissed = mealMissed;
        this.dateMissed = dateMissed;
        this.contacted = contacted;
    }

    //constructor with contacted set to false for creating new report
    public MissedMeal(int resId, String mealMissed, String dateMissed) {
        this.resId = resId;
        this.mealMissed = mealMissed;
        this.dateMissed = dateMissed;
        this.contacted = false;
    }

    public int getResId() {
        return resId;
    }

    public String getMealMissed() {
        return mealMissed;
    }

    public String getDateMissed() {
        return dateMissed;
    }

    public Boolean getContacted() {
        return contacted;
    }
}
