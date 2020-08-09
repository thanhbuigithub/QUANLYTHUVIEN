package controller.banDocController;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controller.CellFactory;
import controller.Main;
import controller.VNCharacterUtils;
import controller.banDocController.suaBanDoc.SuaBanDocController;
import controller.mainController.MainController;
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
import modules.dao.BanDocDAO;
import modules.entities.BanDoc;
import modules.entities.Sach;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class BanDocController {
    private JFXTreeTableView<BanDoc> table = new JFXTreeTableView<>();

    private JFXTreeTableColumn<BanDoc, String> colTenBanDoc = new JFXTreeTableColumn<>("T\u00EAn B\u1EA1n \u0110\u1ECDc");
    private JFXTreeTableColumn<BanDoc, Date> colNgaySinh = new JFXTreeTableColumn<>("Ng\u00E0y Sinh");
    private JFXTreeTableColumn<BanDoc, String> colGioiTinh = new JFXTreeTableColumn<>("Gi\u1EDBi T\u00EDnh");
    private JFXTreeTableColumn<BanDoc, String> colCmnd = new JFXTreeTableColumn<>("S\u1ED1 CMND");
    private JFXTreeTableColumn<BanDoc, String> colEmail = new JFXTreeTableColumn<>("Email");
    private JFXTreeTableColumn<BanDoc, String> colSdt = new JFXTreeTableColumn<>("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
    private JFXTreeTableColumn<BanDoc, Date> colThoiHan = new JFXTreeTableColumn<>("Th\u1EDDi h\u1EA1n th\u1EBB");

    public BanDocController(StackPane rootPane, BorderPane mainPane) {
        colTenBanDoc.setCellValueFactory((param) -> {
            if (colTenBanDoc.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return banDoc.hoVaTen;
            } else return colTenBanDoc.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTenBanDoc);

        colNgaySinh.setCellValueFactory((param) -> {
            if (colNgaySinh.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return banDoc.ngaySinh;
            } else return colNgaySinh.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colNgaySinh);

        colGioiTinh.setCellValueFactory((param) -> {
            if (colGioiTinh.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                if (banDoc.getGioiTinh() == 0) {
                    return new SimpleStringProperty("Nam");
                }
                return new SimpleStringProperty("N\u1EEF");
            } else return colGioiTinh.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colGioiTinh);

        colCmnd.setCellValueFactory((param) -> {
            if (colCmnd.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return banDoc.cmnd;
            } else return colCmnd.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colCmnd);

        colEmail.setCellValueFactory((param) -> {
            if (colEmail.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return banDoc.email;
            } else return colEmail.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colEmail);

        colSdt.setCellValueFactory((param) -> {
            if (colSdt.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return banDoc.sdt;
            } else return colSdt.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSdt);

        colThoiHan.setCellValueFactory((param) -> {
            if (colThoiHan.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return banDoc.thoiHanSuDungThe;
            } else return colThoiHan.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colThoiHan);

        final TreeItem<BanDoc> root = new RecursiveTreeItem<>(BanDocDAO.getInstance().all(), RecursiveTreeObject::getChildren);

        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colTenBanDoc, colNgaySinh, colGioiTinh, colCmnd, colEmail, colSdt, colThoiHan);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemChinhSua = new MenuItem("Ch\u1EC9nh s\u1EEDa");
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().addAll(itemChinhSua, itemXoa);
                itemChinhSua.setOnAction((e) -> {
                    BanDoc banDoc = getTreeTableView().getTreeItem(getIndex()).getValue();
                    try {
                        showChinhSuaDialog(banDoc);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                itemXoa.setOnAction((e) -> {
                    ObjectProperty<JFXDialog> dialogProperty = new SimpleObjectProperty<>();
                    JFXButton btnYES = new JFXButton("YES");
                    JFXButton btnNO = new JFXButton("NO");
                    btnYES.setOnAction(event -> {
                        BanDoc banDoc = getTreeTableView().getTreeItem(getIndex()).getValue();
                        if (BanDocDAO.getInstance().remove(banDoc)) {
                            getTreeTableView().getRoot().getChildren().remove(getIndex());
                        }
                    });
                    AlertMaker.showMaterialDialog(rootPane, mainPane, Arrays.asList(btnNO, btnYES), "X\u00F3a B\u1EA1n \u0110\u1ECDc ?", "B\u1EA1n c\u00F3 mu\u1ED1n x\u00F3a B\u1EA1n \u0111\u1ECDc n\u00E0y kh\u00F4ng ?");
                });
                this.setContextMenu(addMenu);
            }
        });
    }

    private void showChinhSuaDialog(BanDoc banDoc) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/view/banDoc/suaBanDoc.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ch\u1EC9nh s\u1EEDa B\u1EA1n \u0110\u1ECDc");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 443, 720);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        SuaBanDocController controller = loader.getController();
        controller.bindingData(banDoc);
        stage.showAndWait();
    }

    public void setPredicateTable(JFXTextField tfSearch) {
        tfSearch.textProperty().addListener((o, oldVal, newVal) -> {
            String newValueNoAccent = VNCharacterUtils.removeAccent(newVal);
            table.setPredicate(bandocProperty -> {
                BanDoc bandoc = bandocProperty.getValue();
                return VNCharacterUtils.removeAccent(bandoc.getHoVaTen()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(bandoc.getCmnd()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(bandoc.getEmail()).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(bandoc.getSdt()).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }
}
