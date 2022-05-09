package CUS1166Project.Views;

import CUS1166Project.Controllers.ResidentController;
import CUS1166Project.Controllers.UserController;
import CUS1166Project.Models.Resident;
import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResidentView {

    /*********************************************
    **  DISPLAYS MAIN MENU FOR MANAGING RESIDENTS **
     ********************************************/
    public static void displayMenu(Stage stage, User user) {
        stage.setTitle("Sr. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(10);

        Button btAddResident = new Button("Add Resident");
        btAddResident.setOnAction(e -> {
            try {
                displayAddResident(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btAddResident,0,0);

        Button btDeleteResident = new Button("Delete Resident");
        btDeleteResident.setOnAction(e -> {
            displayDeleteResident(stage, user);
        });
        GridPane.setConstraints(btDeleteResident,0,1);

        Button btUpdateResident = new Button("Update Resident Info");
        btUpdateResident.setOnAction(e -> {
            displayUpdateResident(stage, user);
        });
        GridPane.setConstraints(btUpdateResident,0,2);

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
        GridPane.setConstraints(btMainMenu,0,3);

        grid.getChildren().addAll(btAddResident, btDeleteResident, btUpdateResident, btMainMenu);

        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    /*********************************************
    **    DISPLAYS MENU FOR ADDING RESIDENTS     **
     ********************************************/
    public static void displayAddResident(Stage stage, User user) throws Exception {

        // DEFINES THE SIZE OF BOX
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label and textfield for resident id
        Label id = new Label("ID: ");
        GridPane.setConstraints(id,0,0);
        TextField tfID = new TextField();
        tfID.setText(Integer.toString(ResidentController.getNewID()));
        tfID.setEditable(false);
        GridPane.setConstraints(tfID,1,0);

        //label and textfield for first name
        Label firstName = new Label("First Name: ");
        GridPane.setConstraints(firstName,0,1);
        TextField tfFirstName = new TextField();
        GridPane.setConstraints(tfFirstName,1,1);

        //label and text field for last name
        Label lastName = new Label("Last Name: ");
        GridPane.setConstraints(lastName,0,2);
        TextField tfLastName = new TextField();
        GridPane.setConstraints(tfLastName,1,2);

        //label and text field for unit
        Label unit = new Label("Unit #: ");
        GridPane.setConstraints(unit,0,3);
        TextField tfUnit = new TextField();
        GridPane.setConstraints(tfUnit,1,3);

        //label and text field for emergency contact
        Label emergencyContact = new Label("Emergency Contact #: ");
        GridPane.setConstraints(emergencyContact,0,4);
        TextField tfEmergencyContact = new TextField();
        GridPane.setConstraints(tfEmergencyContact,1,4);

        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage, user);
        });
        GridPane.setConstraints(btBack,0,5);

        Button btAddResident = new Button("Add Resident");
        btAddResident.setOnAction(e -> {
            try {
                Resident resident = new Resident(Integer.parseInt(tfID.getText()), tfFirstName.getText(),
                        tfLastName.getText(), tfUnit.getText(), tfEmergencyContact.getText());
                ResidentController.addResident(resident);
                tfID.setText(Integer.toString(ResidentController.getNewID()));
                tfFirstName.clear();
                tfLastName.clear();
                tfUnit.clear();
                tfEmergencyContact.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btAddResident,1,5);

        grid.getChildren().addAll(id, tfID, firstName, tfFirstName, lastName, tfLastName, unit, tfUnit,
                emergencyContact,tfEmergencyContact,btAddResident, btBack);

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,225);
        stage.setScene(scene);
        stage.show();
    }

    /*********************************************
    **    DISPLAYS MENU FOR DELETING RESIDENTS   **
     ********************************************/
    public static void displayDeleteResident(Stage stage, User user) {
        stage.setTitle("Sr. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label residentID = new Label("Resident ID: ");
        GridPane.setConstraints(residentID,0,0);
        TextField tfResidentID = new TextField();
        GridPane.setConstraints(tfResidentID,1,0);


        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage,user);
        });
        GridPane.setConstraints(btBack,0,1);


        Button btDeleteResident = new Button("Delete Resident");
        btDeleteResident.setOnAction(e -> {
            try {
                ResidentController.deleteResident(Integer.parseInt(tfResidentID.getText()));
                tfResidentID.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btDeleteResident,1,1);

        grid.getChildren().addAll(residentID,tfResidentID,btBack,btDeleteResident);

        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    /***********************************************
    **    DISPLAYS MENU FOR UPDATING RESIDENT INFO  **
     **********************************************/
    public static void displayUpdateResident(Stage stage, User user) {
        stage.setTitle("Sr. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label residentId = new Label("Resident ID: ");
        GridPane.setConstraints(residentId,0,0);
        TextField tfResidentID = new TextField();
        GridPane.setConstraints(tfResidentID,1,0);

        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage, user);
        });
        GridPane.setConstraints(btBack,0,1);

        Button btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> {
            try {
                int id = Integer.parseInt(tfResidentID.getText());
                if (ResidentController.idExists(id)) {
                    Resident resident = ResidentController.getResidentDetails(id);
                    displayUpdateResidentDetails(stage, user, resident);
                } else {
                    Alert alertResidentNotExist = new Alert (
                            Alert.AlertType.NONE,
                            "Please enter a valid resident id.",
                            ButtonType.OK
                    );
                    alertResidentNotExist.show();
                    tfResidentID.clear();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btSubmit,1,1);

        grid.getChildren().addAll(residentId,tfResidentID,btBack,btSubmit);

        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    /************************************************
    **  DISPLAYS MENU FOR UPDATING RESIDENT DETAILS  **
     ***********************************************/
    public static void displayUpdateResidentDetails(Stage stage, User user, Resident resident) {
        stage.setTitle("Sr. Housing");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label and textfield for resident id
        Label residentID = new Label("Resident ID: ");
        GridPane.setConstraints(residentID,0,0);
        TextField tfResidentID = new TextField();
        tfResidentID.setEditable(false);
        tfResidentID.setText(Integer.toString(resident.getId()));
        GridPane.setConstraints(tfResidentID,1,0);

        //label and textfield for resident first name
        Label fName = new Label("First Name: ");
        GridPane.setConstraints(fName,0,1);
        TextField tfFName = new TextField();
        tfFName.setText(resident.getFName());
        GridPane.setConstraints(tfFName,1,1);

        //label and textfield for resident last name
        Label lName = new Label("Last Name: ");
        GridPane.setConstraints(lName,0,2);
        TextField tfLName = new TextField();
        tfLName.setText(resident.getLName());
        GridPane.setConstraints(tfLName,1,2);

        //label and textfield for resident unit
        Label unit = new Label("Unit #: ");
        GridPane.setConstraints(unit,0,3);
        TextField tfUnit = new TextField();
        tfUnit.setText(resident.getUnit());
        GridPane.setConstraints(tfUnit,1,3);

        //label and textfield for emergency contact
        Label eContact = new Label("Emergency Contact #: ");
        GridPane.setConstraints(eContact,0,4);
        TextField tfEContact = new TextField();
        tfEContact.setText(resident.getEContact());
        GridPane.setConstraints(tfEContact,1,4);

        Button btBack = new Button("Back");
        btBack.setOnAction(e ->{
            if (user.getType().equals("admin")) {
                displayUpdateResident(stage, user);
            } else {
                try {
                    ResidentMenuView.display(stage,user);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        GridPane.setConstraints(btBack,0,5);

        Button btUpdate = new Button("Update");
        btUpdate.setOnAction(e -> {
            try {
                int newID = Integer.parseInt(tfResidentID.getText());
                String newFName = tfFName.getText();
                String newLName = tfLName.getText();
                String newUnit = tfUnit.getText();
                String newContact = tfEContact.getText();
                Resident newResident = new Resident(newID, newFName, newLName, newUnit, newContact);
                ResidentController.updateResident(newResident);
                if(user.getType().equals("admin")) {
                    displayUpdateResident(stage, user);
                } else {
                    displayUpdateResidentDetails(stage,user,ResidentController.getResidentDetails(resident.getId()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btUpdate,1,5);

        grid.getChildren().addAll(residentID,tfResidentID,fName,tfFName,lName,tfLName,unit,tfUnit,eContact,tfEContact,btBack,btUpdate);


        Scene scene = new Scene(grid,300,250);
        stage.setScene(scene);
        stage.show();

    }

    //function to display log of residents
    public static void displayResidents(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                AdminMenuView.display(stage, user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btBack);

        //creates tableview and populates it with data from database
        TableView tableView = ResidentController.generateResidents();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,430,400);
        stage.setScene(scene);
        stage.show();
    }
}
