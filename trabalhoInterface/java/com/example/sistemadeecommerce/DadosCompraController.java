package com.example.sistemadeecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class DadosCompraController {

    private Scene scene;
    private Stage stage;

    @FXML
    private AnchorPane produtosPane;
    @FXML
    private AnchorPane finalPane;


    @FXML
    private ScrollPane scrollPaneProdutos;

    @FXML
    private Label carrinhoDeComprasLabel;
    @FXML
    private Label pontosGastosLabel;

    @FXML
    private Label pontosTotalLabel;

    @FXML
    private Label precoTotalLabel;

    @FXML
    private Button voltarButton;

    private Cliente cliente = ContaController.getClient();
    private int pontosCliente = cliente.getPontos();
    private DataSingleton data = DataSingleton.getInstance();
    //private Produto produto = data.getProduto();
    private CarrinhoDeCompras carrinho = data.getCarrinho();
    private long pontosTotal;
    private double precoTotal;

    @FXML
    public void initialize () {
        //produto.calcularQtdPontos();

        // = new CarrinhoDeCompras();
        //carrinho.adicionarProduto(produto);
        //int inteiro = carrinho.getQtdProduto().get(0) +1;
        //carrinho.getQtdProduto().set(0, inteiro );




        voltarButton.setOnMouseClicked(mouseEvent -> {
            voltarInicio(mouseEvent);
        });



        gerarNodes();

    }

    public void gerarNodes() {
        produtosPane.getChildren().setAll();
        pontosTotalLabel.setText("Pontos: 0");
        precoTotalLabel.setText("Preço: R$ 0,00");
        pontosGastosLabel.setText("Pontos gastos: 0");

        precoTotal = 0;
        pontosTotal = 0;

        for( int i = -1; i < carrinho.getProdutos().size(); i++) {
            Label labelProdutoNome = new Label();
            Label labelProdutoQtd = new Label();
            Label labelProdutoPrecoUnd = new Label();
            Label labelProdutoDesconto = new Label();
            Label labelProdutoPrecoFinal = new Label();
            Label labelProdutoPontos = new Label();


            if ( i == -1) {
                labelProdutoNome.setText("Produto");
                labelProdutoQtd.setText("Quantidade");
                labelProdutoPrecoUnd.setText("Preço Und");
                labelProdutoDesconto.setText("Desconto");
                labelProdutoPrecoFinal.setText("Preço");
                labelProdutoPontos.setText("Pontos");
            } else {
                pontosTotal = pontosTotal + carrinho.getQtdProduto().get(i) * carrinho.getProdutos().get(i).getQtdPontos();

                DecimalFormat df = new DecimalFormat("#.00");
                if (i == (carrinho.getProdutos().size() - 1)) {

                    String preco;
                    if (data.getValorFinal() != 0) {
                        preco = df.format(data.getValorFinal());
                        preco = preco.replaceAll("\\.", ",");
                    } else {
                         preco = "0,00";
                    }

                    precoTotalLabel.setText("Preço: R$ " + preco );
                    pontosTotalLabel.setText("Pontos: " + pontosTotal);
                    pontosGastosLabel.setText("Pontos gastos: " + data.getPontosGastos());
                }




                labelProdutoNome.setText(carrinho.getProdutos().get(i).getNome());
                labelProdutoQtd.setText("" + carrinho.getQtdProduto().get(i));

                String precoUnd = df.format(carrinho.getProdutos().get(i).getPreço());
                precoUnd = precoUnd.replaceAll("\\.", ",");
                labelProdutoPrecoUnd.setText(precoUnd);

                labelProdutoDesconto.setText(Integer.toString(carrinho.getProdutos().get(i).getDesconto()) + "%");


                String precoConjunto = df.format(carrinho.getQtdProduto().get(i) * (carrinho.getProdutos().get(i).getPreço() - carrinho.getProdutos().get(i).getPreço() * carrinho.getProdutos().get(i).getDesconto()/100));
                precoConjunto = precoConjunto.replaceAll("\\.", ",");


                labelProdutoPrecoFinal.setText("" + precoConjunto);
                labelProdutoPontos.setText("" + carrinho.getQtdProduto().get(i) * carrinho.getProdutos().get(i).getQtdPontos());


            }

            // Antigos valors: 120, 240, 360, 480, 800

            labelProdutoNome.setLayoutX(5);
            labelProdutoQtd.setLayoutX(270);
            labelProdutoPrecoUnd.setLayoutX(390);
            labelProdutoDesconto.setLayoutX(510);
            labelProdutoPrecoFinal.setLayoutX(630);
            labelProdutoPontos.setLayoutX(750);

            labelProdutoNome.setLayoutY((i+1) * 30);
            labelProdutoQtd.setLayoutY((i+1) * 30);
            labelProdutoPrecoUnd.setLayoutY((i+1) * 30);
            labelProdutoDesconto.setLayoutY((i+1) * 30);
            labelProdutoPrecoFinal.setLayoutY((i+1) * 30);
            labelProdutoPontos.setLayoutY((i+1) * 30);

            produtosPane.getChildren().add(labelProdutoNome);
            produtosPane.getChildren().add(labelProdutoQtd);
            produtosPane.getChildren().add(labelProdutoPrecoFinal);
            produtosPane.getChildren().add(labelProdutoPontos);
            produtosPane.getChildren().add(labelProdutoPrecoUnd);
            produtosPane.getChildren().add(labelProdutoDesconto);



        }
    }

    public void voltarInicio (MouseEvent event) {
        carrinho.getProdutos().clear();
        carrinho.getQtdProduto().clear();

        int novosPontos = (int) (cliente.getPontos()-data.getPontosGastos());

        cliente.setPontos(novosPontos);


        try {
            Parent root = FXMLLoader.load(getClass().getResource("produtosViewCliente.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

    }

}
