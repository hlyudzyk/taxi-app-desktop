package com.taxiapp.desktop.menupages.dashboard;

import static com.taxiapp.desktop.TableHelper.createColumn;
import static com.taxiapp.desktop.entities.Role.USER;
import static javafx.scene.layout.Priority.ALWAYS;

import com.taxiapp.desktop.menupages.dashboard.infopages.UserInfoController;
import com.taxiapp.desktop.net.requests.FilterUsersRequest;
import com.taxiapp.desktop.net.responses.UserDataResponse;
import com.taxiapp.desktop.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UsersPageController implements Initializable {

    public VBox contentBox;
    public VBox filterBox;
    public HBox rootBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsers();
    }


    private void displayUsers(){
        contentBox.getChildren().clear();

        try{
            var usersFilter = new FilterUsersRequest();
            usersFilter.setRole(USER);

            List<UserDataResponse> usersList = UserService.getUsers(usersFilter,0,0);

            var userIdColumn = createColumn("ID", UserDataResponse::getUserId);
            var userNameColumn = createColumn("Name", UserDataResponse::getFirstname);
            var userEmailColum = createColumn("Email", UserDataResponse::getEmail);

            var table = new TableView<UserDataResponse>(FXCollections.observableList(usersList));

            table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    UserDataResponse selectedObject = table.getSelectionModel().getSelectedItem();
                    displayUserInfo(selectedObject);
                }
            });

            table.getColumns().setAll(userIdColumn,userNameColumn,userEmailColum);
            VBox.setVgrow(table, ALWAYS);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            contentBox.getChildren().add(table);


        }
        catch (NullPointerException ex){
            contentBox.getChildren().add(new Label("Error"));
        }
    }

    private void displayUserInfo(UserDataResponse userDataResponse) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("infopages/user-info.fxml"));
            Node n = loader.load();
            UserInfoController controller = loader.getController();

            controller.userIdText.setText(userDataResponse.getUserId());
            controller.setUserId(userDataResponse.getUserId());
            controller.emailText.setText(userDataResponse.getEmail());
            controller.userNameText.setText(userDataResponse.getFirstname() + " " + userDataResponse.getLastname());
            HBox.setHgrow(n,ALWAYS);
            rootBox.getChildren().clear();
            rootBox.getChildren().add(n);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
