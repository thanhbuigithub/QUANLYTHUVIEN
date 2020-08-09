package controller.quanLySachController;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.controls.events.JFXDialogEvent;
import controller.CellFactory;
import controller.Main;
import controller.VNCharacterUtils;
import controller.mainController.MainController;
import controller.quanLySachController.suaSach.SuaSachController;
import controller.util.AlertMaker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modules.dao.*;
import modules.entities.*;

import java.io.IOException;
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

    public QuanLySachController(StackPane rootPane, BorderPane mainPane) {
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
                return new SimpleStringProperty(sach.getNhaXuatBan());
            } else return colNhaXuatBan.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNhaXuatBan);

        colNgonNgu.setCellValueFactory((param) -> {
            if (colNgonNgu.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getNgonNgu());
            } else return colNgonNgu.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNgonNgu);

        colTacGia.setCellValueFactory((param) -> {
            if (colTacGia.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getTacGia());
            } else return colTacGia.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTacGia);

        colTheLoai.setCellValueFactory((param) -> {
            if (colTheLoai.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getTheLoai());
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

        final TreeItem<Sach> root = new RecursiveTreeItem<>(SachDAO.getInstance().all(), RecursiveTreeObject::getChildren);

        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);

        table.getColumns().setAll(colTenSach, colNamXuatBan, colNhaXuatBan, colNgonNgu, colTacGia, colTheLoai, colViTri, colGiaBia, colSoLuong, colSoTrang);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemChinhSua = new MenuItem("Ch\u1EC9nh s\u1EEDa");
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().addAll(itemChinhSua, itemXoa);
                itemChinhSua.setOnAction((e) -> {
                    Sach sach = getTreeTableView().getTreeItem(getIndex()).getValue();
                    try {
                        showChinhSuaDialog(sach);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                itemXoa.setOnAction((e) -> {
                    ObjectProperty<JFXDialog> dialogProperty = new SimpleObjectProperty<>();
                    JFXButton btnYES = new JFXButton("YES");
                    JFXButton btnNO = new JFXButton("NO");
                    btnYES.setOnAction(event -> {
                        Sach sach = getTreeTableView().getTreeItem(getIndex()).getValue();
                        if (SachDAO.getInstance().remove(sach)) {
                            getTreeTableView().getRoot().getChildren().remove(getIndex());
                        }
                    });
                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "Xo\u00E1 phi\u1EBFu m\u01B0\u1EE3n", "B\u1EA1n c\u00F3 ch\u1EAFc mu\u1ED1n xo\u00E1 phi\u1EBFu m\u01B0\u1EE3n n\u00E0y?");
                });
                this.setContextMenu(addMenu);
            }
        });
    }

    private void showChinhSuaDialog(Sach sach) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/view/quanLySach/suaSach.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ch\u1EC9nh s\u1EEDa s\u00E1ch");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 443, 720);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        SuaSachController controller = loader.getController();
        controller.bindingData(sach);
        stage.showAndWait();
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal);
            table.setPredicate(sachProperty -> {
                Sach sach = sachProperty.getValue();

                return VNCharacterUtils.removeAccent(sach.getTenSach()).toLowerCase().contains(newValueNoAccent)
                        || String.valueOf(sach.getNamXuatBan()).contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(sach.getNhaXuatBan()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(sach.getNgonNgu()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(sach.getTacGia()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(sach.getTheLoai()).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }
}
