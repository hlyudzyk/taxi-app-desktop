package com.taxiapp.desktop.menupages;

import static com.taxiapp.desktop.entities.Role.ADMIN;

import atlantafx.base.theme.Styles;
import com.taxiapp.desktop.net.requests.FilterUsersRequest;
import com.taxiapp.desktop.net.requests.RegisterRequest;
import com.taxiapp.desktop.net.responses.UserDataResponse;
import com.taxiapp.desktop.services.AdminService;
import com.taxiapp.desktop.services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TeamPageController implements Initializable  {

    public TextField adminFirstName;
    public TextField adminLastName;
    public TextField adminEmail;
    public PasswordField adminPassword;
    public Button registerButton;
    public HBox adminsBox;
    public DatePicker birthdayPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.getStyleClass().add(Styles.SUCCESS);
        loadAdmins();
    }

    private void loadAdmins() {
        var usersFilter = FilterUsersRequest.builder().role(ADMIN).build();
        List<UserDataResponse> admins = UserService.getUsers(usersFilter,0,0);
        for (UserDataResponse admin : admins) {
            VBox vBox = new VBox();
            TextField adminFirstNameText = new TextField(admin.getFirstname());
            TextField adminLastNameText = new TextField(admin.getLastname());
            TextField adminEmailText = new TextField(admin.getEmail());
            TextField idText = new TextField(admin.getUserId());
            vBox.getChildren().addAll(idText, adminFirstNameText, adminLastNameText, adminEmailText);
            adminsBox.getChildren().add(vBox);
        }
    }

    public void registerButtonClicked(MouseEvent mouseEvent) {
        RegisterRequest registerRequest = RegisterRequest.builder()
            .email(adminEmail.getText())
            .role(ADMIN)
            .lastname(adminLastName.getText())
            .firstname(adminFirstName.getText())
            .birthday(birthdayPicker.getValue())
            .password(adminPassword.getText())
            .build();

        AdminService.registerUser(registerRequest);

    }
}
