package view;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.controllers.QuanLyBanDocController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.BanDocDAO;
import model.dto.BanDoc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QuanLyBanDocView extends QuanLyBanDocController {
    public QuanLyBanDocView(StackPane rootPane, BorderPane mainPane) {
        super(rootPane, mainPane);

        JFXTreeTableColumn<BanDoc, String> colTenBanDoc = new JFXTreeTableColumn<>("T\u00EAn B\u1EA1n \u0110\u1ECDc");
        JFXTreeTableColumn<BanDoc, Date> colNgaySinh = new JFXTreeTableColumn<>("Ng\u00E0y Sinh");
        JFXTreeTableColumn<BanDoc, String> colGioiTinh = new JFXTreeTableColumn<>("Gi\u1EDBi T\u00EDnh");
        JFXTreeTableColumn<BanDoc, String> colCmnd = new JFXTreeTableColumn<>("S\u1ED1 CMND");
        JFXTreeTableColumn<BanDoc, String> colEmail = new JFXTreeTableColumn<>("Email");
        JFXTreeTableColumn<BanDoc, String> colSdt = new JFXTreeTableColumn<>("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
        JFXTreeTableColumn<BanDoc, Date> colNgayTaoThe = new JFXTreeTableColumn<>("Ng\u00E0y t\u1EA1o th\u1EBB");
        JFXTreeTableColumn<BanDoc, Integer> colThoiHanSuDung = new JFXTreeTableColumn<>("Th\u1EDDi h\u1EA1n s\u1EED d\u1EE5ng");

        columnSetCellValueFactory(colTenBanDoc, colNgaySinh, colGioiTinh, colCmnd, colEmail, colSdt, colNgayTaoThe, colThoiHanSuDung);

        final TreeItem<BanDoc> root = new RecursiveTreeItem<>(BanDocDAO.getInstance().all(), RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colTenBanDoc, colNgaySinh, colGioiTinh, colCmnd, colEmail, colSdt, colNgayTaoThe, colThoiHanSuDung);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableSetRowFactory();

        JFXButton taoBanDoc = new JFXButton("T\u1EA1o B\u1EA1n \u0110\u1ECDc");
        JFXButton nhapBanDoc = new JFXButton("Nh\u1EADp B\u1EA1n \u0110\u1ECDc");
        JFXButton xuatBanDoc = new JFXButton("Xu\u1EA5t B\u1EA1n \u0110\u1ECDc");
        btnSetOnAction(taoBanDoc, nhapBanDoc, xuatBanDoc);
        taoBanDoc.getStyleClass().add("add-button");
        nhapBanDoc.getStyleClass().add("add-button");
        xuatBanDoc.getStyleClass().add("add-button");
        listBtn.addAll(Arrays.asList(taoBanDoc, nhapBanDoc, xuatBanDoc));
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }

    public List<JFXButton> getListBtn(){
        return listBtn;
    }
}
