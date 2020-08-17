package model.dao;

import model.GlobalDataSession;
import model.dto.PhieuMuon;
import org.hibernate.Session;

public class PhieuMuonDAO extends DAO<PhieuMuon> {
    private PhieuMuonDAO() {
        super(PhieuMuon.class);
    }

    private static final PhieuMuonDAO instance = new PhieuMuonDAO();

    private Session session = GlobalDataSession.getSession();

    public PhieuMuon getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static PhieuMuonDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
