package com.example.sistemadeecommerce;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.*;

public class ContaController implements Initializable {

    @FXML
    private static Cliente client;

    @FXML
    private Label aviso1, aviso2, aviso3, aviso4, aviso5, aviso6, aviso7, aviso8;

    @FXML
    private TextField nome, novoNome, novoCPF, novaIdade;

    @FXML
    private PasswordField senha, novaSenha, repeteSenha;

    @FXML
    private RadioButton cliente, admin, novoCliente, novoAdmin;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private Button botao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(aviso1 != null){

            aviso1.setVisible(false);
            aviso2.setVisible(false);

        }
        else if(aviso3 != null){

            aviso3.setVisible(false);
            aviso4.setVisible(false);
            aviso5.setVisible(false);
            aviso6.setVisible(false);
            aviso7.setVisible(false);
            aviso8.setVisible(false);

        }

    }

    public static Cliente getClient() {

        return client;

    }

    @FXML
    public int verficarLogin(ActionEvent e) throws IOException{

        aviso1.setVisible(false);
        aviso2.setVisible(false);

        botao = (Button)e.getSource();

        String linha;

        boolean verif1;
        boolean verif2;

        File arquivo;

        if(verif2 = cliente.isSelected()){

            arquivo = new File("./src/main/resources/cadastros/clientesCadastrados.txt");

        }
        else if(admin.isSelected()){

            arquivo = new File("./src/main/resources/cadastros/adminsCadastrados.txt");

        }
        else{

            botao.setOnMouseClicked(event -> {

                aviso1.setVisible(true);

            });

            return -1;

        }

        FileReader fr = new FileReader(arquivo);

        BufferedReader br = new BufferedReader(fr);

        linha = br.readLine();

        while(verif1 = (linha != null)){

            String atributos[] = linha.split("\t");

            String nome = atributos[0];
            String cpf = atributos[1];
            int idade = Integer.parseInt(atributos[2]);
            int pontos = 0;
            String senha;

            if(verif2){

                pontos = Integer.parseInt(atributos[3]);
                senha = atributos[4];


            }
            else{

                senha = atributos[3];

            }

            if(this.nome.getText().equals(nome) && this.senha.getText().equals(senha)){

                client = new Cliente(nome, cpf, idade, pontos, senha);

                break;

            }

            linha = br.readLine();

        }

        br.close();
        fr.close();

        if(!verif1){

            botao.setOnMouseClicked(event -> {

                aviso2.setVisible(true);

            });

            return -1;

        }
        else{

            stage = (Stage) botao.getScene().getWindow();

            if(verif2){

                stage.setTitle("pagina do cliente");
                client.setAdmin(false);

            }
            else{

                stage.setTitle("pagina do administrador");
                client.setAdmin(true);

            }

        }

        root = FXMLLoader.load(getClass().getResource("Menu2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return 0;

    }

    @FXML
    public int cadastrar(ActionEvent e) throws IOException{

        aviso3.setVisible(false);
        aviso4.setVisible(false);
        aviso5.setVisible(false);
        aviso6.setVisible(false);
        aviso7.setVisible(false);
        aviso8.setVisible(false);

        botao = (Button)e.getSource();
        stage = (Stage) botao.getScene().getWindow();

        File arquivo;

        boolean verify;

        if(verify = novoCliente.isSelected()){

            arquivo = new File("./src/main/resources/cadastros/clientesCadastrados.txt");

        }
        else if(novoAdmin.isSelected()){

            arquivo = new File("./src/main/resources/cadastros/adminsCadastrados.txt");

        }
        else{

            botao.setOnMouseClicked(event ->{

                aviso8.setVisible(true);

            });

            return -1;

        }

        if(novoNome.getText().equals("")){

            botao.setOnMouseClicked(event -> {

                aviso3.setVisible(true);

            });

            return -1;

        }
        else if(novoCPF.getText().equals("")){

            botao.setOnMouseClicked(event -> {

                aviso4.setVisible(true);

            });

            return -1;

        }
        else if(novaIdade.getText().equals("")){

            botao.setOnMouseClicked(event -> {

                aviso5.setVisible(true);

            });

            return -1;

        }
        else if(novaSenha.getText().equals("")){

            botao.setOnMouseClicked(event -> {

                aviso6.setVisible(true);

            });

            return -1;

        }
        else if(!repeteSenha.getText().equals(novaSenha.getText())){

            botao.setOnMouseClicked(event -> {

                aviso7.setVisible(true);

            });

            return -1;

        }

        String nome = novoNome.getText();
        String cpf = novoCPF.getText();
        int idade = Integer.parseInt(novaIdade.getText());
        String senha = novaSenha.getText();

        client = new Cliente(nome, cpf, idade, 0, senha);

        FileWriter fw = new FileWriter(arquivo, true);

        if(verify){

            stage.setTitle("pagina do cliente");
            client.setAdmin(false);
            fw.write(nome + "\t" + cpf + "\t" + novaIdade.getText() + "\t" + "0" + "\t" + senha + "\n");
            fw.close();

            arquivo = new File("./src/main/resources/comprasClientes/" + client.getNome() + ".txt");
            fw = new FileWriter(arquivo);
            fw.write("");

        }
        else{

            stage.setTitle("pagina do administrador");
            client.setAdmin(true);
            fw.write(nome + "\t" + cpf + "\t" + novaIdade.getText() + "\t" + senha + "\n");

        }

        fw.close();

        root = FXMLLoader.load(getClass().getResource("Menu2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return 0;

    }

    @FXML
    public void retornar(ActionEvent e) throws  IOException{

        botao = (Button)e.getSource();
        stage = (Stage)botao.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Menu1.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
