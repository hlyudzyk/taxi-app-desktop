package com.taxiapp.desktop.menupages;

import atlantafx.base.theme.Styles;
import com.taxiapp.desktop.entities.PricingPolicy;
import com.taxiapp.desktop.net.responses.PricingPolicyResponse;
import com.taxiapp.desktop.services.PricingService;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PricingPageController implements Initializable {


    public VBox pricingBox;
    public Spinner fareSpinner;
    public Spinner basicPriceSpinner;
    public Spinner premiumPriceSpinner;
    public Spinner cargoPriceSpinner;
    public Spinner companyChargeSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PricingPolicyResponse pricingPolicyResponse = PricingService.getPricingPolicy();

        var saveChangesButton = new Button("_Save changes");
        saveChangesButton.getStyleClass().add(Styles.ACCENT);
        saveChangesButton.setMnemonicParsing(true);
        saveChangesButton.setOnMouseClicked(this::saveChangesButtonClicked);

        fareSpinner.getValueFactory().setValue(pricingPolicyResponse.getTariff());
        basicPriceSpinner.getValueFactory().setValue(pricingPolicyResponse.getBasicTaxiTypeMultiplier());
        premiumPriceSpinner.getValueFactory().setValue(pricingPolicyResponse.getPremiumTaxiTypeMultiplier());
        cargoPriceSpinner.getValueFactory().setValue(pricingPolicyResponse.getCargoTaxiTypeMultiplier());

        pricingBox.getChildren().add(saveChangesButton);
    }

    private void saveChangesButtonClicked(MouseEvent mouseEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to save changes?");

        ButtonType confirmButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            PricingPolicy newPricingPolicy = PricingPolicy.builder()
                .tariff((Double) fareSpinner.getValue())
                .basicTaxiTypeMultiplier((Double) basicPriceSpinner.getValue())
                .premiumTaxiTypeMultiplier((Double) premiumPriceSpinner.getValue())
                .cargoTaxiTypeMultiplier((Double) cargoPriceSpinner.getValue())
                .build();
            PricingService.updatePricingPolicy(newPricingPolicy);

        } else {
            System.out.println("Changes not saved.");
            return;
        }
    }
}
