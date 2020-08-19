package view;

import control.controllers.ThemPhieuTraController;
import control.utilities.AutoCompleteComboBoxListener;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;
import model.dao.PhieuMuonDAO;
import model.dto.PhieuMuon;

import java.net.URL;
import java.util.*;

public class ThemPhieuTraView extends ThemPhieuTraController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbxIdPhieuMuon.setItems(PhieuMuonDAO.getInstance().all());
        cbxIdPhieuMuon.setConverter(new StringConverter<>() {
            @Override
            public String toString(PhieuMuon object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public PhieuMuon fromString(String string) {
                return PhieuMuonDAO.getInstance().all().stream().filter(dd -> dd.toString().equals(string)).findAny().orElse(null);
            }
        });
        new AutoCompleteComboBoxListener<>(cbxIdPhieuMuon);

        btnSetOnAction();
    }
}
