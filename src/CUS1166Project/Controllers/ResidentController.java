package CUS1166Project.Controllers;

import CUS1166Project.Models.Request;
import CUS1166Project.Models.Resident;
import CUS1166Project.Models.User;
import CUS1166Project.Utilities.Connect;
import CUS1166Project.Utilities.Encryptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;

public class ResidentController {
    static Connect con = new Connect();

    //Method to get the next id for adding a new resident
    public static int getNewID() throws Exception {
        int result = 0;
        ResultSet rs = con.st.executeQuery("SELECT id FROM residents WHERE id = " +
                "(SELECT MAX(id) FROM residents);"
        );

        while (rs.next()) {
            result = Integer.parseInt(rs.getString("id"));
        }

        return result + 1;
    }

    //function to check if resident exists without using id
    public static boolean residentExists(Resident model) throws Exception {
        String result = null;

        ResultSet rs = con.st.executeQuery("SELECT id FROM residents WHERE fName = '" + model.getFName() +
                "' AND lName = '" + model.getLName() + "' AND unit = '" + model.getUnit() +
                "' AND eContact = '" + model.getEContact() + "';"
        );

        while (rs.next()) {
            result = rs.getString("id");
        }

        return result != null;
    }

    //function to check if a specific resident id exists in the database
    public static boolean idExists(int id) throws Exception {
        String result = null;

        ResultSet rs = con.st.executeQuery("SELECT id FROM residents WHERE id = " + id + ";");

        while (rs.next()) {
            result = rs.getString("id");
        }

        return result != null;
    }

    //function to check if a resident has a username
    public static boolean hasUsername(int id) throws Exception {
        String result = null;

        ResultSet rs = con.st.executeQuery("SELECT username FROM residents WHERE id = " + id + ";");

        while (rs.next()) {
            result = rs.getString("username");
        }

        return result != null;
    }

    //function to return a new ResidentModel by getting details from the database using id
    public static Resident getResidentDetails(int id) throws Exception {
        String fName = "";
        String lName = "";
        String unit = "";
        String eContact = "";

        ResultSet rs = con.st.executeQuery("SELECT * FROM residents WHERE id = " + id + ";");

        while (rs.next()) {
            fName = rs.getString("fName");
            lName = rs.getString("lName");
            unit = rs.getString("unit");
            eContact = rs.getString("eContact");
        }

        return new Resident(id, fName, lName, unit, eContact);
    }

    //function to add resident to database
    public static void addResident(Resident model) throws Exception {
        if (residentExists(model)) {
            Alert alertResidentExists = new Alert(
                    Alert.AlertType.NONE,
                    "This resident already exists.",
                    ButtonType.OK
            );
            alertResidentExists.show();
        } else {
            con.st.executeUpdate("INSERT INTO residents (id, fName, lName, unit, eContact) VALUES ("
                    + model.getId() + ", '" + model.getFName() + "', '" + model.getLName() + "', '"
                    + model.getUnit() + "', '"  + model.getEContact() + "');"
            );

            Alert alertResidentAdded = new Alert (
                    Alert.AlertType.NONE,
                    "Resident Added!",
                    ButtonType.OK
            );
            alertResidentAdded.show();
        }
    }

    //function to delete resident from database
    public static void deleteResident(int id) throws Exception {
        if (!idExists(id)) {
            Alert alertResidentNotExist = new Alert (
                    Alert.AlertType.NONE,
                    "Please enter a valid resident id.",
                    ButtonType.OK
            );
            alertResidentNotExist.show();
        } else {
            con.st.executeUpdate("DELETE FROM residents WHERE id = " + id + ";");

            Alert alertResidentDeleted = new Alert (
                    Alert.AlertType.NONE,
                    "Resident Deleted!",
                    ButtonType.OK
            );
            alertResidentDeleted.show();
        }
    }

    //function to edit resident details
    public static void updateResident(Resident resident) throws Exception {
        con.st.executeUpdate("UPDATE residents SET fName = '" + resident.getFName() +
                "', lName = '" + resident.getLName() + "', unit = '" + resident.getUnit() + "', eContact = '"
                + resident.getEContact() + "' WHERE id = " + resident.getId() + ";"
        );

        Alert alertResidentUpdated = new Alert (
                Alert.AlertType.NONE,
                "Resident Details Successfully Updated!",
                ButtonType.OK
        );
        alertResidentUpdated.show();
    }

    //function to generate log of residents
    public static TableView generateResidents() throws Exception {
        ObservableList<Resident> residents = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Resident, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Resident, String> fName = new TableColumn<>("First Name");
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));

        TableColumn<Resident, String> lName = new TableColumn<>("Last Name");
        lName.setCellValueFactory(new PropertyValueFactory<>("lName"));

        TableColumn<Resident, String> unit = new TableColumn<>("Unit");
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));

        TableColumn<Resident, String> eContact = new TableColumn<>("Emergency Contact");
        eContact.setCellValueFactory(new PropertyValueFactory<>("eContact"));

        TableColumn<Resident, String> username = new TableColumn<>("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        tableView.getColumns().add(id);
        tableView.getColumns().add(fName);
        tableView.getColumns().add(lName);
        tableView.getColumns().add(unit);
        tableView.getColumns().add(eContact);
        tableView.getColumns().add(username);

        ResultSet rs = con.st.executeQuery("SELECT * FROM residents;");

        while(rs.next()) {
            int rId = rs.getInt("id");
            String rFName = rs.getString("fName");
            String rLName = rs.getString("lName");
            String rUnit = rs.getString("unit");
            String rEContact = rs.getString("eContact");
            String rUsername = rs.getString("username");

            Resident resident = new Resident(rId,rFName,rLName,rUnit,rEContact,rUsername);
            residents.add(resident);
        }

        tableView.setItems(residents);

        return tableView;
    }

    //function to create user account for a resident
    public static void createAccount(int id, String username, String password) throws Exception {
        //User user = new User(username,password,"resident");
        con.st.executeUpdate("Insert INTO users VALUES ('" + username + "', '" + Encryptor.encryptString(password) +
                "', 'resident');"
        );

        con.st.executeUpdate("UPDATE residents SET username = '" + username + "' WHERE id = " + id + ";");

        Alert alertSuccess = new Alert(
                Alert.AlertType.NONE,
                "User account successfully created!",
                ButtonType.OK
        );
        alertSuccess.show();
    }
}
