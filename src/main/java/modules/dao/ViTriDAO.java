package modules.dao;

import controller.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.entities.Sach;
import modules.entities.ViTri;
import org.hibernate.Session;

import java.util.List;

public class ViTriDAO extends AbstractDAO<ViTri>{
    private ViTriDAO() {
        super(ViTri.class);
    }

    private static final ViTriDAO instance = new ViTriDAO();

    private Session session = GlobalDataSession.getSession();

    private ObservableList<ViTri> all = FXCollections.observableArrayList();

    public ViTri getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static ViTriDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public ObservableList<ViTri> all(){
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
