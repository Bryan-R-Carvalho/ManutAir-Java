package com.bryan.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.bryan.dao.Persistencia;
import com.bryan.dao.TecnicoDao;
import com.bryan.model.Tecnico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TecnicoController implements Initializable {
    private Tecnico tecnico;
    private Boolean alterando;
    private Persistencia<Tecnico> tecnicoDao = new TecnicoDao();

    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtId;
    @FXML
    private Button BtnGravar;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnIncluir;
    @FXML
    private Button BtnExcluir;
    @FXML
    private Button BtnAlterar;

    @FXML
    private ListView<Tecnico> LstTecnicos;
    
    private void habilitarInterface(Boolean edicao) {
        TxtNome.setEditable(edicao);
        BtnGravar.setDisable(!edicao);
        BtnCancelar.setDisable(!edicao);
        BtnIncluir.setDisable(edicao);
        BtnAlterar.setDisable(edicao);
        BtnExcluir.setDisable(edicao);
    }
    private void exibirDados() {
        tecnico = LstTecnicos.getSelectionModel().getSelectedItem();
        TxtId.setText(tecnico.getId().toString());
        TxtNome.setText(tecnico.getNome());

    }

    @FXML
    private void BtnGravar_Action(ActionEvent event){
        Tecnico tecnico = new Tecnico();
        tecnico.setNome(TxtNome.getText());
        try {
            if(alterando){
                tecnicoDao.alterar(tecnico);
            }else{
                tecnicoDao.gravar(tecnico);
                TxtId.setText(tecnico.getId().toString());
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
            return;
        }

        preencherLista();
        habilitarInterface(false);
        
    }


    @FXML
    private void BtnAlterar_Action(ActionEvent event) {
        tecnico = LstTecnicos.getSelectionModel().getSelectedItem();
        exibirDados();
        alterando = true;
        habilitarInterface(true);
    }

    @FXML
    private void BtnExcluir_Action(ActionEvent event) {
        try {
            tecnico = LstTecnicos.getSelectionModel().getSelectedItem();
            tecnicoDao.excluir(tecnico);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        preencherLista();

    }
    @FXML
    private void BtnIncluir_Action(ActionEvent event) {
        alterando = false;
        TxtNome.setText("");
        TxtNome.requestFocus();
        habilitarInterface(true);

    }
    @FXML
    private void BtnCancelar_Action(ActionEvent event) {
        TxtId.setText("");
        TxtNome.setText("");
        habilitarInterface(false);

    }

    private void preencherLista() {
        try {
            ObservableList<Tecnico> tecnicos = FXCollections.observableArrayList(tecnicoDao.getDados());
            LstTecnicos.setItems(tecnicos);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
        habilitarInterface(false);
    }
    
}
