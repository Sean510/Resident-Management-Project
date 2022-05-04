package CUS1166Project.Models;

public class Quarantine {
    int resId;
    String dateStart;
    String dateEnd;

    public Quarantine(int resId, String dateStart, String dateEnd) {
        this.resId = resId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getResId() {
        return resId;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }
}
