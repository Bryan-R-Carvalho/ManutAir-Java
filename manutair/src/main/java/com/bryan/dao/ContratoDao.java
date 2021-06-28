package com.bryan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bryan.model.Contrato;
import com.bryan.model.Equipamento;


public class ContratoDao extends Dao implements Persistencia<Contrato> {

    @Override
    public void gravar(Contrato dado) throws Exception {
        try{
            String sql = "insert into contrato (data_inicio, prazo, pessoa_id) values (?, ?, ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dado.getDataInicio());
            ps.setString(2, dado.getPrazo());
            ps.setLong(3, dado.getPessoa().getId());

            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dado.setId(rs.getInt("id"));
            }

            System.out.println("Equipamento: " );
            for (Equipamento equipamento: dado.getEquipamentos()) {
                sql = "insert into equipamentocontrato (equipamento_numero, contrato_id) values (?, ?)";
                PreparedStatement psequipamento = getConexao().prepareStatement(sql);
                psequipamento.setString(1, equipamento.getNumeroSerie());
                psequipamento.setInt(2, dado.getId());
                psequipamento.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
            
    }

    
    @Override
    public List<Contrato> getDados() throws Exception {
        String sql = "select * from contrato order by id";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Contrato> contratos = new ArrayList<Contrato>();
        while (rs.next()) {
            Contrato contrato = new Contrato();
            contrato.setId(rs.getInt("id"));
            contrato.setDataInicio(rs.getString("data_inicio"));
            contrato.setPrazo(rs.getString("prazo"));

            sql = "select  equipamento.marca, equipamento.modelo, equipamento.numeroserie " 
                + " from equipamento inner join equipamentocontrato on equipamento.numeroserie = equipamentocontrato.equipamento_numero "
                +"where equipamentocontrato.contrato_id = ?";
            PreparedStatement psdado = getConexao().prepareStatement(sql);
            psdado.setLong(1, contrato.getId());
            ResultSet rsDado = psdado.executeQuery();
            while (rsDado.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setMarca(rsDado.getString("marca"));
                equipamento.setModelo(rsDado.getString("modelo"));
                equipamento.setNumeroSerie(rsDado.getString("numeroserie"));
                contrato.adicionarEquipamento(equipamento);
            }
            contratos.add(contrato);
        }
        return contratos;
    }

    @Override
    public void alterar(Contrato dado) throws Exception {
        String sql = "update contrato set data = ?, prazo = ? where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, dado.getDataInicio());
        ps.setString(2, dado.getPrazo());
        ps.setLong(3, dado.getId());

        ps.executeUpdate();

        sql = "delete from equipamentocontrato where contrato_id = ?";
        ps = getConexao().prepareStatement(sql);
        ps.setLong(1, dado.getId());
        ps.executeUpdate();

        for (Equipamento equipamento: dado.getEquipamentos()) {
            sql = "insert into equipamentocontrato (equipamento_numero, contrato_id) values (?, ?)";
            PreparedStatement psequipamento = getConexao().prepareStatement(sql);
            psequipamento.setString(1, equipamento.getNumeroSerie());
            psequipamento.setLong(2, dado.getId());
            psequipamento.executeUpdate();
        }   

    }

    @Override
    public void excluir(Contrato dado) throws Exception {
        String sql = "delete from equipamentocontrato where contrato_id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setLong(1, dado.getId());
        ps.executeUpdate();

        sql = "delete from os where contrato_id = ?";
        ps = getConexao().prepareStatement(sql);
        ps.setLong(1, dado.getId());
        ps.executeUpdate();

        sql = "delete from contrato where id = ?";
        ps = getConexao().prepareStatement(sql);
        ps.setLong(1, dado.getId());
        ps.executeUpdate();

        
    }
        
}