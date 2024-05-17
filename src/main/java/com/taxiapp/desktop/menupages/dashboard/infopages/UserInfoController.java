package com.taxiapp.desktop.menupages.dashboard.infopages;

import com.taxiapp.desktop.net.requests.FilterOrdersRequest;
import com.taxiapp.desktop.net.responses.OrderDataResponse;
import com.taxiapp.desktop.services.OrderService;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class UserInfoController  {

    public VBox ordersBox;
    private String userId;
    @FXML
    public Label userIdText;
    public Label emailText;
    public Label userNameText;


    private void loadUsersOrders(){
        List<OrderDataResponse> orders = OrderService.getOrders(FilterOrdersRequest.builder().userId(userId).build(),0,100);
        for(OrderDataResponse o:orders){
            TextField from = new TextField(o.getPickupLocation());
            TextField to  = new TextField(o.getDeliveryLocation());
            TextField separator = new TextField(" >>> ");
            separator.setAlignment(Pos.CENTER);

            from.setEditable(false);
            to.setEditable(false);
            separator.setEditable(false);

            HBox hBox = new HBox(
                from,separator, to
            );

            from.setPrefWidth(230);
            to.setPrefWidth(230);

            separator.setStyle("-fx-background-color: lightblue");
            from.setStyle("-fx-background-color: lightgray");
            to.setStyle("-fx-background-color: lightgray");

            HBox.setHgrow(separator,Priority.ALWAYS);
            HBox.setHgrow(hBox, Priority.ALWAYS);

            ordersBox.getChildren().add(hBox);


        }
    }

    public void setUserId(String userId) {
        this.userId = userId;
        loadUsersOrders();
    }

}
