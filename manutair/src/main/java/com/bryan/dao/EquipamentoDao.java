package com.bryan.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bryan.model.Equipamento;

public class EquipamentoDao extends Dao implements Persistencia<Equipamento> {

    @Override
    public void gravar(Equipamento equipamento) throws Exception{
        String sql = "Insert into equipamento (marca, modelo, numeroserie) values (?, ?, ?)";
        PreparedStatement ps =  getConexao().prepareStatement(sql);
        ps.setString(1, equipamento.getMarca());
        ps.setString(2, equipamento.getModelo());
        ps.setString(3, equipamento.getNumeroSerie());
        ps.executeUpdate();
    }
    @Override
    public void excluir(Equipamento equipamento) throws Exception {
        String sql = "delete from equipamento where numeroserie = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, equipamento.getNumeroSerie());
        ps.executeUpdate();

    }
    @Override
    public List<Equipamento> getDados() throws Exception {
        String sql = "select * from equipamento order by marca";
        PreparedStatement ps = getConexao().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<Equipamento> equipamentos= new ArrayList<Equipamento>();

        while (rs.next()){
            Equipamento equipamento = new Equipamento();
            equipamento.setMarca(rs.getString("marca"));
            equipamento.setModelo(rs.getString("modelo"));
            equipamento.setNumeroSerie(rs.getString("numeroserie"));

            equipamentos.add(equipamento);
        }
        return equipamentos;
    }


    @Override
    public void alterar(Equipamento dado) throws Exception {
        String sql = "update equipamento set marca = ?, modelo = ? where numeroserie = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, dado.getMarca());
        ps.setString(2, dado.getModelo());
        ps.setString(3, dado.getNumeroSerie());
        ps.executeUpdate();
        
    }

    
}
