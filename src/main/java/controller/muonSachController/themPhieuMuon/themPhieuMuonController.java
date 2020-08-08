package controller.muonSachController.themPhieuMuon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import controller.AutoCompleteComboBoxListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;
import modules.dao.BanDocDAO;
import modules.dao.SachDAO;
import modules.entities.BanDoc;
import modules.entities.Sach;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class themPhieuMuonController implements Initializable {

    @FXML
    private JFXComboBox<Sach> cbxSach;

    @FXML
    private JFXComboBox<BanDoc> cbxBanDoc;

    @FXML
    private JFXButton btnTaoPhieuMuon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Sach> listSach = SachDAO.getInstance().all();
        cbxSach.setItems(FXCollections.observableArrayList(listSach));
        cbxSach.setConverter(new StringConverter<>() {
            @Override
            public String toString(Sach object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Sach fromString(String string) {
                return listSach.stream().filter(dd -> dd.toString().equals(string)).findAny().orElse(null);
            }
        });

        List<BanDoc> listBanDoc = BanDocDAO.getInstance().all();
        cbxBanDoc.setItems(FXCollections.observableArrayList(listBanDoc));
        cbxBanDoc.setConverter(new StringConverter<>() {
            @Override
            public String toString(BanDoc object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public BanDoc fromString(String string) {
                return listBanDoc.stream().filter(dd -> dd.toString().equals(string)).findAny().orElse(null);
            }
        });

        new AutoCompleteComboBoxListener<>(cbxSach);
        new AutoCompleteComboBoxListener<>(cbxBanDoc);

        btnTaoPhieuMuon.setOnAction(e->{

        });
    }
}
