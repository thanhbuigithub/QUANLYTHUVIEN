package control.controllers;

import com.jfoenix.controls.*;
import control.utilities.DateUtil;
import control.utilities.StageMaker;
import javafx.stage.FileChooser;
import model.dto.NhanVien;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.NhanVienDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class QuanLyNhanVienController {
    StackPane rootPane;
    BorderPane mainPane;
    protected final JFXTreeTableView<NhanVien> table = new JFXTreeTableView<>();
    protected final List<JFXButton> listBtn = new ArrayList<>();

    public QuanLyNhanVienController(StackPane rootPane, BorderPane mainPane){
        this.rootPane = rootPane;
        this.mainPane = mainPane;
    }

    protected void columnSetCellValueFactory(JFXTreeTableColumn<NhanVien, String> colTenNhanVien, JFXTreeTableColumn<NhanVien, Date> colNgaySinh, JFXTreeTableColumn<NhanVien, String> colGioiTinh, JFXTreeTableColumn<NhanVien, String> colCmnd, JFXTreeTableColumn<NhanVien, String> colEmail, JFXTreeTableColumn<NhanVien, String> colSdt, JFXTreeTableColumn<NhanVien, String> colUserName, JFXTreeTableColumn<NhanVien, String> colPassWord, JFXTreeTableColumn<NhanVien, String> colChucDanh) {
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

        colUserName.setCellValueFactory((param) -> {
            if (colUserName.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleStringProperty(NhanVien.getUsername());
            } else return colUserName.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colUserName);

        colPassWord.setCellValueFactory((param) -> {
            if (colPassWord.validateValue(param)) {
                NhanVien NhanVien = param.getValue().getValue();
                return new SimpleStringProperty(NhanVien.getPassword());
            } else return colPassWord.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colPassWord);

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
    }

    protected void tableSetRowFactory(){
        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemNangCap = new MenuItem("N\u00E2ng c\u1EA5p th\u00E0nh Admin");
                MenuItem itemXoa = new MenuItem("Xo\u00E1");
                addMenu.getItems().addAll(itemNangCap, itemXoa);
                itemNangCap.setOnAction((e) -> {
                    NhanVien nhanVien = getTreeTableView().getTreeItem(getIndex()).getValue();
                    nhanVien.setChucDanh(3);
                    NhanVienDAO.getInstance().update(nhanVien);
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

    protected void btnSetOnAction(JFXButton taoNhanVien, JFXButton nhapNhanVien, JFXButton xuatNhanVien) {
        taoNhanVien.setOnAction(e -> {
            try {
                StageMaker.createStage("/view/themNhanVien.fxml", "Th\u00EAm nh\u00E2n vi\u00EAn", 443, 810);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        nhapNhanVien.setOnAction(e -> {
            try {
                nhapNhanVien();
            } catch (IOException | ParseException ioException) {
                ioException.printStackTrace();
            }
        });
        nhapNhanVien.getStyleClass().add("add-button");

        xuatNhanVien.setOnAction(e -> {
            try {
                xuatNhanVien();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        xuatNhanVien.getStyleClass().add("add-button");
    }

    private void showChinhSuaDialog(NhanVien nhanVien) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/view/suaNhanVien.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ch\u1EC9nh s\u1EEDa Nh\u00E2n vi\u00EAn");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 480, 670);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        SuaNhanVienController controller = loader.getController();
        controller.bindingData(nhanVien);
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

    protected void nhapNhanVien() throws IOException, ParseException {
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
            NhanVien nhanVien = new NhanVien();
            nhanVien.setHoVaTen(row.getCell(0).getStringCellValue());
            nhanVien.setNgaySinh(DateUtil.StringToDate(row.getCell(1).getStringCellValue(), "dd/MM/yyyy"));
            nhanVien.setGioiTinh((int) row.getCell(2).getNumericCellValue());
            nhanVien.setCmnd(row.getCell(3).getStringCellValue());
            nhanVien.setEmail(row.getCell(4).getStringCellValue());
            nhanVien.setSdt(row.getCell(5).getStringCellValue());
            nhanVien.setUsername(row.getCell(6).getStringCellValue());
            nhanVien.setPassword(row.getCell(7).getStringCellValue());
            nhanVien.setChucDanh((int) row.getCell(8).getNumericCellValue());

            NhanVienDAO.getInstance().insert(nhanVien);
        }

        NhanVienDAO.getInstance().reload();
    }

    protected void xuatNhanVien() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Danh sach Nhan Vien");

        List<NhanVien> list = NhanVienDAO.getInstance().all();

        int rownum = 0;
        Cell cell;
        Row row;
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("HoVaTen");
        cell.setCellStyle(style);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("NgaySinh");
        cell.setCellStyle(style);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("GioiTinh");
        cell.setCellStyle(style);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("CMND");
        cell.setCellStyle(style);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Email");
        cell.setCellStyle(style);

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("SDT");
        cell.setCellStyle(style);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Username");
        cell.setCellStyle(style);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Password");
        cell.setCellStyle(style);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("ChucDanh");
        cell.setCellStyle(style);

        for (NhanVien nhanVien : list) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(nhanVien.getHoVaTen());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(DateUtil.DateToString(nhanVien.getNgaySinh(),"dd/MM/yyyy"));

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(nhanVien.getGioiTinh());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(nhanVien.getCmnd());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(nhanVien.getEmail());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(nhanVien.getSdt());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(nhanVien.getUsername());

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(nhanVien.getPassword());

            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellValue(nhanVien.getChucDanh());
        }

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\assets\\danh_sach_nhan_vien_" + DateUtil.DateToString(new Date(), "ddMMyyyy_HHmmss") + ".xls");
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


