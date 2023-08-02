package com.example.sistemadeecommerce;

import java.util.ArrayList;

public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();

    private String userName;
    private Produto produto;
    private CarrinhoDeCompras carrinho;
    private long pontosGastos;
    private double valorFinal;
    private ArrayList<Produto> arrayListProdutos;
    private DataSingleton() {};

    public static DataSingleton getInstance() {
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ArrayList<Produto> getArrayListProdutos() {
        return arrayListProdutos;
    }

    public void setArrayListProdutos(ArrayList<Produto> arrayListProdutos) {
        this.arrayListProdutos = arrayListProdutos;
    }

    public long getPontosGastos() {
        return pontosGastos;
    }

    public void setPontosGastos(long pontosGastos) {
        this.pontosGastos = pontosGastos;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public CarrinhoDeCompras getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDeCompras carrinho) {
        this.carrinho = carrinho;
    }


}
