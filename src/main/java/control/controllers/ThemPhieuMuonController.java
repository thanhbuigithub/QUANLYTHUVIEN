package control.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import model.dto.BanDoc;
import model.dto.Sach;

public class ThemPhieuMuonController {

    @FXML
    protected JFXComboBox<Sach> cbxSach;

    @FXML
    protected JFXComboBox<BanDoc> cbxBanDoc;

    @FXML
    protected JFXButton btnTaoPhieuMuon;

    protected void btnSetOnAction() {
        btnTaoPhieuMuon.setOnAction(e->{

        });
    }
}
