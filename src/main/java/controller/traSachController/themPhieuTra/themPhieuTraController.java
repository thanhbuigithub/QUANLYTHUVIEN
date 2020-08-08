package controller.traSachController.themPhieuTra;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import controller.AutoCompleteComboBoxListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import modules.dao.PhieuTraDAO;
import modules.dao.SachDAO;
import modules.entities.PhieuTra;
import modules.entities.Sach;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class themPhieuTraController implements Initializable {

    @FXML
    private JFXComboBox<String> cbxIdPhieuMuon;

    @FXML
    private JFXDatePicker jfxDatePickerNgayTra;

    @FXML
    private JFXComboBox<String> cbxTinhTrang;

    @FXML
    private JFXComboBox<String> cbxBoiThuong;

    @FXML
    private JFXButton btnTaoPhieuTra;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PhieuTra> listPhieuTra = PhieuTraDAO.getInstance().all();
        List<String> listID = new ArrayList<>();
        Set<String> listTinhTrang = new HashSet<>();
        Set<String> listBoiThuong = new HashSet<>();
        for (PhieuTra pt : listPhieuTra
        ) {
            listID.add(pt.getIdPhieuMuon().toString());
            listTinhTrang.add(pt.getTinhTrang());
            listBoiThuong.add(pt.getBoiThuong());
        }
        cbxIdPhieuMuon.setItems(FXCollections.observableArrayList(listID));
        cbxTinhTrang.setItems(FXCollections.observableArrayList(listTinhTrang));
        cbxBoiThuong.setItems(FXCollections.observableArrayList(listBoiThuong));
        jfxDatePickerNgayTra.setValue(LocalDate.now());

        new AutoCompleteComboBoxListener<>(cbxIdPhieuMuon);
        new AutoCompleteComboBoxListener<>(cbxTinhTrang);
        new AutoCompleteComboBoxListener<>(cbxBoiThuong);

        btnTaoPhieuTra.setOnAction(e -> {

        });
    }
}
