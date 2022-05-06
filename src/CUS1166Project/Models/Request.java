package CUS1166Project.Models;

public class Request {
    int id;
    String type;
    int resId;
    String dateCreated;
    String description;
    String dateCompleted;
    String completedBy;

    //constructor with all parameters
    public Request(int id, String type, int resId, String dateCreated, String description,
                   String dateCompleted, String completedBy) {
        this.id = id;
        this.type = type;
        this.resId = resId;
        this.dateCreated = dateCreated;
        this.description = description;
        this.dateCompleted = dateCompleted;
        this.completedBy = completedBy;
    }

    //constructor with only parameters necessary for creating a new request
    public Request(int id, String type, int resId, String dateCreated, String description) {
        this.id = id;
        this.type = type;
        this.resId = resId;
        this.dateCreated = dateCreated;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getResId() {
        return resId;
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

    public String getCompletedBy() { return completedBy; }

    public String completedBy() {
        return completedBy;
    }

 }
