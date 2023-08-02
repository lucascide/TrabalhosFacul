package com.example.sistemadeecommerce;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProdutoController {

    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Button voltarButton;
    @FXML
    private Label labelTituloProduto;
    @FXML
    private Button adicionarCarrinhoButton;
    @FXML
    private Label produtoPreco;
    @FXML
    private Label produtoPrecoComDesconto;
    @FXML
    private ImageView imageViewProduto;
    @FXML
    private Label produtoQuantidadeEmEstoque;
    @FXML
    private AnchorPane paneProdutosSimilares;


    private DataSingleton data = DataSingleton.getInstance();
    private int posicaoProdutoX = 0;

    private ArrayList<Produto> produtosCategoria = data.getArrayListProdutos();

    @FXML
    public void initialize() throws IOException{

        ArrayList<Produto> produtosSimilares = new ArrayList<Produto>();
        Produto produto = data.getProduto();

        labelTituloProduto.setText(produto.getNome());

        DecimalFormat df = new DecimalFormat("#.00");

        String preco = df.format(produto.getPreço());
        preco = preco.replaceAll("\\.", ",");
        produtoPreco.setText("R$ " + preco );

        String precoDescontado = df.format(produto.getPreço() - (produto.getPreço() * produto.getDesconto() / 100));
        precoDescontado = precoDescontado.replaceAll("\\.", ",");
        produtoPrecoComDesconto.setText("Com desconto: " + precoDescontado);

        produtoQuantidadeEmEstoque.setText("Estoque: " + Integer.toString(produto.getQuantidadeEmEstoque()));
        Image produtoImagem = new Image("file:" + produto.getImg());
        imageViewProduto.setImage(produtoImagem);

        for (int i = 0; i < produtosCategoria.size(); i++) {
            if (produtosSimilares.size() == 3)
                break;

            if(!produtosCategoria.get(i).equals(produto) && produtosCategoria.get(i).getSubCategoria().equals(produto.getSubCategoria()) && (produtosCategoria.get(i).getQuantidadeEmEstoque() > 0)) {
                produtosSimilares.add(produtosCategoria.get(i));
            }
        }

        if(produtosSimilares.size() > 0) {
            Label labelSimilares = new Label();
            labelSimilares.setText("Similares");
            labelSimilares.setStyle("-fx-font: 32 arial;");

            for (int i = 0 ; i < produtosSimilares.size() ; i++) {

                Produto produtoSimilarDaVez = produtosSimilares.get(i);

                if(true){
                    AnchorPane produtoPane = new AnchorPane();
                    produtoPane.setCursor(Cursor.HAND);

                    Image produtoSimilarImagem = new Image("file:" + produtoSimilarDaVez.getImg());
                    ImageView produtoImagemView = new ImageView();
                    Label produtoLabel = new Label();
                    Label precoProdutoLabel = new Label();

                    produtoImagemView.setImage(produtoSimilarImagem);


                    produtoLabel.setText(produtoSimilarDaVez.getNome());
                    String precoSimilar = df.format(produtoSimilarDaVez.getPreço() - (produtoSimilarDaVez.getPreço() * produtoSimilarDaVez.getDesconto()/100));
                    precoSimilar = precoSimilar.replaceAll("\\.", ",");
                    precoProdutoLabel.setText("R$ " + precoSimilar );

                    //cada um ocupa 272 px  e a distancia vai ser de 30px
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

                    //TInha os valores padrao
                    produtoPane.setLayoutX( posicaoProdutoX * 278);
                    produtoPane.setLayoutY(45);
                    produtoPane.setPrefWidth(210);
                    produtoPane.setPrefHeight(273);

                    produtoPane.getChildren().add(produtoImagemView);
                    produtoPane.getChildren().add(produtoLabel);
                    produtoPane.getChildren().add(precoProdutoLabel);

                    posicaoProdutoX++;

                    //Ultima parte ajustada

                    String nome = produtoSimilarDaVez.getNome();
                    String precoSubs = precoProdutoLabel.getText();
                    String categoria = produtoSimilarDaVez.getCategoria();
                    String subCategoria = produtoSimilarDaVez.getSubCategoria();
                    String desconto = Integer.toString(produtoSimilarDaVez.getDesconto());
                    String estoque = Integer.toString(produtoSimilarDaVez.getQuantidadeEmEstoque());

                    produtoPane.setOnMouseClicked(mouseEvent -> {
                        try {
                            abrirVisualizacaoDoProduto(mouseEvent, produtoSimilarDaVez);
                        } catch (IOException err) {
                            System.out.println(err);
                        }

                    });
                    //mainPane
                    paneProdutosSimilares.getChildren().add(produtoPane);
                }
            }

            paneProdutosSimilares.getChildren().add(labelSimilares);
        }

        voltarButton.setOnMouseClicked(mouseEvent -> {
            try {
                retornar(mouseEvent);
            } catch (IOException err) {
                System.out.println(err);
            }
        });

        adicionarCarrinhoButton.setOnMouseClicked(mouseEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("carrinhoView.fxml"));
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
        });
    }

    public void retornar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("produtosViewCliente.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void abrirVisualizacaoDoProduto(MouseEvent event, Produto produto) throws IOException{
        data.setProduto(produto);
        data.setArrayListProdutos(produtosCategoria);


        Parent root = FXMLLoader.load(getClass().getResource("produto.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
