package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagementLoginFormController {
    public JFXButton btnCancel;
    public JFXButton btnLogIn;
    public Stage thisStage;
    public AnchorPane loginContext;
    public AnchorPane dashContext;
    public Label lblUserNameError;
    public Label lblPasswordError;
    public Label lblPasswordError1;
    public TextField txtUserName;
    public PasswordField txtPassword;


    public void setStageClose(Stage stage, AnchorPane dashBoardContext){
        thisStage=stage;
        dashContext=dashBoardContext;
    }

    private void close(){
        thisStage.close();
    }

    public void openManagerForm(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("admin")) {
            if(txtPassword.getText().equals("1234")){
                URL resource = getClass().getResource("../view/ManagerForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) dashContext.getScene().getWindow();
                window.setScene(new Scene(load));
                close();
            }else{ lblPasswordError.setVisible(true); }
        }else{ lblUserNameError.setVisible(true); }
    }

    public void openDashboard(ActionEvent actionEvent) {
        close();
    }

    public void hidePasswordError(MouseEvent mouseEvent) {
        lblPasswordError.setVisible(false);
    }

    public void hideUsernameError(MouseEvent mouseEvent) {
        lblUserNameError.setVisible(false);
    }

    public void openChangePassword(MouseEvent mouseEvent) {
        new Alert(Alert.AlertType.NONE, "user name : admin\npassword : 1234", ButtonType.CLOSE).show();

    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
