package CUS1166Project.Models;

public class Resident {
    int id;
    String fName;
    String lName;
    String unit;
    String eContact;
    String username;

    public Resident(int id, String fName, String lName, String unit, String eContact, String username) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.unit = unit;
        this.eContact = eContact;
        this.username = username;
    }

    public Resident(int id, String fName, String lName, String unit, String eContact) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.unit = unit;
        this.eContact = eContact;
    }

    public Resident(int id) {
        this.id = id;
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

    public String getEContact() {
        return eContact;
    }

    public String getUsername() {
        return username;
    }
}
