package controller.quanLySachController;

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

public class QuanLySachController {
    private JFXTreeTableView<Sach> table = new JFXTreeTableView<>();

    private JFXTreeTableColumn<Sach, String> colTenSach = new JFXTreeTableColumn<>("T\u00EAn S\u00E1ch");
    private JFXTreeTableColumn<Sach, Integer> colNamXuatBan = new JFXTreeTableColumn<>("N\u0103m Xu\u1EA5t B\u1EA3n");
    private JFXTreeTableColumn<Sach, String> colNhaXuatBan = new JFXTreeTableColumn<>("Nh\u00E0 Xu\u1EA5t B\u1EA3n");
    private JFXTreeTableColumn<Sach, String> colNgonNgu = new JFXTreeTableColumn<>("Ng\u00F4n Ng\u1EEF");
    private JFXTreeTableColumn<Sach, String> colTacGia = new JFXTreeTableColumn<>("T\u00E1c Gi\u1EA3");
    private JFXTreeTableColumn<Sach, String> colTheLoai = new JFXTreeTableColumn<>("Th\u1EC3 Lo\u1EA1i");
    private JFXTreeTableColumn<Sach, String> colViTri = new JFXTreeTableColumn<>("V\u1ECB Tr\u00ED");
    private JFXTreeTableColumn<Sach, String> colGiaBia = new JFXTreeTableColumn<>("Gi\u00E1 B\u00ECa");
    private JFXTreeTableColumn<Sach, Integer> colSoLuong = new JFXTreeTableColumn<>("S\u1ED1 L\u01B0\u1EE3ng");
    private JFXTreeTableColumn<Sach, Integer> colSoTrang = new JFXTreeTableColumn<>("S\u1ED1 Trang");

    private final String SEPARATOR = ", ";

    ObservableList<Sach> listSach = FXCollections.observableArrayList();

    public QuanLySachController(StackPane rootPane, BorderPane mainPane) {
        listSach.addAll(SachDAO.getInstance().all());

        colTenSach.setCellValueFactory((param) -> {
            if (colTenSach.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return sach.tenSach;
            } else return colTenSach.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTenSach);

        colNamXuatBan.setCellValueFactory((param) -> {
            if (colNamXuatBan.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return sach.namXuatBan;
            } else return colNamXuatBan.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNamXuatBan);

        colNhaXuatBan.setCellValueFactory((param) -> {
            if (colNhaXuatBan.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                Nxb nxb = NhaXuatBanDAO.getInstance().getByID(sach.getMaNxb());
                return new SimpleObjectProperty<>(nxb.getTenNxb());
            } else return colNhaXuatBan.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNhaXuatBan);

        colNgonNgu.setCellValueFactory((param) -> {
            if (colNgonNgu.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                List<SachNgonNgu> list = SachNgonNguDAO.getInstance().getByIDSach(sach.getId());
                return new SimpleStringProperty(listSNNToString(list));
            } else return colNgonNgu.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNgonNgu);

        colTacGia.setCellValueFactory((param) -> {
            if (colTacGia.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                List<SachTacGia> list = SachTacGiaDAO.getInstance().getByIDSach(sach.getId());
                return new SimpleStringProperty(listSTGToString(list));
            } else return colTacGia.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTacGia);

        colTheLoai.setCellValueFactory((param) -> {
            if (colTheLoai.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                List<SachTheLoai> list = SachTheLoaiDAO.getInstance().getByIDSach(sach.getId());
                return new SimpleStringProperty(listSTLToString(list));
            } else return colTheLoai.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTheLoai);

        colViTri.setCellValueFactory((param) -> {
            if (colViTri.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                ViTri viTri = ViTriDAO.getInstance().getByID(sach.getMaViTri());
                return new SimpleStringProperty(viTri.toString());
            } else return colViTri.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colViTri);

        colGiaBia.setCellValueFactory((param) -> {
            if (colGiaBia.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getGiaBia() + " VN\u0110");
            } else return colGiaBia.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colGiaBia);

        colSoLuong.setCellValueFactory((param) -> {
            if (colSoLuong.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return sach.soLuong;
            } else return colSoLuong.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSoLuong);

        colSoTrang.setCellValueFactory((param) -> {
            if (colSoTrang.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return sach.soTrang;
            } else return colSoLuong.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSoTrang);

        final TreeItem<Sach> root = new RecursiveTreeItem<>(listSach, RecursiveTreeObject::getChildren);

        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);

        table.getColumns().setAll(colTenSach, colNamXuatBan, colNhaXuatBan, colNgonNgu, colTacGia, colTheLoai, colViTri, colGiaBia, colSoLuong, colSoTrang);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemGiaHan = new MenuItem("Gia h\u1EA1n");
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().addAll(itemGiaHan, itemXoa);
//                itemGiaHan.setOnAction((e) -> {
//                    JFXButton btnYES = new JFXButton("YES");
//                    JFXButton btnNO = new JFXButton("NO");
//                    btnYES.setOnAction(event -> {
//                        PhieuMuon phieuMuon = getTreeTableView().getTreeItem(getIndex()).getValue();
//                        phieuMuon.giaHan.set(phieuMuon.giaHan.get() + 1);
//                        phieuMuon.thoiHanMuon.set(phieuMuon.thoiHanMuon.get() + 7);
//                        PhieuMuonDAO.getInstance().update(phieuMuon);
//                    });
//                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "Gia h\u1EA1n phi\u1EBFu m\u01B0\u1EE3n", "B\u1EA1n c\u00F3 ch\u1EAFc mu\u1ED1n gia h\u1EA1n phi\u1EBFu m\u01B0\u1EE3n n\u00E0y trong 7 ng\u00E0y?");
//                });
//                itemXoa.setOnAction((e) -> {
//                    ObjectProperty<JFXDialog> dialogProperty = new SimpleObjectProperty<>();
//                    JFXButton btnYES = new JFXButton("YES");
//                    JFXButton btnNO = new JFXButton("NO");
//                    btnYES.setOnAction(event -> {
//                        PhieuMuon phieuMuon = getTreeTableView().getTreeItem(getIndex()).getValue();
//                        if (PhieuMuonDAO.getInstance().remove(phieuMuon)) {
//                            getTreeTableView().getRoot().getChildren().remove(getIndex());
//                        }
//                    });
//                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "Xo\u00E1 phi\u1EBFu m\u01B0\u1EE3n", "B\u1EA1n c\u00F3 ch\u1EAFc mu\u1ED1n xo\u00E1 phi\u1EBFu m\u01B0\u1EE3n n\u00E0y?");
//                });
                this.setContextMenu(addMenu);
            }
        });
    }

    private String listSNNToString(List<SachNgonNgu> list){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(snn->{
            stringBuilder.append(NgonNguDAO.getInstance().getByID(snn.getIdNgonNgu()).getTenNgonNgu());
            stringBuilder.append(SEPARATOR);
        });
        String ngonNgu = stringBuilder.toString();
        return ngonNgu.substring(0, ngonNgu.length() - SEPARATOR.length());
    }

    private String listSTGToString(List<SachTacGia> list){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(stg->{
            stringBuilder.append(TacGiaDAO.getInstance().getByID(stg.getIdTacGia()).getTenTacGia());
            stringBuilder.append(SEPARATOR);
        });
        String tacGia = stringBuilder.toString();
        return tacGia.substring(0, tacGia.length() - SEPARATOR.length());
    }

    private String listSTLToString(List<SachTheLoai> list){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(stl->{
            stringBuilder.append(TheLoaiDAO.getInstance().getByID(stl.getIdTheLoai()).getTenTheLoai());
            stringBuilder.append(SEPARATOR);
        });
        String theLoai = stringBuilder.toString();
        return theLoai.substring(0, theLoai.length() - SEPARATOR.length());
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal);
            table.setPredicate(sachProperty -> {
                Sach sach = sachProperty.getValue();
                Nxb nxb = NhaXuatBanDAO.getInstance().getByID(sach.getMaNxb());
                List<SachNgonNgu> listNgonNgu = SachNgonNguDAO.getInstance().getByIDSach(sach.getId());
                List<SachTacGia> listTacGia = SachTacGiaDAO.getInstance().getByIDSach(sach.getId());
                List<SachTheLoai> listTheLoai = SachTheLoaiDAO.getInstance().getByIDSach(sach.getId());

                return VNCharacterUtils.removeAccent(sach.getTenSach()).contains(newValueNoAccent)
                        || String.valueOf(sach.getNamXuatBan()).contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(nxb.getTenNxb()).contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(listSNNToString(listNgonNgu)).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(listSTGToString(listTacGia)).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(listSTLToString(listTheLoai)).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }
}
