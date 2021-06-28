package com.bryan.model;

public enum TipoEstado {
    Aberta("Em Aberto"),
    EmAndamento("Em andamento"),
    Concluida("Concluído");
    
    private String nome;
    TipoEstado (String nome) {
        this.nome = nome;
    }
    @Override
    public String toString () {
        return nome;
    }
}