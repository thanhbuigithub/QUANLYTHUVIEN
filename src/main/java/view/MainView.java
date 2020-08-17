package view;

import control.controllers.*;
import control.utilities.StageMaker;
import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.ClockBuilder;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainView extends MainController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupUtilBox();
        setupMainPane();
        setupViewController();
        setupDefaultView();
        btnAccountEventHanler();
    }

    private void setupUtilBox(){
        utilBox.setSpacing(10);
        utilBox.getChildren().addAll(createClock(),createChart());
    }

    private void setupMainPane() {
        rootPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainPane.setPrefHeight(newValue.doubleValue());
        });

        rootPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainPane.setPrefWidth(newValue.doubleValue());
        });
    }

    private void setupDefaultView() {
        SelectButton(btnMuonSach);
        selectedBtn.set(btnMuonSach);
        mainPane.setRight(null);
        tablePane.setCenter(muonSachView.getTable(tfSearch));
        createBtnBox.getChildren().addAll(muonSachView.getListBtn());
        btnCreateSelected.addAll(muonSachView.getListBtn());
    }

    private void setupViewController() {
        muonSachView = new MuonSachView(rootPane, mainPane);
        traSachView = new TraSachView(rootPane, mainPane);
        quanLySachView = new QuanLySachView(rootPane, mainPane);
        quanLyBanDocView = new QuanLyBanDocView(rootPane, mainPane);
        quanLyNhanVienView = new QuanLyNhanVienView(rootPane, mainPane);
    }

    private Clock createClock(){
        return ClockBuilder.create()
                .secondsVisible(true)
                .dateVisible(true)
                .hourColor(Color.valueOf("#2c3e50"))
                .minuteColor(Color.valueOf("#2c3e50"))
                .secondColor(Color.valueOf("#2c3e50"))
                .dateColor(Color.valueOf("#2c3e50"))
                .alarmColor(Color.valueOf("#2c3e50"))
                .skinType(Clock.ClockSkinType.SLIM)
                .running(true)
                .build();
    }
}
