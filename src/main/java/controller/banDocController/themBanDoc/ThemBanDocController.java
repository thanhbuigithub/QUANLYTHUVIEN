package controller.banDocController.themBanDoc;

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
import modules.dao.BanDocDAO;
import modules.entities.BanDoc;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ThemBanDocController implements Initializable {
    @FXML
    private JFXButton btnThemBanDoc;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnThemBanDoc.getStyleClass().add("dialog-button");
        bindingData();
        notNullValidator(tfTenBanDoc);
        notNullValidator(tfCmnd);
        notNullValidator(tfEmail);
        notNullValidator(tfSodt);

        numberOnlyTextField(tfCmnd);
    }

    @FXML
    void themBanDoc(ActionEvent event) {
        if (isValidateAll()) {
            BanDocDAO.getInstance().insert(getBanDoc());
            BanDocDAO.getInstance().reload();
            Stage primatyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primatyStage.close();
        }
    }

    public void bindingData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = "01/01/2000";
        LocalDate localDate = LocalDate.parse(date, formatter);
        ObservableList<String> data = FXCollections.observableArrayList("Nam", "N\u1EEF");
        cbxGioiTinh.setItems(data);
        jdpNgaySinh.setValue(localDate);
        cbxGioiTinh.setValue("Nam");
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
                tfSodt.validate() && jdpNgaySinh.validate() && jdpThoiHan.validate() && cbxGioiTinh.validate();
    }

    private BanDoc getBanDoc() {
        BanDoc bandoc = new BanDoc();
        bandoc.setHoVaTen(tfTenBanDoc.getText());
        bandoc.setNgaySinh(Date.from(jdpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (cbxGioiTinh.getValue().toLowerCase().equals("nam")) {
            bandoc.setGioiTinh(0);
        } else if (cbxGioiTinh.getValue().toLowerCase().equals("n\u1EEF")) {
            bandoc.setGioiTinh(1);
        }
        bandoc.setCmnd(tfCmnd.getText());
        bandoc.setEmail(tfEmail.getText());
        bandoc.setSdt(tfSodt.getText());
        bandoc.setThoiHanSuDungThe(Date.from(jdpThoiHan.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return bandoc;
    }
}
