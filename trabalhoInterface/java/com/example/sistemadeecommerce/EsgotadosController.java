package com.example.sistemadeecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;


public class EsgotadosController {
    @FXML
    private TableView tableViewEsgotados;

    private Stage stage;
    @FXML
    private TableColumn nomeCol;

    private Scene scene;

    @FXML
    public Button voltarButton;

    File dirProdutos = new File("./src/main/resources/produtos");
    File arquivoCategoria;
    ObservableList<Produto> listaTabela = FXCollections.observableArrayList();

    @FXML
    public void initialize() {


        nomeCol.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        gerenciarTabela();


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

    public void gerenciarTabela() {
        for ( int i = 0; i < dirProdutos.list().length; i++) {

            arquivoCategoria = new File(dirProdutos, dirProdutos.list()[i]);

            try {
                FileReader fr = new FileReader(arquivoCategoria);
                BufferedReader br = new BufferedReader(fr);
                String linha;

                while((linha = br.readLine()) != null){
                    String[] arrayLinha = linha.split("\t");
                    Produto p = new Produto(arrayLinha[0], arrayLinha[1], arrayLinha[2], Double.parseDouble(arrayLinha[3]), Integer.parseInt(arrayLinha[4]), Integer.parseInt(arrayLinha[5]), arrayLinha[7]);
                    p.calcularQtdPontos();
                    if(p.getQuantidadeEmEstoque() == 0) {
                        listaTabela.add(p);
                    }
                }


                br.close();
                fr.close();
            } catch (FileNotFoundException fileNotFoundException){
                System.out.println(fileNotFoundException);
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
        }

        if (listaTabela.size() != 0) {
            tableViewEsgotados.setItems(listaTabela);
            tableViewEsgotados.getColumns().addAll();
        }

    }
}
