package control.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.dao.BanDocDAO;
import model.dto.BanDoc;

import java.util.ArrayList;
import java.util.List;

public class ThemBanDocController{
    @FXML
    protected JFXButton btnThemBanDoc;

    @FXML
    protected JFXTextField tfTenBanDoc;

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
        return tfTenBanDoc.validate() &&
                tfNgaySinh.validate() &&
                cbxGioiTinh.validate() &&
                tfSodt.validate() &&
                tfCmnd.validate() &&
                tfEmail.validate();

    }

    private BanDoc getBanDoc() {
        BanDoc bandoc = new BanDoc();
        bandoc.setHoVaTen(tfTenBanDoc.getText());
        bandoc.setGioiTinh(cbxGioiTinh.getValue());
        bandoc.setCmnd(tfCmnd.getText());
        bandoc.setEmail(tfEmail.getText());
        bandoc.setSdt(tfSodt.getText());
        return bandoc;
    }
}
