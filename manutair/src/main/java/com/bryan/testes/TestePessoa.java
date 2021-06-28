package com.bryan.testes;

import com.bryan.dao.PessoaDao;
import com.bryan.model.Fisica;

public class TestePessoa {
    public static void main(String[] args){
        //testarPessoa("rua","2222222","156348275","andre");
        excluirPessoa("1");
        //listarPessoa("b2230");
    }
    


private static void excluirPessoa(String id){
    Fisica fisica = new Fisica();
    fisica.setCpf(id);

    try{
        new PessoaDao().excluir(fisica);
    }catch(Exception e){
        e.printStackTrace();
    }
}

private static void testarPessoa( String endereco, String telefone, String documento,String nome){
    Fisica fisica = new Fisica();
    fisica.setEndereco(endereco);
    fisica.setTelefone(telefone);
    fisica.setCpf(documento);
    fisica.setNome(nome);

    try{
        new PessoaDao().gravar(fisica);
    }catch(Exception e){
        e.printStackTrace();
    }
    System.out.println(fisica.getCpf());
}

/*private static void listarEquipamento(String string) {
    List<Equipamento> equipamentos = new ArrayList<Equipamento>();

    try{
        equipamentos = new EquipamentoDao().getEquipamentos();
    }catch(Exception e){
        e.printStackTrace();
    }
    for (Equipamento a: equipamentos) {
        System.out.println(a.getMarca().toString() + " - " + a.getModelo() + " - " + a.getNumeroSerie());
    }
    
}*/
}
