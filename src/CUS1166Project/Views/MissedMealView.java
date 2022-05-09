package CUS1166Project.Views;

import CUS1166Project.Controllers.MissedMealController;
import CUS1166Project.Controllers.RequestController;
import CUS1166Project.Models.MissedMeal;
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

public class MissedMealView {

    public static void addMealMissed(Stage stage, User user) {
        stage.setTitle("SR. Housing");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label resId = new Label("Resident ID: ");
        TextField tfResId = new TextField();
        tfResId.setPromptText("resident id");
        GridPane.setConstraints(resId,0,0);
        GridPane.setConstraints(tfResId,1,0);

        Label mealMissed = new Label("Meal Missed:");
        ChoiceBox<String> cbMealMissed = new ChoiceBox<>();
        cbMealMissed.getItems().add("Breakfast");
        cbMealMissed.getItems().add("Lunch");
        cbMealMissed.getItems().add("Dinner");
        cbMealMissed.setValue("Select One");
        GridPane.setConstraints(mealMissed,0,1);
        GridPane.setConstraints(cbMealMissed,1,1);

        Label dateMissed = new Label("Date Missed: ");
        DatePicker dpDateMissed = new DatePicker();
        dpDateMissed.setValue(LocalDate.now());
        GridPane.setConstraints(dateMissed,0,2);
        GridPane.setConstraints(dpDateMissed,1,2);

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
        GridPane.setConstraints(btBack,0,4);

        Button btAdd = new Button("Add");
        btAdd.setOnAction(e -> {
            try {
                int id = Integer.parseInt(tfResId.getText());
                String missedMeal = cbMealMissed.getValue().toLowerCase();
                String date = dpDateMissed.getValue().toString();
                MissedMeal meal = new MissedMeal(id,missedMeal,date);
                MissedMealController.add(meal);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            tfResId.clear();
            cbMealMissed.setValue("Select One");
            dpDateMissed.setValue(LocalDate.now());
        });
        GridPane.setConstraints(btAdd,1,4);

        grid.getChildren().addAll(resId,tfResId,mealMissed,cbMealMissed,dateMissed,dpDateMissed,
                btBack,btAdd
        );

        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }

    //function to display missed meal reports with uncontacted residents
    public static void displayUncontacted(Stage stage, User user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
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
        GridPane.setConstraints(btBack,0,3);

        //hbox to add buttons at bottom of tableview
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
//        hBox.getChildren().addAll(btBack);

        //creates tableview and populates it with data from database
        TableView tableView = MissedMealController.generateUncontacted();

        //button to mark contacted
        Button btMarkContacted = new Button("Mark Contacted");
        btMarkContacted.setOnAction(i -> {
            try {
                ObservableList<MissedMeal> selected = tableView.getSelectionModel().getSelectedItems();
                int resId = selected.get(0).getResId();
                String mealMissed = selected.get(0).getMealMissed();
                String dateMissed = selected.get(0).getDateMissed();
                MissedMeal meal = new MissedMeal(resId,mealMissed,dateMissed);
                MissedMealController.markContacted(meal);
                displayUncontacted(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btMarkContacted,0,4);

        hBox.getChildren().addAll(btBack,btMarkContacted);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView,hBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
