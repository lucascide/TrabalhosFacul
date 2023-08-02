package com.example.sistemadeecommerce;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    List<Integer> qtdProduto = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void adicionarProduto(Produto produto) {

        if(produtos.contains(produto)) {
            qtdProduto.set(produtos.indexOf(produto), qtdProduto.get(produtos.indexOf(produto)) + 1);
        } else {
            produtos.add(produto);
            qtdProduto.add(1);
        }

    }

    public List<Integer> getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(List<Integer> qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
}
