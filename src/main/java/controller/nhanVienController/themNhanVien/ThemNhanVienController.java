package controller.nhanVienController.themNhanVien;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import modules.dao.NhanVienDAO;
import modules.entities.NhanVien;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ThemNhanVienController implements Initializable {
    @FXML
    private JFXButton btnThemNhanVien;

    @FXML
    private JFXTextField tfTenNhanVien;

    @FXML
    private JFXDatePicker jdpNgaySinh;

    @FXML
    private JFXComboBox<String> cbxGioiTinh;

    @FXML
    private JFXTextField tfCmnd;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfSodt;

    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXPasswordField tfPassWord;

    @FXML
    private JFXPasswordField tfConfirmPassword;

    @FXML
    private JFXComboBox<String> cbxChucVu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnThemNhanVien.getStyleClass().add("dialog-button");
        bindingData();
        notNullValidator(tfTenNhanVien);
        notNullValidator(tfCmnd);
        notNullValidator(tfEmail);
        notNullValidator(tfSodt);
        notNullValidator(tfUsername);
        notNullValidatorPassWord(tfPassWord);
        notNullValidatorPassWord(tfConfirmPassword);

        numberOnlyTextField(tfCmnd);
    }

    @FXML
    void themNhanVien(ActionEvent event) {
        if (!tfPassWord.getText().equals(tfConfirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setContentText("M\u1EADt kh\u1EA9u kh\u00F4ng tr\u00F9ng kh\u1EDBp !");
            alert.showAndWait();
        }
        if (isValidateAll()) {
            NhanVienDAO.getInstance().insert(getNhanVien());
            NhanVienDAO.getInstance().reload();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
        }
    }

    public void bindingData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = "01/01/2000";
        LocalDate localDate = LocalDate.parse(date, formatter);
        ObservableList<String> gioiTinh = FXCollections.observableArrayList("Nam", "N\u1EEF");
        ObservableList<String> chucVu = FXCollections.observableArrayList("Nh\u00E2n vi\u00EAn", "Admin", "Th\u1EE7 th\u01B0");
        cbxGioiTinh.setItems(gioiTinh);
        cbxChucVu.setItems(chucVu);
        jdpNgaySinh.setValue(localDate);
        cbxGioiTinh.setValue("Nam");
        cbxChucVu.setValue("Nh\u00E2n vi\u00EAn");
    }

    private void notNullValidator(JFXTextField tf) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tf.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validator.validate();
            }
        });
    }

    private void notNullValidatorPassWord(JFXPasswordField tf) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tf.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validator.validate();
            }
        });
    }

    private void numberOnlyTextField(JFXTextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (oldValue.equals("0")) {
                tf.setText(String.valueOf(Integer.parseInt(newValue)));
            }
        });
    }

    private boolean isValidateAll() {
        return tfTenNhanVien.validate() &&
                tfCmnd.validate() &&
                tfEmail.validate() &&
                tfSodt.validate() && jdpNgaySinh.validate()
                && cbxGioiTinh.validate() && cbxChucVu.validate() && tfUsername.validate()
                && tfPassWord.validate() && tfConfirmPassword.validate() && tfPassWord.getText().equals(tfConfirmPassword.getText());
    }

    private NhanVien getNhanVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoVaTen(tfTenNhanVien.getText());
        nhanVien.setNgaySinh(Date.from(jdpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (cbxGioiTinh.getValue().toLowerCase().equals("nam")) {
            nhanVien.setGioiTinh(0);
        } else if (cbxGioiTinh.getValue().toLowerCase().equals("n\u1EEF")) {
            nhanVien.setGioiTinh(1);
        }
        nhanVien.setCmnd(tfCmnd.getText());
        nhanVien.setEmail(tfEmail.getText());
        nhanVien.setSdt(tfSodt.getText());
        nhanVien.setUsername(tfUsername.getText());
        nhanVien.setPassword(tfPassWord.getText());
        if (cbxChucVu.getValue().toLowerCase().equals("nh\u00E2n vi\u00EAn")) {
            nhanVien.setChucDanh(0);
        } else if (cbxChucVu.getValue().toLowerCase().equals("admin")) {
            nhanVien.setChucDanh(1);
        } else nhanVien.setChucDanh(2);
        return nhanVien;
    }
}
