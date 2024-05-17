package com.taxiapp.desktop.menupages.dashboard;

import static com.taxiapp.desktop.TableHelper.createColumn;
import static javafx.scene.layout.Priority.ALWAYS;

import atlantafx.base.theme.Styles;
import com.taxiapp.desktop.menupages.dashboard.infopages.DriverInfoController;
import com.taxiapp.desktop.net.responses.DriverDataResponse;
import com.taxiapp.desktop.services.DriverService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DriversPageController implements Initializable {

    @FXML
    public VBox contentBox;
    @FXML
    public VBox filterBox;
    public HBox rootBox;
    public Button newDriverButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contentBox.getChildren().clear();
        newDriverButton.getStyleClass().add(Styles.ACCENT);
        try{

            List<DriverDataResponse> driversList = DriverService.getDrivers();

            var driverIdColumn = createColumn("ID", DriverDataResponse::getDriverId);
            var driverNameColumn = createColumn("Name", DriverDataResponse::getDriverName);
            var driverEmailColum = createColumn("Email", DriverDataResponse::getDriverEmail);
            var driverStatusColumn = createColumn("Status", DriverDataResponse::getDriverStatus);
            var carPlateColumn = createColumn("Car Plate", DriverDataResponse::getCarPlate);

            var table = new TableView<DriverDataResponse>(FXCollections.observableList(driversList));

            table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    DriverDataResponse selectedObject = table.getSelectionModel().getSelectedItem();
                    displayDriverInfo(selectedObject);
                }
            });

            table.getColumns().setAll(driverIdColumn,driverNameColumn,driverEmailColum,driverStatusColumn,carPlateColumn);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            VBox.setVgrow(table, Priority.ALWAYS);
            contentBox.getChildren().add(table);
        }catch (NullPointerException ex){
            contentBox.getChildren().add(new Label("Error"));
        }
    }

    private void displayDriverInfo(DriverDataResponse driverDataResponse) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("infopages/driver-info.fxml"));
            Node n = loader.load();
            DriverInfoController controller = loader.getController();

            controller.driverIdText.setText(driverDataResponse.getDriverId());
            controller.setDriverId(driverDataResponse.getDriverId());
            controller.emailText.setText(driverDataResponse.getDriverEmail());
            controller.driverNameText.setText(driverDataResponse.getDriverName());
            HBox.setHgrow(n,ALWAYS);
            rootBox.getChildren().clear();
            rootBox.getChildren().add(n);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void newDriverButtonClicked(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("infopages/new-driver.fxml"));
            Node n = loader.load();
            HBox.setHgrow(n,ALWAYS);
            rootBox.getChildren().clear();
            rootBox.getChildren().add(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
