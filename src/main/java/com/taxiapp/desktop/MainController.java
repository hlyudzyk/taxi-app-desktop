package com.taxiapp.desktop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {
    @FXML
    public Button dashboardButton;
    @FXML
    public Button statisticsButton;
    @FXML
    public Button pricingButton;
    public Pane mainPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayViewInMainPane("menupages/dashboard/dashboard-page.fxml");
    }

    @FXML
    public void onDashboardButtonClicked(MouseEvent event) {
        displayViewInMainPane("menupages/dashboard/dashboard-page.fxml");
    }

    private void displayViewInMainPane(String itemSource) {
        mainPane.getChildren().clear();

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(itemSource));
            Node n = loader.load();
            mainPane.getChildren().add(n);

        } catch (IOException ex) {
            mainPane.getChildren().add(new Label("Error has occurred. Please, try again."));
            ex.printStackTrace();
        }
    }

    public void onStatisticsButtonClicked(MouseEvent event) {
        displayViewInMainPane("menupages/statistics-page.fxml");
    }

    public void onPricingButtonClicked(MouseEvent mouseEvent) {
        displayViewInMainPane("menupages/pricing-page.fxml");
    }

    public void onMessagesButtonClicked(MouseEvent mouseEvent) {
        displayViewInMainPane("menupages/messages-page.fxml");
    }

    public void onCarParkButtonClicked(MouseEvent mouseEvent) {
        displayViewInMainPane("menupages/carpark-page.fxml");
    }

    public void onMyteamButtonClicked(MouseEvent mouseEvent) {
        displayViewInMainPane("menupages/myteam-page.fxml");
    }
}
