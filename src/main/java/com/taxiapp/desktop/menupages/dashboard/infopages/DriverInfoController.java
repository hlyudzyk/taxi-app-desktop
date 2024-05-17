package com.taxiapp.desktop.menupages.dashboard.infopages;

import atlantafx.base.theme.Styles;
import com.taxiapp.desktop.net.requests.FilterOrdersRequest;
import com.taxiapp.desktop.net.responses.OrderDataResponse;
import com.taxiapp.desktop.services.OrderService;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DriverInfoController {

    public Label driverNameText;
    public Label emailText;
    public Label driverIdText;
    public VBox ordersBox;
    public VBox statisticsBox;
    private String driverId;
    

    private void loadDriverData(){
        //TODO add limit
        List<OrderDataResponse> orders = OrderService.getOrders(
            FilterOrdersRequest.builder().driverId(driverId).build(),0,100);
        for(OrderDataResponse o:orders){
            ordersBox.getChildren().add(new Label(o.getId()));
        }

        var accentBtn = new Button("_Discover statistics");
        accentBtn.getStyleClass().add(Styles.ACCENT);
        accentBtn.setMnemonicParsing(true);

        ordersBox.getChildren().add(new Label("Click to see more statistics"));
        ordersBox.getChildren().add(accentBtn);



    }

    public void setDriverId(String userId) {
        this.driverId = userId;
        loadDriverData();
    }
}
