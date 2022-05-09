package CUS1166Project.Views;

import CUS1166Project.Controllers.RequestController;
import CUS1166Project.Controllers.UserController;
import CUS1166Project.Utilities.Encryptor;
import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserView {

    /*********************************************
    ** DISPLAYS MAIN MENU FOR MANAGING USERS **
     ********************************************/
    public static void displayMenu(Stage stage, User user) {

        stage.setTitle("SR. Housing");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(10);

        //add user button
        Button btAddUser = new Button("Add User");
        btAddUser.setOnAction(e -> {
            try {
                displayAddUser(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btAddUser,0,0);


        //remove user button
        Button btDeleteUser = new Button("Delete User");
        btDeleteUser.setOnAction(e -> {
            try {
                displayDeleteUser(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btDeleteUser,0,1);


        //edit password button
        Button btEditPassword = new Button("Edit Password");
        btEditPassword.setOnAction(e -> {
            try {
                displayUpdatePassword(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btEditPassword,0,2);


        //main menu button
        Button btMainMenu = new Button("Main Menu");
        btMainMenu.setOnAction(e -> {
            try {
                switch (user.getType().toLowerCase()) {
                    case "admin":
                        AdminMenuView.display(stage, user);
                        break;
                    case "maintenance":
//                    MaintenanceMenuView.displayMenu(stage, user);
                        break;
                    case "nurse":
//                    NurseMenuView.displayMenu(stage, user);
                        break;
                    case "resident":
//                        ResidentMenuView.displayMenu(stage, user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btMainMenu,0,4);

        // ADDS ALL BUTTONS TO GRID
        grid.getChildren().addAll(btAddUser, btDeleteUser, btEditPassword, btMainMenu);

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    /******************************************
    **  DISPLAYS MENU FOR ADDING AN EMPLOYEE  **
     *****************************************/
    public static void displayAddUser(Stage stage, User user) {
        //actual window surrounding the scene
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //usernameame label
        Label username = new Label("Username: ");
        GridPane.setConstraints(username,0,0);

        //usernamename input
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("username"); // grayed out text prompting users to enter their first name
        GridPane.setConstraints(tfUsername,1,0);

        //Password label
        Label password = new Label("Password: ");
        GridPane.setConstraints(password,0,1);

        //Password input
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("password");
        GridPane.setConstraints(tfPassword,1,1);

        //User type label
        Label type = new Label("Type: ");
        GridPane.setConstraints(type,0,2);

        //User type input
        ChoiceBox<String> cbUserType = new ChoiceBox<>();
        cbUserType.getItems().add("Admin");
        cbUserType.getItems().add("Maintenance");
        cbUserType.getItems().add("Nurse");
        cbUserType.getItems().add("Resident");
        GridPane.setConstraints(cbUserType, 1, 2);

        //button to add new user
        Button btAddUser = new Button("Add User");
        btAddUser.setOnAction(e -> {
            try {
                User newUser = new User(tfUsername.getText(), tfPassword.getText(), cbUserType.getValue());
                UserController.addUser(newUser);
                tfUsername.clear();
                tfPassword.clear();
                cbUserType.setValue("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //button to go back to user main menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage, user);
        });

        //sets layout of buttons
        GridPane.setConstraints(btAddUser, 1, 4);
        GridPane.setConstraints(btBack, 0, 4);
        grid.getChildren().addAll(username, tfUsername, password, tfPassword, type, cbUserType, btAddUser, btBack);

        //sets scene
        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    /******************************************
    ** DISPLAYS MENU FOR DELETING AN EMPLOYEE **
     *****************************************/
    public static void displayDeleteUser(Stage stage, User user) {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //username label
        Label username = new Label("Username: ");
        GridPane.setConstraints(username,0,0);

        //username input
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("username");
        GridPane.setConstraints(tfUsername, 1, 0);

        //password label
        Label password = new Label("Password: ");
        GridPane.setConstraints(password, 0, 1);

        //password textfield
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("password");
        GridPane.setConstraints(tfPassword, 1, 1);



        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                displayMenu(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,4);

        //delete user button
        Button btDeleteUser = new Button("Delete User");
        btDeleteUser.setOnAction(e -> {
            try {
                User userToDelete = new User(tfUsername.getText(), tfPassword.getText());
                UserController.deleteUser(userToDelete);
                tfUsername.clear();
                tfPassword.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btDeleteUser,1,4);

        grid.getChildren().addAll(username, tfUsername, password, tfPassword, btDeleteUser, btBack);

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    /****************************************************
    **  DISPLAYS MENU FOR UPDATING AN EMPLOYEES PASSWORD **
     ***************************************************/
    public static void displayUpdatePassword(Stage stage, User user) {
        //actual window surrounding the scene
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //username label
        Label username = new Label("Username: ");
        GridPane.setConstraints(username,0,0);

        //First name input
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("username");
        GridPane.setConstraints(tfUsername,1,0);

        //label for old password
        Label oldPassword = new Label("Old Password: ");
        GridPane.setConstraints(oldPassword,0,1);

        //textfield for old password
        PasswordField tfOldPassword = new PasswordField();
        tfOldPassword.setPromptText("old password");
        GridPane.setConstraints(tfOldPassword,1,1);

        //label for new password
        Label newPassword = new Label("New Password: ");
        GridPane.setConstraints(newPassword,0,2);

        //textfield for new password
        PasswordField tfNewPassword = new PasswordField();
        tfNewPassword.setPromptText("new password");
        GridPane.setConstraints(tfNewPassword,1,2);


        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                displayMenu(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,4);

        //Login button
        Button btUpdatePassword = new Button("Update Password");
        btUpdatePassword.setOnAction(e -> {
            try {
                User userToUpdate = new User(tfUsername.getText(), tfOldPassword.getText());
                UserController.updatePassword(userToUpdate, Encryptor.encryptString(tfNewPassword.getText()));
                tfUsername.clear();
                tfOldPassword.clear();
                tfNewPassword.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btUpdatePassword,1,4);

        grid.getChildren().addAll(username, tfUsername, oldPassword,tfOldPassword, newPassword, tfNewPassword,
                btBack, btUpdatePassword
        );

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    //function to display log of users
    public static void displayUsers(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage, user);
        });
        GridPane.setConstraints(btBack,0,3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btBack);

        //creates tableview and populates it with data from database
        TableView tableView = UserController.generateUsers();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,385,400);
        stage.setScene(scene);
        stage.show();
    }
}
