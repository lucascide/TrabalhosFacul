package com.example.sistemadeecommerce;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuLogInController implements Initializable {

    private Cliente cliente;

    @FXML
    private Label nomeCliente;

    @FXML
    private Button botao, relatorioButton, rankingButton, esgotadosButton;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cliente = ContaController.getClient();

        String nom[] = cliente.getNome().split(" ");

        nomeCliente.setText(nom[0]);

        if (cliente.isAdmin()) {
            rankingButton.setVisible(true);
            esgotadosButton.setVisible(true);
        } else {
            relatorioButton.setVisible(true);
        }

        relatorioButton.setOnMouseClicked(mouseEvent -> {
            mostrarRelatorio(mouseEvent);
        });

        esgotadosButton.setOnMouseClicked(mouseEvent -> {
            mostrarEsgotados(mouseEvent);
        });

    }

    @FXML
    public void selecionarCategoria(ActionEvent event) throws IOException {

        botao = (Button)event.getSource();

        DataSingleton data = DataSingleton.getInstance();
        data.setUserName(botao.getText());

        stage = (Stage)botao.getScene().getWindow();

        if(cliente.isAdmin()){

            rankingButton.setVisible(false);
            esgotadosButton.setVisible(false);

            root = FXMLLoader.load(getClass().getResource( "produtosViewAdmin.fxml"));

        }
        else{

            relatorioButton.setVisible(false);
            root = FXMLLoader.load(getClass().getResource( "produtosViewCliente.fxml"));

        }

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(botao.getText());
        stage.show();

    }

    public void mostrarRelatorio(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("historicoDeCompra.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public void mostrarEsgotados ( MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("esgotados.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

}
