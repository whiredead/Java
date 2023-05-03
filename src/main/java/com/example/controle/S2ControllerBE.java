package com.example.controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class S2ControllerBE {
    @FXML
    private TextField id;
    @FXML
    private TextField age;

    @FXML
    private TextField name;

    @FXML
    private TextField hours;
    @FXML
    private ComboBox type;

    @FXML
    private ListView<String> listview;

    private Serializer serializer = new Serializer("Managers.txt");

    @FXML
    protected void logout() throws IOException {
        Stage s = (Stage) age.getScene().getWindow();
        FXMLLoader FX1=new FXMLLoader(MainApp.class.getResource("Scene1BE.fxml"));
        Scene scene = new Scene(FX1.load());
        s.setScene(scene);
    }

    @FXML
    protected void onsubmit() throws Exception {
        if(isConfirm()) {
            Manager m;
            System.out.println("#############age###"+age.getText());
            System.out.println("#############hours###"+hours.getText());
            if (type.getValue().equals("Junior")) {
                m = new Junior(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(hours.getText()));
            } else {
                m = new Senior(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(hours.getText()));
            }
            serializer.write(m);
            listview.getItems().add(m.toString());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("veuillez remplir tous les champs");
            alert.show();
        }
    }
    @FXML
    protected void recap() throws Exception {
        Set<Manager> managers =serializer.Read();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Base de Donnee");
        alert.setHeaderText("Managers");
        String s =" ";
        for (Manager m:managers) {
            s += m.toString();
        }
        alert.setContentText(s);
        alert.show();
    }
    @FXML
    protected void calculercout() throws Exception {
        if(isConfirm()) {
            Manager m;
            if (type.getValue().equals("Junior")) {
                m = new Junior(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(hours.getText()));
            } else {
                m = new Senior(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(hours.getText()));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("cout du manager " + m.name);
            alert.setHeaderText("calcul du cout");
            alert.setContentText(String.valueOf(m.cout()));
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("veuillez remplir tous les champs");
            alert.show();
        }
    }
    protected boolean isConfirm(){
        if(id.getText().isEmpty() || age.getText().isEmpty() || name.getText().isEmpty() || hours.getText().isEmpty() || type.getValue()==null)
            return false;
        else return true;
    }

}
