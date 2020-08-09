package modules.dao;

import controller.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.entities.PhieuTra;
import modules.entities.Sach;
import org.hibernate.Session;

import java.util.List;

public class PhieuTraDAO extends AbstractDAO<PhieuTra> {
    private PhieuTraDAO() {
        super(PhieuTra.class);
    }

    private static final PhieuTraDAO instance = new PhieuTraDAO();
    private Session session = GlobalDataSession.getSession();

    private ObservableList<PhieuTra> all = FXCollections.observableArrayList();

    public PhieuTra getByID(Integer id) {
        return all().parallelStream().filter(e -> e.getId() == id).findAny().orElse(null);
    }

    public static PhieuTraDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }


    public ObservableList<PhieuTra> all(){
        if(all.isEmpty()){
            all.addAll(instance.getAll());
        }
        return all;
    }

    public void reload(){
        all.clear();
        all.addAll(instance.getAll());
    }
}
