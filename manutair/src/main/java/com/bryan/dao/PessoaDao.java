package com.bryan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bryan.model.Fisica;
import com.bryan.model.Juridica;
import com.bryan.model.Pessoa;

public class PessoaDao extends Dao implements Persistencia<Pessoa>{
    @Override
    public void gravar(Pessoa dado) throws Exception {
        if (dado instanceof Fisica) {
            Fisica fisica = (Fisica) dado;
            gravarFisica(fisica);
        } else {
            Juridica juridica = (Juridica) dado;
            gravarJuridica(juridica);
        }
    }

    private void gravarFisica(Fisica fisica) throws Exception{
        String sql = "insert into pessoa (id, endereco, telefone, documento, nome, tipo) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setLong(1, fisica.getId());
        ps.setString(2, fisica.getEndereco());
        ps.setString(3, fisica.getTelefone());
        ps.setString(4, fisica.getCpf());
        ps.setString(5, fisica.getNome());
        ps.setString(6, "fisica");

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            fisica.setId(rs.getLong("id"));
        }

        ps.executeUpdate();

    }

    private void gravarJuridica(Juridica juridica) throws Exception{
        String sql = "insert into pessoa (id,endereco, telefone, documento, razaosocial, nome, tipo) values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setLong(1, juridica.getId());
        ps.setString(2, juridica.getEndereco());
        ps.setString(3, juridica.getTelefone());
        ps.setString(4, juridica.getCnpj());
        ps.setString(5, juridica.getRazaoSocial());
        ps.setString(6, juridica.getNomeResponsavel());
        ps.setString(7, "juridica");

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            juridica.setId(rs.getLong("id"));
        }

        ps.executeUpdate();

    }

    @Override
    public void excluir(Pessoa pessoa) throws Exception {
        if (pessoa instanceof Fisica) {
            Fisica fisica = (Fisica) pessoa;
            excluirFisica(fisica);
        } else {
            Juridica juridica = (Juridica) pessoa;
            excluirJuridica(juridica);
        }

    }
    private void excluirFisica(Fisica fisica) throws Exception{
        String sql = "delete from pessoa where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setLong(1, fisica.getId());
        ps.executeUpdate();

    }

    private void excluirJuridica(Juridica juridica) throws Exception{
        String sql = "delete from pessoa where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setLong(1, juridica.getId());
        ps.executeUpdate();

    }

    @Override
    public List<Pessoa> getDados() throws Exception {
        String sql = "select * from pessoa order by nome ";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Pessoa> pessoas= new ArrayList<Pessoa>();

        while (rs.next()){
            if (rs.getString("tipo").equals("fisica")) {
                Fisica fisica = new Fisica();
                fisica.setId(rs.getLong("id"));
                fisica.setEndereco(rs.getString("endereco"));
                fisica.setTelefone(rs.getString("telefone"));
                fisica.setCpf(rs.getString("documento"));
                fisica.setNome(rs.getString("nome"));
                pessoas.add(fisica);
            } else {
                Juridica juridica = new Juridica();
                juridica.setId(rs.getLong("id"));
                juridica.setEndereco(rs.getString("endereco"));
                juridica.setTelefone(rs.getString("telefone"));
                juridica.setCnpj(rs.getString("documento"));
                juridica.setRazaoSocial(rs.getString("razaosocial"));
                juridica.setNomeResponsavel(rs.getString("nome"));
                pessoas.add(juridica);
            }
        
        }
        return pessoas;
    }

    @Override
    public void alterar(Pessoa dado) throws Exception {
        if (dado instanceof Fisica) {
            Fisica fisica = (Fisica) dado;
            alterarFisica(fisica);
        } else {
            Juridica juridica = (Juridica) dado;
            alterarJuridica(juridica);
        }

    }

    private void alterarFisica(Fisica fisica) throws Exception {
        String sql = "update pessoa set endereco = ?, telefone = ?, nome = ? where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, fisica.getEndereco());
        ps.setString(2, fisica.getTelefone());
        ps.setString(3, fisica.getNome());
        ps.setLong(4, fisica.getId());
        
        ps.executeUpdate();
    }

    private void alterarJuridica(Juridica juridica) throws Exception {
        String sql = "update pessoa set endereco = ?, telefone = ?, nome = ?, razaosocial = ? where id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, juridica.getEndereco());
        ps.setString(2, juridica.getTelefone());
        ps.setString(3, juridica.getNomeResponsavel());
        ps.setString(4, juridica.getRazaoSocial());
        ps.setLong(5, juridica.getId());
        ps.executeUpdate();
    }
    
}