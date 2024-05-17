package com.taxiapp.desktop.menupages.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class DashboardPageController  implements Initializable {
    @FXML
    public AnchorPane dashboardMainPane;
    public Tab driversTab;
    public Tab usersTab;
    public Tab ordersTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        driversTab.setOnSelectionChanged(this::allDriversButtonClick);
        usersTab.setOnSelectionChanged(this::allUsersButtonClicked);
        ordersTab.setOnSelectionChanged(this::allOrdersButtonClick);
        setDashboardMainPaneContent("orders-page.fxml");
    }

    @FXML
    protected void allOrdersButtonClick(Event event) {
        setDashboardMainPaneContent("orders-page.fxml");
    }

    @FXML
    public void allDriversButtonClick(Event event) {
        setDashboardMainPaneContent("drivers-page.fxml");
    }

    @FXML
    void allUsersButtonClicked(Event event) {
        setDashboardMainPaneContent("users-page.fxml");
    }

    private void setDashboardMainPaneContent(String itemSource){
        dashboardMainPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(itemSource));
        try {
            dashboardMainPane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            dashboardMainPane.getChildren().add(new Label("Error has occurred. Please, try again later!"));
        }
    }



}
