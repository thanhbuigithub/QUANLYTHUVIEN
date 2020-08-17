package view;

import control.controllers.ThemBanDocController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemBanDocView extends ThemBanDocController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnThemBanDoc.getStyleClass().add("dialog-button");

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
