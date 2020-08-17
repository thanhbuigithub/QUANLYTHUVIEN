package view;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.controllers.QuanLySachController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.SachDAO;
import model.dto.Sach;

import java.util.Arrays;
import java.util.List;

public class QuanLySachView extends QuanLySachController {
    public QuanLySachView(StackPane rootPane, BorderPane mainPane) {
        super(rootPane, mainPane);

        JFXTreeTableColumn<Sach, String> colTenSach = new JFXTreeTableColumn<>("T\u00EAn S\u00E1ch");
        JFXTreeTableColumn<Sach, Integer> colNamXuatBan = new JFXTreeTableColumn<>("N\u0103m Xu\u1EA5t B\u1EA3n");
        JFXTreeTableColumn<Sach, String> colNhaXuatBan = new JFXTreeTableColumn<>("Nh\u00E0 Xu\u1EA5t B\u1EA3n");
        JFXTreeTableColumn<Sach, String> colNgonNgu = new JFXTreeTableColumn<>("Ng\u00F4n Ng\u1EEF");
        JFXTreeTableColumn<Sach, String> colTacGia = new JFXTreeTableColumn<>("T\u00E1c Gi\u1EA3");
        JFXTreeTableColumn<Sach, String> colTheLoai = new JFXTreeTableColumn<>("Th\u1EC3 Lo\u1EA1i");
        JFXTreeTableColumn<Sach, String> colViTri = new JFXTreeTableColumn<>("V\u1ECB Tr\u00ED");
        JFXTreeTableColumn<Sach, String> colGiaBia = new JFXTreeTableColumn<>("Gi\u00E1 B\u00ECa");
        JFXTreeTableColumn<Sach, Integer> colSoLuong = new JFXTreeTableColumn<>("S\u1ED1 L\u01B0\u1EE3ng");
        JFXTreeTableColumn<Sach, Integer> colSoTrang = new JFXTreeTableColumn<>("S\u1ED1 Trang");

        columnSetCellValueFactory(colTenSach, colNamXuatBan, colNhaXuatBan, colNgonNgu, colTacGia, colTheLoai, colViTri, colGiaBia, colSoLuong, colSoTrang);

        final TreeItem<Sach> root = new RecursiveTreeItem<>(SachDAO.getInstance().all(), RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colTenSach, colNamXuatBan, colNhaXuatBan, colNgonNgu, colTacGia, colTheLoai, colViTri, colGiaBia, colSoLuong, colSoTrang);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableSetRowFactory();

        JFXButton taoSach = new JFXButton("T\u1EA1o s\u00E1ch");
        JFXButton nhapSach = new JFXButton("Nh\u1EADp s\u00E1ch");
        JFXButton xuatSach = new JFXButton("Xu\u1EA5t s\u00E1ch");
        btnSetOnAction(taoSach, nhapSach, xuatSach);
        taoSach.getStyleClass().add("add-button");
        nhapSach.getStyleClass().add("add-button");
        xuatSach.getStyleClass().add("add-button");
        listBtn.addAll(Arrays.asList(taoSach, nhapSach, xuatSach));
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }

    public List<JFXButton> getListBtn(){
        return listBtn;
    }
}
