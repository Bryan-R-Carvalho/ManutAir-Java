package com.bryan.gui;

import java.net.URL;

import java.util.ResourceBundle;

import com.bryan.dao.EquipamentoDao;
import com.bryan.dao.Persistencia;
import com.bryan.model.Equipamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EquipamentoController implements Initializable {
    private Equipamento equipamento;
    private Boolean alterando;
    private Persistencia<Equipamento> equipamentoDao = new EquipamentoDao();

    @FXML
    private Button BtnGravar;
    @FXML
    private Button BtnExcluir;
    @FXML
    private Button BtnAlterar;
    @FXML
    private Button BtnIncluir;
    @FXML
    private Button BtnCancelar;
    
    @FXML
    private TextField TxtMarca;
    @FXML
    private TextField TxtModelo;
    @FXML
    private TextField TxtNumero;

    @FXML
    private ListView<Equipamento> LstEquipamentos;

    private void preencherLista() {
        try {
            ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList(equipamentoDao.getDados());
            LstEquipamentos.setItems(equipamentos);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    private void limparCampo(){
        TxtMarca.setText("");
        TxtModelo.setText("");
        TxtNumero.setText("");
    }
    private void habilitarInterface(Boolean edicao) {
        TxtMarca.setEditable(edicao);
        TxtModelo.setEditable(edicao);
        TxtNumero.setEditable(edicao);
        BtnGravar.setDisable(!edicao);
        BtnCancelar.setDisable(!edicao);
        BtnIncluir.setDisable(edicao);
        BtnAlterar.setDisable(edicao);
        BtnExcluir.setDisable(edicao);
    }

    @FXML
    private void BtnGravar_Action(ActionEvent event){
        Equipamento equipamento = new Equipamento();
        equipamento.setMarca(TxtMarca.getText());
        equipamento.setModelo(TxtModelo.getText());
        equipamento.setNumeroSerie(TxtNumero.getText());
        try {
            if(alterando){
                equipamentoDao.alterar(equipamento);
            }else{
                equipamentoDao.gravar(equipamento);
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
            return;
        }
        limparCampo();
        habilitarInterface(false);
        TxtNumero.setDisable(false);
        preencherLista();
        
        
    }
    @FXML
    private void BtnExcluir_Action(ActionEvent event) {
        try {
            equipamento = LstEquipamentos.getSelectionModel().getSelectedItem();
            equipamentoDao.excluir(equipamento);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        limparCampo();
        preencherLista();

    }
    @FXML
    private void BtnAlterar_Action(ActionEvent event){
        equipamento = LstEquipamentos.getSelectionModel().getSelectedItem();
        exibirDados();
        habilitarInterface(true);
        TxtNumero.setDisable(true);
        alterando = true;
    }

    private void exibirDados() {
        equipamento = LstEquipamentos.getSelectionModel().getSelectedItem();
        TxtMarca.setText(equipamento.getMarca());
        TxtModelo.setText(equipamento.getModelo());
        TxtNumero.setText(equipamento.getNumeroSerie());
    }
    @FXML
    private void BtnEquipamentos_MouseClicked(MouseEvent event) {
        exibirDados();
    }
    @FXML
    private void BtnIncluir_Action(ActionEvent event) {
        alterando = false;
        limparCampo();
        TxtMarca.requestFocus();
        habilitarInterface(true);
    }
    @FXML
    private void BtnCancelar_Action(ActionEvent event) {
        limparCampo();
        habilitarInterface(false);
        TxtNumero.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
        habilitarInterface(false);
        
    }

}