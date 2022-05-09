package CUS1166Project.Views;

import CUS1166Project.Controllers.RequestController;
import CUS1166Project.Controllers.UserController;
import CUS1166Project.Models.Request;
import CUS1166Project.Models.User;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;

public class RequestView {

    /*********************************************
     **  DISPLAYS MAIN MENU FOR MANAGING REQUESTS **
     ********************************************/
    public static void displayMenu(Stage stage, User user) {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(8);

        //CREATE REQUEST BUTTON
        Button btCreateRequest = new Button("Create Request");
        btCreateRequest.setOnAction(e -> {
            displayCreateRequest(stage, user);
        });
        GridPane.setConstraints(btCreateRequest,0,0);

        //MARK REQUEST COMPLETE
        Button btMarkComplete = new Button("Mark Request Completed");
        btMarkComplete.setOnAction(e -> {
            displayCompleteRequest(stage, user);
        });
        GridPane.setConstraints(btMarkComplete,0,1);

        //CREATE PENDING REQUEST LIST
        Button btPendingRequests = new Button("Pending Requests");
        btPendingRequests.setOnAction(e -> {
            try {
                displayPendingRequests(stage, user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btPendingRequests,0,2);

        //GENERATE NURSE LOGS
        Button btGenNurse = new Button("Generate Nurse Logs");
        btGenNurse.setOnAction(e -> {
            try {
                displayNurseLogs(stage,user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btGenNurse,0,3);

        //GENERATE MAINTENANCE LOGS
        Button btGenMaintenance = new Button("Generate Maintenance Logs");
        btGenMaintenance.setOnAction(e -> {
            //include generate maintenance function
        });
        GridPane.setConstraints(btGenMaintenance,0,4);


        Button btMainMenu = new Button("Main Menu");
        btMainMenu.setOnAction(e ->{
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
        GridPane.setConstraints(btMainMenu,0,6);

        grid.getChildren().addAll(btCreateRequest,btPendingRequests,btGenNurse,btGenMaintenance,btMarkComplete,btMainMenu);

        Scene scene = new Scene(grid,300,250);
        stage.setScene(scene);
        stage.show();
    }

    /******************************************
    **   DISPLAYS MENU FOR CREATING REQUESTS  **
     *****************************************/
    public static void displayCreateRequest(Stage stage, User user) {
        stage.setTitle("SR. Housing");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);


        // REQUEST TYPE //
        Label type = new Label("Request Type: ");
        ChoiceBox<String> cbType = new ChoiceBox<>();
        cbType.getItems().add("Nurse");
        cbType.getItems().add("Maintenance");
        cbType.setValue("Select One");
        GridPane.setConstraints(type,0,0);
        GridPane.setConstraints(cbType,1,0);

        // RESIDENT ID //
        Label residentID = new Label("Resident ID: ");
        TextField tfResidentID = new TextField();
        tfResidentID.setPromptText("resident id");
        GridPane.setConstraints(residentID,0,1);
        GridPane.setConstraints(tfResidentID,1,1);

        // DATE CREATED //
        Label date = new Label("Date Created: ");
        DatePicker dpDate = new DatePicker();
        dpDate.setValue(LocalDate.now());
        GridPane.setConstraints(date,0,3);
        GridPane.setConstraints(dpDate,1,3);

        // DESCRIPTION //
        Label description = new Label("Description: ");
        TextArea taDescription = new TextArea();
        taDescription.setPrefHeight(100);
        taDescription.setPrefWidth(200);
        GridPane.setConstraints(description,0,2);
        GridPane.setConstraints(taDescription,1,2);

        //button to go to the previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e ->{
            try {
                AdminMenuView.display(stage,user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,5);

        //button to create request
        Button btCreate = new Button("Create Request");
        btCreate.setOnAction(e ->{
            try {
                int newID = RequestController.getNewID();
                String newType = cbType.getValue();
                int newResidentID = Integer.parseInt(tfResidentID.getText());
                String newDescription = taDescription.getText();
                String newDate = dpDate.getValue().toString();
                Request request = new Request(newID, newType, newResidentID, newDate, newDescription);
                RequestController.createRequest(request);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            cbType.setValue(null);
            tfResidentID.clear();
            taDescription.clear();
            dpDate.setValue(LocalDate.now());
        });
        GridPane.setConstraints(btCreate,1,5);


        grid.getChildren().addAll(type,cbType,residentID,tfResidentID,description,taDescription,date,dpDate,btBack,btCreate);

        Scene scene = new Scene(grid,300,250);
        stage.setScene(scene);
        stage.show();
    }

    /******************************************
    ** DISPLAYS MENU FOR COMPLETING REQUESTS  **
     *****************************************/
    public static void displayCompleteRequest(Stage stage, User user) {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label and textfield for request id
        Label requestID = new Label("Request ID:");
        TextField tfRequestID = new TextField();
        GridPane.setConstraints(requestID,0,0);
        GridPane.setConstraints(tfRequestID,1,0);

        //label and datepicker for date completed
        Label date = new Label("Date Completed: ");
        DatePicker dpDate = new DatePicker();
        dpDate.setValue(LocalDate.now());
        GridPane.setConstraints(date,0,1);
        GridPane.setConstraints(dpDate,1,1);

        //label and textfield for employee who completed
        Label completedBy = new Label("Completed By: ");
        TextField tfCompletedBy = new TextField();
        GridPane.setConstraints(completedBy,0,2);
        GridPane.setConstraints(tfCompletedBy,1,2);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            displayMenu(stage, user);
        });
        GridPane.setConstraints(btBack,0,3);

        //button to complete request
        Button btComplete = new Button("Complete");
        btComplete.setOnAction(e -> {
            try {
                int reqID = Integer.parseInt(tfRequestID.getText());
                String dateFinished = dpDate.getValue().toString();
                String username = tfCompletedBy.getText();
                if (RequestController.exists(reqID)) {
                    RequestController.completeRequest(reqID, dateFinished, username);
                } else {
                    Alert alertNotExist = new Alert(
                            Alert.AlertType.NONE,
                            "Please make sure you enter a valid request.",
                            ButtonType.OK
                    );
                    alertNotExist.show();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            tfRequestID.clear();
            tfCompletedBy.clear();
            dpDate.setValue(LocalDate.now());
        });
        GridPane.setConstraints(btComplete,1,3);

        grid.getChildren().addAll(requestID,tfRequestID,date,dpDate,completedBy,tfCompletedBy,btBack,btComplete);

        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    //funciton to display complete a request for maintenance
    public static void displayCompleteRequestNotAdmin(Stage stage, User user) {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label and textfield for request id
        Label requestID = new Label("Request ID:");
        TextField tfRequestID = new TextField();
        GridPane.setConstraints(requestID,0,0);
        GridPane.setConstraints(tfRequestID,1,0);

        //label and datepicker for date completed
        Label date = new Label("Date Completed: ");
        DatePicker dpDate = new DatePicker();
        dpDate.setValue(LocalDate.now());
        GridPane.setConstraints(date,0,1);
        GridPane.setConstraints(dpDate,1,1);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                if (user.getType().equals("maintenance")) { MaintenanceMenuView.display(stage, user); }
                if (user.getType().equals("nurse")) { NurseMenuView.display(stage, user); }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,3);

        //button to complete request
        Button btComplete = new Button("Complete");
        btComplete.setOnAction(e -> {
            try {
                int reqID = Integer.parseInt(tfRequestID.getText());
                String dateFinished = dpDate.getValue().toString();
                String username = user.getUsername();
                if (RequestController.exists(reqID)) {
                    RequestController.completeRequest(reqID, dateFinished, username);
                } else {
                    Alert alertNotExist = new Alert(
                            Alert.AlertType.NONE,
                            "Please make sure you enter a valid request.",
                            ButtonType.OK
                    );
                    alertNotExist.show();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            tfRequestID.clear();
            dpDate.setValue(LocalDate.now());
        });
        GridPane.setConstraints(btComplete,1,3);

        grid.getChildren().addAll(requestID,tfRequestID,date,dpDate,btBack,btComplete);

        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

     /******************************
    **  DISPLAYS PENDING REQUESTS  **
     ******************************/
    public static void displayPendingRequests(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //creates tableview and populates it with data from database
        TableView tableView = RequestController.generatePendingRequests();

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

        //text area to add date completed for completing requests
        DatePicker dpDateCompleted = new DatePicker();
        dpDateCompleted.setPromptText("Date Completed");
        GridPane.setConstraints(dpDateCompleted,0,4);

        //button to complete request
        Button btCompleteRequest = new Button("Complete Request");
        btCompleteRequest.setOnAction(e -> {
            try {
                if (dpDateCompleted.getValue() == null) {
                    Alert alertNull = new Alert(
                            Alert.AlertType.NONE,
                            "Please make sure to enter a date completed",
                            ButtonType.OK
                    );
                    alertNull.show();
                } else {
                    ObservableList<Request> selected = tableView.getSelectionModel().getSelectedItems();
                    int id = selected.get(0).getId();
                    String dateCompleted = dpDateCompleted.getValue().toString();
                    String completedBy = user.getUsername();
                    RequestController.completeRequest(id,dateCompleted,completedBy);
                    displayPendingRequests(stage,user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btCompleteRequest,0,5);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.setMargin(btBack,new Insets(0,40,0,0));
        hBox.getChildren().addAll(btBack,dpDateCompleted,btCompleteRequest);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,425,400);
        stage.setScene(scene);
        stage.show();
    }

    /*******************************************
     **  DISPLAYS PENDING MAINTENANCE REQUESTS  **
     ******************************************/
    public static void displayPendingMaintenanceRequests(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                MaintenanceMenuView.display(stage, user);
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
        TableView tableView = RequestController.generatePendingMaintenanceRequests();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,425,400);
        stage.setScene(scene);
        stage.show();
    }

    /*******************************************
     **  DISPLAYS PENDING NURSE REQUESTS       **
     ******************************************/
    public static void displayPendingNurseRequests(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                NurseMenuView.display(stage, user);
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
        TableView tableView = RequestController.generatePendingNurseRequests();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,425,400);
        stage.setScene(scene);
        stage.show();
    }

    //function to display nurse logs
    public static void displayNurseLogs(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                NurseMenuView.display(stage, user);
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
        TableView tableView = RequestController.generateNurseLogs();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,650,400);
        stage.setScene(scene);
        stage.show();
    }

    //function to generate logs for all requests
    public static void displayLogs(Stage stage, User user) throws Exception {
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
        TableView tableView = RequestController.generateLogs();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,625,400);
        stage.setScene(scene);
        stage.show();
    }

    //function to display create request for residents
    public static void displayCreateRequestResident(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);


        // REQUEST TYPE //
        Label type = new Label("Request Type: ");
        ChoiceBox<String> cbType = new ChoiceBox<>();
        cbType.getItems().add("Nurse");
        cbType.getItems().add("Maintenance");
        cbType.setValue("Select One");
        GridPane.setConstraints(type,0,0);
        GridPane.setConstraints(cbType,1,0);

        // DATE CREATED //
        Label date = new Label("Date Created: ");
        DatePicker dpDate = new DatePicker();
        dpDate.setValue(LocalDate.now());
        GridPane.setConstraints(date,0,2);
        GridPane.setConstraints(dpDate,1,2);

        // DESCRIPTION //
        Label description = new Label("Description: ");
        TextArea taDescription = new TextArea();
        taDescription.setPrefHeight(100);
        taDescription.setPrefWidth(200);
        GridPane.setConstraints(description,0,3);
        GridPane.setConstraints(taDescription,1,3);

        //button to go to the previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e ->{
            try {
                ResidentMenuView.display(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,4);

        //button to create request
        Button btCreate = new Button("Create Request");
        btCreate.setOnAction(e ->{
            try {
                int newID = RequestController.getNewID();
                String newType = cbType.getValue();
                int newResidentID = UserController.getResidentId(user);
                String newDescription = taDescription.getText();
                String newDate = dpDate.getValue().toString();
                Request request = new Request(newID, newType, newResidentID, newDate, newDescription);
                RequestController.createRequest(request);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            cbType.setValue(null);
//            tfResidentID.clear();
            taDescription.clear();
            dpDate.setValue(LocalDate.now());
        });
        GridPane.setConstraints(btCreate,1,4);


        grid.getChildren().addAll(type,cbType,
//                residentID,tfResidentID,
                description,taDescription,date,dpDate,btBack,btCreate);

        Scene scene = new Scene(grid,300,250);
        stage.setScene(scene);
        stage.show();
    }

    //function to display pending maintenance requests
    public static void displayPendingRequestsResident(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //creates tableview and populates it with data from database
        TableView tableView = RequestController.generatePendingRequestsResident(user);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                ResidentMenuView.display(stage, user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.setMargin(btBack,new Insets(0,40,0,0));
        hBox.getChildren().addAll(btBack);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,425,400);
        stage.setScene(scene);
        stage.show();
    }

    //function to display maintenance log
    public static void displayMaintenanceLog(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //creates tableview and populates it with data from database
        TableView tableView = RequestController.generateMaintenanceLogs();

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                MaintenanceMenuView.display(stage, user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack,0,3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.setMargin(btBack,new Insets(0,40,0,0));
        hBox.getChildren().addAll(btBack);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox,625,400);
        stage.setScene(scene);
        stage.show();
    }
}
