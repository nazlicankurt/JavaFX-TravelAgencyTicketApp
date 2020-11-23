/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import BIN.ConstomAnchor;
import Database.DatabaseManager;
import Pojo.Turlar;
import java.io.IOException;
import javafx.collections.ObservableList;
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
public class TurEkle extends StackPane {

    private Pojo.Turlar turlar;

    @FXML
    private TextField adi, nereden, nereye, fiyat, gun, gece;

    @FXML
    private Label message;

    @FXML
    private Button saveButton;

    private boolean isEdit;

    public TurEkle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TurEkle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        ConstomAnchor.fullAnchor(this, 0, 0, 0, 0);
        isEdit = false;
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
        if (isControl()) {
            try {
                DatabaseManager<Pojo.Turlar> kaydet = new DatabaseManager(Pojo.Turlar.class);
                if (!isEdit) {
                    Turlar turlar = new Turlar();
                    turlar.setTadi(adi.getText());
                    turlar.setNereye(nereye.getText());
                    turlar.setNereden(nereden.getText());
                    turlar.setFiyat(Integer.valueOf(fiyat.getText()));
                    turlar.setKacgun(gun.getText());
                    turlar.setKacgece(gece.getText());
                    kaydet.save(turlar);
                } else {
                    turlar.setTadi(adi.getText());
                    turlar.setNereye(nereye.getText());
                    turlar.setNereden(nereden.getText());
                    turlar.setFiyat(Integer.valueOf(fiyat.getText()));
                    turlar.setKacgun(gun.getText());
                    turlar.setKacgece(gece.getText());
                    kaydet.update(turlar);
                }
                FXMLDocumentController.mainController.getChildren().remove(this);
            } catch (NumberFormatException e) {
                message.setText("Fiyat, Gün, Gece sadece sayı olabilir.");
            }
        } else {

            message.setText("Lütfen tüm alanları doldurunuz.");
        }

    }

    public boolean isControl() {

        return (!adi.getText().equals("") && adi.getText() != null) && (!nereden.getText().equals("") && nereden.getText() != null)
                && (!nereye.getText().equals("") && nereye.getText() != null) && (!fiyat.getText().equals("") && fiyat.getText() != null)
                && (!gun.getText().equals("") && gun.getText() != null) && (!gece.getText().equals("") && gece.getText() != null);
    }

    public void setData(Pojo.Turlar t) {
        this.turlar = t;
        adi.setText(t.getTadi());
        nereden.setText(t.getNereden());
        nereye.setText(t.getNereye());
        fiyat.setText("" + t.getFiyat());
        gun.setText(t.getKacgun());
        gece.setText(t.getKacgece());
        isEdit = true;
        saveButton.setText("Düzenle");
    }

}
