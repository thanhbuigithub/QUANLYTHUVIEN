package controller.nhanVienController;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controller.CellFactory;
import controller.Main;
import controller.VNCharacterUtils;
import controller.mainController.MainController;
import controller.nhanVienController.suaNhanVien.SuaNhanVienController;
import controller.util.AlertMaker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modules.dao.NhanVienDAO;
import modules.entities.NhanVien;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class NhanVienController {
    private JFXTreeTableView<NhanVien> table = new JFXTreeTableView<>();

    private JFXTreeTableColumn<NhanVien, String> colTenNhanVien = new JFXTreeTableColumn<>("T\u00EAn Nh\u00E2n vi\u00EAn");
    private JFXTreeTableColumn<NhanVien, Date> colNgaySinh = new JFXTreeTableColumn<>("Ng\u00E0y Sinh");
    private JFXTreeTableColumn<NhanVien, String> colGioiTinh = new JFXTreeTableColumn<>("Gi\u1EDBi T\u00EDnh");
    private JFXTreeTableColumn<NhanVien, String> colCmnd = new JFXTreeTableColumn<>("S\u1ED1 CMND");
    private JFXTreeTableColumn<NhanVien, String> colEmail = new JFXTreeTableColumn<>("Email");
    private JFXTreeTableColumn<NhanVien, String> colSdt = new JFXTreeTableColumn<>("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
    private JFXTreeTableColumn<NhanVien, String> colChucDanh = new JFXTreeTableColumn<>("Ch\u1EE9c danh");

    public NhanVienController(StackPane rootPane, BorderPane mainPane) {
        colTenNhanVien.setCellValueFactory((param) -> {
            if (colTenNhanVien.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleStringProperty(NhanVien.getHoVaTen());
            } else return colTenNhanVien.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTenNhanVien);

        colNgaySinh.setCellValueFactory((param) -> {
            if (colNgaySinh.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleObjectProperty<>(NhanVien.getNgaySinh());
            } else return colNgaySinh.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colNgaySinh);

        colGioiTinh.setCellValueFactory((param) -> {
            if (colGioiTinh.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                if (NhanVien.getGioiTinh() == 0) {
                    return new SimpleStringProperty("Nam");
                }
                return new SimpleStringProperty("N\u1EEF");
            } else return colGioiTinh.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colGioiTinh);

        colCmnd.setCellValueFactory((param) -> {
            if (colCmnd.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleStringProperty(NhanVien.getCmnd());
            } else return colCmnd.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colCmnd);

        colEmail.setCellValueFactory((param) -> {
            if (colEmail.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleStringProperty(NhanVien.getEmail());
            } else return colEmail.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colEmail);

        colSdt.setCellValueFactory((param) -> {
            if (colSdt.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleStringProperty(NhanVien.getSdt());
            } else return colSdt.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSdt);

        colChucDanh.setCellValueFactory((param) -> {
            if (colChucDanh.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                if (NhanVien.getChucDanh() == 0) {
                    return new SimpleStringProperty("Nh\u00E2n vi\u00EAn");
                } else if (NhanVien.getChucDanh() == 1) {
                    return new SimpleStringProperty("Admin");
                }
                return new SimpleStringProperty("Th\u1EE7 th\u01B0");
            } else return colChucDanh.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colChucDanh);

        final TreeItem<NhanVien> root = new RecursiveTreeItem<>(NhanVienDAO.getInstance().all(), RecursiveTreeObject::getChildren);

        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colTenNhanVien, colNgaySinh, colGioiTinh, colCmnd, colEmail, colSdt, colChucDanh);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemChinhSua = new MenuItem("Ch\u1EC9nh s\u1EEDa");
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().addAll(itemChinhSua, itemXoa);
                itemChinhSua.setOnAction((e) -> {
                    NhanVien nhanVien = getTreeTableView().getTreeItem(getIndex()).getValue();
                    try {
                        showChinhSuaDialog(nhanVien);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                itemXoa.setOnAction((e) -> {
                    ObjectProperty<JFXDialog> dialogProperty = new SimpleObjectProperty<>();
                    JFXButton btnYES = new JFXButton("YES");
                    JFXButton btnNO = new JFXButton("NO");
                    btnYES.setOnAction(event -> {
                        NhanVien nhanVien = getTreeTableView().getTreeItem(getIndex()).getValue();
                        if (NhanVienDAO.getInstance().remove(nhanVien)) {
                            getTreeTableView().getRoot().getChildren().remove(getIndex());
                        }
                    });
                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "X\u00F3a nh\u00E2n vi\u00EAn ?", "B\u1EA1n c\u00F3 mu\u1ED1n x\u00F3a nh\u00E2n vi\u00EAn n\u00E0y kh\u00F4ng ?");
                });
                this.setContextMenu(addMenu);
            }
        });
    }

    private void showChinhSuaDialog(NhanVien NhanVien) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/view/nhanVien/suaNhanVien.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ch\u1EC9nh s\u1EEDa Nh\u00E2n vi\u00EAn");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 478, 530);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        SuaNhanVienController controller = loader.getController();
        controller.bindingData(NhanVien);
        stage.showAndWait();
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal);
            table.setPredicate(nvProperty -> {
                NhanVien nhanVien = nvProperty.getValue();
                switch (newValueNoAccent) {
                    case "Nh\u00E2n vi\u00EAn":
                        return nhanVien.getChucDanh().equals(0);
                    case "Admin":
                        return nhanVien.getChucDanh().equals(1);
                    case "Th\u1EE7 th\u01B0":
                        return nhanVien.getChucDanh().equals(2);
                }
                return VNCharacterUtils.removeAccent(nhanVien.getHoVaTen()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(nhanVien.getCmnd()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(nhanVien.getEmail()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(nhanVien.getSdt()).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }
}


