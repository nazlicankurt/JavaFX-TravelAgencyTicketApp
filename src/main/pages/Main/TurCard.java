/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Database.DatabaseManager;
import Ekle.Ekle;
import Pojo.Turlar;
import View.View;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.naming.Context;
import seyahatacentesi.FXMLDocumentController;

/**
 *
 * @author HP
 * @param <T>
 */


public class TurCard<T extends Turlar> extends HBox implements Search{
    
    private T t;
    
    private ContextMenu menu;
    
    @FXML
    private Label tur , nerden, nereye, fiyat, gun, gece; 
    
    public TurCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TurCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        menu= new ContextMenu();
        
        MenuItem duzenle= new MenuItem("Düzenle");
        
        duzenle.setOnAction((event) -> {
             TurEkle ekle = new TurEkle();
             ekle.setData(t);
             FXMLDocumentController.mainController.getChildren().add(ekle);
        });
        
        MenuItem sil= new MenuItem("Sil");
       
        sil.setOnAction((event) -> {
            DatabaseManager<Pojo.Turlar> turlar = new DatabaseManager(Pojo.Turlar.class);
            turlar.delete(t);
            Pane pane= (Pane) this.getParent();
            pane.getChildren().remove(this);
        });
        
        MenuItem musteriekle= new MenuItem("Müşteri Ekle");
        musteriekle.setOnAction((event) -> {
            FXMLDocumentController.mainController.getChildren().add(new Ekle(t));
        });
        MenuItem incele= new MenuItem("İncele"); 
       
       
        incele.setOnAction((event) -> {
            FXMLDocumentController.mainController.getChildren().add(new View(t));
             
        });
        menu.getItems().addAll(duzenle, sil, musteriekle, incele);
        this.setOnContextMenuRequested((event) -> {
            menu.show(this, event.getScreenX(), event.getScreenY());
        });
    }
    
    @FXML
    private void initialize() {
        
    }
    
    public void setData(T t){
        this.t = t;
        tur.setText(t.getTadi());
        nerden.setText(t.getNereden());
        nereye.setText(t.getNereye());
        fiyat.setText(""+t.getFiyat());
        gun.setText(""+t.getKacgun());
        gece.setText(""+t.getKacgece()); 
        MenuItem turadi= new MenuItem(t.getTadi());
        SeparatorMenuItem separator=new SeparatorMenuItem();
        
        menu.getItems().add(0, turadi);
        menu.getItems().add(1,separator);
    }
    
    public T getData(){
        return t;
    }

    @Override
    public String searchData() {
        return t.getTadi() + " " + t.getNereden() + " " + t.getNereye();
    }
    
}
