package view;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.controllers.QuanLyNhanVienController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.NhanVienDAO;
import model.dto.NhanVien;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QuanLyNhanVienView extends QuanLyNhanVienController {
    public QuanLyNhanVienView(StackPane rootPane, BorderPane mainPane) {
        super(rootPane, mainPane);

        JFXTreeTableColumn<NhanVien, String> colTenNhanVien = new JFXTreeTableColumn<>("T\u00EAn Nh\u00E2n vi\u00EAn");
        JFXTreeTableColumn<NhanVien, Date> colNgaySinh = new JFXTreeTableColumn<>("Ng\u00E0y Sinh");
        JFXTreeTableColumn<NhanVien, String> colGioiTinh = new JFXTreeTableColumn<>("Gi\u1EDBi T\u00EDnh");
        JFXTreeTableColumn<NhanVien, String> colCmnd = new JFXTreeTableColumn<>("S\u1ED1 CMND");
        JFXTreeTableColumn<NhanVien, String> colEmail = new JFXTreeTableColumn<>("Email");
        JFXTreeTableColumn<NhanVien, String> colSdt = new JFXTreeTableColumn<>("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
        JFXTreeTableColumn<NhanVien, String> colUserName = new JFXTreeTableColumn<>("UserName");
        JFXTreeTableColumn<NhanVien, String> colPassWord = new JFXTreeTableColumn<>("M\u1EADt kh\u1EA9u");
        JFXTreeTableColumn<NhanVien, String> colChucDanh = new JFXTreeTableColumn<>("Ch\u1EE9c danh");
        columnSetCellValueFactory(colTenNhanVien, colNgaySinh, colGioiTinh, colCmnd, colEmail, colSdt, colUserName, colPassWord, colChucDanh);

        final TreeItem<NhanVien> root = new RecursiveTreeItem<>(NhanVienDAO.getInstance().all(), RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colTenNhanVien, colNgaySinh, colGioiTinh, colCmnd, colEmail, colSdt, colUserName, colPassWord, colChucDanh);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableSetRowFactory();

        JFXButton taoNhanVien = new JFXButton("T\u1EA1o Nh\u00E2n Vi\u00EAn");
        JFXButton nhapNhanVien = new JFXButton("Nh\u1EADp Nh\u00E2n Vi\u00EAn");
        JFXButton xuatNhanVien = new JFXButton("Xu\u1EA5t Nh\u00E2n Vi\u00EAn");
        btnSetOnAction(taoNhanVien, nhapNhanVien, xuatNhanVien);
        taoNhanVien.getStyleClass().add("add-button");
        nhapNhanVien.getStyleClass().add("add-button");
        xuatNhanVien.getStyleClass().add("add-button");
        listBtn.addAll(Arrays.asList(taoNhanVien, nhapNhanVien, xuatNhanVien));
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }

    public List<JFXButton> getListBtn(){
        return listBtn;
    }
}
