package view;

import control.controllers.SuaBanDocController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SuaBanDocView extends SuaBanDocController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCapNhat.getStyleClass().add("dialog-button");
        btnHuy.getStyleClass().add("dialog-button");

        initCbxGioiTinh(cbxGioiTinh);

        notNullValidator(tfTenBanDoc);
        notNullValidator(tfNgaySinh);
        notNullValidator(tfCmnd);
        notNullValidator(tfEmail);
        notNullValidator(tfSodt);

        numberOnlyTextField(tfCmnd);
        numberOnlyTextField(tfSodt);
    }
}
