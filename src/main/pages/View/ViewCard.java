/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Pojo.Musteri;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author HP
 */
public class ViewCard extends HBox {
    
    private Pojo.Musteri data;
    
    @FXML
    private Label madi,tel,cins,tc;

    
    public ViewCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private void initialize() {
        
    }

    public void setData(Musteri data) {
        this.data = data;
        madi.setText(data.getAd() + " " + data.getSoyad() );
        tel.setText(""+data.getTelno());
        cins.setText(data.getCinsiyet());
        tc.setText(""+data.getTc());
        
    }
    
    
}
