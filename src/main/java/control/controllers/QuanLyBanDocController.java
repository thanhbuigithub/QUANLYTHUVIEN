package control.controllers;

import com.jfoenix.controls.*;
import control.utilities.DateUtil;
import control.utilities.StageMaker;
import javafx.stage.FileChooser;
import model.dto.BanDoc;
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
import model.dao.BanDocDAO;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class QuanLyBanDocController {
    StackPane rootPane;
    BorderPane mainPane;
    protected final JFXTreeTableView<BanDoc> table = new JFXTreeTableView<>();
    protected final List<JFXButton> listBtn = new ArrayList<>();

    public QuanLyBanDocController(StackPane rootPane, BorderPane mainPane){
        this.rootPane = rootPane;
        this.mainPane = mainPane;
    }

    protected void columnSetCellValueFactory(JFXTreeTableColumn<BanDoc, String> colTenBanDoc, JFXTreeTableColumn<BanDoc, Date> colNgaySinh, JFXTreeTableColumn<BanDoc, String> colGioiTinh, JFXTreeTableColumn<BanDoc, String> colCmnd, JFXTreeTableColumn<BanDoc, String> colEmail, JFXTreeTableColumn<BanDoc, String> colSdt, JFXTreeTableColumn<BanDoc, Date> colNgayTaoThe, JFXTreeTableColumn<BanDoc, Integer> colThoiHanSuDung) {
        colTenBanDoc.setCellValueFactory((param) -> {
            if (colTenBanDoc.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return new SimpleStringProperty(banDoc.getHoVaTen());
            } else return colTenBanDoc.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colTenBanDoc);

        colNgaySinh.setCellValueFactory((param) -> {
            if (colNgaySinh.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return new SimpleObjectProperty<Date>(banDoc.getNgaySinh());
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
                return new SimpleStringProperty(banDoc.getCmnd());
            } else return colCmnd.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colCmnd);

        colEmail.setCellValueFactory((param) -> {
            if (colEmail.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return new SimpleStringProperty(banDoc.getEmail());
            } else return colEmail.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colEmail);

        colSdt.setCellValueFactory((param) -> {
            if (colSdt.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return new SimpleStringProperty(banDoc.getSdt());
            } else return colSdt.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colSdt);

        colNgayTaoThe.setCellValueFactory((param) -> {
            if (colNgayTaoThe.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return new SimpleObjectProperty<>(banDoc.getNgayTaoThe());
            } else return colNgayTaoThe.getComputedValue(param);
        });
        CellFactory.getInstance().DateValueFactory(colNgayTaoThe);

        colThoiHanSuDung.setCellValueFactory((param) -> {
            if (colThoiHanSuDung.validateValue(param)) {
                BanDoc banDoc = param.getValue().getValue();
                return new SimpleObjectProperty<>(banDoc.getThoiHanSuDung());
            } else return colThoiHanSuDung.getComputedValue(param);
        });
        CellFactory.getInstance().StringCenterValueFactory(colThoiHanSuDung);
    }

    protected void tableSetRowFactory(){
        table.setRowFactory(value -> new JFXTreeTableRow<>() {
            {
                ContextMenu addMenu = new ContextMenu();
                MenuItem itemChinhSua = new MenuItem("Ch\u1EC9nh s\u1EEDa");
                MenuItem itemGiaHan = new MenuItem("Gia h\u1EA1n th\u1EBB");
                MenuItem itemIn = new MenuItem("In th\u1EBB");
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

    protected void btnSetOnAction(JFXButton taoBanDoc, JFXButton nhapBanDoc, JFXButton xuatBanDoc) {
        taoBanDoc.setOnAction(e -> {
            try {
                StageMaker.createStage("/view/themBanDoc.fxml", "Th\u00EAm b\u1EA1n \u0111\u1ECDc", 443, 630);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        nhapBanDoc.setOnAction(e -> {
            try {
                nhapBanDoc();
            } catch (IOException | ParseException ioException) {
                ioException.printStackTrace();
            }
        });
        nhapBanDoc.getStyleClass().add("add-button");

        xuatBanDoc.setOnAction(e -> {
            try {
                xuatBanDoc();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        xuatBanDoc.getStyleClass().add("add-button");
    }

    private void showChinhSuaDialog(BanDoc banDoc) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/view/suaBanDoc.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ch\u1EC9nh s\u1EEDa B\u1EA1n \u0110\u1ECDc");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 480, 600);
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

    protected void nhapBanDoc() throws IOException, ParseException {
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
            BanDoc banDoc = new BanDoc();
            banDoc.setHoVaTen(row.getCell(0).getStringCellValue());
            banDoc.setNgaySinh(DateUtil.StringToDate(row.getCell(1).getStringCellValue(), "dd/MM/yyyy"));
            banDoc.setGioiTinh((int) row.getCell(2).getNumericCellValue());
            banDoc.setCmnd(row.getCell(3).getStringCellValue());
            banDoc.setEmail(row.getCell(4).getStringCellValue());
            banDoc.setSdt(row.getCell(5).getStringCellValue());
            //banDoc.setThoiHanSuDungThe();

            BanDocDAO.getInstance().insert(banDoc);
        }

        BanDocDAO.getInstance().reload();
    }

    protected void xuatBanDoc() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Danh sach Ban doc");

        List<BanDoc> list = BanDocDAO.getInstance().all();

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

        for (BanDoc banDoc : list) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(banDoc.getHoVaTen());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(DateUtil.DateToString(banDoc.getNgaySinh(),"dd/MM/yyyy"));

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(banDoc.getGioiTinh());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(banDoc.getCmnd());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(banDoc.getEmail());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(banDoc.getSdt());
        }

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\assets\\danh_sach_ban_doc_" + DateUtil.DateToString(new Date(), "ddMMyyyy_HHmmss") + ".xls");
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
