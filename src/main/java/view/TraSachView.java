package view;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.controllers.TraSachController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.PhieuTraDAO;
import model.dto.PhieuTra;

import java.util.Date;
import java.util.List;

public class TraSachView extends TraSachController {
    public TraSachView(StackPane rootPane, BorderPane mainPane) {
        super(rootPane, mainPane);

        JFXTreeTableColumn<PhieuTra, String> colSach = new JFXTreeTableColumn<>("S\u00E1ch");
        JFXTreeTableColumn<PhieuTra, String> colBanDoc = new JFXTreeTableColumn<>("B\u1EA1n \u0110\u1ECDc");
        JFXTreeTableColumn<PhieuTra, Date> colNgayMuon = new JFXTreeTableColumn<>("Ng\u00E0y M\u01B0\u1EE3n");
        JFXTreeTableColumn<PhieuTra, Date> colNgayHenTra = new JFXTreeTableColumn<>("Ng\u00E0y H\u1EB9n Tr\u1EA3");
        JFXTreeTableColumn<PhieuTra, Date> colNgayThucTra = new JFXTreeTableColumn<>("Ng\u00E0y th\u1EF1c tr\u1EA3");
        JFXTreeTableColumn<PhieuTra, String> colTinhTrang = new JFXTreeTableColumn<>("T\u00ECnh Tr\u1EA1ng");
        JFXTreeTableColumn<PhieuTra, String> colBoiThuong = new JFXTreeTableColumn<>("B\u1ED3i th\u01B0\u1EDDng");

        columnSetCellValueFactory(colSach, colBanDoc, colNgayMuon, colNgayHenTra, colNgayThucTra, colTinhTrang, colBoiThuong);

        CellFactory.getInstance().StringValueFactory(colSach);
        CellFactory.getInstance().StringValueFactory(colBanDoc);
        CellFactory.getInstance().DateValueFactory(colNgayMuon);
        CellFactory.getInstance().DateValueFactory(colNgayHenTra);
        CellFactory.getInstance().DateValueFactory(colNgayThucTra);
        CellFactory.getInstance().StringValueFactory(colTinhTrang);
        CellFactory.getInstance().StringValueFactory(colBoiThuong);

        final TreeItem<PhieuTra> root = new RecursiveTreeItem<>(PhieuTraDAO.getInstance().all(), RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colSach, colBanDoc, colNgayMuon, colNgayHenTra, colNgayThucTra, colTinhTrang, colBoiThuong);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableSetRowFactory();

        JFXButton taoPhieuTra = new JFXButton("T\u1EA1o phi\u1EBFu tr\u1EA3");
        btnSetOnAction(taoPhieuTra);
        taoPhieuTra.getStyleClass().add("add-button");
        listBtn.add(taoPhieuTra);
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }

    public List<JFXButton> getListBtn(){
        return listBtn;
    }
}
