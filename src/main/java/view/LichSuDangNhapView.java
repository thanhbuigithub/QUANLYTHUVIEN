package view;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.controllers.LichSuDangNhapController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.LichSuDangNhapDAO;
import model.dto.LichSuDangNhap;

import java.util.List;

public class LichSuDangNhapView extends LichSuDangNhapController {
    public LichSuDangNhapView(StackPane rootPane, BorderPane mainPane) {
        super(rootPane, mainPane);

        JFXTreeTableColumn<LichSuDangNhap, String> colNhanVien = new JFXTreeTableColumn<>("Nh\u00E2n Vi\u00EAn");
        JFXTreeTableColumn<LichSuDangNhap, String> colThoiGianDangNhap = new JFXTreeTableColumn<>("Th\u1EDDi gian \u0111\u0103ng nh\u1EADp");
        JFXTreeTableColumn<LichSuDangNhap, String> colThoiGianDangXuat = new JFXTreeTableColumn<>("Th\u1EDDi gian \u0111\u0103ng xu\u1EA5t");

        columnSetCellValueFactory(colNhanVien, colThoiGianDangNhap, colThoiGianDangXuat);

        CellFactory.getInstance().StringValueFactory(colNhanVien);

        final TreeItem<LichSuDangNhap> root = new RecursiveTreeItem<>(LichSuDangNhapDAO.getInstance().all(), RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colNhanVien, colThoiGianDangNhap, colThoiGianDangXuat);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableSetRowFactory();
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }

    public List<JFXButton> getListBtn(){
        return listBtn;
    }

}
