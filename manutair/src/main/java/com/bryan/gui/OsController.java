package com.bryan.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.bryan.dao.ContratoDao;
import com.bryan.dao.EquipamentoDao;
import com.bryan.dao.OsDao;
import com.bryan.dao.Persistencia;
import com.bryan.dao.TecnicoDao;
import com.bryan.model.Contrato;
import com.bryan.model.Equipamento;
import com.bryan.model.Os;
import com.bryan.model.Tecnico;
import com.bryan.model.TipoEstado;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OsController implements Initializable{
    private Os os;
    private Boolean alterando;
    private Persistencia<Contrato> contratoDao = new ContratoDao();
    private Persistencia<Tecnico> tecnicoDao = new TecnicoDao();
    private Persistencia<Equipamento> equipamentoDao = new EquipamentoDao();
    private Persistencia<Os> osDao = new OsDao();


    @FXML
    private TextField TxtId;
    @FXML
    private TextField TxtEndereco;
    @FXML
    private TextField TxtDia;
    @FXML
    private TextField TxtHora;
    @FXML
    private TextArea TxtDescricao;

    @FXML
    private ComboBox<Contrato> CboContratos;
    @FXML
    private ComboBox<Tecnico> CboTecnicos;
    @FXML
    private ComboBox<Equipamento> CboEquipamentos;
    @FXML
    private ComboBox<TipoEstado> CboEstados;

    @FXML
    private Button BtnIncluir;
    @FXML
    private Button BtnExcluir;
    @FXML
    private Button BtnAlterar;
    @FXML
    private Button BtnMudarEstado;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnGravar;

    @FXML
    private ListView<Os> LstOs;

    private void preencherLista() {
        try {
            ObservableList<Os> os = FXCollections.observableArrayList(osDao.getDados());
            LstOs.setItems(os);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    private void preencherCombo() {
        try{
            
            CboEstados.getItems().setAll(TipoEstado.values());

            ObservableList<Tecnico> tecnicos = FXCollections.observableArrayList(tecnicoDao.getDados());
            CboTecnicos.setItems(tecnicos);

            ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList(equipamentoDao.getDados());
            CboEquipamentos.setItems(equipamentos);
            
            ObservableList<Contrato> contratos = FXCollections.observableArrayList(contratoDao.getDados());
            CboContratos.setItems(contratos);
        }catch (Exception e) {
            e.getCause().printStackTrace();
        }

    }

    private void exibirDados() {
        os = LstOs.getSelectionModel().getSelectedItem();
        TxtId.setText(os.getId().toString());
        TxtEndereco.setText(os.getEndereco());
        TxtDia.setText(os.getDia());
        TxtHora.setText(os.getHora());
        TxtDescricao.setText(os.getDescricao());
        
        CboContratos.getSelectionModel().getSelectedItem();
        CboTecnicos.getSelectionModel().getSelectedItem();
        CboEquipamentos.getSelectionModel().getSelectedItem();
        CboEstados.getSelectionModel().getSelectedItem();
        
    }
    private void limparCampo(){
        TxtEndereco.setText("");
        TxtDia.setText("");
        TxtHora.setText("");
        TxtDescricao.setText("");
    }
    private void habilitarInterface(Boolean edicao) {
        TxtEndereco.setEditable(edicao);
        TxtDia.setEditable(edicao);
        TxtHora.setEditable(edicao);
        TxtDescricao.setEditable(edicao);

        CboContratos.setDisable(!edicao);
        CboTecnicos.setDisable(!edicao);
        CboEquipamentos.setDisable(!edicao);
        CboEstados.setDisable(!edicao);

        BtnGravar.setDisable(!edicao);
        BtnCancelar.setDisable(!edicao);
        BtnIncluir.setDisable(edicao);
        BtnAlterar.setDisable(edicao);
        BtnExcluir.setDisable(edicao);
        BtnMudarEstado.setDisable(edicao);
    }

    @FXML
    private void BtnGravar_Action(ActionEvent event){
        os.setEndereco(TxtEndereco.getText());
        os.setDia(TxtDia.getText());
        os.setHora(TxtHora.getText());
        os.setDescricao(TxtDescricao.getText());
        
        try {
            if (alterando) {
                osDao.alterar(os);
            } else {
                osDao.gravar(os);
                TxtId.setText(os.getId().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        preencherLista();
        habilitarInterface(false);
    }
    @FXML
    private void BtnExcluir_Action(ActionEvent event){
        try {
            os = LstOs.getSelectionModel().getSelectedItem();
            osDao.excluir(os);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        preencherLista();
    }
    @FXML
    private void BtnIncluir_Action(ActionEvent event){
        alterando = false;
        limparCampo();
        habilitarInterface(true);
        this.os = new Os();
        TxtEndereco.requestFocus();
        CboEstados.getSelectionModel().select(0);
    }
    @FXML
    private void BtnAlterar_Action(ActionEvent event){
        habilitarInterface(true);
        exibirDados();
    }
    @FXML
    private void BtnMudarEstado_Action(ActionEvent event){
        exibirDados();
        //habilitarInterface(true);
        CboEstados.setEditable(true);
        CboEstados.setDisable(false);
        BtnGravar.setDisable(false);
        BtnCancelar.setDisable(false);
    }
    @FXML
    private void BtnCancelar_Action(ActionEvent event){
        limparCampo();
        habilitarInterface(false);
    }
    @FXML
    private void CboContratos_Action(ActionEvent event){
        os.setContrato(CboContratos.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void CboTecnicos_Action(ActionEvent event){
        os.setTecnico(CboTecnicos.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void CboEquipamentos_Action(ActionEvent event){
        os.setEquipamento(CboEquipamentos.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void CboEstados_Action(ActionEvent event){
        os.setTipoEstado(CboEstados.getSelectionModel().getSelectedItem());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        habilitarInterface(false);
        preencherCombo();
        preencherLista();
        
    }
}