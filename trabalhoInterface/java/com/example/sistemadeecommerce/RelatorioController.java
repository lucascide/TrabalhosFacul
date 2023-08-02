package com.example.sistemadeecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;

public class RelatorioController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    public AnchorPane relatoriosPane;
    @FXML
    public Button voltarButton;

    @FXML
    private Cliente cliente = ContaController.getClient();

    @FXML
    public void initialize() {
        File relatorioArquivo = new File("./src/main/resources/comprasClientes/" + cliente.getNome() + ".txt");
        try {
            FileReader fr = new FileReader(relatorioArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;

            int linhaNaCena = 0;
            boolean emNegrito = false;
            boolean linhaResultado = false;

            DecimalFormat df = new DecimalFormat("#.00");

            while( (linha = br.readLine()) != null) {
                if(linha.equals("Compra")) {
                    emNegrito = true;
                } else if (linha.equals("Resultado")){
                    linhaResultado = true;
                } else {
                    if(emNegrito) {

                        linhaNaCena++;
                        Label labelData = new Label();
                        labelData.setStyle("-fx-font: 20 arial;");

                        labelData.setText(linha);
                        labelData.setLayoutY(linhaNaCena * 30);
                        relatoriosPane.getChildren().add(labelData);

                        emNegrito = false;
                    } else if (linhaResultado) {
                         linhaNaCena ++;
                         Label labelResultado = new Label();

                         String[] arrayLinha = linha.split("\t");
                         labelResultado.setLayoutY(linhaNaCena * 30);

                        String preco = df.format(Double.parseDouble(arrayLinha[1]));
                        preco = preco.replaceAll("\\.", ",");

                         labelResultado.setText("Pontos usados: " + arrayLinha[0] + "    Valor final: " + preco + "    Pontos adquiridos: " + arrayLinha[2]);
                         relatoriosPane.getChildren().add(labelResultado);
                         linhaResultado = false;
                    } else {
                        linhaNaCena++;


                        String[] arrayLinha = linha.split("\t");

                        Label labelProduto = new Label();
                        Label labelUnidades = new Label();
                        Label labelValor = new Label();
                        Label labelPontosGanhos = new Label();


                        labelProduto.setText(arrayLinha[0]);
                        labelProduto.setLayoutY(linhaNaCena * 30);
                        labelProduto.setMaxWidth(260);
                        relatoriosPane.getChildren().add(labelProduto);

                        labelUnidades.setText(arrayLinha[1]);
                        labelUnidades.setLayoutY(linhaNaCena * 30);
                        labelUnidades.setLayoutX(270);
                        labelUnidades.setMaxWidth(100);
                        relatoriosPane.getChildren().add(labelUnidades);


                        String preco = arrayLinha[2];

                        if(!arrayLinha[2].equals("Valor")) {
                            preco = df.format(Double.parseDouble(arrayLinha[2]));
                            preco = preco.replaceAll("\\.", ",");
                        }


                        labelValor.setText(preco);
                        labelValor.setLayoutY(linhaNaCena * 30);
                        labelValor.setLayoutX(390);
                        labelValor.setMaxWidth(100);
                        relatoriosPane.getChildren().add(labelValor);

                        labelPontosGanhos.setText(arrayLinha[3]);
                        labelPontosGanhos.setLayoutY(linhaNaCena * 30);
                        labelPontosGanhos.setLayoutX(510);
                        labelPontosGanhos.setMaxWidth(100);
                        relatoriosPane.getChildren().add(labelPontosGanhos);
                    }




                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        voltarButton.setOnMouseClicked(mouseEvent -> {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("Menu2.fxml"));
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ioException) {
                System.out.println(ioException);
            }

        });

    }

}
