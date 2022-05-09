package CUS1166Project.Controllers;

import CUS1166Project.Models.MissedMeal;
import CUS1166Project.Models.Quarantine;
import CUS1166Project.Utilities.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class QuarantineController {
    static Connect con = new Connect();

    //function to make sure this quarantine hasn't been recorded already
    public static boolean exists(Quarantine q) throws Exception {
        String result = null;
        ResultSet rs = con.st.executeQuery("Select * FROM quarantines WHERE resId = " + q.getResId() +
                " AND dateStart = '" + q.getDateStart() + "';"
        );

        while(rs.next()) {
            result = rs.getString("dateStart");
        }

        return result != null;
    }
    //function to add a new person into quarantine list
    public static void create(Quarantine q) throws Exception {
        if (exists(q)) {
            Alert alertExists = new Alert (
                    Alert.AlertType.NONE,
                    "This quarantine record already exists",
                    ButtonType.OK
            );
            alertExists.show();
        } else {

            con.st.executeUpdate("INSERT INTO quarantines VALUES (" + q.getResId() + ", '" + q.getDateStart() +
                    "', '" + q.getDateEnd() + "');"
            );

            Alert alertCreated = new Alert (
                    Alert.AlertType.NONE,
                    "New quarantine record added!",
                    ButtonType.OK
            );
            alertCreated.show();
        }

        Alert alertCreated = new Alert (
                Alert.AlertType.NONE,
                "New quarantine record added!",
                ButtonType.OK
        );
        alertCreated.show();
    }

    //function to generate tableview for displaying in progress quarantines
    public static TableView generateInProgress() throws Exception {
        ObservableList<Quarantine> quarantines = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Quarantine, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<Quarantine, String> dateStart = new TableColumn<>("Start Date");
        dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));

        TableColumn<Quarantine, String> dateEnd = new TableColumn<>("End Date");
        dateEnd.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));


        tableView.getColumns().add(resId);
        tableView.getColumns().add(dateStart);
        tableView.getColumns().add(dateEnd);


        ResultSet rs = con.st.executeQuery("SELECT * FROM quarantines WHERE dateEnd > '" + LocalDate.now() +"';");

        while(rs.next()) {
            int qResId = rs.getInt("resId");
            String qDateStart = rs.getString("dateStart");
            String qDateEnd = rs.getString("dateEnd");

            quarantines.add(new Quarantine(qResId, qDateStart, qDateEnd));
        }

        tableView.setItems(quarantines);

        return tableView;
    }

    //generate quarantine tableview for logs
    public static TableView generateLogs() throws Exception {
        ObservableList<Quarantine> quarantines = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Quarantine, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<Quarantine, String> dateStart = new TableColumn<>("Start Date");
        dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));

        TableColumn<Quarantine, String> dateEnd = new TableColumn<>("End Date");
        dateEnd.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));


        tableView.getColumns().add(resId);
        tableView.getColumns().add(dateStart);
        tableView.getColumns().add(dateEnd);


        ResultSet rs = con.st.executeQuery("SELECT * FROM quarantines ORDER BY dateStart;");

        while(rs.next()) {
            int qResId = rs.getInt("resId");
            String qDateStart = rs.getString("dateStart");
            String qDateEnd = rs.getString("dateEnd");

            quarantines.add(new Quarantine(qResId, qDateStart, qDateEnd));
        }

        tableView.setItems(quarantines);

        return tableView;
    }
}
