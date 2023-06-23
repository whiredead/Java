package com.example.controle.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class S2ControllerBE {

    @FXML
    ListView listusers;

    Socket sock;

    @FXML
    ListView listchat;

    @FXML
    ListView listchatprivee;

    @FXML
    TextField message;

    @FXML
    TextField to;

    public void démarrer(Socket s) throws IOException {
        sock =s;
        InputStream is = sock.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bfr = new BufferedReader(isr);
        new Thread(()->{
            while(true) {
                try {
                    String response = bfr.readLine();
                    if (response != null) {
                        String[] res = response.split(":");
                        String first = res[0];
                        try{
                            String second = res[1];
                            if (first.equals("users")) {
                                Platform.runLater(() -> {
                                    listusers.getItems().clear(); // Clear the existing user list
                                    System.out.println("second######### " + second);
                                    String[] donnees = second.split("/");
                                    for (String donnee : donnees) {
                                        System.out.println("donnee#########" + donnee);
                                        listusers.getItems().add(donnee); // Add each user to the list
                                    }
                                });
                            }
                            /*if (first.equals("users")) {
                                System.out.println("second######### "+second);
                                String [] donnees = second.split("/");
                                for(String donnee : donnees){
                                    Platform.runLater(() -> {
                                        System.out.println("donnee#########"+donnee);
                                        listusers.getItems().add(donnee);
                                    });
                                }
                            }*/
                            else if (first.equals("chatprivee")) {
                                Platform.runLater(() -> {
                                    System.out.println("privee#########" + second);
                                    listchatprivee.getItems().add(second);
                                });
                            }
                            else if (first.equals("chat")) {
                                Platform.runLater(() -> {
                                    System.out.println("chat#########" + second);
                                    listchat.getItems().add(second);
                                });
                            }
                        }catch(Exception e){e.printStackTrace();}
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @FXML
    public void SendMsg() throws IOException {
        String msg;
        if (message.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("ecrire d'abord un message !!");
            alert.show();
        }
        else{
            OutputStream os = sock.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            if (!to.getText().isEmpty()) {
                pw.println(to.getText() + "=>" + message.getText());
                to.clear();
            }
            else {
                pw.println(message.getText());
            }
            message.clear();
        }
    }

}
