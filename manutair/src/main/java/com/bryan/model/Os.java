package com.bryan.model;

public class Os {
    private Long id;
    private String descricao;
    private String endereco;
    private String dia;
    private String hora;
    private TipoEstado tipoEstado;
    private Tecnico tecnico;
    private Equipamento equipamento;
    private Contrato contrato;
    
    
    public Tecnico getTecnico() {
        return tecnico;
    }
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    public Contrato getContrato() {
        return contrato;
    }
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public TipoEstado getTipoEstado() {
        return tipoEstado;
    }
    public void setTipoEstado(TipoEstado tipoEstado) {
        this.tipoEstado = tipoEstado;
    }
    @Override
    public String toString() {
        return "contrato: " + contrato + ", Descricao: " + descricao + ", dia: " + dia + ", endereco: " + endereco
                + ", equipamento: " + equipamento + ", hora: " + hora + ", id: " + id + ", tecnico: " + tecnico
                + ", Estado:" + tipoEstado ;
    }
    

}
