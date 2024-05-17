package com.taxiapp.desktop.menupages;

import com.taxiapp.desktop.entities.Role;
import com.taxiapp.desktop.net.requests.NewMessageRequest;
import com.taxiapp.desktop.net.responses.MessageDataResponse;
import com.taxiapp.desktop.services.MessageService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessagesPageController implements Initializable {

    public Button sendMessageButton;
    public TextField messageSubject;
    public TextArea messageContent;
    public CheckBox adminsCheckBox;
    public CheckBox usersCheckBox;
    public CheckBox driversCheckBox;
    public VBox messagesBox;




    public void sendMessage(MouseEvent mouseEvent) {
        boolean adminsFlag = adminsCheckBox.isSelected();
        boolean usersFlag = usersCheckBox.isSelected();
        boolean driversFlag = driversCheckBox.isSelected();

        List<Role> targetRoles = new ArrayList<Role>();
        if(adminsFlag) targetRoles.add(Role.ADMIN);
        if(usersFlag) targetRoles.add(Role.USER);
        if(driversFlag) targetRoles.add(Role.DRIVER);

        NewMessageRequest newMessageRequest = NewMessageRequest.builder()
            .subject(messageSubject.getText())
            .content(messageContent.getText())
            .targetRoles(targetRoles)
            .build();

        addMessageToMessageBox(MessageService.sendMessage(newMessageRequest));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<MessageDataResponse> messages = MessageService.getAllMessages();

        for(MessageDataResponse messageDataResponse : messages) {
            addMessageToMessageBox(messageDataResponse);
        }

    }

    private void addMessageToMessageBox(MessageDataResponse messageDataResponse) {
        HBox hBox = new HBox();
        TextField sender = new TextField(messageDataResponse.getSenderEmail());
        TextField subject = new TextField(messageDataResponse.getSubject());
        sender.setEditable(false);
        subject.setEditable(false);
        hBox.getChildren().addAll(sender,subject);
        messagesBox.getChildren().add(hBox);
    }
}
