package Main;

import BIN.ConstomAnchor;
import Database.DatabaseManager;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import seyahatacentesi.FXMLDocumentController;

public class Main extends VBox {

    @FXML
    private TextField ara;

    @FXML
    private VBox list;

    private ObservableList<Node> oldList;
    private boolean isSearch;

    public Main() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        ConstomAnchor.fullAnchor(this, 0, 0, 0, 0);

        oldList = FXCollections.observableArrayList();
        isSearch = false;
        DatabaseManager<Pojo.Turlar> turlar = new DatabaseManager(Pojo.Turlar.class);
        turlar.get().forEach((t) -> {
            TurCard card = new TurCard();
            card.setData(t);
            list.getChildren().add(card);
            oldList.add(card);
        });

        ara.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null || !"".equals(newValue)) {
                isSearch = true;
            }
            if (isSearch) {
                this.list.getChildren().clear();

                oldList.filtered((t) -> {
                    Search s = (Search) t;
                    return s.searchData().toLowerCase().contains(newValue.toLowerCase());
                }).forEach((t) -> {
                    this.list.getChildren().add(t);
                });
                
                if (newValue == null ||  newValue.isEmpty()) {
                    this.list.getChildren().clear();
                    this.list.getChildren().addAll(oldList);
                    isSearch = false;
                }
            }

        });
    }

    @FXML
    private void initialize() {

    }

    @FXML
    void ekle(ActionEvent event) {
        FXMLDocumentController.mainController.getChildren().add(new TurEkle());

    }

    @FXML
    void guncelle(ActionEvent event) {
        list.getChildren().clear();
        DatabaseManager<Pojo.Turlar> turlar = new DatabaseManager(Pojo.Turlar.class);
        turlar.get().forEach((t) -> {
            TurCard card = new TurCard();
            card.setData(t);
            list.getChildren().add(card);
            oldList.add(card);
        });
    }
}
