package CUS1166Project.Models;

import CUS1166Project.Utilities.Encryptor;

public class User {
    String username;
    String password;
    String type;

    //constructor for a new UserModel with all parameters
    public User(String username, String password, String type) throws Exception {
        this.username = username;
        this.password = Encryptor.encryptString(password);
        this.type = type;
    }

    //constructor for a new UserModel with only username and password
    public User(String username, String password) throws Exception {
        this.username = username;
        this.password = Encryptor.encryptString(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setType(String department) {
        this.type = department;
    }
}
