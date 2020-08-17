package control.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.dao.NhanVienDAO;
import model.dto.NhanVien;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class SuaNhanVienController implements Initializable {
    @FXML
    private JFXButton btnCapNhat;

    @FXML
    private JFXButton btnHuy;

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
    private JFXPasswordField tfPassword;

    @FXML
    private JFXComboBox<String> cbxChucVu;

    NhanVien localNhanVien = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCapNhat.getStyleClass().add("dialog-button");
        btnHuy.getStyleClass().add("dialog-button");

        notNullValidator(tfTenNhanVien);
        notNullValidator(tfCmnd);
        notNullValidator(tfEmail);
        notNullValidator(tfSodt);
        notNullValidator(tfUsername);
        notNullValidatorPassWord(tfPassword);

        numberOnlyTextField(tfCmnd);
        numberOnlyTextField(tfSodt);
    }

    @FXML
    void capNhat(ActionEvent event) {
        if (isValidateAll()) {
            NhanVienDAO.getInstance().update(updateNhanVien(localNhanVien));
            NhanVienDAO.getInstance().reload();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
        }
    }

    @FXML
    void huy(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    public void bindingData(NhanVien nhanVien) {
        localNhanVien = nhanVien;
        LocalDate localDate = nhanVien.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ObservableList<String> gioiTinh = FXCollections.observableArrayList("Nam", "N\u1EEF");
        cbxGioiTinh.setItems(gioiTinh);
        tfTenNhanVien.setText(nhanVien.getHoVaTen());
        jdpNgaySinh.setValue(localDate);
        if (localNhanVien.getGioiTinh() == 0) {
            cbxGioiTinh.setValue("Nam");
        } else {
            cbxGioiTinh.setValue("N\u1EEF");
        }
        tfCmnd.setText(nhanVien.getCmnd());
        tfEmail.setText(nhanVien.getEmail());
        tfSodt.setText(nhanVien.getSdt());
        tfUsername.setText(nhanVien.getUsername());
        tfPassword.setText(nhanVien.getPassword());
        ObservableList<String> chucVu = FXCollections.observableArrayList("Nh\u00E2n vi\u00EAn", "Admin", "Th\u1EE7 th\u01B0");
        cbxChucVu.setItems(chucVu);
        if (localNhanVien.getChucDanh() == 0) {
            cbxChucVu.setValue("Nh\u00E2n vi\u00EAn");
        } else if (localNhanVien.getChucDanh() == 1) {
            cbxChucVu.setValue("Admin");
        } else cbxChucVu.setValue("Th\u1EE7 th\u01B0");
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
                tfSodt.validate() && jdpNgaySinh.validate() && cbxGioiTinh.validate()
                && cbxChucVu.validate() && tfUsername.validate() && tfPassword.validate();
    }

    private NhanVien updateNhanVien(NhanVien nhanVien) {
        nhanVien.setHoVaTen(tfTenNhanVien.getText());
        nhanVien.setNgaySinh(Date.from(jdpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (cbxGioiTinh.getValue().toLowerCase().equals("nam")) {
            nhanVien.setGioiTinh(0);
        } else {
            nhanVien.setGioiTinh(1);
        }
        nhanVien.setCmnd(tfCmnd.getText());
        nhanVien.setEmail(tfEmail.getText());
        nhanVien.setSdt(tfSodt.getText());
        nhanVien.setUsername(tfUsername.getText());
        nhanVien.setPassword(tfPassword.getText());
        if (cbxChucVu.getValue().toLowerCase().equals("nh\u00E2n vi\u00EAn")) {
            nhanVien.setChucDanh(0);
        } else if (cbxChucVu.getValue().toLowerCase().equals("admin")) {
            nhanVien.setChucDanh(1);
        } else nhanVien.setChucDanh(2);
        return nhanVien;
    }
}
