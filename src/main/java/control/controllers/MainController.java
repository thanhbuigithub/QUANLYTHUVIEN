package control.controllers;

import animatefx.animation.*;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import control.utilities.MAINCOLOR;
import javafx.animation.ScaleTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.dao.*;
import model.dto.PhieuMuon;
import view.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainController {
    @FXML
    protected StackPane rootPane;

    @FXML
    protected JFXButton btnMuonSach;

    @FXML
    protected JFXButton btnTraSach;

    @FXML
    protected JFXButton btnQuanLySach;

    @FXML
    protected JFXButton btnNhanVien;

    @FXML
    protected JFXButton btnBanDoc;

    @FXML
    protected JFXButton btnThongKe;

    @FXML
    protected JFXButton btnLichSuDangNhap;

    @FXML
    protected JFXTextField tfSearch;

    @FXML
    protected AnchorPane topPane;

    @FXML
    protected BorderPane mainPane;

    @FXML
    protected VBox accountPane;

    @FXML
    protected JFXHamburger btnAccount;

    @FXML
    protected BorderPane tablePane;

    @FXML
    protected HBox createBtnBox;

    @FXML
    protected VBox utilBox;

    protected final Double speed = 0.5;

    protected final ObjectProperty<JFXButton> selectedBtn = new SimpleObjectProperty<>();
    protected final ObservableList<JFXButton> btnCreateSelected = FXCollections.observableArrayList();

    protected MuonSachView muonSachView;
    protected TraSachView traSachView;
    protected QuanLySachView quanLySachView;
    protected QuanLyBanDocView quanLyBanDocView;
    protected QuanLyNhanVienView quanLyNhanVienView;


    protected void btnAccountEventHanler(){
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
    }

    @FXML
    void handleToolBarClick(ActionEvent event) {
        Object target = event.getSource();
        if (target == btnMuonSach) {
            switchFunction(MAINCOLOR.MuonSach, muonSachView.getTable(tfSearch), muonSachView.getListBtn());
        } else if (target == btnTraSach) {
            switchFunction(MAINCOLOR.TraSach, traSachView.getTable(tfSearch), traSachView.getListBtn());
        } else if (target == btnQuanLySach) {
            switchFunction(MAINCOLOR.QuanLySach, quanLySachView.getTable(tfSearch), quanLySachView.getListBtn());
        } else if (target == btnBanDoc) {
            switchFunction(MAINCOLOR.BanDoc, quanLyBanDocView.getTable(tfSearch), quanLyBanDocView.getListBtn());
        } else if (target == btnNhanVien) {
            switchFunction(MAINCOLOR.NhanVien, quanLyNhanVienView.getTable(tfSearch), quanLyNhanVienView.getListBtn());
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

    protected void SelectButton(JFXButton btn) {
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
        } else if (btn == btnNhanVien) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.NhanVien);
        } else if (btn == btnBanDoc) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.BanDoc);
        } else if (btn == btnThongKe) {
            btn.setStyle("-fx-background-color: " + MAINCOLOR.ThongKe);
        }
    }

    protected void UnSelectButton(JFXButton btn) {
        ScaleTransition transition = new ScaleTransition(Duration.seconds(speed), btn);
        transition.setToX(1f);
        transition.setToY(1f);
        transition.setAutoReverse(true);

        transition.play();

        btn.setStyle("-fx-background-color: " + MAINCOLOR.UnHoverBtn);
        btn.setButtonType(JFXButton.ButtonType.FLAT);

    }

    private void switchFunction(String color, JFXTreeTableView table, List<JFXButton> btnCreate) {
        tablePane.setStyle("-fx-border-color: " + color);
        topPane.setStyle("-fx-background-color: " + color);
        tablePane.setCenter(table);
        createBtnBox.getChildren().removeAll(createBtnBox.getChildren());
        createBtnBox.getChildren().addAll(btnCreate);
        btnCreateSelected.addAll(btnCreate);
    }

    protected LineChart createChart(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Ng\u00E0y");
        yAxis.setLabel("S\u1ED1 phi\u1EBFu m\u01B0\u1EE3n");

        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);

        XYChart.Series series = new XYChart.Series();
        List<PhieuMuon> phieuMuonList = PhieuMuonDAO.getInstance().all();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

        int max = 0;

        for (int i = 5; i > 0; i--) {
            Date past = new Date(cal.getTimeInMillis() - MILLIS_IN_DAY*i);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
            List<PhieuMuon> phieuMuonInPast = phieuMuonList.stream().filter(pm -> pm.getNgayMuon().compareTo(past) == 0).collect(Collectors.toList());
            series.getData().add(new XYChart.Data(sdf.format(past), phieuMuonInPast.size()));
            if (phieuMuonInPast.size() > max) max = phieuMuonInPast.size();
        }

        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(max + 1);

        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        lineChart.setTitleSide(Side.BOTTOM);
        lineChart.setTitle("S\u1ED1 phi\u1EBFu m\u01B0\u1EE3n 5 ng\u00E0y g\u1EA7n nh\u1EA5t");
        return lineChart;
    }
}
