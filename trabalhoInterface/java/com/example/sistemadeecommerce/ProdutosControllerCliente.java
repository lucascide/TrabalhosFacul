package com.example.sistemadeecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProdutosControllerCliente {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private AnchorPane visualizacaoProdutosPane;
    @FXML
    private Button voltarMenuButton;
    @FXML
    private Label labelCategoria;
    private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
    private int posicaoProdutoX = 0;
    private int posicaoProdutoY = 0;

    private DataSingleton data = DataSingleton.getInstance();
    private File arquivoProdutos = new File("./src/main/resources/produtos/" + data.getUserName() + ".txt" );

    @FXML
    public void initialize() {


        labelCategoria.setText(data.getUserName());


        try {
            FileReader fr = new FileReader(arquivoProdutos);
            BufferedReader br = new BufferedReader(fr);
            String linha;

            while((linha = br.readLine()) != null){
                String[] arrayLinha = linha.split("\t");
                Produto p = new Produto(arrayLinha[0], arrayLinha[1], arrayLinha[2] , Double.parseDouble(arrayLinha[3]), Integer.parseInt(arrayLinha[4]), Integer.parseInt(arrayLinha[5]), arrayLinha[7]);
                p.calcularQtdPontos();
                listaProdutos.add(p);
            }


            br.close();
            fr.close();
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }



        gerarLabelProdutos();

        voltarMenuButton.setOnMouseClicked(mouseEvent -> {
            voltarProMenu(mouseEvent);
        });

    }

    public void gerarLabelProdutos() {
        try {
            FileWriter fw = new FileWriter(arquivoProdutos, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < listaProdutos.size(); i++) {

                if (i == 0) {
                    bw.write(listaProdutos.get(i).getNome() + "\t" + listaProdutos.get(i).getCategoria() + "\t" + listaProdutos.get(i).getSubCategoria() + "\t" + listaProdutos.get(i).getPreço() + "\t" + listaProdutos.get(i).getDesconto() + "\t" + listaProdutos.get(i).getQuantidadeEmEstoque() + "\t" + listaProdutos.get(i).getQtdPontos() + "\t" + listaProdutos.get(i).getImg());
                } else {
                    bw.write("\n" + listaProdutos.get(i).getNome() + "\t" + listaProdutos.get(i).getCategoria() + "\t" + listaProdutos.get(i).getSubCategoria() + "\t" + listaProdutos.get(i).getPreço() + "\t" + listaProdutos.get(i).getDesconto() + "\t" + listaProdutos.get(i).getQuantidadeEmEstoque() + "\t" + listaProdutos.get(i).getQtdPontos() + "\t" + listaProdutos.get(i).getImg());
                }

                bw.flush();

            }
            bw.close();
            fw.close();
        } catch (IOException err) {
            System.out.println(err);
        }


        posicaoProdutoX = 0;
        posicaoProdutoY = 0;
        visualizacaoProdutosPane.getChildren().setAll();


        for (int i = 0; i < listaProdutos.size(); i++) {
            if(listaProdutos.get(i).getQuantidadeEmEstoque() > 0) {
                AnchorPane produtoPane = new AnchorPane();
                produtoPane.setCursor(Cursor.HAND);

                Image produtoImagem = new Image("file:" + listaProdutos.get(i).getImg());
                ImageView produtoImagemView = new ImageView();
                Label produtoLabel = new Label();
                Label precoProdutoLabel = new Label();

                produtoImagemView.setImage(produtoImagem);
                produtoLabel.setText(listaProdutos.get(i).getNome());

                double precoAMostar = listaProdutos.get(i).getPreço() - (listaProdutos.get(i).getPreço() * listaProdutos.get(i).getDesconto()/100);

                DecimalFormat df = new DecimalFormat("#.00");
                String preco = df.format(precoAMostar);
                preco = preco.replaceAll("\\.", ",");
                precoProdutoLabel.setText("R$ " + preco);

                produtoImagemView.setFitHeight(200);
                produtoImagemView.setFitWidth(200);

                produtoLabel.setLayoutY(213);
                produtoLabel.setStyle("-fx-font: 20 arial;");

                produtoPane.maxWidth(Double.MAX_VALUE);
                AnchorPane.setLeftAnchor(produtoLabel, 0.0);
                AnchorPane.setRightAnchor(produtoLabel, 0.0);
                produtoLabel.setAlignment(Pos.CENTER);

                precoProdutoLabel.setLayoutY(238);
                precoProdutoLabel.setLayoutX(6);
                precoProdutoLabel.setStyle("-fx-font: 28 arial;" + "-fx-font-weight: bold");

                produtoPane.setLayoutY(posicaoProdutoY * 303);
                produtoPane.setLayoutX(posicaoProdutoX * 278);
                produtoPane.setPrefWidth(210);
                produtoPane.setPrefHeight(273);

                produtoPane.getChildren().add(produtoImagemView);
                produtoPane.getChildren().add(produtoLabel);
                produtoPane.getChildren().add(precoProdutoLabel);

                posicaoProdutoX++;
                if (posicaoProdutoX == 4) {
                    posicaoProdutoX = 0;
                    posicaoProdutoY++;
                }


                Produto produto = listaProdutos.get(i);

                precoProdutoLabel.setText("R$ " + preco);


                produtoPane.setOnMouseClicked(mouseEvent -> {
                    try {
                        abrirVisualizacaoDoProduto(mouseEvent, produto);
                    } catch (IOException ioException) {
                        System.out.println(ioException);
                    }

                });

                visualizacaoProdutosPane.getChildren().add(produtoPane);
            }

        }
    }

    public void abrirVisualizacaoDoProduto(MouseEvent event, Produto produto) throws IOException{
        data.setProduto(produto);
        data.setArrayListProdutos(listaProdutos);


        Parent root = FXMLLoader.load(getClass().getResource("produto.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void voltarProMenu(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu2.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

    }


}
