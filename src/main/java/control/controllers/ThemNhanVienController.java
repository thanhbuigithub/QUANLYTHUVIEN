package control.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import control.utilities.DateUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.dao.NhanVienDAO;
import model.dto.NhanVien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ThemNhanVienController{
    @FXML
    protected JFXButton btnThemNhanVien;

    @FXML
    protected JFXTextField tfTenNhanVien;

    @FXML
    protected JFXTextField tfNgaySinh;

    @FXML
    protected JFXComboBox<Integer> cbxGioiTinh;

    @FXML
    protected JFXTextField tfCmnd;

    @FXML
    protected JFXTextField tfEmail;

    @FXML
    protected JFXTextField tfSodt;

    @FXML
    protected JFXTextField tfUsername;

    @FXML
    protected JFXPasswordField tfPassWord;

    @FXML
    protected JFXPasswordField tfConfirmPassword;

    @FXML
    protected JFXComboBox<Integer> cbxChucVu;

    @FXML
    void themNhanVien(ActionEvent event) throws ParseException {
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

    protected void initCbxGioiTinh(JFXComboBox<Integer> cbx) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        cbx.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        cbx.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                validator.validate();
            }
        });

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        cbx.setItems(FXCollections.observableArrayList(list));
        cbx.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null) return null;
                if (object == 0) return "Nam";
                else return "N\u1EEF";
            }

            @Override
            public Integer fromString(String string) {
                if (string.equals("Nam")) return 0;
                else return 1;
            }
        });
    }

    protected void initCbxChucDanh(JFXComboBox<Integer> cbx) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        cbx.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        cbx.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                validator.validate();
            }
        });

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        cbx.setItems(FXCollections.observableArrayList(list));
        cbx.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null) return null;
                if (object == 1) return "Th\u1EE7 th\u01B0";
                else if (object == 2) return "Ban K\u1EF9 Thu\u1EADt";
                else return "Admin";
            }

            @Override
            public Integer fromString(String string) {
                if (string.equals("Th\u1EE7 th\u01B0")) return 1;
                else if (string.equals("Ban K\u1EF9 Thu\u1EADt")) return 2;
                else return 3;
            }
        });
    }

    public void bindingData() {
    }

    protected void notNullValidator(JFXTextField tf) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tf.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validator.validate();
            }
        });
    }

    protected void notNullValidatorPassWord(JFXPasswordField tf) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tf.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validator.validate();
            }
        });
    }

    protected void numberOnlyTextField(JFXTextField tf) {
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
                tfNgaySinh.validate() &&
                tfCmnd.validate() &&
                tfEmail.validate() &&
                tfSodt.validate()
                && cbxGioiTinh.validate() && cbxChucVu.validate() && tfUsername.validate()
                && tfPassWord.validate() && tfConfirmPassword.validate() && tfPassWord.getText().equals(tfConfirmPassword.getText());
    }

    private NhanVien getNhanVien() throws ParseException {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoVaTen(tfTenNhanVien.getText());
        nhanVien.setNgaySinh(DateUtil.StringToDate(tfNgaySinh.getText(), "dd/MM/yyyy"));
        nhanVien.setGioiTinh(cbxGioiTinh.getValue());
        nhanVien.setCmnd(tfCmnd.getText());
        nhanVien.setEmail(tfEmail.getText());
        nhanVien.setSdt(tfSodt.getText());
        nhanVien.setUsername(tfUsername.getText());
        nhanVien.setPassword(tfPassWord.getText());
        nhanVien.setChucDanh(cbxChucVu.getValue());
        return nhanVien;
    }
}
