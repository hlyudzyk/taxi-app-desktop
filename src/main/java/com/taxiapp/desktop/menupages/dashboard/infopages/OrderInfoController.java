package com.taxiapp.desktop.menupages.dashboard.infopages;

import atlantafx.base.controls.ModalPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class OrderInfoController {
    @FXML
    public Label orderTitle;
    @FXML
    public Label orderIdText;
    @FXML
    public Label userNameText;
    @FXML
    public Label priceText;
    @FXML
    public Label locationFromText;
    @FXML
    public Label locationToText;
    public Label taxiTypeLabel;
    public Label orderedAtText;
    public Label finishedAtText;

    public void openUserInfoModalPane(MouseEvent event) {
        ModalPane modalPane = new ModalPane();
        var dialog = new DialogPane();

            modalPane.setPersistent(true);
            modalPane.show(dialog);

        var closeBtn = new Button("Close");
        closeBtn.setOnAction(evt -> {
            modalPane.hide(true);
            modalPane.setPersistent(false);
        });
        dialog.getChildren().setAll(closeBtn);

    }
}
