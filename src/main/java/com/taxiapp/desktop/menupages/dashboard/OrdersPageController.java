package com.taxiapp.desktop.menupages.dashboard;

import static com.taxiapp.desktop.TableHelper.createColumn;
import static javafx.scene.layout.Priority.ALWAYS;

import atlantafx.base.theme.Styles;
import atlantafx.base.util.IntegerStringConverter;
import com.taxiapp.desktop.entities.OrderStatus;
import com.taxiapp.desktop.menupages.dashboard.infopages.OrderInfoController;
import com.taxiapp.desktop.net.requests.FilterOrdersRequest;
import com.taxiapp.desktop.net.responses.OrderDataResponse;
import com.taxiapp.desktop.services.OrderService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class OrdersPageController implements Initializable {

    public VBox contentBox;

    public VBox filterBox;
    public TextField locationToField;
    public TextField locationFromField;
    public TextField userIdField;
    public ComboBox orderStatusComboBox;
    public DatePicker orderDatePicker;
    public HBox rootBox;
    public Button newOrderButton;
    public Spinner pageSpinner;
    public Spinner limitSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringConverter<Integer> converter = new IntegerStringConverter();
        pageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        limitSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 10));

        pageSpinner.getValueFactory().setConverter(converter);
        limitSpinner.getValueFactory().setConverter(converter);



        List<String> values = List.of(OrderStatus.CANCELLED.toString(),
            OrderStatus.PENDING.toString(),
            OrderStatus.COMPLETED.toString(),
            OrderStatus.IN_PROGRESS.toString(),
            "All");

        ObservableList orderStatusValues  = FXCollections.observableList(values);

        orderStatusComboBox.setItems(orderStatusValues);
        orderStatusComboBox.getSelectionModel().select("All");

        var accentBtn = new Button("_Apply");
        accentBtn.getStyleClass().add(Styles.ACCENT);
        accentBtn.setMnemonicParsing(true);
        accentBtn.setOnMouseClicked(this::applyFilterButtonClicked);

        newOrderButton.getStyleClass().add(Styles.ACCENT);
        newOrderButton.setMnemonicParsing(true);
        newOrderButton.setOnMouseClicked(this::goToCreateNewOrderPage);

        var today = LocalDate.now(ZoneId.systemDefault());
        orderDatePicker.setValue(today);
        orderDatePicker.setEditable(false);

        filterBox.getChildren().add(accentBtn);


        displayOrders();
    }

    private void displayOrders() {
        contentBox.getChildren().clear();
        Task<List<OrderDataResponse>> task = new Task<>() {
            @Override
            protected List<OrderDataResponse> call() throws Exception {
                var ordersFilter = buildFilter();
                return OrderService.getOrders(ordersFilter, (int) pageSpinner.valueProperty().getValue(),
                    (int) limitSpinner.valueProperty().getValue());
            }
        };

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fetching Orders");
        alert.setHeaderText(null);
        alert.setContentText("Please wait while we fetch the orders...");

        ProgressBar progressBar = new ProgressBar();
        alert.setGraphic(progressBar);

        task.setOnSucceeded(event -> {
            alert.close();
            List<OrderDataResponse> ordersList = task.getValue();
            var table = initializeTable(ordersList);
            contentBox.getChildren().add(table);
        });

        task.setOnFailed(event -> {
            alert.close();
            // Handle task failure if needed
        });

        new Thread(task).start();

        alert.show();
    }


    private TableView<OrderDataResponse> initializeTable(
        List<OrderDataResponse> ordersList) {
        var orderIdColumn = createColumn("ID", OrderDataResponse::getId);
        var userNameColumn = createColumn("User Name", OrderDataResponse::getUserName);
        var driverNameColumn = createColumn("Driver Name", OrderDataResponse::getDriverName);
        var locationFromColumn = createColumn("From", OrderDataResponse::getPickupAddress);
        var locationToColumn = createColumn("To", OrderDataResponse::getDeliveryAddress);
        var statusColumn = createColumn("Status", OrderDataResponse::getOrderStatus);
        var priceColumn = createColumn("Price", OrderDataResponse::getPrice);

        var table = new TableView<OrderDataResponse>(FXCollections.observableList(ordersList));

        Styles.toggleStyleClass(table,Styles.BORDERED);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
             if (newSelection != null) {
                 OrderDataResponse selectedObject = table.getSelectionModel().getSelectedItem();
                 displayOrderInfo(selectedObject);
             }
         });

        table.getColumns().setAll(orderIdColumn, userNameColumn, driverNameColumn,locationFromColumn,locationToColumn,priceColumn,statusColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(table, Priority.ALWAYS);
        return table;
    }

    private FilterOrdersRequest buildFilter() {
        FilterOrdersRequest ordersFilter = new FilterOrdersRequest();
        String userId = userIdField.getText();
        String ploc = locationFromField.getText();
        String dloc = locationToField.getText();
        String orderStatus = orderStatusComboBox.getValue().toString();

        ordersFilter.setUserId(userId.isBlank()?null:userId);
        ordersFilter.setPickupLocation(ploc.isBlank()?null:ploc);
        ordersFilter.setDeliveryLocation(dloc.isBlank()?null:dloc);
        ordersFilter.setOrderStatus(orderStatus.equals("All")?null:OrderStatus.valueOf(orderStatus));
        return ordersFilter;
    }


    private void displayOrderInfo(OrderDataResponse orderData) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("infopages/order-info.fxml"));
            Node n = loader.load();
            OrderInfoController controller = loader.getController();

            controller.orderTitle.setText("Order");
            controller.orderIdText.setText(orderData.getId());
            controller.locationFromText.setText(orderData.getPickupLocation());
            controller.locationToText.setText(orderData.getDeliveryLocation());
            controller.userNameText.setText(orderData.getUserName());
            controller.taxiTypeLabel.setText(orderData.getTaxiType());
            controller.orderedAtText.setText(orderData.getOrderedAt());
            controller.finishedAtText.setText(orderData.getFinishedAt());

            HBox.setHgrow(n,ALWAYS);
            rootBox.getChildren().clear();
            rootBox.getChildren().add(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToCreateNewOrderPage(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("infopages/new-order.fxml"));
            Node n = loader.load();
            HBox.setHgrow(n,ALWAYS);
            rootBox.getChildren().clear();
            rootBox.getChildren().add(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyFilterButtonClicked(MouseEvent event) {
        displayOrders();
    }
}
