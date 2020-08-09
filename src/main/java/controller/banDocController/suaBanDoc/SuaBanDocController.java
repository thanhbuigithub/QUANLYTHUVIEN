package controller.banDocController.suaBanDoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modules.dao.BanDocDAO;
import modules.dao.SachDAO;
import modules.dao.ViTriDAO;
import modules.entities.BanDoc;
import modules.entities.Sach;
import modules.entities.ViTri;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class SuaBanDocController implements Initializable {
    @FXML
    private JFXButton btnCapNhat;

    @FXML
    private JFXButton btnHuy;

    @FXML
    private JFXTextField tfTenBanDoc;

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
    private JFXDatePicker jdpThoiHan;

    BanDoc localbanDoc = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCapNhat.getStyleClass().add("dialog-button");
        btnHuy.getStyleClass().add("dialog-button");

        notNullValidator(tfTenBanDoc);
        notNullValidator(tfCmnd);
        notNullValidator(tfEmail);
        notNullValidator(tfSodt);

        numberOnlyTextField(tfCmnd);
    }

    @FXML
    void capNhat(ActionEvent event) {
        if (isValidateAll()) {
            BanDocDAO.getInstance().update(updateBanDoc(localbanDoc));
            Stage primatyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primatyStage.close();
        }
    }

    @FXML
    void huy(ActionEvent event) {
        Stage primatyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primatyStage.close();
    }

    public void bindingData(BanDoc banDoc) {
        localbanDoc = banDoc;
        LocalDate localDate = banDoc.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ObservableList<String> data = FXCollections.observableArrayList("Nam", "N\u1EEF");
        cbxGioiTinh.setItems(data);
        tfTenBanDoc.setText(banDoc.getHoVaTen());
        jdpNgaySinh.setValue(localDate);
        if (localbanDoc.getGioiTinh() == 0) {
            cbxGioiTinh.setValue("Nam");
        } else {
            cbxGioiTinh.setValue("N\u1EEF");
        }
        tfCmnd.setText(banDoc.getCmnd());
        tfEmail.setText(banDoc.getEmail());
        tfSodt.setText(banDoc.getSdt());
        jdpThoiHan.setValue(LocalDate.now());
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
        return tfTenBanDoc.validate() &&
                tfCmnd.validate() &&
                tfEmail.validate() &&
                tfSodt.validate() && jdpNgaySinh.validate() && jdpThoiHan.validate();
    }

    private BanDoc updateBanDoc(BanDoc bandoc) {
        bandoc.setHoVaTen(tfTenBanDoc.getText());
        bandoc.setNgaySinh(Date.from(jdpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (cbxGioiTinh.getValue().toLowerCase().equals("nam")) {
            bandoc.setGioiTinh(0);
        } else {
            bandoc.setGioiTinh(1);
        }
        bandoc.setCmnd(tfCmnd.getText());
        bandoc.setEmail(tfEmail.getText());
        bandoc.setSdt(tfSodt.getText());
        bandoc.setThoiHanSuDungThe(Date.from(jdpThoiHan.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return bandoc;
    }
}
