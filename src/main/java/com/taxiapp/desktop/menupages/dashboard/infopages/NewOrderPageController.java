package com.taxiapp.desktop.menupages.dashboard.infopages;


import static com.taxiapp.desktop.services.LocationService.parseCoordinatesFromUrl;

import com.taxiapp.desktop.entities.Role;
import com.taxiapp.desktop.entities.TaxiType;
import com.taxiapp.desktop.net.requests.FilterUsersRequest;
import com.taxiapp.desktop.net.responses.UserDataResponse;
import com.taxiapp.desktop.services.LocationService.Coordinates;
import com.taxiapp.desktop.services.OrderService;
import com.taxiapp.desktop.services.UserService;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NewOrderPageController implements Initializable {

    public VBox inputBox;
    public ComboBox<String> userComboBox;
    public TextField pickupLocationText;
    public TextField deliveryLocationText;
    public ComboBox<TaxiType> taxiTypeComboBox;
    public Button createOrderButton;
    public HBox mainBox;
    public WebView webView;
    public TextField searchBar;
    public ListView listView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> userIdList = UserService.getUsers(
            FilterUsersRequest.builder().role(Role.USER).build(),0,0)
            .stream()
            .map(UserDataResponse::getUserId)
            .toList();

        userComboBox.setItems(FXCollections.observableArrayList(
            userIdList
        ));

        taxiTypeComboBox.setItems(FXCollections.observableArrayList(TaxiType.values()));
        userComboBox.getSelectionModel().selectFirst();

        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.google.com/maps");
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue,
                String newValue) {

                System.out.println(newValue);
                Coordinates coordinates = parseCoordinatesFromUrl(newValue);
                if (coordinates != null) {
                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationAlert.setHeaderText(null);
                    confirmationAlert.setContentText("Set location ["+ coordinates.toString()+"] as pickup/delivery location?");

                    ButtonType pickupButton = new ButtonType("Set as pickup location");
                    ButtonType deliveryButton = new ButtonType("Set as delivery location");
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    confirmationAlert.getButtonTypes().setAll(pickupButton,deliveryButton, cancelButton);

                    Optional<ButtonType> result = confirmationAlert.showAndWait();
                    if (result.isPresent() && result.get() == pickupButton) {
                        pickupLocationText.setText(coordinates.toString());
                    }
                    else if (result.isPresent() && result.get() == deliveryButton) {
                        deliveryLocationText.setText(coordinates.toString());
                    }


                    System.out.println("Latitude: " + coordinates.latitude);
                    System.out.println("Longitude: " + coordinates.longitude);
                } else {
                    System.out.println("Coordinates not found in the URL");
                }
            }

        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userComboBox.getSelectionModel().select(newValue);
            }
        });
    }


    public void createOrderButtonClicked(MouseEvent mouseEvent) {
        String userId = userComboBox.getSelectionModel().getSelectedItem();
        String pickupLocation = pickupLocationText.getText();
        String deliveryLocation = deliveryLocationText.getText();
        String taxiType = taxiTypeComboBox.getSelectionModel().getSelectedItem().toString();

        OrderService.createNewOrder(userId,pickupLocation,deliveryLocation,taxiType);
    }


    public void searchUser(ActionEvent actionEvent) {
        listView.getItems().clear();
        FilterUsersRequest filterUsersRequest = FilterUsersRequest.builder()
            .role(Role.USER)
            .userEmail(searchBar.getText())
            .build();

        listView.getItems().addAll(UserService.getUsers(filterUsersRequest,0,0).stream().map(UserDataResponse::getUserId).toList());
    }

}
