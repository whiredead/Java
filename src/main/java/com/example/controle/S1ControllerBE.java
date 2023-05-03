package com.example.controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class S1ControllerBE {
    @FXML
    private TextField usr;
    @FXML
    private PasswordField pwd;

    @FXML
    protected void authentifier() throws IOException {
        if(usr.getText().equals("admin")) {
            if(pwd.getText().equals("admin")) {
                Stage s = (Stage) usr.getScene().getWindow();
                FXMLLoader FX1 = new FXMLLoader(MainApp.class.getResource("Scene2BE.fxml"));
                Scene scene = new Scene(FX1.load());
                s.setScene(scene);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("error d'authentification ");
                alert.setContentText("mot de passe incorrect");
                alert.show();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("error d'authentification ");
            alert.setContentText("username incorrect");
            alert.show();
        }
    }
}