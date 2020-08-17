package view;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.controllers.MuonSachController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.dao.PhieuMuonDAO;
import model.dto.PhieuMuon;


import java.util.Date;
import java.util.List;

public class MuonSachView extends MuonSachController{
    public MuonSachView(StackPane rootPane, BorderPane mainPane) {
        super(rootPane, mainPane);

        JFXTreeTableColumn<PhieuMuon, String> colSach = new JFXTreeTableColumn<>("S\u00E1ch");
        JFXTreeTableColumn<PhieuMuon, String> colBanDoc = new JFXTreeTableColumn<>("B\u1EA1n \u0110\u1ECDc");
        JFXTreeTableColumn<PhieuMuon, Date> colNgayMuon = new JFXTreeTableColumn<>("Ng\u00E0y M\u01B0\u1EE3n");
        JFXTreeTableColumn<PhieuMuon, Integer> colThoiHanMuon = new JFXTreeTableColumn<>("Th\u1EDDi H\u1EA1n M\u01B0\u1EE3n");
        JFXTreeTableColumn<PhieuMuon, Integer> colGiaHan = new JFXTreeTableColumn<>("Gia H\u1EA1n");
        JFXTreeTableColumn<PhieuMuon, String> colNhanVien = new JFXTreeTableColumn<>("Nh\u00E2n Vi\u00EAn");

        columnSetCellValueFactory(colSach, colBanDoc, colNgayMuon, colThoiHanMuon, colGiaHan, colNhanVien);

        CellFactory.getInstance().StringValueFactory(colSach);
        CellFactory.getInstance().StringValueFactory(colBanDoc);
        CellFactory.getInstance().DateValueFactory(colNgayMuon);
        CellFactory.getInstance().StringValueFactory(colNhanVien);

        final TreeItem<PhieuMuon> root = new RecursiveTreeItem<>(PhieuMuonDAO.getInstance().all(), RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
        table.setEditable(true);
        table.getColumns().setAll(colSach, colBanDoc, colNgayMuon, colThoiHanMuon, colGiaHan, colNhanVien);
        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableSetRowFactory();

        JFXButton taoPhieuMuon = new JFXButton("T\u1EA1o phi\u1EBFu m\u01B0\u1EE3n");
        btnSetOnAction(taoPhieuMuon);
        taoPhieuMuon.getStyleClass().add("add-button");
        listBtn.add(taoPhieuMuon);
    }

    public JFXTreeTableView getTable(JFXTextField tfSearch) {
        setPredicateTable(tfSearch);
        return table;
    }

    public List<JFXButton> getListBtn(){
        return listBtn;
    }

}
