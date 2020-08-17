package model.dao;

import model.GlobalDataSession;
import model.dto.PhieuTra;
import org.hibernate.Session;

public class PhieuTraDAO extends DAO<PhieuTra> {
    private PhieuTraDAO() {
        super(PhieuTra.class);
    }

    private static final PhieuTraDAO instance = new PhieuTraDAO();
    private Session session = GlobalDataSession.getSession();

    public PhieuTra getByID(Integer id) {
        return all().parallelStream().filter(e -> e.getId() == id).findAny().orElse(null);
    }

    public static PhieuTraDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
