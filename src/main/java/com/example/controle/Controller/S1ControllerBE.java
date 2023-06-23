package com.example.controle.Controller;

import com.example.controle.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

public class S1ControllerBE {

    @FXML
    private TextField host;

    @FXML
    private TextField port;

    @FXML
    private TextField username;

    @FXML
    public void authentifier() throws IOException {
        String hostname = host.getText();
        int numport = Integer.parseInt(port.getText());
        String name = username.getText();

        // Connection to server
        Socket s = new Socket(hostname, numport);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bfr = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        // Send name of the client to the server
        pw.println(name);

        // Load the second scene using FXMLLoader
        FXMLLoader FX1 = new FXMLLoader(MainApp.class.getResource("Scene2BE.fxml"));
        Scene scene = new Scene(FX1.load());

        // Get the controller of Scene2.fxml
        S2ControllerBE scene2Controller = FX1.getController();

        // Add the message to the ListView
        scene2Controller.démarrer(s);

        // Get the stage and set the second scene
        Stage stage = (Stage) host.getScene().getWindow();
        stage.setScene(scene);
    }
}
