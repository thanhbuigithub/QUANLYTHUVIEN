package modules.dao;

import controller.GlobalDataSession;
import modules.entities.PhieuTra;
import org.hibernate.Session;

import java.util.List;

public class PhieuTraDAO extends AbstractDAO<PhieuTra> {
    private PhieuTraDAO() {
        super(PhieuTra.class);
    }

    private static final PhieuTraDAO instance = new PhieuTraDAO();
    private Session session = GlobalDataSession.getSession();

    private List<PhieuTra> all = null;

    public PhieuTra getByID(Integer id) {
        return all().parallelStream().filter(e -> e.getId() == id).findAny().orElse(null);
    }

    public static PhieuTraDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<PhieuTra> all() {
        if (all == null)
            return instance.getAll();
        else return all;
    }
}
