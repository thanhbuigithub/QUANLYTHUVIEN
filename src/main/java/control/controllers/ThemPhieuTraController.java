package control.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import model.dto.PhieuMuon;

public class ThemPhieuTraController {

    @FXML
    protected JFXComboBox<PhieuMuon> cbxIdPhieuMuon;

    @FXML
    protected JFXTextField tfTinhTrang;

    @FXML
    protected  JFXTextField tfBoiThuong;

    @FXML
    protected JFXButton btnTaoPhieuTra;
}
