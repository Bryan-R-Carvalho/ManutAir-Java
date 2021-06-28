package com.bryan.testes;

import java.util.ArrayList;
import java.util.List;

import com.bryan.dao.EquipamentoDao;
import com.bryan.model.Equipamento;

public class TesteEquipamento {
    public static void main(String[] args){
        testarEquipamento("sony","maq","111111");
        //excluirEquipamento("b2230");
        listarEquipamento("b2230");
    }
    
    private static void excluirEquipamento(String numeroSerie){
        Equipamento equipamento = new Equipamento();
        equipamento.setNumeroSerie(numeroSerie);

        try{
            new EquipamentoDao().excluir(equipamento);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void testarEquipamento(String marca, String modelo, String numeroSerie){
        Equipamento equipamento = new Equipamento();
        equipamento.setMarca(marca);
        equipamento.setModelo(modelo);
        equipamento.setNumeroSerie(numeroSerie);

        try{
            new EquipamentoDao().gravar(equipamento);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(equipamento.getNumeroSerie());
    }

    private static void listarEquipamento(String string) {
        List<Equipamento> equipamentos = new ArrayList<Equipamento>();

        try{
            equipamentos = new EquipamentoDao().getEquipamentos();
        }catch(Exception e){
            e.printStackTrace();
        }
        for (Equipamento a: equipamentos) {
            System.out.println(a.getMarca().toString() + " - " + a.getModelo() + " - " + a.getNumeroSerie());
        }
        
    }
    
}
