/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package finaltermassignementticketmanagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;

/**
 *
 * @author samiu
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtDestination;
    @FXML
    private TableView<Passenger> table;
    @FXML
    private TableColumn<Passenger, String> IDColmn;
    @FXML
    private TableColumn<Passenger, String> NameColmn;
    @FXML
    private TableColumn<Passenger, String> MobileColmn;
    @FXML
    private TableColumn<Passenger, String> DestinationColmn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    void Add(ActionEvent event) {
        String stname, mobile, destination;
        stname = txtName.getText();
        mobile = txtMobile.getText();
        destination = txtDestination.getText();
        Connect();

        try {
            pst = con.prepareStatement("insert into registration(name, mobile, destination) values (?, ?, ?)");
            pst.setString(1, stname);
            pst.setString(2, mobile);
            pst.setString(3, destination);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Passenger Registration");
            alert.setHeaderText("Passenger Registration");
            alert.setContentText("Record Added!");
            alert.showAndWait();

            table();

            txtName.setText("");
            txtMobile.setText("");
            txtDestination.setText("");
            txtName.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void table() {
        Connect();
        ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id,name,mobile,destination from registration");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Passenger st = new Passenger();
                    st.setId(rs.getString("id"));
                    st.setName(rs.getString("name"));
                    st.setMobile(rs.getString("mobile"));
                    st.setDestination(rs.getString("destination"));
                    passengers.add(st);
                }
            }
            table.setItems(passengers);
            IDColmn.setCellValueFactory(f -> f.getValue().idProperty());
            NameColmn.setCellValueFactory(f -> f.getValue().nameProperty());
            MobileColmn.setCellValueFactory(f -> f.getValue().mobileProperty());
            DestinationColmn.setCellValueFactory(f -> f.getValue().destinationProperty());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setRowFactory(tv -> {
            TableRow<Passenger> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtMobile.setText(table.getItems().get(myIndex).getMobile());
                    txtDestination.setText(table.getItems().get(myIndex).getDestination());

                }
            });
            return myRow;
        });

    }

    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try {
            pst = con.prepareStatement("delete from registration where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Passenger Registrationn");

            alert.setHeaderText("Passenger Registration");
            alert.setContentText("Deletedd!");
            alert.showAndWait();
            table();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Update(ActionEvent event) {

        String stname, mobile, destination;

        myIndex = table.getSelectionModel().getSelectedIndex();
        if (myIndex >= 0) {
            id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        } else {
        }

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        stname = txtName.getText();
        mobile = txtMobile.getText();
        destination = txtDestination.getText();
        try {
            pst = con.prepareStatement("update registration set name = ?,mobile = ? ,destination = ? where id = ? ");
            pst.setString(1, stname);
            pst.setString(2, mobile);
            pst.setString(3, destination);
            pst.setInt(4, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Passenger Registrationn");

            alert.setHeaderText("Passenger Registration");
            alert.setContentText("Updateddd!");
            alert.showAndWait();
            table();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ticketmanagementdb", "root", "");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
