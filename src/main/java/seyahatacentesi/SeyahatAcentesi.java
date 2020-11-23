/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seyahatacentesi;

import Database.Database;
import Pojo.Yonetici;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nazlıcan Kurt
 */


public class SeyahatAcentesi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Database database= new Database();
        database.Connection();
       
        Scene scene = new Scene(root);
        stage.setWidth(1200);
        stage.setHeight(650);
        stage.setScene(scene);
        stage.setTitle("Nazlıcan Kurt - 161103057");
        stage.show();
        stage.setOnCloseRequest((event) -> {
            database.Disconnected();
            Platform.exit();
            System.exit(0);
            
        });
        
        
        
    }

  
    public static void main(String[] args) {
        launch(args);
    }

  
    
}
