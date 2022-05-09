package CUS1166Project.Views;

import CUS1166Project.Controllers.MissedMealController;
import CUS1166Project.Controllers.QuarantineController;
import CUS1166Project.Controllers.ResidentController;
import CUS1166Project.Models.Quarantine;
import CUS1166Project.Models.Resident;
import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;


public class QuarantineView {

    //function to display menu for creating new quarantine
    public static void displayCreate(Stage stage, User user) {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label and textfield for resident id
        Label id = new Label("Resident ID: ");
        GridPane.setConstraints(id, 0, 0);
        TextField tfId = new TextField();
        tfId.setPromptText("resident id");
        GridPane.setConstraints(tfId, 1, 0);

        //label and datepicker for start date
        Label dateStart = new Label("Start Date: ");
        GridPane.setConstraints(dateStart, 0, 1);
        DatePicker dpStart = new DatePicker();
        dpStart.setValue(LocalDate.now());
        GridPane.setConstraints(dpStart, 1, 1);

        //label and datepicker for end date
        Label dateEnd = new Label("End Date: ");
        GridPane.setConstraints(dateEnd, 0, 2);
        DatePicker dpEnd = new DatePicker();
        dpEnd.setValue(dpStart.getValue().plusDays(5));
        GridPane.setConstraints(dpEnd, 1, 2);


        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                if (user.getType().toLowerCase().equals("admin")) {
                    AdminMenuView.display(stage, user);
                } else if (user.getType().toLowerCase().equals("nurse")) {
                    NurseMenuView.display(stage, user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btBack, 0, 4);

        Button btCreate = new Button("Add");
        btCreate.setOnAction(e -> {
            try {
                int resId = Integer.parseInt(tfId.getText());
                String start = dpStart.getValue().toString();
                String end = dpEnd.getValue().toString();
                Quarantine newQuarantine = new Quarantine(resId, start, end);
                if (ResidentController.residentExists(new Resident(resId))) {
                    QuarantineController.create(newQuarantine);
                } else {
                    Alert alertInvalid = new Alert(
                            Alert.AlertType.NONE,
                            "Please enter a valid user.",
                            ButtonType.OK
                    );
                    alertInvalid.show();
                    tfId.clear();
                }
                QuarantineController.create(newQuarantine);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            tfId.clear();
            dpStart.setValue(LocalDate.now());
            dpEnd.setValue(dpStart.getValue().plusDays(5));
        });
        GridPane.setConstraints(btCreate, 1, 4);

        grid.getChildren().addAll(id, tfId, dateStart, dpStart, dateEnd, dpEnd, btBack, btCreate);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    //function to display residents currently in quarantine
    public static void displayInProgress(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                if (user.getType().equals("admin")) {
                    AdminMenuView.display(stage, user);
                } else if (user.getType().equals("nurse")) {
                    NurseMenuView.display(stage, user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        GridPane.setConstraints(btBack, 0, 3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btBack);

        //creates tableview and populates it with data from database
        TableView tableView = QuarantineController.generateInProgress();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView, hBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    //function to display quarantine records for logs
    public static void displayLogs(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //button to go back to previous menu
        Button btBack = new Button("Back");
        btBack.setOnAction(e -> {
            try {
                if (user.getType().equals("admin")) {
                    AdminMenuView.display(stage, user);
                } else if (user.getType().equals("nurse")) {
                    NurseMenuView.display(stage, user);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        GridPane.setConstraints(btBack, 0, 3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btBack);

        //creates tableview and populates it with data from database
        TableView tableView = QuarantineController.generateLogs();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView, hBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

}
