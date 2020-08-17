package view;

import control.controllers.SuaSachController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SuaSachView extends SuaSachController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCapNhat.getStyleClass().add("dialog-button");
        btnHuy.getStyleClass().add("dialog-button");

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
