package com.bryan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bryan.model.Tecnico;

public class TecnicoDao extends Dao implements Persistencia<Tecnico> {

    @Override
    public void gravar(Tecnico dado) throws Exception {
        String sql = "Insert into tecnico (nome) values (?)";
        PreparedStatement ps =  getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dado.getNome());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            dado.setId(rs.getInt("id"));
        }
    }

    @Override
    public List<Tecnico> getDados() throws Exception {
        String sql = "select * from tecnico order by id";
        PreparedStatement ps = getConexao().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<Tecnico> tecnicos= new ArrayList<Tecnico>();

        while (rs.next()){
            Tecnico tecnico = new Tecnico();
            tecnico.setId(rs.getInt("id"));
            tecnico.setNome(rs.getString("nome"));
            tecnicos.add(tecnico);
        }
        return tecnicos;
    }

    @Override
    public void alterar(Tecnico dado) throws Exception {
        String sql = "update tecnico set nome = ? where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, dado.getNome());
        ps.setInt(2, dado.getId());
        ps.executeUpdate();
    }

    @Override
    public void excluir(Tecnico dado) throws Exception {
        String sql = "delete from tecnico where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, dado.getId());
        ps.executeUpdate();
        
    }
    
}
