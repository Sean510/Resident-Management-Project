package CUS1166Project.Models;

public class RequestModel {
    int id;
    String type;
    int residentID;
    String dateCreated;
    String description;
    String dateCompleted;
    int completedBy;

    //constructor with all parameters
    public RequestModel(int id, String type, int residentID, String dateCreated, String description, String dateCompleted, int completedBy) {
        this.id = id;
        this.type = type;
        this.residentID = residentID;
        this.dateCreated = dateCreated;
        this.description = description;
        this.dateCompleted = dateCompleted;
        this.completedBy = completedBy;
    }

    //constructor with only parameters necessary for creating a new request
    public RequestModel(int id, String type, int residentID, String dateCreated, String description) {
        this.id = id;
        this.type = type;
        this.residentID = residentID;
        this.dateCreated = dateCreated;
        this.description = description;
    }

    public int getID() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getResidentID() {
        return residentID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public int completedBy() {
        return completedBy;
    }
 }
