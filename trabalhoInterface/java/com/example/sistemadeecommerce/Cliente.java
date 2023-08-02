package com.example.sistemadeecommerce;

public class Cliente {

    private String nome;
    private String CPF;
    private int idade;
    private int pontos;
    private String senha;
    private boolean isAdmin;

    public Cliente(String nome, String CPF, int idade, int pontos, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.idade = idade;
        this.senha = senha;
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
