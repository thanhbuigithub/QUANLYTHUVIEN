package view;

import control.controllers.ThemPhieuMuonController;
import control.utilities.AutoCompleteComboBoxListener;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;
import model.dao.BanDocDAO;
import model.dao.SachDAO;
import model.dto.BanDoc;
import model.dto.Sach;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemPhieuMuonView extends ThemPhieuMuonController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbxSach.setItems(SachDAO.getInstance().all());
        cbxSach.setConverter(new StringConverter<>() {
            @Override
            public String toString(Sach object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Sach fromString(String string) {
                return SachDAO.getInstance().all().stream().filter(dd -> dd.toString().equals(string)).findAny().orElse(null);
            }
        });

        cbxBanDoc.setItems(BanDocDAO.getInstance().all());
        cbxBanDoc.setConverter(new StringConverter<>() {
            @Override
            public String toString(BanDoc object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public BanDoc fromString(String string) {
                return BanDocDAO.getInstance().all().stream().filter(dd -> dd.toString().equals(string)).findAny().orElse(null);
            }
        });

        new AutoCompleteComboBoxListener<>(cbxSach);
        new AutoCompleteComboBoxListener<>(cbxBanDoc);

        btnSetOnAction();
    }
}
