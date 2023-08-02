package com.example.sistemadeecommerce;

import javafx.event.ActionEvent;
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

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CarrinhoController {

    private Scene scene;
    private Stage stage;
    double precoTotal = 0;
    long  pontosTotal = 0;

    @FXML
    private AnchorPane produtosPane;
    @FXML
    private AnchorPane confirmarCompraPane;
    @FXML
    private AnchorPane finalPane;


    @FXML
    private ScrollPane scrollPaneProdutos;

    @FXML
    private Label carrinhoDeComprasLabel;

    @FXML
    private Label pontosTotalLabel;
    @FXML
    private Label pontosPossuidosLabel;

    @FXML
    private Label precoTotalLabel;

    @FXML
    private Button voltarButton;
    @FXML
    private Button voltarButton1;
    @FXML
    private Button confirmarCompraButton;
    @FXML
    private Button prosseguirCompraButton;

    @FXML
    private RadioButton radioButtonPontos;
    @FXML
    private RadioButton radioButtonParcelar;
    @FXML
    private RadioButton radioButtonAVista;

    private Cliente cliente = ContaController.getClient();
    int pontosCliente = cliente.getPontos();
    String clienteNome = cliente.getNome();

    private DataSingleton data = DataSingleton.getInstance();
    private Produto produto = data.getProduto();
    private CarrinhoDeCompras carrinho;

    @FXML
    public void initialize () {

        if((carrinho = data.getCarrinho()) == null){

            carrinho = new CarrinhoDeCompras();

        }

        produto.calcularQtdPontos();

        carrinho.adicionarProduto(produto);

        voltarButton.setOnMouseClicked(mouseEvent -> {
            voltarInicio(mouseEvent);
        });

        voltarButton1.setOnMouseClicked(mouseEvent -> {
            voltarAoCarrinho();
        });

        data.setCarrinho(carrinho);

        gerarNodes();

    }

    public void gerarNodes() {
        produtosPane.getChildren().setAll();
        pontosTotalLabel.setText("Pontos: 0");
        precoTotalLabel.setText("Preço: R$ 0,00");

        precoTotal = 0.00;
        pontosTotal = 0;

        for( int i = -1; i < carrinho.getProdutos().size(); i++) {
            Label labelProdutoNome = new Label();
            Label labelProdutoQtd = new Label();
            Label labelProdutoPrecoFinal = new Label();
            Label labelProdutoPontos = new Label();


            if ( i == -1) {
                labelProdutoNome.setText("Produto");
                labelProdutoQtd.setText("Quantidade");
                labelProdutoPrecoFinal.setText("Preço");
                labelProdutoPontos.setText("Pontos");
            } else {
                precoTotal = precoTotal + carrinho.getQtdProduto().get(i) * (carrinho.getProdutos().get(i).getPreço() - carrinho.getProdutos().get(i).getPreço() * carrinho.getProdutos().get(i).getDesconto()/100);
                pontosTotal = pontosTotal + carrinho.getQtdProduto().get(i) * carrinho.getProdutos().get(i).getQtdPontos();

                DecimalFormat df = new DecimalFormat("#.00");
                if (i == (carrinho.getProdutos().size() - 1)) {


                    String preco = df.format(precoTotal);
                    preco = preco.replaceAll("\\.", ",");
                    precoTotalLabel.setText("Preço: R$ " + preco );
                    pontosTotalLabel.setText("Pontos: " + pontosTotal);
                }




                labelProdutoNome.setText(carrinho.getProdutos().get(i).getNome());
                labelProdutoQtd.setText("" + carrinho.getQtdProduto().get(i));

                String precoConjunto = df.format(carrinho.getQtdProduto().get(i) * (carrinho.getProdutos().get(i).getPreço() - carrinho.getProdutos().get(i).getPreço() * carrinho.getProdutos().get(i).getDesconto()/100));
                precoConjunto = precoConjunto.replaceAll("\\.", ",");


                labelProdutoPrecoFinal.setText("" + precoConjunto);
                labelProdutoPontos.setText("" + carrinho.getQtdProduto().get(i) * carrinho.getProdutos().get(i).getQtdPontos());

                Button adicionarButton = new Button();
                Button removerButton = new Button();

                adicionarButton.setText("Adicionar");
                adicionarButton.setLayoutX(630);
                adicionarButton.setLayoutY((i+1) * 30 - 5);
                produtosPane.getChildren().add(adicionarButton);

                int indice = i;

                adicionarButton.setOnMouseClicked(mouseEvent -> {

                    if(!((carrinho.getQtdProduto().get(indice) + 1) > carrinho.getProdutos().get(indice).getQuantidadeEmEstoque())) {
                        carrinho.getQtdProduto().set(indice, carrinho.getQtdProduto().get(indice) + 1);
                        gerarNodes();
                    }
                });

                removerButton.setText("Remover");
                removerButton.setLayoutX(750);
                removerButton.setLayoutY((i+1) * 30 -5);
                produtosPane.getChildren().add(removerButton);

                removerButton.setOnMouseClicked(mouseEvent -> {
                    if((carrinho.getQtdProduto().get(indice) - 1 )== 0) {
                        carrinho.getProdutos().remove(indice);
                        carrinho.getQtdProduto().remove(indice);
                        gerarNodes();
                    } else {
                        carrinho.getQtdProduto().set(indice, carrinho.getQtdProduto().get(indice) - 1);
                        gerarNodes();
                    }

                });

            }

            // Antigos valors: 120, 240, 360, 480, 600
            labelProdutoNome.setLayoutX(5);
            labelProdutoQtd.setLayoutX(270);
            labelProdutoPrecoFinal.setLayoutX(390);
            labelProdutoPontos.setLayoutX(510);

            labelProdutoNome.setLayoutY((i+1) * 30);
            labelProdutoQtd.setLayoutY((i+1) * 30);
            labelProdutoPrecoFinal.setLayoutY((i+1) * 30);
            labelProdutoPontos.setLayoutY((i+1) * 30);

            produtosPane.getChildren().add(labelProdutoNome);
            produtosPane.getChildren().add(labelProdutoQtd);
            produtosPane.getChildren().add(labelProdutoPrecoFinal);
            produtosPane.getChildren().add(labelProdutoPontos);



        }
    }

    public void voltarInicio (MouseEvent event) {
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

    public void prosseguirCompra() {
        if (precoTotal > 0) {
            confirmarCompraPane.setVisible(true);
            confirmarCompraPane.toFront();

            scrollPaneProdutos.setVisible(false);
            finalPane.setVisible(false);
            voltarButton.setVisible(false);
            prosseguirCompraButton.setVisible(false);
            carrinhoDeComprasLabel.setVisible(false);

            pontosPossuidosLabel.setText("Pontos Possuídos: " + Integer.toString(cliente.getPontos()));

            DecimalFormat df = new DecimalFormat("#.00");
            String preco = df.format(precoTotal);
            preco = preco.replaceAll("\\.", ",");
            radioButtonAVista.setText("Pagar à vista R$ " + preco);

            String precoParcelado = df.format((precoTotal/2));
            precoParcelado = precoParcelado.replaceAll("\\.", ",");

            radioButtonParcelar.setText("Pagar parcelado em 2 vezes de R$ " + precoParcelado);

            if (precoTotal > 50.00) {
                radioButtonParcelar.setDisable(false);
            } else {
                radioButtonParcelar.setDisable(true);
            }



            radioButtonPontos.setOnAction(mouseEvent -> {
                if(radioButtonPontos.isSelected()) {
                    long pontosUtilizaveis = cliente.getPontos() - (cliente.getPontos()%10);

                    double valorAPagar;

                    if(pontosUtilizaveis/10 >= Math.round(precoTotal)) {
                        valorAPagar = 0;
                    } else {
                        valorAPagar = precoTotal - pontosUtilizaveis/10;
                    }

                    String precoAVista;
                    String precoParc;
                    if(valorAPagar == 0) {
                        precoAVista = "0,00";
                        precoParc = "0,00";
                    } else {
                        precoAVista = df.format(valorAPagar);
                        precoAVista = precoAVista.replaceAll("\\.", ",");
                        precoParc= df.format((valorAPagar/2));
                        precoParc = precoParc.replaceAll("\\.", ",");

                    }

                    radioButtonAVista.setText("Pagar à vista R$ " + precoAVista);



                    radioButtonParcelar.setText("Pagar parcelado em 2 vezes de R$ " + precoParc);


                } else {
                    String precoAVista = df.format(precoTotal);
                    precoAVista = precoAVista.replaceAll("\\.", ",");
                    radioButtonAVista.setText("Pagar à vista R$ " + precoAVista);

                    String precoParc = df.format((precoTotal/2));
                    precoParc = precoParc.replaceAll("\\.", ",");

                    radioButtonParcelar.setText("Pagar parcelado em 2 vezes de R$ " + precoParc);
                }
            });




        }


    }

    public void voltarAoCarrinho() {
        confirmarCompraPane.setVisible(false);

        scrollPaneProdutos.setVisible(true);
        finalPane.setVisible(true);
        voltarButton.setVisible(true);
        prosseguirCompraButton.setVisible(true);
        carrinhoDeComprasLabel.setVisible(true);
    }

    public int finalizarCompra (ActionEvent event) {

        reduzirEstoque();

        if(!radioButtonPontos.isSelected()) {
            data.setPontosGastos(0);
            data.setValorFinal(precoTotal);

        } else {

            long pontosUtilizaveis = cliente.getPontos() - (cliente.getPontos()%10);

            if(pontosUtilizaveis/10 >= Math.round(precoTotal)) {
                data.setPontosGastos(Math.round(precoTotal) * 10);
                data.setValorFinal(0);
            } else {
                data.setPontosGastos(pontosUtilizaveis);
                data.setValorFinal(precoTotal - pontosUtilizaveis/10);
            }

        }

        if(precoTotal > 50 && !(radioButtonAVista.isSelected() || radioButtonParcelar.isSelected())){

            return -1;

        }

        File arquivo = new File("./src/main/resources/cadastros/clientesCadastrados.txt");

        try {

            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> pontosModify = new ArrayList<String>();
            String linha;
            int ind = 0;

            while((linha = br.readLine()) != null){

                String atributos[] = linha.split("\t");
                long pontos;

                if(cliente.getNome().equals(atributos[0])){

                    pontos = pontosTotal + Integer.parseInt(atributos[3]) - data.getPontosGastos();

                    atributos[3] = Long.toString(pontos);

                }

                pontosModify.add(atributos[0] + "\t" + atributos[1] + "\t" +  atributos[2] + "\t" +  atributos[3] + "\t" +  atributos[4] + "\n");

                ind++;

            }

            br.close();
            fr.close();

            FileWriter fw;

            for(int ind2 = 0; ind2 < ind; ind2++){

                fw = new FileWriter(arquivo, (ind2 > 0));

                fw.write(pontosModify.get(ind2));

                fw.close();

            }

            Parent root = FXMLLoader.load(getClass().getResource("dadosCompraView.fxml"));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        gerarRelatorio();

        return 0;

    }

    public void gerarRelatorio() {
        File arquivoRelatorio = new File("./src/main/resources/comprasClientes/" + cliente.getNome() + ".txt");
        try {
            FileWriter fw = new FileWriter(arquivoRelatorio, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (arquivoRelatorio.length() == 0) {
                bw.write("Compra");
            } else {
                bw.write("\nCompra");
            }
            bw.write("\n" + Tempo.setDataAtual());
            long pontosTotais = 0;
            for (int i = -1; i < carrinho.getProdutos().size() ; i++) {
                if (i == -1) {
                    bw.write("\n" + "Produto\tUnidades\tValor\tPontos");
                    continue;
                }

                double precoProdutos = carrinho.getQtdProduto().get(i) * (carrinho.getProdutos().get(i).getPreço() - carrinho.getProdutos().get(i).getPreço() * carrinho.getProdutos().get(i).getDesconto()/100);

                long pontosProdutos = carrinho.getQtdProduto().get(i) * carrinho.getProdutos().get(i).getQtdPontos();
                pontosTotais += pontosProdutos;

                bw.write("\n" + carrinho.getProdutos().get(i).getNome() + "\t" + carrinho.getQtdProduto().get(i) + "\t" + precoProdutos + "\t" + pontosProdutos);
                bw.flush();
            }
            bw.write("\nResultado");


            bw.write("\n" + data.getPontosGastos() + "\t" + data.getValorFinal() + "\t" + pontosTotais);


            bw.close();
            fw.close();
        } catch (IOException err) {
            System.out.println(err);
        }



    }

    public void reduzirEstoque () {
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

        for (int i = 0; i < carrinho.getProdutos().size(); i++) {
            carrinho.getProdutos().get(i).setQuantidadeEmEstoque(carrinho.getProdutos().get(i).getQuantidadeEmEstoque() - carrinho.getQtdProduto().get(i));
            listaProdutos.clear();

            File arquivo = new File("./src/main/resources/produtos/" + carrinho.getProdutos().get(i).getCategoria() + ".txt");

            try {
                FileReader fr = new FileReader(arquivo);
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

            for(int j = 0; j < listaProdutos.size(); j++) {
                if (listaProdutos.get(j).getNome().equals(carrinho.getProdutos().get(i).getNome())) {
                    listaProdutos.set(j, carrinho.getProdutos().get(i));


                    try {
                        FileWriter fw = new FileWriter(arquivo, false);
                        BufferedWriter bw = new BufferedWriter(fw);

                        boolean primeiraLinha = true;

                        for (int k = 0; k < listaProdutos.size(); k++) {

                            if (primeiraLinha) {
                                bw.write(listaProdutos.get(k).getNome() + "\t" + listaProdutos.get(k).getCategoria() + "\t" + listaProdutos.get(k).getSubCategoria() + "\t" + listaProdutos.get(k).getPreço() + "\t" + listaProdutos.get(k).getDesconto() + "\t" + listaProdutos.get(k).getQuantidadeEmEstoque() + "\t" + listaProdutos.get(k).getQtdPontos() + "\t" + listaProdutos.get(k).getImg());
                                primeiraLinha = false;
                            } else {
                                bw.write("\n" + listaProdutos.get(k).getNome() + "\t" + listaProdutos.get(k).getCategoria() + "\t" + listaProdutos.get(k).getSubCategoria() + "\t" + listaProdutos.get(k).getPreço() + "\t" + listaProdutos.get(k).getDesconto() + "\t" + listaProdutos.get(k).getQuantidadeEmEstoque() + "\t" + listaProdutos.get(k).getQtdPontos() + "\t" + listaProdutos.get(k).getImg());
                            }

                            bw.flush();

                        }
                        bw.close();
                        fw.close();
                    } catch (IOException err) {
                        System.out.println(err);
                    }




                    break;
                }



            }

        }





    }
}
