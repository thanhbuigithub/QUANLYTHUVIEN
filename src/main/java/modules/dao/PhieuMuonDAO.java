package modules.dao;

import controller.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.entities.PhieuMuon;
import modules.entities.PhieuTra;
import org.hibernate.Session;

import java.util.List;

public class PhieuMuonDAO extends AbstractDAO<PhieuMuon>{
    private PhieuMuonDAO() {
        super(PhieuMuon.class);
    }

    private static final PhieuMuonDAO instance = new PhieuMuonDAO();

    private Session session = GlobalDataSession.getSession();

    private ObservableList<PhieuMuon> all = FXCollections.observableArrayList();

    public PhieuMuon getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static PhieuMuonDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public ObservableList<PhieuMuon> all(){
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
