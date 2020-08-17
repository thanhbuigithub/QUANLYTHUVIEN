package view;

import control.controllers.ThemSachController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemSachView extends ThemSachController implements Initializable {
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
}
