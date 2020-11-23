/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ekle;

import BIN.ConstomAnchor;
import Database.DatabaseManager;
import Pojo.Musteri;
import Pojo.Seferler;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import seyahatacentesi.FXMLDocumentController;

/**
 *
 * @author HP
 */
public class Ekle extends StackPane {
    
    private Pojo.Turlar tur;
    
    @FXML
    private TextField adi,soyadi,tel,cins,tc;

    @FXML
    private Button saveButton;

    @FXML
    private Label message;

   
    
    public Ekle(Pojo.Turlar tur) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ekle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.tur = tur;
        
        ConstomAnchor.fullAnchor(this, 0, 0, 0, 0);
    }
    
    @FXML
    private void initialize() {
    }
     @FXML
    void iptal(ActionEvent event) {
         FXMLDocumentController.mainController.getChildren().remove(this);
         
    }

    @FXML
    void kaydet(ActionEvent event) {
        if(isControl()){
            DatabaseManager<Pojo.Musteri> musteri = new DatabaseManager(Pojo.Musteri.class);
       Pojo.Musteri mus = new Musteri();
       mus.setAd(adi.getText());
       mus.setCinsiyet(cins.getText());
       mus.setSoyad(soyadi.getText());
       mus.setTc(Long.valueOf(tc.getText()));
       mus.setTelno(Long.valueOf(tel.getText()));
       musteri.save(mus);
       DatabaseManager<Pojo.Seferler> seferler = new DatabaseManager(Pojo.Seferler.class);
       Pojo.Seferler sef= new Seferler();
       sef.setMusteri(musteri.get().get(musteri.get().size()-1));
       sef.setTurlar(tur);
       seferler.save(sef);

       FXMLDocumentController.mainController.getChildren().remove(this);
        }else{
            message.setVisible(true);
            message.setText("Lütfen tüm alanları doldurunuz!");
        }
       
    }

    private boolean isControl() {
        return (adi.getText() != null && !"".equals(adi.getText())) && (cins.getText() != null && !"".equals(cins.getText())) 
                && (soyadi.getText() != null && !"".equals(soyadi.getText())) && (tc.getText() != null && !"".equals(tc.getText()))
                && (tel.getText() != null && !"".equals(tel.getText()));
    }
}
