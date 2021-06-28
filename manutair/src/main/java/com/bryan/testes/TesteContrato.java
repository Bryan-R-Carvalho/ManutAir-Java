package com.bryan.testes;

import com.bryan.dao.ContratoDao;
import com.bryan.model.Contrato;
import com.bryan.model.Equipamento;
import com.bryan.model.Pessoa;

public class TesteContrato {
    public static void main(String[] args){
        gravarContrato("20","38","1","2230");
        //excluirContrato("b2230");
        //listarContrato("b2230");
    }
    
    private static void excluirContrato(String numeroSerie){
        Contrato contrato = new Contrato();
        contrato.setNumeroSerie(numeroSerie);

        try{
            new ContratoDao().excluir(equipamento);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void gravarContrato(String data,String prazo,Pessoa pessoa,Equipamento equip){
        Contrato contrato = new Contrato();
        contrato.setDataInicio(data);
        contrato.setPrazo(prazo);
        contrato.setPessoa(pessoa);
        contrato.setEquipamentos(equip);

        try{
            new ContratoDao().gravar(contrato);
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(contrato.getNumeroSerie());
    }

    private static void listarEquipamento(String string) {
        List<contrato> equipamentos = new ArrayList<Equipamento>();

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
