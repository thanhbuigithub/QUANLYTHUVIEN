package controller.muonSachController;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MuonSachController implements Initializable {
    private JFXTreeTableView<?> table = new JFXTreeTableView<>();

    private JFXTreeTableColumn<?,?> col = new JFXTreeTableColumn<>("muonSach");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public JFXTreeTableView getTable(){
        return table;
    }
}
