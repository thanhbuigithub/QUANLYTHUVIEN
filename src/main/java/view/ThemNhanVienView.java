package view;

import control.controllers.ThemNhanVienController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemNhanVienView extends ThemNhanVienController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnThemNhanVien.getStyleClass().add("dialog-button");

        initCbxGioiTinh(cbxGioiTinh);
        initCbxChucDanh(cbxChucVu);

        notNullValidator(tfTenNhanVien);
        notNullValidator(tfCmnd);
        notNullValidator(tfEmail);
        notNullValidator(tfSodt);
        notNullValidator(tfUsername);
        notNullValidatorPassWord(tfPassWord);
        notNullValidatorPassWord(tfConfirmPassword);

        numberOnlyTextField(tfCmnd);
        numberOnlyTextField(tfSodt);
    }
}
