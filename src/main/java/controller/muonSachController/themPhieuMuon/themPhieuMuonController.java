package controller.muonSachController.themPhieuMuon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import controller.AutoCompleteComboBoxListener;
import controller.Data;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;
import modules.dao.SachDAO;
import modules.dao.TheThuVienDAO;
import modules.entities.Sach;
import modules.entities.TheThuVien;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class themPhieuMuonController implements Initializable {

    @FXML
    private JFXComboBox<Sach> cbxSach;

    @FXML
    private JFXComboBox<TheThuVien> cbxTheThuVien;

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

        List<TheThuVien> listTheThuVien = TheThuVienDAO.getInstance().all();
        cbxTheThuVien.setItems(FXCollections.observableArrayList(listTheThuVien));
        cbxTheThuVien.setConverter(new StringConverter<>() {
            @Override
            public String toString(TheThuVien object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public TheThuVien fromString(String string) {
                return listTheThuVien.stream().filter(dd -> dd.toString().equals(string)).findAny().orElse(null);
            }
        });

        new AutoCompleteComboBoxListener<>(cbxSach);
        new AutoCompleteComboBoxListener<>(cbxTheThuVien);

        btnTaoPhieuMuon.setOnAction(e->{

        });
    }
}
