/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seyahatacentesi;

import Database.DatabaseManager;
import Login.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author HP
 */

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main;
    
    public static AnchorPane mainController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainController = this.main;
        this.main.getChildren().add(new Login());
    }    


}
