package com.bryan.model;

public class Equipamento {
    private String marca;
    private String modelo;
    private String numeroSerie;

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numeroSerie == null) ? 0 : numeroSerie.hashCode());
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
        Equipamento other = (Equipamento) obj;
        if (numeroSerie == null) {
            if (other.numeroSerie != null)
                return false;
        } else if (!numeroSerie.equals(other.numeroSerie))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Marca: " + marca + ", modelo: " + modelo + ", Numero de serie: " + numeroSerie;
    }
    
}
