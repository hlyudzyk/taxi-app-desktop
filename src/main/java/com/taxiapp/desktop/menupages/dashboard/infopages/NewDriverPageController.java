package com.taxiapp.desktop.menupages.dashboard.infopages;

import static com.taxiapp.desktop.entities.Role.DRIVER;

import com.taxiapp.desktop.net.requests.RegisterRequest;
import com.taxiapp.desktop.services.AdminService;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class NewDriverPageController {

    public VBox inputBox;
    public TextField driverFirstName;
    public TextField driverLastName;
    public TextField driverEmail;
    public DatePicker birthdayPicker;
    public PasswordField driverPassword;
    public Button registerButton;

    public void registerButtonClicked(MouseEvent mouseEvent) {
        RegisterRequest registerRequest = RegisterRequest.builder()
            .email(driverEmail.getText())
            .role(DRIVER)
            .lastname(driverLastName.getText())
            .firstname(driverFirstName.getText())
            .birthday(birthdayPicker.getValue())
            .password(driverPassword.getText())
            .build();

        AdminService.registerUser(registerRequest);
    }
}
