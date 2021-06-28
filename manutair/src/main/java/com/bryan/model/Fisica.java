package com.bryan.model;

public class Fisica extends Pessoa{
    private String Cpf;
    private String Nome;
    
    public String getCpf() {
        return Cpf;
    }
    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Cpf == null) ? 0 : Cpf.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fisica other = (Fisica) obj;
        if (Cpf == null) {
            if (other.Cpf != null)
                return false;
        } else if (!Cpf.equals(other.Cpf))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Nome: " + Nome + " CPF: " + Cpf;
    }
    
}
