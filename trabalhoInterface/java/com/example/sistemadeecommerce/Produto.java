package com.example.sistemadeecommerce;

public class Produto {
    private String nome;
    private String categoria;
    private String subCategoria;


    private double preço;
    private int desconto;
    private int quantidadeEmEstoque;

    private long qtdPontos;

    private String img;

    public Produto(String nome, double preço, long qtdPontos){
        this.nome = nome;
        this.preço = preço;
        this.qtdPontos = qtdPontos;
    }

    public Produto(String nome, String categoria, String subCategoria, double preço, int desconto, int quantidadeEmEstoque, String img) {
        this.nome = nome;
        this.categoria = categoria;
        this.preço = preço;
        this.desconto = desconto;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.img = img;
        this.subCategoria = subCategoria;
    }

    public void calcularQtdPontos(){
        long precoArredondado =  Math.round(preço - (preço * desconto/100));
        qtdPontos = ((precoArredondado - (precoArredondado % 10)) / 10) * 5 ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public long getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(int qtdPontos) {
        this.qtdPontos = qtdPontos;
    }
}
