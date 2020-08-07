package controller.mainController;

import animatefx.animation.*;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import controller.Main;
import controller.muonSachController.MuonSachController;
import controller.traSachController.TraSachController;
import javafx.animation.ScaleTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import modules.dao.*;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

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

    private JFXButton taoPhieuMuon = new JFXButton("T\u1EA1o phi\u1EBFu m\u01B0\u1EE3n");

    private JFXButton taoPhieuTra = new JFXButton("T\u1EA1o phi\u1EBFu tr\u1EA3");

    private ObjectProperty<JFXButton> selectedBtn = new SimpleObjectProperty<>();

    private final Double speed = 0.5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        JFXTreeTableView table = new MuonSachController(rootPane, mainPane).getTable(tfSearch);
        tablePane.setCenter(table);

        taoPhieuMuon.setOnAction(e -> {
            try {
                taoPhieuMuon();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        taoPhieuMuon.getStyleClass().add("add-button");
        AnchorPane.setRightAnchor(taoPhieuMuon, (double) 10);
        AnchorPane.setTopAnchor(taoPhieuMuon, (double) 15);
        topPane.getChildren().add(taoPhieuMuon);
    }

    @FXML
    void handleToolBarClick(ActionEvent event) {
        Object target = event.getSource();
        if (target == btnMuonSach) {
            tablePane.setCenter(null);
            topPane.getChildren().remove(taoPhieuTra);
            SelectButton(btnMuonSach);
            UnSelectButton(btnTraSach);
            selectedBtn.set(btnMuonSach);
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.MuonSach);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.MuonSach);
            JFXTreeTableView table = new MuonSachController(rootPane, mainPane).getTable(tfSearch);
            tablePane.setCenter(table);

            taoPhieuMuon.setOnAction(e -> {
                try {
                    taoPhieuMuon();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            taoPhieuMuon.getStyleClass().add("add-button");
            AnchorPane.setRightAnchor(taoPhieuMuon, (double) 10);
            AnchorPane.setTopAnchor(taoPhieuMuon, (double) 15);
            topPane.getChildren().add(taoPhieuMuon);
        } else if (target == btnTraSach) {
            tablePane.setCenter(null);
            topPane.getChildren().remove(taoPhieuMuon);
            UnSelectButton(btnMuonSach);
            SelectButton(btnTraSach);
            selectedBtn.set(btnTraSach);
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.TraSach);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.TraSach);
            JFXTreeTableView table = new TraSachController(rootPane, mainPane).getTable(tfSearch);
            tablePane.setCenter(table);
            taoPhieuTra.setOnAction(e -> {
                try {
                    taoPhieuTra();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            taoPhieuTra.getStyleClass().add("add-button");
            AnchorPane.setRightAnchor(taoPhieuTra, (double) 10);
            AnchorPane.setTopAnchor(taoPhieuTra, (double) 15);
            topPane.getChildren().add(taoPhieuTra);
        } else if (target == btnQuanLySach) {
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.QuanLySach);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.QuanLySach);
        } else if (target == btnTheThuVien) {
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.TheThuVien);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.TheThuVien);
        } else if (target == btnBanDoc) {
            tablePane.setStyle("-fx-border-color: " + MAINCOLOR.BanDoc);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.BanDoc);
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

    private void taoPhieuMuon() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/phieuMuon/themPhieuMuon.fxml"));
        Stage stage = new Stage();
        stage.setTitle("T\u1EA1o phi\u1EBFu m\u01B0\u1EE3n");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 560, 250);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.showAndWait();
    }

    private void taoPhieuTra() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/phieuTra/themPhieuTra.fxml"));
        Stage stage = new Stage();
        stage.setTitle("T\u1EA1o phi\u1EBFu tr\u1EA3");
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, 560, 250);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.showAndWait();
    }
}
