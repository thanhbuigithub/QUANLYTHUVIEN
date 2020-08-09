package controller.quanLySachController.themSach;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import com.jfoenix.validation.RequiredFieldValidator;
import controller.AutoCompleteComboBoxListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modules.dao.SachDAO;
import modules.dao.ViTriDAO;
import modules.entities.Sach;
import modules.entities.ViTri;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ThemSachController implements Initializable {
    @FXML
    private JFXButton btnThemSach;

    @FXML
    private JFXTextField tfTenSach;

    @FXML
    private JFXTextField tfNamXuatBan;

    @FXML
    private JFXTextField tfNhaXuatBan;

    @FXML
    private JFXTextField tfNgonNgu;

    @FXML
    private JFXTextField tfTacGia;

    @FXML
    private JFXTextField tfTheLoai;

    @FXML
    private JFXComboBox<ViTri> cbxMaViTri;

    @FXML
    private JFXTextField tfGiaBia;

    @FXML
    private JFXTextField tfSoLuong;

    @FXML
    private JFXTextField tfSoTrang;

    Boolean error;
    String errorMessage = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnThemSach.getStyleClass().add("dialog-button");

        initCbxMaViTri(cbxMaViTri);

        notNullValidator(tfTenSach);
        notNullValidator(tfNamXuatBan);
        notNullValidator(tfNhaXuatBan);
        notNullValidator(tfNgonNgu);
        notNullValidator(tfTacGia);
        notNullValidator(tfTheLoai);
        notNullValidator(tfGiaBia);
        notNullValidator(tfSoLuong);
        notNullValidator(tfSoTrang);

        numberOnlyTextField(tfNamXuatBan);
        numberOnlyTextField(tfGiaBia);
        numberOnlyTextField(tfSoLuong);
        numberOnlyTextField(tfSoTrang);
    }

    @FXML
    void themSach(ActionEvent event) {
        if (isValidateAll()){
            SachDAO.getInstance().insert(getSach());
            SachDAO.getInstance().reload();
            Stage primatyStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primatyStage.close();
        }
    }

    private void notNullValidator(JFXTextField  tf){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tf.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                validator.validate();
            }
        });
    }

    private void numberOnlyTextField(JFXTextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (oldValue.equals("0")) {
                tf.setText(String.valueOf(Integer.parseInt(newValue)));
            }
        });
    }

    private boolean isValidateAll() {
        return tfTenSach.validate() &&
                tfNamXuatBan.validate() &&
                tfNhaXuatBan.validate() &&
                tfNgonNgu.validate() &&
                tfTacGia.validate() &&
                tfTheLoai.validate() &&
                cbxMaViTri.validate() &&
                tfGiaBia.validate() &&
                tfSoLuong.validate() &&
                tfSoTrang.validate();

    }

    private Sach getSach(){
        Sach sach = new Sach();
        sach.setTenSach(tfTenSach.getText());
        sach.setNamXuatBan(Integer.valueOf(tfNamXuatBan.getText()));
        sach.setNhaXuatBan(tfNhaXuatBan.getText());
        sach.setNgonNgu(tfNgonNgu.getText());
        sach.setTacGia(tfTacGia.getText());
        sach.setTheLoai(tfTheLoai.getText());
        sach.setMaViTri(cbxMaViTri.getValue().getId());
        sach.setGiaBia(Integer.valueOf(tfGiaBia.getText()));
        sach.setSoLuong(Integer.valueOf(tfSoLuong.getText()));
        sach.setSoTrang(Integer.valueOf(tfSoTrang.getText()));
        return sach;
    }

    private void initCbxMaViTri(JFXComboBox<ViTri> cbx){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        cbx.getValidators().add(validator);
        validator.setMessage("Kh\u00F4ng \u0111\u01B0\u1EE3c b\u1ECF tr\u1ED1ng");
        cbx.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                validator.validate();
            }
        });

        List<ViTri> listViTri = ViTriDAO.getInstance().all();
        cbx.setItems(FXCollections.observableArrayList(listViTri));
        cbx.setConverter(new StringConverter<>() {
            @Override
            public String toString(ViTri object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public ViTri fromString(String string) {
                return listViTri.stream().filter(vt -> vt.toString().equals(string)).findAny().orElse(null);
            }
        });

        new AutoCompleteComboBoxListener<>(cbx);
    }
}
