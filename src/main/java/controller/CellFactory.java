package controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.cells.editors.base.OnPressedEditableTreeTableCell;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CellFactory {
    private CellFactory(){}

    private static final CellFactory instance = new CellFactory();

    public static CellFactory getInstance() {return instance;}

    public void StringValueFactory(JFXTreeTableColumn col){
        col.setCellFactory(tc -> {
            JFXTreeTableCell<?, ?> cell = new JFXTreeTableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.wrappingWidthProperty().bind(col.widthProperty().multiply(0.9));
            text.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    return String.valueOf(cell.getItem());
                }
            }, cell.emptyProperty(), cell.itemProperty()));
            return cell ;
        });
    }

    public void DateValueFactory(JFXTreeTableColumn col){
        col.setCellFactory(tc -> {
            JFXTreeTableCell<?, Date> cell = new JFXTreeTableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.wrappingWidthProperty().bind(col.widthProperty().multiply(0.9));
            text.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return sdf.format(cell.getItem());
                }
            }, cell.emptyProperty(), cell.itemProperty()));
            text.getStyleClass().add("text-center");
            return cell ;
        });
    }

    public void StringCenterValueFactory(JFXTreeTableColumn col){
        col.setCellFactory(tc -> {
            JFXTreeTableCell<?, ?> cell = new JFXTreeTableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.wrappingWidthProperty().bind(col.widthProperty().multiply(0.9));
            text.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    return String.valueOf(cell.getItem());
                }
            }, cell.emptyProperty(), cell.itemProperty()));
            text.getStyleClass().add("text-center");
            return cell ;
        });
    }
}
