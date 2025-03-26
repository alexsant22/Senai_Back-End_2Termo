package com.example.aula1Spring;

public class User {
    // Attributes for class
    private int id;
    private String nome;
    private String sobrenome;

    public User(int id, String nome, String sobrenome) { // Bob constructor with parameters
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
