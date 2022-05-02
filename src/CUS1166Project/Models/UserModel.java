package CUS1166Project.Models;

public class UserModel {
    String username;
    String password;
    String department;

    //constructor for a new UserModel with all parameters
    public UserModel(String username, String password, String department) {
        this.username = username;
        this.password = password;
        this.department = department;
    }

    //constructor for a new UserModel with only username and password
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
