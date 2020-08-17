package control.controllers;

import com.jfoenix.controls.*;

import control.utilities.StageMaker;
import control.utilities.AlertMaker;
import control.utilities.VNCharacterUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.*;
import model.dto.*;

import java.io.IOException;
import java.util.*;

public class TraSachController {
    StackPane rootPane;
    BorderPane mainPane;
    protected final JFXTreeTableView<PhieuTra> table = new JFXTreeTableView<>();
    protected final List<JFXButton> listBtn = new ArrayList<>();

    public TraSachController(StackPane rootPane, BorderPane mainPane){
        this.rootPane = rootPane;
        this.mainPane = mainPane;
    }

    protected void columnSetCellValueFactory(JFXTreeTableColumn<PhieuTra, String> colSach, JFXTreeTableColumn<PhieuTra, String> colBanDoc, JFXTreeTableColumn<PhieuTra, Date> colNgayMuon, JFXTreeTableColumn<PhieuTra, Date> colNgayHenTra, JFXTreeTableColumn<PhieuTra, Date> colHanChot, JFXTreeTableColumn<PhieuTra, String> colTinhTrang, JFXTreeTableColumn<PhieuTra, String> colBoiThuong){
        colSach.setCellValueFactory((param) -> {
            if (colSach.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = phieuTra.getPhieuMuon();
                return new SimpleStringProperty(phieuMuon.getSach().toString());
            } else return colSach.getComputedValue(param);
        });

        colBanDoc.setCellValueFactory((param) -> {
            if (colBanDoc.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = phieuTra.getPhieuMuon();
                return new SimpleStringProperty(phieuMuon.getBanDoc().toString());
            } else return colBanDoc.getComputedValue(param);
        });
        colNgayMuon.setCellValueFactory((param) -> {
            if (colNgayMuon.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = phieuTra.getPhieuMuon();
                return new SimpleObjectProperty<>(phieuMuon.getNgayMuon());
            } else return colNgayMuon.getComputedValue(param);
        });
        colNgayHenTra.setCellValueFactory((param) -> {
            if (colNgayHenTra.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = phieuTra.getPhieuMuon();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(phieuMuon.getNgayMuon());
                calendar.add(Calendar.DAY_OF_MONTH, phieuMuon.getThoiHanMuon());
                return new SimpleObjectProperty<>(calendar.getTime());
            } else return colNgayHenTra.getComputedValue(param);
        });

        colHanChot.setCellValueFactory((param) -> {
            if (colHanChot.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getNgayTra());
            } else return colHanChot.getComputedValue(param);
        });

        colTinhTrang.setCellValueFactory((param) -> {
            if (colTinhTrang.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getTinhTrang());
            } else return colTinhTrang.getComputedValue(param);
        });

        colBoiThuong.setCellValueFactory((param) -> {
            if (colBoiThuong.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getBoiThuong());
            } else return colBoiThuong.getComputedValue(param);
        });
    }

    protected void tableSetRowFactory(){
        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().add(itemXoa);
                itemXoa.setOnAction((e) -> {
                    ObjectProperty<JFXDialog> dialogProperty = new SimpleObjectProperty<>();
                    JFXButton btnYES = new JFXButton("YES");
                    JFXButton btnNO = new JFXButton("NO");
                    btnYES.setOnAction(event -> {
                        PhieuTra phieuTra = getTreeTableView().getTreeItem(getIndex()).getValue();
                        if (PhieuTraDAO.getInstance().remove(phieuTra)) {
                            getTreeTableView().getRoot().getChildren().remove(getIndex());
                        }
                    });
                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "Xo\u00E1 phi\u1EBFu tr\u1EA3", "B\u1EA1n c\u00F3 ch\u1EAFc mu\u1ED1n xo\u00E1 phi\u1EBFu tr\u1EA3 n\u00E0y?");
                });
                this.setContextMenu(addMenu);
            }
        });
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal);
            table.setPredicate(ptProperty -> {
                PhieuTra pt = ptProperty.getValue();

                return pt.getPhieuMuon().toString().contains(newValueNoAccent);
            });
        });
    }

    protected void btnSetOnAction(JFXButton taoPhieuTra) {
        taoPhieuTra.setOnAction(e -> {
            try {
                StageMaker.createStage("/view/themPhieuTra.fxml", "T\u1EA1o phi\u1EBFu tr\u1EA3", 560, 500);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
