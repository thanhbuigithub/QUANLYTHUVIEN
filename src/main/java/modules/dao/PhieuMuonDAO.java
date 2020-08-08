package modules.dao;

import controller.GlobalDataSession;
import modules.entities.PhieuMuon;
import org.hibernate.Session;

import java.util.List;

public class PhieuMuonDAO extends AbstractDAO<PhieuMuon>{
    private PhieuMuonDAO() {
        super(PhieuMuon.class);
    }

    private static final PhieuMuonDAO instance = new PhieuMuonDAO();

    private Session session = GlobalDataSession.getSession();

    private List<PhieuMuon> all = null;

    public PhieuMuon getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static PhieuMuonDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<PhieuMuon> all(){
        if (all == null)
        return instance.getAll();
        else return all;
    }
}
