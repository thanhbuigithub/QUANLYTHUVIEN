package control.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import control.utilities.AutoCompleteComboBoxListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.dao.*;
import model.dto.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThemSachController {
    @FXML
    protected JFXButton btnThemSach;

    @FXML
    protected JFXTextField tfTenSach;

    @FXML
    protected JFXTextField tfNamXuatBan;

    @FXML
    protected JFXTextField tfNhaXuatBan;

    @FXML
    protected JFXTextField tfNgonNgu;

    @FXML
    protected JFXTextField tfTacGia;

    @FXML
    protected JFXTextField tfTheLoai;

    @FXML
    protected JFXComboBox<ViTri> cbxMaViTri;

    @FXML
    protected JFXTextField tfGiaBia;

    @FXML
    protected JFXTextField tfSoLuong;

    @FXML
    protected JFXTextField tfSoTrang;

    Boolean error;
    String errorMessage = "";

    @FXML
    void themSach(ActionEvent event) {
        if (isValidateAll()){
            SachDAO.getInstance().insert(getSach());
            SachDAO.getInstance().reload();
            Stage primatyStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primatyStage.close();
        }
    }

    protected void notNullValidator(JFXTextField  tf){
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

    protected void numberOnlyTextField(JFXTextField tf){
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
        sach.setNamXuatBan(Integer.parseInt(tfNamXuatBan.getText()));

        Set<NhaXuatBan> nhaXuatBanSet = new HashSet<>();
        nhaXuatBanSet.add(NhaXuatBanDAO.getInstance().getByID(Integer.valueOf(tfNhaXuatBan.getText())));
        sach.setNhaXuatBan(nhaXuatBanSet);
        Set<NgonNgu> ngonNguSet = new HashSet<>();
        ngonNguSet.add(NgonNguDAO.getInstance().getByID(Integer.valueOf(tfNgonNgu.getText())));
        sach.setNgonNgu(ngonNguSet);
        Set<TacGia> tacGiaSet = new HashSet<>();
        tacGiaSet.add(TacGiaDAO.getInstance().getByID(Integer.valueOf(tfTacGia.getText())));
        sach.setTacGia(tacGiaSet);

        Set<TheLoai> theLoaiSet = new HashSet<>();
        theLoaiSet.add(TheLoaiDAO.getInstance().getByID(Integer.valueOf(tfTheLoai.getText())));
        sach.setTheLoai(theLoaiSet);

        ViTri viTri = ViTriDAO.getInstance().getByID(cbxMaViTri.getValue().getId());
        sach.setViTri(viTri);
        sach.setGiaBia(Integer.valueOf(tfGiaBia.getText()));
        sach.setSoLuong(Integer.valueOf(tfSoLuong.getText()));
        sach.setSoTrang(Integer.parseInt(tfSoTrang.getText()));
        return sach;
    }

    protected void initCbxMaViTri(JFXComboBox<ViTri> cbx){
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
