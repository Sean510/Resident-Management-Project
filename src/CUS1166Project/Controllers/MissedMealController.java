package CUS1166Project.Controllers;

import CUS1166Project.Models.MissedMeal;
import CUS1166Project.Models.Request;
import CUS1166Project.Models.Resident;
import CUS1166Project.Utilities.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;

public class MissedMealController {

    static Connect con = new Connect();

    //function to check if a missed meal has already been recorded
    public static boolean exists(MissedMeal meal) throws Exception {
        String result = null;
        ResultSet rs = con.st.executeQuery("SELECT * FROM missedmeals WHERE resId = " + meal.getResId() +
                " AND mealMissed = '" + meal.getMealMissed() + "' AND dateMissed = '" + meal.getDateMissed() +
                "';"
        );

        while (rs.next()) {
            result = rs.getString("mealMissed");
        }

        return result != null;
    }

    //function to record a new missed meal
    public static void add(MissedMeal meal) throws Exception {
        if (exists(meal)) {
            Alert alertExists = new Alert(
                    Alert.AlertType.NONE,
                    "This missed meal has already been recorded.",
                    ButtonType.OK
            );
            alertExists.show();
        } else {
            con.st.executeUpdate("INSERT INTO missedmeals VALUES (" + meal.getResId() + ", '"
                    + meal.getMealMissed() + "', '" + meal.getDateMissed() + "', 'no');"
            );

            Alert alertRecorded = new Alert(
                    Alert.AlertType.NONE,
                    "Missed meal successfully recorded!",
                    ButtonType.OK
            );
            alertRecorded.show();
        }
    }

    //function to generate tableview of uncontacted residents
    public static TableView generateUncontacted() throws Exception {
        ObservableList<MissedMeal> meals = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<MissedMeal, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<MissedMeal, String> mealMissed = new TableColumn<>("Meal Missed");
        mealMissed.setCellValueFactory(new PropertyValueFactory<>("mealMissed"));

        TableColumn<MissedMeal, String> dateMissed = new TableColumn<>("Date Missed");
        dateMissed.setCellValueFactory(new PropertyValueFactory<>("dateMissed"));


        tableView.getColumns().add(resId);
        tableView.getColumns().add(mealMissed);
        tableView.getColumns().add(dateMissed);


        ResultSet rs = con.st.executeQuery("SELECT * FROM missedmeals WHERE contacted = 'no';");

        while(rs.next()) {
            int mResId = rs.getInt("resId");
            String mMealMissed = rs.getString("mealMissed");
            String mDateMissed = rs.getString("dateMissed");

            meals.add(new MissedMeal(mResId, mMealMissed, mDateMissed));
        }

        tableView.setItems(meals);

        return tableView;
    }

    //function to mark a resident who missed a meal as contacted
    public static void markContacted(Resident resident) throws Exception {
        
    }
}
