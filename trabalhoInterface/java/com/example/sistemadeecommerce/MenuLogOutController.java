package com.example.sistemadeecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuLogOutController implements Initializable{

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void login(ActionEvent e) throws IOException{

        stage = (Stage)((Button)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("telaLogin");
        stage.show();

    }

    @FXML
    public void criarConta(ActionEvent e) throws IOException{

        stage = (Stage)((Button)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TelaCriarConta.fxml"));
        scene = new Scene(root);
        stage.setTitle("telaCriarConta");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void sair(ActionEvent e){

        stage = (Stage)((Button)e.getSource()).getScene().getWindow();
        stage.close();

    }

}

