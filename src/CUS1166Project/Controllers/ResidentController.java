package CUS1166Project.Controllers;

import CUS1166Project.Models.Resident;
import CUS1166Project.Utilities.Connect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
                    + model.getID() + ", '" + model.getFName() + "', '" + model.getLName() + "', '"
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
                + resident.getEContact() + "' WHERE id = " + resident.getID() + ";"
        );

        Alert alertResidentUpdated = new Alert (
                Alert.AlertType.NONE,
                "Resident Details Successfully Updated!",
                ButtonType.OK
        );
        alertResidentUpdated.show();
    }
}
