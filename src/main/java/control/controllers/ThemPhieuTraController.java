package control.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.dao.PhieuTraDAO;
import model.dto.PhieuMuon;
import model.dto.PhieuTra;

import java.util.Date;

public class ThemPhieuTraController {

    @FXML
    protected JFXComboBox<PhieuMuon> cbxIdPhieuMuon;

    @FXML
    protected JFXTextField tfTinhTrang;

    @FXML
    protected  JFXTextField tfBoiThuong;

    @FXML
    protected JFXButton btnTaoPhieuTra;

    protected void btnSetOnAction() {
        btnTaoPhieuTra.setOnAction(e->{
            System.out.println("here");
            PhieuTra phieuTra = new PhieuTra();
            phieuTra.setPhieuMuon(cbxIdPhieuMuon.getValue());
            phieuTra.setNgayTra(new Date());
            phieuTra.setBoiThuong(tfBoiThuong.getText());
            phieuTra.setTinhTrang(tfTinhTrang.getText());
            PhieuTraDAO.getInstance().insert(phieuTra);
            PhieuTraDAO.getInstance().all().add(phieuTra);
            ((Stage) ((Node)e.getSource()).getScene().getWindow()).close();
        });
    }
}
