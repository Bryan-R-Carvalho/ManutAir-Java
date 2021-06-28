package com.bryan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bryan.model.Contrato;
import com.bryan.model.Os;

public class OsDao extends Dao implements Persistencia<Os>{

    @Override
    public void gravar(Os dado) throws Exception {
        try{
            String sql = "insert into os (descricao, endereco, dia, hora, contrato_id, equipamento_numero, tecnico_id, estado) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dado.getDescricao());
            ps.setString(2, dado.getEndereco());
            ps.setString(3, dado.getDia());
            ps.setString(4, dado.getHora());
            ps.setLong(5, dado.getContrato().getId());
            ps.setString(6, dado.getEquipamento().getNumeroSerie());
            ps.setLong(7, dado.getTecnico().getId());
            ps.setString(8, dado.getTipoEstado().toString());
            
            ps.executeUpdate();
    
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dado.setId(rs.getLong("id"));
            
            }
            } catch (Exception e) {
                e.printStackTrace();
                
            }   
    }

    @Override
    public List<Os> getDados() throws Exception {
        String sql = "select * from os order by id";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Os> ordens = new ArrayList<Os>();
        while (rs.next()) {
            Os os = new Os();
            os.setId(rs.getLong("id"));
            os.setEndereco(rs.getString("endereco"));
            os.setDia(rs.getString("dia"));
            os.setHora(rs.getString("hora"));
            os.setDescricao(rs.getString("descricao"));


            sql = "select  contrato.id " 
                + " from contrato left join os on contrato.id = os.contrato_id "
                + "where os.contrato_id = ?";
                
            PreparedStatement psdado = getConexao().prepareStatement(sql);
            psdado.setLong(1, os.getId());
            ResultSet rsDado = psdado.executeQuery();
            while (rsDado.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rsDado.getInt("id"));
                contrato.setDataInicio(rsDado.getString("data_inicio"));
                contrato.setPrazo(rsDado.getString("prazo"));
                os.setContrato(contrato);
            }
            ordens.add(os);
        }
        return ordens;
    }

    @Override
    public void alterar(Os dado) throws Exception {
        String sql = "update os set endereco = ?, descricao = ?, dia = ?, hora = ?, equipamento_numero = ?, contrato_id = ?, tecnico = ?,  estado = ? where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, dado.getEndereco());
        ps.setString(2, dado.getDescricao());
        ps.setString(3, dado.getDia());
        ps.setString(4, dado.getHora());
        ps.setString(5, dado.getEquipamento().getNumeroSerie());
        ps.setLong(6, dado.getContrato().getId());
        ps.setLong(7, dado.getTecnico().getId());
        ps.setString(8, dado.getTipoEstado().toString());
        ps.setLong(9, dado.getId());

        ps.executeUpdate();
        
    }

    @Override
    public void excluir(Os dado) throws Exception {
        String sql = "delete from os where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setLong(1, dado.getId());
        ps.executeUpdate();


    }

}
