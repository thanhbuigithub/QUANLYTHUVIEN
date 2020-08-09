package modules.dao;

import controller.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.entities.Sach;
import org.hibernate.Session;

import java.util.List;

public class SachDAO extends AbstractDAO<Sach>{
    private SachDAO() {
        super(Sach.class);
    }

    private static final SachDAO instance = new SachDAO();

    private Session session = GlobalDataSession.getSession();

    public ObservableList<Sach> all = FXCollections.observableArrayList();

    public Sach getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static SachDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public ObservableList<Sach> all(){
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
