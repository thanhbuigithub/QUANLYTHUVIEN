package controller.mainController;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable {
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
    private BorderPane borderPane;

    @FXML
    private AnchorPane topPane;

    private ObjectProperty<JFXButton> selectedBtn = new SimpleObjectProperty<>();

    private final Double speed = 0.5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SelectButton(btnMuonSach);
        selectedBtn.set(btnMuonSach);
    }

    @FXML
    void handleToolBarClick(ActionEvent event) {
        Object target = event.getSource();
        if (target == btnMuonSach){
            borderPane.setStyle("-fx-border-color: " + MAINCOLOR.MuonSach);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.MuonSach);
        } else if (target == btnTraSach) {
            borderPane.setStyle("-fx-border-color: " + MAINCOLOR.TraSach);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.TraSach);
        } else if (target == btnQuanLySach) {
            borderPane.setStyle("-fx-border-color: " + MAINCOLOR.QuanLySach);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.QuanLySach);
        } else if (target == btnTheThuVien) {
            borderPane.setStyle("-fx-border-color: " + MAINCOLOR.TheThuVien);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.TheThuVien);
        } else if (target == btnBanDoc) {
            borderPane.setStyle("-fx-border-color: " + MAINCOLOR.BanDoc);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.BanDoc);
        } else if (target == btnThongKe) {
            borderPane.setStyle("-fx-border-color: " + MAINCOLOR.ThongKe);
            topPane.setStyle("-fx-background-color: " + MAINCOLOR.ThongKe);
        }

        if (selectedBtn.get() != target){
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

    private void SelectButton(JFXButton btn){
        ScaleTransition scale = new ScaleTransition(Duration.seconds(speed), btn);
        scale.setToX(1.3f);
        scale.setToY(1.3f);
        scale.setAutoReverse(true);

        scale.play();

        btn.setButtonType(JFXButton.ButtonType.RAISED);

        if (btn == btnMuonSach) {
            ((JFXButton) btn).setStyle("-fx-background-color: " + MAINCOLOR.MuonSach);
        } else if (btn == btnTraSach) {
            ((JFXButton) btn).setStyle("-fx-background-color: " + MAINCOLOR.TraSach);
        } else if (btn == btnQuanLySach) {
            ((JFXButton) btn).setStyle("-fx-background-color: " + MAINCOLOR.QuanLySach);
        } else if (btn == btnTheThuVien) {
            ((JFXButton) btn).setStyle("-fx-background-color: " + MAINCOLOR.TheThuVien);
        } else if (btn == btnBanDoc) {
            ((JFXButton) btn).setStyle("-fx-background-color: " + MAINCOLOR.BanDoc);
        } else if (btn == btnThongKe) {
            ((JFXButton) btn).setStyle("-fx-background-color: " + MAINCOLOR.ThongKe);
        }
    }

    private void UnSelectButton(JFXButton btn){
        ScaleTransition transition = new ScaleTransition(Duration.seconds(speed), btn);
        transition.setToX(1f);
        transition.setToY(1f);
        transition.setAutoReverse(true);

        transition.play();

        btn.setStyle("-fx-background-color: " + MAINCOLOR.UnHoverBtn);
        btn.setButtonType(JFXButton.ButtonType.FLAT);

    }
}
