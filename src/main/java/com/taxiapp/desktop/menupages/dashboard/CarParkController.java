package com.taxiapp.desktop.menupages.dashboard;

import static com.taxiapp.desktop.entities.Role.DRIVER;

import atlantafx.base.util.IntegerStringConverter;
import com.taxiapp.desktop.net.requests.FilterUsersRequest;
import com.taxiapp.desktop.net.requests.NewCarRequest;
import com.taxiapp.desktop.net.responses.CarDataResponse;
import com.taxiapp.desktop.net.responses.DriverDataResponse;
import com.taxiapp.desktop.services.CarService;
import com.taxiapp.desktop.services.DriverService;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class CarParkController implements Initializable {

    public TextField carIdText;
    public TextField carPlateText;
    public ComboBox carStatusComboBox;
    public VBox filterBox;
    public VBox contentBox;
    public VBox inputBox;
    public TextField manufacturerText;
    public TextField modelText;
    public Spinner carYearSpinner;
    public ComboBox driverIdComboBox;
    public ScrollPane scrollPane;


    public void saveCarButtonClicked(MouseEvent mouseEvent) {
        NewCarRequest newCarRequest = NewCarRequest.builder()
            .manufacturer(manufacturerText.getText())
            .model(modelText.getText())
            .carPlate(carPlateText.getText())
            .productionYear((int)carYearSpinner.valueProperty().getValue())
            .driverId(driverIdComboBox.getSelectionModel().getSelectedItem().toString())
            .build();

        CarService.createNewCar(newCarRequest);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contentBox.prefWidthProperty().bind(scrollPane.widthProperty());

        StringConverter<Integer> converter = new IntegerStringConverter();
        carYearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1995, LocalDate.now().getYear(), 1995));
        carYearSpinner.setEditable(true);
        carYearSpinner.setPrefWidth(120);
        carYearSpinner.getValueFactory().setConverter(converter);

        var usersFilter = FilterUsersRequest.builder().role(DRIVER).build();
        List<DriverDataResponse> drivers = DriverService.getDrivers();

        driverIdComboBox.setItems(
            FXCollections.observableList(drivers.stream().map(d->d.getDriverId()).toList())
        );

        List<CarDataResponse> allCars = CarService.getAllCars();

        for(CarDataResponse car : allCars){
            VBox vBox = new VBox();
            TextField carManufacturer = new TextField(car.getCarManufacturer());
            TextField carModel = new TextField(car.getCarModel());
            TextField carPlate = new TextField(car.getCarPlate());
            TextField idText = new TextField(car.getCarId());
            TextField driverIdText = new TextField(car.getDriverId());
            vBox.getChildren().addAll(idText, driverIdText, carManufacturer, carModel, carPlate);
            contentBox.getChildren().add(vBox);
        }

    }
}
