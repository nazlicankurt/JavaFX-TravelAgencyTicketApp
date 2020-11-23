/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import BIN.ConstomAnchor;
import Database.DatabaseManager;
import Main.Main;
import Pojo.Yonetici;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seyahatacentesi.FXMLDocumentController;

/**
 *
 * @author HP
 */
public class Login extends StackPane {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;
    @FXML
    private VBox reg;
    @FXML
    private VBox login;
    @FXML
    private TextField regname;
    @FXML
    private TextField reglastname;
    @FXML
    private TextField regusername;
    @FXML
    private PasswordField regpass;
    @FXML
    private Button regbutton;
    @FXML
    private Label regmessage;
    @FXML
    private Slider slider;
    @FXML
    private Label random;
    @FXML
    private Label sonuc;

    private int value;

    public Login() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        ConstomAnchor.fullAnchor(this, 0, 0, 0, 0);

        Random random = new Random();
        int sayi1 = (int) random.nextInt(25);
        int sayi2 = (int) random.nextInt(25);
        this.value = sayi1 + sayi2;
        this.random.setText(sayi1 + " + " + sayi2 + " = ?");

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sonuc.setText("" + newValue.intValue());
        });

    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void login(ActionEvent event) {
        if (isControl()) {
            if (value == Integer.valueOf(sonuc.getText())) {
                DatabaseManager<Pojo.Yonetici> users = new DatabaseManager(Pojo.Yonetici.class);
                ObservableList<Pojo.Yonetici> list = (ObservableList<Pojo.Yonetici>) users.get().filtered(t -> t.getYAdi().equals(username.getText()) && t.getYSifre().equals(password.getText()));
                if (!list.isEmpty()) {
                    FXMLDocumentController.mainController.getChildren().clear();
                    FXMLDocumentController.mainController.getChildren().add(new Main());
                } else {
                    message.setVisible(true);
                    message.setText("Sistemde kayıtlı böyle bir kullanıcı yok.");
                }
            } else {
                message.setVisible(true);
                message.setText("Senin matematik hocan kim?");
            }
        } else {
            message.setVisible(true);
            message.setText("Lütfen tüm alanları doldurun.");
        }
    }

    @FXML
    private void reg(ActionEvent event) {
        login.setVisible(false);
        reg.setVisible(true);
        regbutton.setVisible(false);
    }

    @FXML
    void cancel(ActionEvent event) {

        login.setVisible(true);
        reg.setVisible(false);
        regbutton.setVisible(true);

    }

    @FXML
    void save(ActionEvent event) {
        if (isregControl()) {
            DatabaseManager<Pojo.Yonetici> database = new DatabaseManager(Pojo.Yonetici.class);
            Yonetici yonetici = new Yonetici();
            yonetici.setIsim(regname.getText());
            yonetici.setSoyisim(reglastname.getText());
            yonetici.setYAdi(regusername.getText());
            yonetici.setYSifre(regpass.getText());
            database.save(yonetici);
            regmessage.setVisible(true);
            regmessage.setText("Kayıt İşlemi Başarılı!");
        } else {
            message.setVisible(true);
            message.setText("Lütfen tüm alanları doldurun.");
        }
    }

    private boolean isControl() {

        return (!"".equals(username.getText()) && username.getText() != null) && (!"".equals(password.getText()) && password.getText() != null);
    }

    private boolean isregControl() {

        return (!"".equals(regname.getText()) && regname.getText() != null) && (!"".equals(regpass.getText())
                && regpass.getText() != null) && reglastname.getText() != null && !"".equals(reglastname.getText())
                && regusername.getText() != null && !"".equals(regusername.getText());
    }
}
