package com.taxiapp.desktop;


import atlantafx.base.theme.Styles;
import com.taxiapp.desktop.net.controllers.AuthenticationRequestController;
import com.taxiapp.desktop.security.AccessTokenManager;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoggedInController implements Initializable {

    Parent root;
    Scene scene;
    Stage stage;
    @FXML
    public Button loginButton;
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    public Button signUpButton;


    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        String access_token =  AuthenticationRequestController.authenticate(username.getText(),password.getText());

        if(access_token==null) {
            Alert error = new Alert(AlertType.ERROR);
            error.setHeaderText("Login failed! Try again!");
            error.show();
            return;
        }

        AccessTokenManager.getInstance().setAccessToken(access_token);
        switchToMainScene(event);
    }

    private void switchToMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.getStyleClass().add(Styles.ACCENT);
        signUpButton.getStyleClass().add(Styles.ACCENT);

    }
}
