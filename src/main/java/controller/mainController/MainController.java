package controller.mainController;

import animatefx.animation.*;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import controller.Main;
import controller.banDocController.BanDocController;
import controller.muonSachController.MuonSachController;
import controller.quanLySachController.QuanLySachController;
import controller.traSachController.TraSachController;
import javafx.animation.ScaleTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import modules.dao.*;
import modules.entities.Sach;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private StackPane rootPane;

    @FXML
    private JFXButton btnMuonSach;

    @FXML
    private JFXButton btnTraSach;

    @FXML
    private JFXButton btnQuanLySach;

    @FXML
    private JFXButton btnTheThuVien;

    @FXML
    private JFXButton btnBanDoc;

    @FXML
    private JFXButton btnThongKe;

    @FXML
    private JFXTextField tfSearch;

    @FXML
    private AnchorPane topPane;

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox accountPane;

    @FXML
    private JFXHamburger btnAccount;

    @FXML
    private BorderPane tablePane;

    @FXML
    private HBox createBtnBox;

    private JFXButton taoPhieuMuon = new JFXButton("T\u1EA1o phi\u1EBFu m\u01B0\u1EE3n");

    private JFXButton taoPhieuTra = new JFXButton("T\u1EA1o phi\u1EBFu tr\u1EA3");

    private JFXButton taoSach = new JFXButton("T\u1EA1o s\u00E1ch");

    private JFXButton nhapSach = new JFXButton("Nh\u1EADp s\u00E1ch");
    private JFXButton xuatSach = new JFXButton("Xu\u1EA5t s\u00E1ch");

    private JFXButton taoBanDoc = new JFXButton("T\u1EA1o B\u1EA1n \u0110\u1ECDc");

    private final ObjectProperty<JFXButton> selectedBtn = new SimpleObjectProperty<>();
    private final ObservableList<JFXButton> btnCreateSelected = FXCollections.observableArrayList();

    private final Double speed = 0.5;

    MuonSachController muonSachController;
    TraSachController traSachController;
    QuanLySachController quanLySachController;
    BanDocController banDocController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rootPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainPane.setPrefHeight(newValue.doubleValue());
        });

        rootPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainPane.setPrefWidth(newValue.doubleValue());
        });

        SelectButton(btnMuonSach);
        selectedBtn.set(btnMuonSach);

        HamburgerNextArrowBasicTransition burgerTask = new HamburgerNextArrowBasicTransition(btnAccount);
        burgerTask.setRate(-1);
        btnAccount.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();
            if (burgerTask.getRate() == 1) {
                mainPane.setRight(accountPane);
                new SlideInRight(accountPane).play();
            } else {
                SlideOutRight slide = new SlideOutRight(accountPane);
                slide.setOnFinished(event -> {
                    mainPane.setRight(null);
                });
                slide.play();
            }
        });

        mainPane.setRight(null);

        muonSachController = new MuonSachController(rootPane, mainPane);
        traSachController = new TraSachController(rootPane, mainPane);
        quanLySachController = new QuanLySachController(rootPane, mainPane);
        banDocController = new BanDocController(rootPane, mainPane);

        tablePane.setCenter(muonSachController.getTable(tfSearch));

        taoPhieuMuon.setOnAction(e -> {
            try {
                createStage("/view/phieuMuon/themPhieuMuon.fxml", "T\u1EA1o phi\u1EBFu m\u01B0\u1EE3n", 560, 250);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        taoPhieuMuon.getStyleClass().add("add-button");
        AnchorPane.setRightAnchor(taoPhieuMuon, (double) 10);
        AnchorPane.setTopAnchor(taoPhieuMuon, (double) 15);
        createBtnBox.getChildren().add(taoPhieuMuon);
        btnCreateSelected.add(taoPhieuMuon);

        taoPhieuTra.setOnAction(e -> {
            try {
                createStage("/view/phieuTra/themPhieuTra.fxml", "T\u1EA1o phi\u1EBFu tr\u1EA3", 560, 500);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        taoPhieuTra.getStyleClass().add("add-button");
        AnchorPane.setRightAnchor(taoPhieuTra, (double) 10);
        AnchorPane.setTopAnchor(taoPhieuTra, (double) 15);

        taoSach.setOnAction(e -> {
            try {
                createStage("/view/quanLySach/themSach.fxml", "Th\u00EAm s\u00E1ch", 443, 720);
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

        taoBanDoc.setOnAction(e -> {
            try {
                createStage("/view/banDoc/themBanDoc.fxml", "Th\u00EAm b\u1EA1n \u0111\u1ECDc", 443, 565);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        taoBanDoc.getStyleClass().add("add-button");
        AnchorPane.setRightAnchor(taoBanDoc, (double) 10);
        AnchorPane.setTopAnchor(taoBanDoc, (double) 15);
        createBtnBox.getChildren().add(taoBanDoc);
        btnCreateSelected.add(taoBanDoc);
    }

    @FXML
    void handleToolBarClick(ActionEvent event) {
        Object target = event.getSource();
        if (target == btnMuonSach) {
            switchFunction(MAINCOLOR.MuonSach, muonSachController.getTable(tfSearch), Collections.singletonList(taoPhieuMuon));
        } else if (target == btnTraSach) {
            switchFunction(MAINCOLOR.TraSach, traSachController.getTable(tfSearch), Collections.singletonList(taoPhieuTra));
        } else if (target == btnQuanLySach) {
            switchFunction(MAINCOLOR.QuanLySach, quanLySachController.getTable(tfSearch), Arrays.asList(xuatSach, nhapSach, taoSach));

        } else if (target == btnTheThuVien) {
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.TheThuVien);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.TheThuVien);
        } else if (target == btnBanDoc) {
            switchFunction(MAINCOLOR.BanDoc, banDocController.getTable(tfSearch), Collections.singletonList(taoBanDoc));
        } else if (target == btnThongKe) {
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.ThongKe);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.ThongKe);
        }

        if (selectedBtn.get() != target) {
            UnSelectButton(selectedBtn.get());
            selectedBtn.set((JFXButton) target);
        }
    }


    @FXML
    void handleToolBarHover(MouseEvent event) {
        Object target = event.getSource();
        if (target != selectedBtn.get()) {
            SelectButton((JFXButton) target);
        }
    }

    @FXML
    void handleToolBarUnHover(MouseEvent event) {
        Object target = event.getSource();
        if (target != selectedBtn.get()) {
            UnSelectButton((JFXButton) target);
        }
    }

    private void SelectButton(JFXButton btn) {
        ScaleTransition scale = new ScaleTransition(Duration.seconds(speed), btn);
        scale.setToX(1.3f);
        scale.setToY(1.3f);
        scale.setAutoReverse(true);

        scale.play();

        btn.setButtonType(JFXButton.ButtonType.RAISED);

        if (btn == btnMuonSach) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.MuonSach);
        } else if (btn == btnTraSach) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.TraSach);
        } else if (btn == btnQuanLySach) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.QuanLySach);
        } else if (btn == btnTheThuVien) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.TheThuVien);
        } else if (btn == btnBanDoc) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.BanDoc);
        } else if (btn == btnThongKe) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.ThongKe);
        }
    }

    private void UnSelectButton(JFXButton btn) {
        ScaleTransition transition = new ScaleTransition(Duration.seconds(speed), btn);
        transition.setToX(1f);
        transition.setToY(1f);
        transition.setAutoReverse(true);

        transition.play();

        btn.setStyle("-fx-background-color: " + MAINCOLOR.UnHoverBtn);
        btn.setButtonType(JFXButton.ButtonType.FLAT);

    }

    public static void createStage(String loc, String title, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(loc));
        Stage stage = new Stage();
        stage.setTitle(title);
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, width, height);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.showAndWait();
    }


    private void nhapSach() throws IOException {
        FileChooser.ExtensionFilter excelFilter
                = new FileChooser.ExtensionFilter("Excel File", "*.xls", "*.xlsx");

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
            sach.setNhaXuatBan(row.getCell(2).getStringCellValue());
            sach.setNgonNgu(row.getCell(3).getStringCellValue());
            sach.setTacGia(row.getCell(4).getStringCellValue());
            sach.setTheLoai(row.getCell(5).getStringCellValue());
            sach.setMaViTri((int) row.getCell(6).getNumericCellValue());
            sach.setGiaBia((int) row.getCell(7).getNumericCellValue());
            sach.setSoLuong((int) row.getCell(8).getNumericCellValue());
            sach.setSoTrang((int) row.getCell(9).getNumericCellValue());

            SachDAO.getInstance().insert(sach);
        }

        SachDAO.getInstance().reload();
    }

    private void xuatSach() throws IOException {
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

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(sach.getNhaXuatBan());

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(sach.getNgonNgu());

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(sach.getTacGia());

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue(sach.getTheLoai());

            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(sach.getMaViTri());

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

    private HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private void switchFunction(String color, JFXTreeTableView table, List<JFXButton> btnCreate) {
        tablePane.setStyle("-fx-border-color: " + color);
        topPane.setStyle("-fx-background-color: " + color);
        tablePane.setCenter(table);
        createBtnBox.getChildren().removeAll(createBtnBox.getChildren());
        createBtnBox.getChildren().addAll(btnCreate);
        btnCreateSelected.addAll(btnCreate);
    }
}
