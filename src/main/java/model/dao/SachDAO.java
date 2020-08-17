package model.dao;

import model.GlobalDataSession;
import model.dto.Sach;
import org.hibernate.Session;

public class SachDAO extends DAO<Sach> {
    private SachDAO() {
        super(Sach.class);
    }

    private static final SachDAO instance = new SachDAO();

    private Session session = GlobalDataSession.getSession();

    public Sach getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static SachDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
