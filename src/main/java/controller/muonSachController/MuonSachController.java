package controller.muonSachController;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.controls.events.JFXDialogEvent;
import controller.CellFactory;
import controller.VNCharacterUtils;
import controller.util.AlertMaker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import modules.dao.*;
import modules.entities.*;

import java.util.*;

public class MuonSachController {
    private JFXTreeTableView<PhieuMuon> table = new JFXTreeTableView<>();

    private JFXTreeTableColumn<PhieuMuon, String> colSach = new JFXTreeTableColumn<>("S\u00E1ch");
    private JFXTreeTableColumn<PhieuMuon, String> colBanDoc = new JFXTreeTableColumn<>("B\u1EA1n \u0110\u1ECDc");
    private JFXTreeTableColumn<PhieuMuon, Date> colNgayMuon = new JFXTreeTableColumn<>("Ng\u00E0y M\u01B0\u1EE3n");
    private JFXTreeTableColumn<PhieuMuon, Integer> colThoiHanMuon = new JFXTreeTableColumn<>("Th\u1EDDi H\u1EA1n M\u01B0\u1EE3n");
    private JFXTreeTableColumn<PhieuMuon, Integer> colGiaHan = new JFXTreeTableColumn<>("Gia H\u1EA1n");
    private JFXTreeTableColumn<PhieuMuon, String> colNhanVien = new JFXTreeTableColumn<>("Nh\u00E2n Vi\u00EAn");

    ObservableList<PhieuMuon> pms = FXCollections.observableArrayList();

    public MuonSachController(StackPane rootPane, BorderPane mainPane) {
        pms.addAll(PhieuMuonDAO.getInstance().all());

        colSach.setCellValueFactory((param) -> {
            if (colSach.validateValue(param)) {
                PhieuMuon phieuMuon = param.getValue().getValue();
                Sach sach = SachDAO.getInstance().getByID(phieuMuon.getIdSach());
                return new SimpleStringProperty(phieuMuon.getIdSach() + " - " + sach.getTenSach());
            } else return colSach.getComputedValue(param);
        });

        CellFactory.getInstance().StringValueFactory(colSach);

        colBanDoc.setCellValueFactory((param) -> {
            if (colBanDoc.validateValue(param)) {
                PhieuMuon phieuMuon = param.getValue().getValue();
                TheThuVien theThuVien = TheThuVienDAO.getInstance().getByID(phieuMuon.getIdTheThuVien());
                BanDoc banDoc = BanDocDAO.getInstance().getByID(theThuVien.getIdBanDoc());
                return new SimpleStringProperty(theThuVien.getIdBanDoc() + " - " + banDoc.getHoVaTen());
            } else return colBanDoc.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colBanDoc);

        colNgayMuon.setCellValueFactory((param) -> {
            if (colNgayMuon.validateValue(param)) {
                return param.getValue().getValue().ngayMuon;
            } else return colNgayMuon.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colNgayMuon);

        colThoiHanMuon.setCellValueFactory((param) -> {
            if (colThoiHanMuon.validateValue(param)) {
                return param.getValue().getValue().thoiHanMuon;
            } else return colThoiHanMuon.getComputedValue(param);
        });

        colGiaHan.setCellValueFactory((param) -> {
            if (colThoiHanMuon.validateValue(param)) {
                return param.getValue().getValue().giaHan;
            } else return colThoiHanMuon.getComputedValue(param);
        });

        colNhanVien.setCellValueFactory((param) -> {
            if (colNhanVien.validateValue(param)) {
                PhieuMuon phieuMuon = param.getValue().getValue();
                NhanVien nhanVien = NhanVienDAO.getInstance().getByID(phieuMuon.getIdNhanVien());
                return new SimpleStringProperty(phieuMuon.getIdNhanVien() + " - " + nhanVien.getHoVaTen());

            } else return colNhanVien.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colNhanVien);

        final TreeItem<PhieuMuon> root = new RecursiveTreeItem<>(pms, RecursiveTreeObject::getChildren);

        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);

        table.getColumns().setAll(colSach, colBanDoc, colNgayMuon, colThoiHanMuon, colGiaHan, colNhanVien);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

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
                        phieuMuon.giaHan.set(phieuMuon.giaHan.get() + 1);
                        phieuMuon.thoiHanMuon.set(phieuMuon.thoiHanMuon.get() + 7);
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
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal);
            table.setPredicate(pmProperty -> {
                PhieuMuon pm = pmProperty.getValue();
                Sach sach = SachDAO.getInstance().getByID(pm.getIdSach());
                TheThuVien theThuVien = TheThuVienDAO.getInstance().getByID(pm.getIdTheThuVien());
                BanDoc banDoc = BanDocDAO.getInstance().getByID(theThuVien.getIdBanDoc());
                NhanVien nhanVien = NhanVienDAO.getInstance().getByID(pm.getIdNhanVien());

                return pm.getIdSach().toString().contains(newValueNoAccent)
                        || pm.getIdTheThuVien().toString().contains(newValueNoAccent)
                        || pm.getIdNhanVien().toString().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(sach.getTenSach()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(banDoc.getHoVaTen()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(nhanVien.getHoVaTen()).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }
}
