package com.bryan.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable{
    
    private void abrirTela(String fxml, String titulo) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/"+ fxml + ".fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @FXML
    private void BtnEquipamentos_Action(ActionEvent event) {
        abrirTela("Equipamento", "Cadastro de Equipamentos");
    
    }
    @FXML
    private void BtnPessoas_Action(ActionEvent event) {
        abrirTela("Pessoa", "Cadastro de Pessoas");
    
    }
    @FXML
    private void BtnTecnicos_Action(ActionEvent event) {
        abrirTela("Tecnico", "Cadastro de Tecnicos");
    
    }
    @FXML
    private void BtnContratos_Action(ActionEvent event) {
        abrirTela("Contrato", "Cadastro de Contratos");
    }
    @FXML
    private void BtnOs_Action(ActionEvent event) {
        abrirTela("Os", "Cadastro de Os");
    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
        
}
