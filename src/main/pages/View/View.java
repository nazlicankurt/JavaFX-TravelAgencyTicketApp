/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BIN.ConstomAnchor;
import Database.DatabaseManager;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seyahatacentesi.FXMLDocumentController;

/**
 *
 * @author HP
 */
public class View extends StackPane {
    @FXML
    private Label turadi, kgun, kgece, nene, message;


    @FXML
    private VBox list;

    private Pojo.Turlar tur;
    
    public View(Pojo.Turlar tur) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.tur = tur;
        
        turadi.setText(this.tur.getTadi());
        kgun.setText(this.tur.getKacgun());
        kgece.setText(this.tur.getKacgece());
        nene.setText(this.tur.getNereden() + " - " +this.tur.getNereye());
        
        
        DatabaseManager<Pojo.Seferler> sefer = new DatabaseManager(Pojo.Seferler.class);
        sefer.get().filtered( t -> Objects.equals(t.getTurlar().getTurId(), this.tur.getTurId())).forEach((t) -> {
            ViewCard card = new ViewCard();
            card.setData(t.getMusteri());
            list.getChildren().add(card);
        });
        ConstomAnchor.fullAnchor(this, 0, 0, 0, 0);
    }
    
    @FXML
    private void initialize() {
        
    }
    
    @FXML
    void close(ActionEvent event) {
        
        FXMLDocumentController.mainController.getChildren().remove(this);

    }
}
