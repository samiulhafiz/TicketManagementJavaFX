/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finaltermassignementticketmanagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author samiu
 */
public class Passenger {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty mobile;
    private final StringProperty destination;

    public Passenger() {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        mobile = new SimpleStringProperty(this, "mobile");
        destination = new SimpleStringProperty(this, "destination");
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String newId) {
        id.set(newId);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String newName) {
        name.set(newName);
    }

    public StringProperty mobileProperty() {
        return mobile;
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String newMobile) {
        mobile.set(newMobile);
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public String getDestination() {
        return destination.get();
    }

    public void setDestination(String newDestination) {
        destination.set(newDestination);
    }

}
