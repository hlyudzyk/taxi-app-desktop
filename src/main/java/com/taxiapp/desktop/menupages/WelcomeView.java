package com.taxiapp.desktop.menupages;

import static javafx.scene.input.KeyCombination.CONTROL_DOWN;

import atlantafx.base.theme.Styles;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;

public class WelcomeView implements Initializable {

    public AnchorPane anchorPane;


    private Label contextMenuExample () {
        var contextMenu = new ContextMenu();
        var undoItem = createItem("_Undo",
            new KeyCodeCombination(KeyCode.Z, CONTROL_DOWN));

        undoItem.setMnemonicParsing(true);

        var redoItem = createItem("_Redo",
            new KeyCodeCombination (KeyCode.Y, CONTROL_DOWN));
        redoItem.setMnemonicParsing(true);

        contextMenu.getItems().addAll(
            undoItem,
            redoItem,
            new SeparatorMenuItem(),
            createItem("Cut",  new KeyCodeCombination (KeyCode.X, CONTROL_DOWN)),
            createItem("Copy",  new KeyCodeCombination (KeyCode.C, CONTROL_DOWN)),
            createItem("Paste",  new KeyCodeCombination (KeyCode.V, CONTROL_DOWN)));
        var clickArea = new Label ("Right-Click Here");
        clickArea.setAlignment (Pos.CENTER);
        clickArea.setMinSize ( 100 / 2d, 200);
        clickArea.setMaxSize (100 / 2d, 200);
        clickArea.setContextMenu (contextMenu);
        clickArea.getStyleClass().add(Styles. BORDERED);
        return clickArea;
    }
    private MenuItem createItem(String text, KeyCombination accelerator) {
        var item = new MenuItem(text);

        if (accelerator != null) {
            item.setAccelerator(accelerator);
        }
        return item;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.getChildren().add(contextMenuExample());
    }
}
