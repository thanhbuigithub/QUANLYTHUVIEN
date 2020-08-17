package control.controllers;

import com.jfoenix.controls.*;
import control.utilities.AlertMaker;
import control.utilities.DateUtil;
import control.utilities.StageMaker;
import control.utilities.VNCharacterUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.BanDocDAO;
import model.dao.NhanVienDAO;
import model.dao.PhieuMuonDAO;
import model.dao.SachDAO;
import model.dto.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LichSuDangNhapController {
    StackPane rootPane;
    BorderPane mainPane;
    protected final JFXTreeTableView<LichSuDangNhap> table = new JFXTreeTableView<>();
    protected final List<JFXButton> listBtn = new ArrayList<>();

    public LichSuDangNhapController(StackPane rootPane, BorderPane mainPane){
        this.rootPane = rootPane;
        this.mainPane = mainPane;
    }

    protected void columnSetCellValueFactory(JFXTreeTableColumn<LichSuDangNhap, String> colNhanVien, JFXTreeTableColumn<LichSuDangNhap, String> colThoiGianDangNhap, JFXTreeTableColumn<LichSuDangNhap, String> colThoiGianDangXuat){
        colNhanVien.setCellValueFactory((param) -> {
            if (colNhanVien.validateValue(param)) {
                return new SimpleStringProperty(param.getValue().getValue().getNhanVien().toString());
            } else return colNhanVien.getComputedValue(param);
        });

        colThoiGianDangNhap.setCellValueFactory((param) -> {
            if (colThoiGianDangNhap.validateValue(param)) {
                return new SimpleStringProperty(DateUtil.DateToString(param.getValue().getValue().getThoiGianDangNhap(),"HH:mm dd/MM/yyyy"));
            } else return colThoiGianDangNhap.getComputedValue(param);
        });
    }

    protected void tableSetRowFactory(){
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal).toLowerCase();
            table.setPredicate(lsdnProperty -> {
                LichSuDangNhap lsdn = lsdnProperty.getValue();
                return  VNCharacterUtils.removeAccent(lsdn.getNhanVien().toString()).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    protected void btnSetOnAction(JFXButton taoPhieuMuon) {
    }
}
