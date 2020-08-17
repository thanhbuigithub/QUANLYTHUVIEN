package control.controllers;

import com.jfoenix.controls.*;
import control.utilities.StageMaker;
import control.utilities.AlertMaker;
import control.utilities.VNCharacterUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.*;
import model.dto.*;

import java.io.IOException;
import java.util.*;

public class MuonSachController {
    StackPane rootPane;
    BorderPane mainPane;
    protected final JFXTreeTableView<PhieuMuon> table = new JFXTreeTableView<>();
    protected final List<JFXButton> listBtn = new ArrayList<>();

    public MuonSachController(StackPane rootPane, BorderPane mainPane){
        this.rootPane = rootPane;
        this.mainPane = mainPane;
    }

    protected void columnSetCellValueFactory(JFXTreeTableColumn<PhieuMuon, String> colSach, JFXTreeTableColumn<PhieuMuon, String> colBanDoc, JFXTreeTableColumn<PhieuMuon, Date> colNgayMuon, JFXTreeTableColumn<PhieuMuon, Integer> colThoiHanMuon, JFXTreeTableColumn<PhieuMuon, Integer> colGiaHan, JFXTreeTableColumn<PhieuMuon, String> colNhanVien){
        colSach.setCellValueFactory((param) -> {
            if (colSach.validateValue(param)) {
                PhieuMuon phieuMuon = param.getValue().getValue();
                return new SimpleStringProperty(phieuMuon.getSach().toString());
            } else return colSach.getComputedValue(param);
        });

        colBanDoc.setCellValueFactory((param) -> {
            if (colBanDoc.validateValue(param)) {
                PhieuMuon phieuMuon = param.getValue().getValue();
                return new SimpleStringProperty(phieuMuon.getBanDoc().toString());
            } else return colBanDoc.getComputedValue(param);
        });

        colNgayMuon.setCellValueFactory((param) -> {
            if (colNgayMuon.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getNgayMuon());
            } else return colNgayMuon.getComputedValue(param);
        });

        colThoiHanMuon.setCellValueFactory((param) -> {
            if (colThoiHanMuon.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getThoiHanMuon());
            } else return colThoiHanMuon.getComputedValue(param);
        });

        colGiaHan.setCellValueFactory((param) -> {
            if (colThoiHanMuon.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getGiaHan());
            } else return colThoiHanMuon.getComputedValue(param);
        });

        colNhanVien.setCellValueFactory((param) -> {
            if (colNhanVien.validateValue(param)) {
                PhieuMuon phieuMuon = param.getValue().getValue();
                return new SimpleStringProperty(phieuMuon.getNhanVien().toString());

            } else return colNhanVien.getComputedValue(param);
        });
    }

    protected void tableSetRowFactory(){
        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemGiaHan = new MenuItem("Gia h\u1EA1n");
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().addAll(itemGiaHan, itemXoa);
                itemGiaHan.setOnAction((e) -> {
                    JFXButton btnYES = new JFXButton("YES");
                    JFXButton btnNO = new JFXButton("NO");
                    btnYES.setOnAction(event -> {
                        PhieuMuon phieuMuon = getTreeTableView().getTreeItem(getIndex()).getValue();
                        phieuMuon.setGiaHan(phieuMuon.getGiaHan() + 1);
                        phieuMuon.setThoiHanMuon(phieuMuon.getThoiHanMuon() + 7);
                        PhieuMuonDAO.getInstance().update(phieuMuon);
                    });
                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "Gia h\u1EA1n phi\u1EBFu m\u01B0\u1EE3n", "B\u1EA1n c\u00F3 ch\u1EAFc mu\u1ED1n gia h\u1EA1n phi\u1EBFu m\u01B0\u1EE3n n\u00E0y trong 7 ng\u00E0y?");
                });
                itemXoa.setOnAction((e) -> {
                    ObjectProperty<JFXDialog> dialogProperty = new SimpleObjectProperty<>();
                    JFXButton btnYES = new JFXButton("YES");
                    JFXButton btnNO = new JFXButton("NO");
                    btnYES.setOnAction(event -> {
                        PhieuMuon phieuMuon = getTreeTableView().getTreeItem(getIndex()).getValue();
                        if (PhieuMuonDAO.getInstance().remove(phieuMuon)) {
                            getTreeTableView().getRoot().getChildren().remove(getIndex());
                        }
                    });
                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "Xo\u00E1 phi\u1EBFu m\u01B0\u1EE3n", "B\u1EA1n c\u00F3 ch\u1EAFc mu\u1ED1n xo\u00E1 phi\u1EBFu m\u01B0\u1EE3n n\u00E0y?");
                });
                this.setContextMenu(addMenu);
            }
        });
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal).toLowerCase();
            table.setPredicate(pmProperty -> {
                PhieuMuon pm = pmProperty.getValue();

                return  VNCharacterUtils.removeAccent(pm.getSach().toString()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(pm.getBanDoc().toString()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(pm.getNhanVien().toString()).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    protected void btnSetOnAction(JFXButton taoPhieuMuon) {
        taoPhieuMuon.setOnAction(e -> {
            try {
                StageMaker.createStage("/view/themPhieuMuon.fxml", "T\u1EA1o phi\u1EBFu m\u01B0\u1EE3n", 560, 250);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
