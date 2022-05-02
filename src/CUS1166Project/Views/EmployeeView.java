package CUS1166Project.Views;

import CUS1166Project.Controllers.EmployeeController;
import CUS1166Project.Models.UserModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeView {

    //DISPLAYS MAIN MENU FOR MANAGING EMPLOYEES
    public static void displayMenu(Stage stage, UserModel user) {

        stage.setTitle("SR. Housing");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(10);

        //add employee button
        Button btAddEmployee = new Button("Add Employee");
        btAddEmployee.setOnAction(e -> {
            try {
                displayAddEmployee(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btAddEmployee,0,0);


        //remove employee button
        Button btRemoveEmployee = new Button("Remove Employee");
        btRemoveEmployee.setOnAction(e -> {
            try {
//                EmployeeFunctions.removeEmployee(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btRemoveEmployee,0,1);


        //edit password button
        Button btEditPassword = new Button("Edit Password");
        btEditPassword.setOnAction(e -> {
            try {
//                EmployeeFunctions.changePassword(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btEditPassword,0,2);


        //main menu button
        Button btMainMenu = new Button("Main Menu");
        btMainMenu.setOnAction(e -> {
            try {
                if(user.getDepartment().toLowerCase().equals("admin")) {
                    AdminMenuView.displayMenu(stage, user);
                } else if (user.getDepartment().toLowerCase().equals("maintenance")) {
//                    MaintenanceMenuView.displayMenu(stage);
                } else if (user.getDepartment().toLowerCase().equals("nurse")) {
//                    NurseMenuView.displayMenu(stage);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btMainMenu,0,4);

        // ADDS ALL BUTTONS TO GRID
        grid.getChildren().addAll(btAddEmployee, btRemoveEmployee, btEditPassword, btMainMenu);

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    //DISPLAYS MENU FOR ADDING A NEW EMPLOYEE
    public static void displayAddEmployee(Stage stage, UserModel user) {
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
        tfUsername.setPromptText("UserName"); // grayed out text prompting users to enter their first name
        GridPane.setConstraints(tfUsername,1,0);

        //Password label
        Label password = new Label("Password: ");
        GridPane.setConstraints(password,0,1);

        //Password input
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("password");
        GridPane.setConstraints(tfPassword,1,1);

        //Employee type label
        Label type = new Label("Employee Type: ");
        GridPane.setConstraints(type,0,2);

        //Employee type input
        ChoiceBox<String> cbEmployeeType = new ChoiceBox<>();
        cbEmployeeType.getItems().add("Admin");
        cbEmployeeType.getItems().add("Maintenance");
        cbEmployeeType.getItems().add("Nurse");
        GridPane.setConstraints(cbEmployeeType, 1, 2);

        //button to add new employee
        Button btAddEmployee = new Button("Add Employee");
        btAddEmployee.setOnAction(e -> {
            try {
                UserModel newUser = new UserModel(tfUsername.getText(), tfPassword.getText(), cbEmployeeType.getValue());
                EmployeeController.addEmployee(newUser);
                tfUsername.clear();
                tfPassword.clear();
                cbEmployeeType.setValue("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //button to go back to employee main menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage, user);
        });

        //sets layout of buttons
        GridPane.setConstraints(btAddEmployee, 0, 4);
        GridPane.setConstraints(btBack, 1, 4);
        grid.getChildren().addAll(username, tfUsername, password, tfPassword, type, cbEmployeeType, btAddEmployee, btBack);

        //sets scene
        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
