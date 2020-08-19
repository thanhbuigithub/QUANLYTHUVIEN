package control.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import control.utilities.StageMaker;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import view.CellFactory;
import control.Main;
import control.utilities.AlertMaker;
import control.utilities.VNCharacterUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.*;
import model.dto.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class QuanLySachController {
    StackPane rootPane;
    BorderPane mainPane;
    protected final JFXTreeTableView<Sach> table = new JFXTreeTableView<>();
    protected final List<JFXButton> listBtn = new ArrayList<>();

    public QuanLySachController(StackPane rootPane, BorderPane mainPane){
        this.rootPane = rootPane;
        this.mainPane = mainPane;
    }

    protected void columnSetCellValueFactory(JFXTreeTableColumn<Sach, String> colTenSach, JFXTreeTableColumn<Sach, Integer> colNamXuatBan, JFXTreeTableColumn<Sach, String> colNhaXuatBan, JFXTreeTableColumn<Sach, String> colNgonNgu, JFXTreeTableColumn<Sach, String> colTacGia, JFXTreeTableColumn<Sach, String> colTheLoai, JFXTreeTableColumn<Sach, String> colViTri, JFXTreeTableColumn<Sach, String> colGiaBia, JFXTreeTableColumn<Sach, Integer> colSoLuong, JFXTreeTableColumn<Sach, Integer> colSoTrang){
        colTenSach.setCellValueFactory((param) -> {
            if (colTenSach.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getTenSach());
            } else return colTenSach.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTenSach);

        colNamXuatBan.setCellValueFactory((param) -> {
            if (colNamXuatBan.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleObjectProperty<>(sach.getNamXuatBan());
            } else return colNamXuatBan.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNamXuatBan);

        colNhaXuatBan.setCellValueFactory((param) -> {
            if (colNhaXuatBan.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getNhaXuatBan().stream().map(NhaXuatBan::getTenNhaXuatBan).collect(Collectors.joining(", ")));
            } else return colNhaXuatBan.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNhaXuatBan);

        colNgonNgu.setCellValueFactory((param) -> {
            if (colNgonNgu.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getNgonNgu().stream().map(NgonNgu::getTenNgonNgu).collect(Collectors.joining(", ")));
            } else return colNgonNgu.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colNgonNgu);

        colTacGia.setCellValueFactory((param) -> {
            if (colTacGia.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getTacGia().stream().map(TacGia::getTenTacGia).collect(Collectors.joining(", ")));
            } else return colTacGia.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTacGia);

        colTheLoai.setCellValueFactory((param) -> {
            if (colTheLoai.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getTheLoai().stream().map(TheLoai::getTenTheLoai).collect(Collectors.joining(", ")));
            } else return colTheLoai.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTheLoai);

        colViTri.setCellValueFactory((param) -> {
            if (colViTri.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleStringProperty(sach.getViTri().toString());
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
                return new SimpleObjectProperty<>(sach.getSoLuong());
            } else return colSoLuong.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSoLuong);

        colSoTrang.setCellValueFactory((param) -> {
            if (colSoTrang.validateValue(param)) {
                Sach sach = param.getValue().getValue();
                return new SimpleObjectProperty<>(sach.getSoTrang());
            } else return colSoLuong.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSoTrang);
    }

    protected void tableSetRowFactory(){
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

    protected void btnSetOnAction(JFXButton taoSach, JFXButton nhapSach, JFXButton xuatSach){
        taoSach.setOnAction(e -> {
            try {
                StageMaker.createStage("/view/themSach.fxml", "Th\u00EAm s\u00E1ch", 443, 720);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        taoSach.getStyleClass().add("add-button");

        nhapSach.setOnAction(e -> {
            try {
                nhapSach();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        nhapSach.getStyleClass().add("add-button");

        xuatSach.setOnAction(e -> {
            try {
                xuatSach();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        xuatSach.getStyleClass().add("add-button");
    }

    private void showChinhSuaDialog(Sach sach) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/view/suaSach.fxml"));
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
                String nhaXuatBan = sach.getNhaXuatBan().stream().map(NhaXuatBan::getTenNhaXuatBan).collect(Collectors.joining(", "));
                String ngonNgu = sach.getNgonNgu().stream().map(NgonNgu::getTenNgonNgu).collect(Collectors.joining(", "));
                String tacGia = sach.getTacGia().stream().map(TacGia::getTenTacGia).collect(Collectors.joining(", "));
                String theLoai = sach.getTheLoai().stream().map(TheLoai::getTenTheLoai).collect(Collectors.joining(", "));

                return VNCharacterUtils.removeAccent(sach.getTenSach()).toLowerCase().contains(newValueNoAccent)
                        || String.valueOf(sach.getNamXuatBan()).contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(nhaXuatBan).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(ngonNgu).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(theLoai).toLowerCase().contains(newValueNoAccent)
                        || VNCharacterUtils.removeAccent(tacGia).toLowerCase().contains(newValueNoAccent);
            });
        });
    }

    protected void nhapSach() throws IOException {
        FileChooser.ExtensionFilter excelFilter
                = new FileChooser.ExtensionFilter("Excel File", "*.xls");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(excelFilter);

        File file = fc.showOpenDialog(Main.stage);

        FileInputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Sach sach = new Sach();
            sach.setTenSach(row.getCell(0).getStringCellValue());
            sach.setNamXuatBan((int) row.getCell(1).getNumericCellValue());
            Set<NhaXuatBan> nhaXuatBanSet = new HashSet<>();
            nhaXuatBanSet.add(NhaXuatBanDAO.getInstance().getByID(Integer.parseInt(row.getCell(2).getStringCellValue())));
            sach.setNhaXuatBan(nhaXuatBanSet);
            Set<NgonNgu> ngonNguSet = new HashSet<>();
            ngonNguSet.add(NgonNguDAO.getInstance().getByID(Integer.parseInt(row.getCell(3).getStringCellValue())));
            sach.setNgonNgu(ngonNguSet);
            Set<TacGia> tacGiaSet = new HashSet<>();
            tacGiaSet.add(TacGiaDAO.getInstance().getByID(Integer.parseInt(row.getCell(4).getStringCellValue())));
            sach.setTacGia(tacGiaSet);

            Set<TheLoai> theLoaiSet = new HashSet<>();
            theLoaiSet.add(TheLoaiDAO.getInstance().getByID(Integer.parseInt(row.getCell(5).getStringCellValue())));
            sach.setTheLoai(theLoaiSet);

            ViTri viTri = ViTriDAO.getInstance().getByID((int) row.getCell(6).getNumericCellValue());
            sach.setViTri(viTri);
            sach.setGiaBia((int) row.getCell(7).getNumericCellValue());
            sach.setSoLuong((int) row.getCell(8).getNumericCellValue());
            sach.setSoTrang((int) row.getCell(9).getNumericCellValue());

            System.out.println();
            SachDAO.getInstance().insert(sach);
        }

        SachDAO.getInstance().reload();
    }

    protected void xuatSach() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Danh sach Sach");

        List<Sach> list = SachDAO.getInstance().all();

        int rownum = 0;
        Cell cell;
        Row row;
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("TenSach");
        cell.setCellStyle(style);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("NamXuatBan");
        cell.setCellStyle(style);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("NhaXuatBan");
        cell.setCellStyle(style);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("NgonNgu");
        cell.setCellStyle(style);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("TacGia");
        cell.setCellStyle(style);

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("TheLoai");
        cell.setCellStyle(style);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("MaViTri");
        cell.setCellStyle(style);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("GiaBia");
        cell.setCellStyle(style);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("SoLuong");
        cell.setCellStyle(style);

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("SoTrang");
        cell.setCellStyle(style);

        for (Sach sach : list) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(sach.getTenSach());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(sach.getNamXuatBan());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(sach.getTheLoai().stream().map(e -> String.valueOf(e.getId())).collect(Collectors.joining(", ")));

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(sach.getNgonNgu().stream().map(e -> String.valueOf(e.getId())).collect(Collectors.joining(", ")));

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(sach.getTacGia().stream().map(e -> String.valueOf(e.getId())).collect(Collectors.joining(", ")));

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(sach.getTheLoai().stream().map(e -> String.valueOf(e.getId())).collect(Collectors.joining(", ")));

            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(sach.getViTri().getId());

            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(sach.getGiaBia());

            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellValue(sach.getSoLuong());

            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellValue(sach.getSoTrang());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\assets\\danh_sach_sach_" + sdf.format(new Date()) + ".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }

    protected HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
