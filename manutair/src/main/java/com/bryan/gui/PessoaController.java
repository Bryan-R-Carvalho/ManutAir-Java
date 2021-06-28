package com.bryan.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bryan.dao.Persistencia;
import com.bryan.dao.PessoaDao;
import com.bryan.model.Fisica;
import com.bryan.model.Juridica;
import com.bryan.model.Pessoa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PessoaController implements Initializable {
    private Pessoa pessoa;
    private Boolean alterando;
    private Persistencia<Pessoa> pessoaDao = new PessoaDao();

    @FXML
    private TextField TxtId;
    @FXML
    private TextField TxtEndereco;
    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtCadastro;
    @FXML
    private TextField TxtRazao;

    @FXML
    private Label LblNome;
    @FXML
    private Label LblCadastro;
    @FXML
    private Label LblRazao;

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
    private ComboBox<String> CboPessoa;

    @FXML
    private ListView<Pessoa> LstPessoas;


    private void preencherCombo() {
        List<String> dados = new ArrayList<String>();
        dados.add("Fisica");
        dados.add("Juridica");
        
        ObservableList<String> opcoes = FXCollections.observableArrayList(dados);
        CboPessoa.setItems(opcoes);
        CboPessoa.getSelectionModel().select("Fisica");
    }
    private void limparCampo(){
        TxtId.setText("");
        TxtEndereco.setText("");
        TxtTelefone.setText("");
        TxtNome.setText("");
        TxtCadastro.setText("");
        TxtRazao.setText("");
        
    }
    private void habilitarInterface(Boolean edicao) {
        TxtId.setEditable(edicao);
        TxtEndereco.setEditable(edicao);
        TxtTelefone.setEditable(edicao);
        TxtNome.setEditable(edicao);
        TxtRazao.setEditable(edicao);
        TxtEndereco.setDisable(!edicao);
        TxtTelefone.setDisable(!edicao);
        TxtNome.setDisable(!edicao);
        TxtRazao.setDisable(!edicao);
        BtnGravar.setDisable(!edicao);
        BtnCancelar.setDisable(!edicao);
        BtnIncluir.setDisable(edicao);
        BtnAlterar.setDisable(edicao);
        BtnExcluir.setDisable(edicao);
    }
    
    @FXML
    private void BtnGravar_Action(ActionEvent event){
        try {
            if(alterando){
                if (pessoa instanceof Fisica) {
                    Fisica fisica = (Fisica) pessoa;
                    fisica.setEndereco(TxtId.getText());
                    fisica.setEndereco(TxtEndereco.getText());
                    fisica.setTelefone(TxtTelefone.getText());
                    fisica.setCpf(TxtCadastro.getText());
                    fisica.setNome(TxtNome.getText());
                    pessoaDao.alterar(fisica);
                } else {
                    Juridica juridica = (Juridica) pessoa;
                    juridica.setEndereco(TxtId.getText());
                    juridica.setEndereco(TxtEndereco.getText());
                    juridica.setTelefone(TxtTelefone.getText());
                    juridica.setCnpj(TxtCadastro.getText());
                    juridica.setRazaoSocial(TxtRazao.getText());
                    juridica.setNomeResponsavel(TxtNome.getText());
                    pessoaDao.alterar(juridica);
                }

            }else{
                if (CboPessoa.getSelectionModel().getSelectedItem().equals("Fisica")) {
                    Fisica fisica  = new Fisica();
                    fisica.setId(Long.parseLong(TxtId.getText()));
                    fisica.setEndereco(TxtEndereco.getText());
                    fisica.setTelefone(TxtTelefone.getText());
                    fisica.setCpf(TxtCadastro.getText());
                    fisica.setNome(TxtNome.getText());
                    pessoaDao.gravar(fisica);

                } else {
                    Juridica juridica = new Juridica();
                    juridica.setId(Long.parseLong(TxtId.getText()));
                    juridica.setEndereco(TxtEndereco.getText());
                    juridica.setTelefone(TxtTelefone.getText());
                    juridica.setCnpj(TxtCadastro.getText());
                    juridica.setRazaoSocial(TxtRazao.getText());
                    juridica.setNomeResponsavel(TxtNome.getText());
                    pessoaDao.gravar(juridica);
                    }
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
            return;
        }

        preencherLista();
        habilitarInterface(false);
        limparCampo();
        TxtId.setEditable(false);
        TxtCadastro.setEditable(false);
        TxtId.setDisable(true);
        TxtCadastro.setDisable(true);
        CboPessoa.setDisable(true);
        
    }


    @FXML
    private void BtnAlterar_Action(ActionEvent event) {
        pessoa = LstPessoas.getSelectionModel().getSelectedItem();
        TxtEndereco.requestFocus();
        exibirDados();
        alterando = true;
        habilitarInterface(true);
        TxtId.setDisable(true);
        TxtCadastro.setDisable(true);
        CboPessoa.setDisable(true);
    }

    @FXML
    private void BtnIncluir_Action(ActionEvent event) {
        habilitarInterface(true);
        limparCampo();
        alterando = false;
        TxtId.setEditable(true);
        TxtId.setDisable(false);
        TxtCadastro.setEditable(true);
        TxtCadastro.setDisable(false);
        CboPessoa.setDisable(false);

    }
    @FXML
    private void BtnCancelar_Action(ActionEvent event) {
        limparCampo();
        habilitarInterface(false);
        TxtId.setEditable(false);
        TxtId.setDisable(true);
        TxtCadastro.setEditable(false);
        TxtCadastro.setDisable(true);
        CboPessoa.setDisable(true);

    }
    @FXML
    private void BtnExcluir_Action(ActionEvent event) {
        try {
            pessoa = LstPessoas.getSelectionModel().getSelectedItem();
            pessoaDao.excluir(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        preencherLista();

    }

    private void exibir() {
        if (CboPessoa.getSelectionModel().getSelectedItem().equals("Fisica")) {
            LblNome.setText("Nome");
            LblCadastro.setText("CPF");
            LblRazao.setVisible(false);
            TxtRazao.setVisible(false);
            TxtRazao.setText("");
        } else {
            LblNome.setText("Nome do responsável");
            LblCadastro.setText("CNPJ");
            LblRazao.setVisible(true);
            TxtRazao.setVisible(true);
        }
    }
    private void exibirDados() {
        pessoa = LstPessoas.getSelectionModel().getSelectedItem();
        TxtId.setText(pessoa.getId().toString());
        TxtEndereco.setText(pessoa.getEndereco());
        TxtTelefone.setText(pessoa.getTelefone());

        if (pessoa instanceof Fisica) {
            exibir();
            Fisica fisica = (Fisica) pessoa;;
            TxtCadastro.setText(fisica.getCpf());
            TxtNome.setText(fisica.getNome());
            CboPessoa.getSelectionModel().select("Fisica");
        } else {
            exibir();
            Juridica juridica = (Juridica) pessoa;;
            TxtCadastro.setText(juridica.getCnpj());
            TxtRazao.setText(juridica.getRazaoSocial());
            TxtNome.setText(juridica.getNomeResponsavel());
            CboPessoa.getSelectionModel().select("Juridica");
        }
    }

    private void preencherLista() {
        try {
            ObservableList<Pessoa> pessoas = FXCollections.observableArrayList(pessoaDao.getDados());
            LstPessoas.setItems(pessoas);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @FXML
    private void CboPessoa_Action(ActionEvent event) {
        exibir();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherCombo();
        preencherLista();
        habilitarInterface(false);
        TxtCadastro.setEditable(false);
        TxtCadastro.setDisable(true);
        CboPessoa.setDisable(true);
    }
}