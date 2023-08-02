package com.example.sistemadeecommerce;

import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EventListener;

public class ProdutosControllerAdm {
    private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
    private int posicaoProdutoX = 0;
    private int posicaoProdutoY = 0;
    private String nomePreAlteracao = "";
    String urlImagem;

    private Stage stage;
    private Scene scene;
    @FXML
    private Label labelCategoria, aviso;
    @FXML
    private AnchorPane criarProdutoPane;
    @FXML
    private Button menuAdicionaButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button buttonVolta;
    @FXML
    private Button buttonAdicionaImagem;
    @FXML
    private ImageView imageViewProduto;
    @FXML
    private ImageView imageViewProduto1;
    @FXML
    private Label labelInserirImagem;
    @FXML
    private Label labelInserirImagem1;

    @FXML
    private TextField textFieldProdutoNome;
    @FXML
    private TextField textFieldProdutoNome1;
    @FXML
    private MenuButton menuProdutoCategoria;
    @FXML
    private MenuButton menuProdutoCategoria1;
    @FXML
    private MenuItem menuItemAlimentos1;
    @FXML
    private Button voltarMenuButton;
    @FXML
    private MenuItem menuItemBebidas1;
    @FXML
    private MenuButton menu;
    @FXML
    private TextField textFieldProdutoPreco;
    @FXML
    private TextField textFieldProdutoPreco1;
    @FXML
    private TextField textFieldProdutoDesconto;
    @FXML
    private TextField textFieldProdutoDesconto1;
    @FXML
    private TextField textFieldProdutoEstoque;
    @FXML
    private TextField textFieldProdutoEstoque1;
    @FXML
    private MenuItem menuItemAlimentos;
    @FXML
    private MenuItem menuItemBebidas;
    @FXML
    private Button adicionaButton;
    @FXML
    private AnchorPane alterarProdutoPane;
    @FXML
    private Button alteraButton;
    @FXML
    private Button deletaButton;
    @FXML
    private Button buttonVolta1;
    @FXML
    private AnchorPane visualizacaoProdutosPane;
    @FXML
    private Button buttonAdicionaImagem1;

    @FXML
    private MenuButton menuProdutoSubCategoria;
    @FXML
    private MenuButton menuProdutoSubCategoria1;

    DataSingleton data = DataSingleton.getInstance();
    File arquivoProdutos = new File("./src/main/resources/produtos/" + data.getUserName() + ".txt" );


    @FXML
    public void initialize() {

        labelCategoria.setText(data.getUserName());

        try {
            FileReader fr = new FileReader(arquivoProdutos);
            BufferedReader br = new BufferedReader(fr);
            String linha;

            while((linha = br.readLine()) != null){
                String[] arrayLinha = linha.split("\t");
                Produto p = new Produto(arrayLinha[0], arrayLinha[1], arrayLinha[2], Double.parseDouble(arrayLinha[3]), Integer.parseInt(arrayLinha[4]), Integer.parseInt(arrayLinha[5]), arrayLinha[7]);
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

        menuAdicionaButton.setOnMouseClicked(mouseEvent -> {
                abrirMenuAdicionarProduto();
        });

        buttonVolta.setOnMouseClicked(mouseEvent -> {
            retornar("menuCriacao");
        });

        buttonVolta1.setOnMouseClicked(mouseEvent -> {
            retornar("menuAlteracao");
        });

        buttonAdicionaImagem.setOnMouseClicked(mouseEvent -> {
            adicionarImagem(mouseEvent, "menuCriacao");
        });

        buttonAdicionaImagem1.setOnMouseClicked(mouseEvent -> {
            adicionarImagem(mouseEvent, "menuAlteracao");
        });

        adicionaButton.setOnMouseClicked(mouseEvent -> {
            adicionarProduto();
        });



       deletaButton.setOnMouseClicked(mouseEvent -> {
            deletarProduto();
        });

        alteraButton.setOnMouseClicked(mouseEvent -> {
            alterarProduto();
        });

    }

    public void abrirMenuAdicionarProduto() {

        criarProdutoPane.setVisible(true);

        menuProdutoCategoria.setText(data.getUserName());

        menuProdutoSubCategoria.setDisable(false);

        for(int ind = 0; ind < 4; ind++){

            MenuItem subCategoriaItem = new MenuItem();

            switch(menuProdutoCategoria.getText()){

                case "Roupas":

                    switch(ind){

                        case 0:
                            subCategoriaItem.setText("Vestidos");
                            break;
                        case 1:
                            subCategoriaItem.setText("Camisas");
                            break;
                        case 2:
                            subCategoriaItem.setText("Calçados");
                            break;
                        case 3:
                            subCategoriaItem.setText("Bermudas");
                            break;
                    }

                    subCategoriaItem.setOnAction(actionEvent -> {
                        menuProdutoSubCategoria.setText(subCategoriaItem.getText());
                    });

                    menuProdutoSubCategoria.getItems().add(subCategoriaItem);

                    break;
                case "Cozinha":

                    switch(ind){

                        case 0:
                            subCategoriaItem.setText("Espatulas");
                            break;
                        case 1:
                            subCategoriaItem.setText("Facas");
                            break;
                        case 2:
                            subCategoriaItem.setText("Tigelas");
                            break;
                        case 3:
                            subCategoriaItem.setText("Copos");
                            break;
                    }

                    subCategoriaItem.setOnAction(actionEvent -> {
                        menuProdutoSubCategoria.setText(subCategoriaItem.getText());
                    });

                    menuProdutoSubCategoria.getItems().add(subCategoriaItem);

                    break;
                case "Material escolar":

                    switch(ind){

                        case 0:
                            subCategoriaItem.setText("Lápis");
                            break;
                        case 1:
                            subCategoriaItem.setText("Canetas");
                            break;
                        case 2:
                            subCategoriaItem.setText("Cadernos");
                            break;
                        case 3:
                            subCategoriaItem.setText("Mochilas");
                            break;
                    }

                    subCategoriaItem.setOnAction(actionEvent -> {
                        menuProdutoSubCategoria.setText(subCategoriaItem.getText());
                    });

                    menuProdutoSubCategoria.getItems().add(subCategoriaItem);

                    break;
                case "Alimentos":

                    switch(ind){

                        case 0:
                            subCategoriaItem.setText("Frutas");
                            break;
                        case 1:
                            subCategoriaItem.setText("Derivados do leite");
                            break;
                        case 2:
                            subCategoriaItem.setText("Chocolates");
                            break;
                        case 3:
                            subCategoriaItem.setText("Salgadinhos");
                            break;
                    }

                    subCategoriaItem.setOnAction(actionEvent -> {
                        menuProdutoSubCategoria.setText(subCategoriaItem.getText());
                    });

                    menuProdutoSubCategoria.getItems().add(subCategoriaItem);

                    break;
                case "Bebidas":

                    switch(ind){

                        case 0:
                            subCategoriaItem.setText("Sucos");
                            break;
                        case 1:
                            subCategoriaItem.setText("Refrigerantes");
                            break;
                        case 2:
                            subCategoriaItem.setText("Águas");
                            break;
                        case 3:
                            subCategoriaItem.setText("Energéticos");
                            break;
                    }

                    subCategoriaItem.setOnAction(actionEvent -> {
                        menuProdutoSubCategoria.setText(subCategoriaItem.getText());
                    });

                    menuProdutoSubCategoria.getItems().add(subCategoriaItem);

                    break;
                case "Eletrodomésticos":

                    switch(ind){

                        case 0:
                            subCategoriaItem.setText("Geladeiras");
                            break;
                        case 1:
                            subCategoriaItem.setText("Fogões");
                            break;
                        case 2:
                            subCategoriaItem.setText("Liquidificadores");
                            break;
                        case 3:
                            subCategoriaItem.setText("Ventiladores");
                            break;
                    }

                    subCategoriaItem.setOnAction(actionEvent -> {
                        menuProdutoSubCategoria.setText(subCategoriaItem.getText());
                    });

                    menuProdutoSubCategoria.getItems().add(subCategoriaItem);

                    break;
            }

        }

        criarProdutoPane.toFront();

    }

    public int adicionarProduto() {

        try {

            String precoAjustado = textFieldProdutoPreco.getText().replaceAll(",", ".");

            Double.parseDouble(precoAjustado);
            Integer.parseInt(textFieldProdutoDesconto.getText());
            Integer.parseInt(textFieldProdutoEstoque.getText());

            if(!textFieldProdutoNome.getText().equals("") && !menuProdutoSubCategoria.getText().equals("")  && !menuProdutoCategoria.getText().equals("") && !textFieldProdutoDesconto.getText().equals("") && !textFieldProdutoEstoque.getText().equals("")  && imageViewProduto.getImage() != null){
                AnchorPane produtoPane = new AnchorPane();
                produtoPane.setCursor(Cursor.HAND);

                Image produtoImagem = imageViewProduto.getImage();
                ImageView produtoImagemView = new ImageView();
                Label produtoLabel = new Label();
                Label precoProdutoLabel = new Label();

                produtoImagemView.setImage(produtoImagem);
                produtoLabel.setText(textFieldProdutoNome.getText());


                //Adicionando ao objeto
                String produtoNome = textFieldProdutoNome.getText();
                String produtoCategoria = data.getUserName();
                String produtoSubcategoria = menuProdutoSubCategoria.getText();
                double produtoPreco = Double.parseDouble(precoAjustado);
                int produtoDesconto = Integer.parseInt(textFieldProdutoDesconto.getText());
                int produtoEstoque = Integer.parseInt(textFieldProdutoEstoque.getText());

                double precoAMostrar = produtoPreco - (produtoPreco * produtoDesconto/100);

                DecimalFormat df = new DecimalFormat("#.00");
                String precoAMostrarString = df.format(precoAMostrar).replaceAll("\\.", ",");


                precoProdutoLabel.setText("R$ " + precoAMostrarString);
                String produtoImage = urlImagem;


                Produto p = new Produto(produtoNome, produtoCategoria, produtoSubcategoria, produtoPreco, produtoDesconto, produtoEstoque, produtoImage );

                //Calculo de pontos
                p.calcularQtdPontos();
                long qtdPontos = p.getQtdPontos();

                listaProdutos.add(p);

                //Escrevendo no arquivo
                try {
                    FileWriter fw = new FileWriter(arquivoProdutos, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    if (arquivoProdutos.length() == 0) {
                        bw.write(  produtoNome + "\t" + produtoCategoria + "\t" + produtoSubcategoria + "\t"  + produtoPreco + "\t" + produtoDesconto + "\t" + produtoEstoque + "\t" + qtdPontos + "\t"+produtoImage);
                    } else {
                        bw.write( "\n" + produtoNome + "\t" + produtoCategoria + "\t" + produtoSubcategoria + "\t" + produtoPreco + "\t" + produtoDesconto + "\t" + produtoEstoque +"\t" + qtdPontos +  "\t"+produtoImage);
                    }

                    bw.flush();
                    bw.close();
                    fw.close();
                } catch (IOException err) {
                    System.out.println(err);
                }

                urlImagem ="";




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

                produtoPane.setLayoutY( posicaoProdutoY * 303);
                produtoPane.setLayoutX(posicaoProdutoX * 278);
                produtoPane.setPrefWidth(210);
                produtoPane.setPrefHeight(273);

                produtoPane.getChildren().add(produtoImagemView);
                produtoPane.getChildren().add(produtoLabel);
                produtoPane.getChildren().add(precoProdutoLabel);

                posicaoProdutoX++;
                if (posicaoProdutoX == 4){
                    posicaoProdutoX = 0;
                    posicaoProdutoY++;
                }


                String precoProd = df.format(p.getPreço());
                precoProd = precoProd.replaceAll("\\.", ",");
                String precoProdComSimbolo = "R$ " + precoProd;

                precoProdutoLabel.setText("R$ " + precoAMostrarString);

                String precoSubs = precoProdutoLabel.getText();
                String desconto = textFieldProdutoDesconto.getText();
                String estoque = textFieldProdutoEstoque.getText();

                produtoPane.setOnMouseClicked(mouseEvent -> {
                    abrirMenualterarProduto(produtoNome, produtoCategoria, produtoSubcategoria, precoProdComSimbolo, desconto, estoque, produtoImagem);
                });

                visualizacaoProdutosPane.getChildren().add(produtoPane);

                retornar("menuCriacao");
            }

        } catch (NumberFormatException numberFormatException) {
            //
        }

        return 0;

    }


    public void deletarProduto() {
     String nomeProduto = nomePreAlteracao;

        for(int i = 0; i < listaProdutos.size(); i++) {
            if(listaProdutos.get(i).getNome().equals(nomeProduto)) {
                listaProdutos.remove(i);
                break;
            }
        }

        gerarLabelProdutos();

        retornar("menuAlteracao");

    }


    public void retornar(String menu) {
        if(menu.equals("menuCriacao")){
            textFieldProdutoNome.setText("");
            textFieldProdutoPreco.setText("");
            menuProdutoCategoria.setText("");
            menuProdutoSubCategoria.setText("");
            menuProdutoSubCategoria.getItems().setAll();
            menuProdutoSubCategoria.setDisable(true);
            textFieldProdutoDesconto.setText("");
            textFieldProdutoEstoque.setText("");
            imageViewProduto.setImage(null);
            labelInserirImagem.setText("Insira a imagem");
            criarProdutoPane.setVisible(false);
            urlImagem = "";
        } else {

            alterarProdutoPane.setVisible(false);
        }


    }

    public void adicionarImagem(MouseEvent event, String menu) {
        if (menu.equals("menuCriacao")){
            labelInserirImagem.setText("");

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
            if (selectedFile != null) {
                InputStream targetStream;

                try {
                    targetStream = new DataInputStream(new FileInputStream(selectedFile));


                    File dir = new File("./src/main/resources/images/");
                    File src = new File(selectedFile.getAbsolutePath());
                    File target = new File(dir, selectedFile.getName());

                    Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    urlImagem = "./src/main/resources/images/" + target.getName();



                    Image produtoImagem = new Image(targetStream);
                    imageViewProduto.setImage(produtoImagem);
                } catch (FileNotFoundException err) {
                    System.out.println(err);
                } catch (IOException errIo) {
                    System.out.println(errIo);
                }
            }
        } else {
            labelInserirImagem1.setText("");

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
            if (selectedFile != null) {
                InputStream targetStream;

                try {
                    targetStream = new DataInputStream(new FileInputStream(selectedFile));


                    File dir = new File("./src/main/resources/images/");
                    File src = new File(selectedFile.getAbsolutePath());
                    File target = new File(dir, selectedFile.getName());

                    Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    urlImagem = "./src/main/resources/images/" + target.getName();



                    Image produtoImagem = new Image(targetStream);
                    imageViewProduto1.setImage(produtoImagem);
                } catch (FileNotFoundException err) {
                    System.out.println(err);
                } catch (IOException errIo) {
                    System.out.println(errIo);
                }
            }
        }



    }
    //, String produtoCategoria, String produtoDesconto, String produtoEstoque
    public void abrirMenualterarProduto(String produtoNome, String produtoCategoria, String produtoSubCategoria,String produtoPreco, String produtoDesconto, String produtoEstoque, Image produtoImage) {


        nomePreAlteracao = produtoNome;
        alterarProdutoPane.setVisible(true);
        alterarProdutoPane.toFront();
        textFieldProdutoNome1.setText(produtoNome);

        String[] arrayPreco = produtoPreco.split(" ");

        textFieldProdutoPreco1.setText(arrayPreco[1]);
        imageViewProduto1.setImage(produtoImage);
        labelInserirImagem1.setText("");

        menuProdutoCategoria1.setText(produtoCategoria);
        menuProdutoCategoria1.setDisable(true);
        menuProdutoSubCategoria1.setText(produtoSubCategoria);
        menuProdutoSubCategoria1.setDisable(true);

        textFieldProdutoDesconto1.setText(produtoDesconto);
        textFieldProdutoEstoque1.setText(produtoEstoque);


    }

    public void alterarProduto() {
        String nomeProduto = nomePreAlteracao;

        try {
            String precoAjustado = textFieldProdutoPreco1.getText().replaceAll(",", ".");

            Double.parseDouble(precoAjustado);
            Integer.parseInt(textFieldProdutoDesconto1.getText());
            Integer.parseInt(textFieldProdutoEstoque1.getText());


            if (!textFieldProdutoNome1.getText().equals("")) {
                for(int i = 0; i < listaProdutos.size(); i++) {
                    if(listaProdutos.get(i).getNome().equals(nomeProduto)) {
                        listaProdutos.get(i).setNome(textFieldProdutoNome1.getText());
                        listaProdutos.get(i).setCategoria(menuProdutoCategoria1.getText());
                        listaProdutos.get(i).setSubCategoria(menuProdutoSubCategoria1.getText());
                        listaProdutos.get(i).setPreço(Double.parseDouble(precoAjustado));
                        listaProdutos.get(i).setDesconto(Integer.parseInt(textFieldProdutoDesconto1.getText()));

                        //Calculo qtdPontos
                        listaProdutos.get(i).calcularQtdPontos();

                        listaProdutos.get(i).setQuantidadeEmEstoque(Integer.parseInt(textFieldProdutoEstoque1.getText()));
                        if(urlImagem != "" && urlImagem != null)
                            listaProdutos.get(i).setImg(urlImagem);
                        break;
                    }
                }

                gerarLabelProdutos();

                retornar("menuAlteracao");

            }

        } catch (NumberFormatException numberFormatException)  {
            //
        }



    }

    public void gerarLabelProdutos() {
        try {
            FileWriter fw = new FileWriter(arquivoProdutos, false);
            BufferedWriter bw = new BufferedWriter(fw);

            boolean primeiraLinha = true;
            for (int i = 0; i < listaProdutos.size(); i++) {

                if (primeiraLinha) {
                    bw.write(listaProdutos.get(i).getNome() + "\t" + listaProdutos.get(i).getCategoria() + "\t" + listaProdutos.get(i).getSubCategoria() + "\t" + listaProdutos.get(i).getPreço() + "\t" + listaProdutos.get(i).getDesconto() + "\t" + listaProdutos.get(i).getQuantidadeEmEstoque() + "\t" + listaProdutos.get(i).getQtdPontos() + "\t" + listaProdutos.get(i).getImg());
                    primeiraLinha = false;
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

            String nome = produto.getNome();


            String precoProd = df.format(listaProdutos.get(i).getPreço());
            precoProd = precoProd.replaceAll("\\.", ",");
            String precoProdComSimbolo = "R$ " + precoProd;

            precoProdutoLabel.setText("R$ " + preco);


            String categoria = produto.getCategoria();
            String subCategoria = produto.getSubCategoria();
            String desconto = Integer.toString(produto.getDesconto());
            String estoque = Integer.toString(produto.getQuantidadeEmEstoque());

            produtoPane.setOnMouseClicked(mouseEvent -> {
                abrirMenualterarProduto(nome, categoria, subCategoria, precoProdComSimbolo, desconto, estoque, produtoImagem);
            });

            visualizacaoProdutosPane.getChildren().add(produtoPane);
        }
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
