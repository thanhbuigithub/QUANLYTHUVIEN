package controller.traSachController;

import com.jfoenix.controls.*;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controller.CellFactory;
import controller.VNCharacterUtils;
import controller.util.AlertMaker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import modules.dao.*;
import modules.entities.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TraSachController {
    private JFXTreeTableView<PhieuTra> table = new JFXTreeTableView<>();
    private JFXTreeTableColumn<PhieuTra, String> colSach = new JFXTreeTableColumn<>("S\u00E1ch");
    private JFXTreeTableColumn<PhieuTra, String> colBanDoc = new JFXTreeTableColumn<>("B\u1EA1n \u0110\u1ECDc");
    private JFXTreeTableColumn<PhieuTra, Date> colNgayMuon = new JFXTreeTableColumn<>("Ng\u00E0y M\u01B0\u1EE3n");
    private JFXTreeTableColumn<PhieuTra, Date> colNgayTra = new JFXTreeTableColumn<>("Ng\u00E0y Tr\u1EA3");
    private JFXTreeTableColumn<PhieuTra, String> colTinhTrang = new JFXTreeTableColumn<>("T\u00ECnh Tr\u1EA1ng");
    private JFXTreeTableColumn<PhieuTra, String> colBoiThuong = new JFXTreeTableColumn<>("B\u1ED3i th\u01B0\u1EDDng");
    private JFXTreeTableColumn<PhieuTra, String> colNhanVien = new JFXTreeTableColumn<>("Nh\u00E2n Vi\u00EAn");

    ObservableList<PhieuTra> pts = FXCollections.observableArrayList();

    public TraSachController(StackPane rootPane, BorderPane mainPane) {
        pts.addAll(PhieuTraDAO.getInstance().all());

        colSach.setCellValueFactory((param) -> {
            if (colSach.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = PhieuMuonDAO.getInstance().getByID(phieuTra.getIdPhieuMuon());
                Sach sach = SachDAO.getInstance().getByID(phieuMuon.getIdSach());
                return new SimpleStringProperty(phieuMuon.getIdSach() + " - " + sach.getTenSach());
            } else return colSach.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colSach);

        colBanDoc.setCellValueFactory((param) -> {
            if (colBanDoc.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = PhieuMuonDAO.getInstance().getByID(phieuTra.getIdPhieuMuon());
                BanDoc banDoc = BanDocDAO.getInstance().getByID(phieuMuon.getIdBanDoc());
                return new SimpleStringProperty(banDoc.getId() + " - " + banDoc.getHoVaTen());
            } else return colBanDoc.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colBanDoc);

        colNgayMuon.setCellValueFactory((param) -> {
            if (colNgayMuon.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = PhieuMuonDAO.getInstance().getByID(phieuTra.getIdPhieuMuon());
                return new SimpleObjectProperty<>(phieuMuon.getNgayMuon());
            } else return colNgayMuon.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colNgayMuon);

        colNgayTra.setCellValueFactory((param) -> {
            if (colNgayTra.validateValue(param)) {
                return new SimpleObjectProperty<>(param.getValue().getValue().getNgayTra());
            } else return colNgayTra.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colNgayTra);

        colTinhTrang.setCellValueFactory((param) -> {
            if (colTinhTrang.validateValue(param)) {
                return new SimpleStringProperty(param.getValue().getValue().getTinhTrang());
            } else return colTinhTrang.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colTinhTrang);

        colBoiThuong.setCellValueFactory((param) -> {
            if (colBoiThuong.validateValue(param)) {
                return new SimpleStringProperty(param.getValue().getValue().getBoiThuong());
            } else return colBoiThuong.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colBoiThuong);

        colNhanVien.setCellValueFactory((param) -> {
            if (colNhanVien.validateValue(param)) {
                PhieuTra phieuTra = param.getValue().getValue();
                PhieuMuon phieuMuon = PhieuMuonDAO.getInstance().getByID(phieuTra.getIdPhieuMuon());
                NhanVien nhanVien = NhanVienDAO.getInstance().getByID(phieuMuon.getIdNhanVien());
                return new SimpleStringProperty(phieuMuon.getIdNhanVien() + " - " + nhanVien.getHoVaTen());

            } else return colNhanVien.getComputedValue(param);
        });
        CellFactory.getInstance().StringValueFactory(colNhanVien);
        final TreeItem<PhieuTra> root = new RecursiveTreeItem<>(pts, RecursiveTreeObject::getChildren);

        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colSach, colBanDoc, colNgayMuon, colNgayTra, colTinhTrang, colBoiThuong, colNhanVien);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

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

                return pt.getIdPhieuMuon().toString().contains(newValueNoAccent);
            });
        });
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }
}
