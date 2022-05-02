package CUS1166Project.Models;

public class ResidentModel {
    int id;
    String fName;
    String lName;
    String unit;
    String phoneNum;

    public ResidentModel(int id, String fName, String lName, String unit, String phoneNum) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.unit = unit;
        this.phoneNum = phoneNum;
    }

    public int getID() {
        return id;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getUnit() {
        return unit;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
