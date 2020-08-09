package modules.dao;

import controller.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.entities.BanDoc;
import modules.entities.PhieuTra;
import org.hibernate.Session;

import java.util.List;

public class BanDocDAO extends AbstractDAO<BanDoc>{
    private BanDocDAO() {
        super(BanDoc.class);
    }

    private static final BanDocDAO instance = new BanDocDAO();

    private Session session = GlobalDataSession.getSession();

    private ObservableList<BanDoc> all = FXCollections.observableArrayList();

    public BanDoc getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static BanDocDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public ObservableList<BanDoc> all(){
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
