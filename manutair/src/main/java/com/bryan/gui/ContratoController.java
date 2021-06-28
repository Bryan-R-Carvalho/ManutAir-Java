package com.bryan.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.bryan.dao.ContratoDao;
import com.bryan.dao.EquipamentoDao;
import com.bryan.dao.Persistencia;
import com.bryan.dao.PessoaDao;
import com.bryan.model.Contrato;
import com.bryan.model.Equipamento;
import com.bryan.model.Pessoa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ContratoController implements Initializable{
    private Contrato contrato;
    private Boolean alterando;
    private Persistencia<Contrato> contratoDao = new ContratoDao();
    private Persistencia<Pessoa> pessoaDao = new PessoaDao();
    private Persistencia<Equipamento> equipamentoDao = new EquipamentoDao();

    @FXML
    private TextField TxtId;
    @FXML
    private TextField TxtDataInicio;
    @FXML
    private TextField TxtPrazo;

    @FXML
    private ComboBox<Pessoa> CboClientes;
    @FXML
    private ComboBox<Equipamento> CboEquipamentos;

    @FXML
    private ListView<Contrato> LstContratos;
    @FXML
    private ListView<Equipamento> LstEquipamentos;

    @FXML
    private Button BtnGravar;
    @FXML
    private Button BtnAlterar;
    @FXML
    private Button BtnIncluir;
    @FXML
    private Button BtnExcluir;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnRetiraItem;
    @FXML
    private Button BtnAdd; //adicionar equipamento

    private void limparCampo(){
        TxtId.setText("");
        TxtDataInicio.setText("");
        TxtPrazo.setText("");
        CboClientes.getSelectionModel().clearSelection();
        CboEquipamentos.getSelectionModel().clearSelection();
        LstEquipamentos.getItems().clear();
    }
    
    private void habilitarInterface(Boolean edicao) {
        TxtDataInicio.setEditable(edicao);
        TxtPrazo.setEditable(edicao);
        CboClientes.setDisable(!edicao);
        CboEquipamentos.setDisable(!edicao);
        BtnGravar.setDisable(!edicao);
        BtnCancelar.setDisable(!edicao);
        BtnIncluir.setDisable(edicao);
        BtnAlterar.setDisable(edicao);
        BtnExcluir.setDisable(edicao);
        BtnAdd.setDisable(!edicao);
        BtnRetiraItem.setDisable(!edicao);
    }

    @FXML
    private void BtnGravar_Action(ActionEvent event){
        contrato.setDataInicio(TxtDataInicio.getText());
        contrato.setPrazo(TxtPrazo.getText());
        
        try {
            if (alterando) {
                contratoDao.alterar(contrato);
            } else {
                contratoDao.gravar(contrato);
                TxtId.setText(contrato.getId().toString());
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
            return;
        }
        limparCampo();
        preencherLista();
        habilitarInterface(false);
    }

    @FXML
    private void BtnAlterar_Action(ActionEvent event){
        alterando = true;
        habilitarInterface(true);
        contrato = LstContratos.getSelectionModel().getSelectedItem();
        TxtDataInicio.requestFocus();
        exibirDados();
    }

    @FXML
    private void BtnIncluir_Action(ActionEvent event){
        alterando = false;
        this.contrato = new Contrato();
        limparCampo();
        preencherEquipamentos();
        habilitarInterface(true);
        TxtDataInicio.requestFocus();
    }
    
    @FXML
    private void BtnExcluir_Action(ActionEvent event){
        try {
            contrato = LstContratos.getSelectionModel().getSelectedItem();
            contratoDao.excluir(contrato);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        preencherLista();
    }

    @FXML
    private void BtnCancelar_Action(ActionEvent event){
        limparCampo();
        habilitarInterface(false);
    }

    @FXML
    private void BtnAdd_Action(ActionEvent event){

        Equipamento equipamento = CboEquipamentos.getSelectionModel().getSelectedItem();
        contrato.adicionarEquipamento(equipamento);
        preencherEquipamentos();
    }
    @FXML
    private void BtnRetiraItem_Action(ActionEvent event){
        LstEquipamentos.getItems().clear();
        //preencherEquipamentos();
    }

    @FXML
    private void CboClientes_Action(ActionEvent event){
        contrato.setPessoa(CboClientes.getSelectionModel().getSelectedItem());
    }
    
    private void preencherCombo() {
        try {
            ObservableList<Pessoa> clientes = FXCollections.observableArrayList(pessoaDao.getDados());
            CboClientes.setItems(clientes);

            ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList(equipamentoDao.getDados());
            CboEquipamentos.setItems(equipamentos);
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
        
    }

    private void preencherLista() {
        try {
            ObservableList<Contrato> contratos = FXCollections.observableArrayList(contratoDao.getDados());
            LstContratos.setItems(contratos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void preencherEquipamentos() {
        ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList(contrato.getEquipamentos());
        LstEquipamentos.setItems(equipamentos);
    }

    private void exibirDados() {
        contrato = LstContratos.getSelectionModel().getSelectedItem();
        TxtId.setText(contrato.getId().toString());
        TxtDataInicio.setText(contrato.getDataInicio());
        TxtPrazo.setText(contrato.getPrazo());
        CboClientes.getSelectionModel().getSelectedItem();
        CboEquipamentos.getSelectionModel().getSelectedItem();
        preencherEquipamentos();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherCombo();
        preencherLista();        
        habilitarInterface(false);
    }
    
}