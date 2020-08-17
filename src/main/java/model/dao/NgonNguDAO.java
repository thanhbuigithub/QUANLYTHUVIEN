package model.dao;

import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.NgonNgu;
import org.hibernate.Session;

public class NgonNguDAO extends DAO<NgonNgu> {
    private NgonNguDAO() {
        super(NgonNgu.class);
    }

    private static final NgonNguDAO instance = new NgonNguDAO();

    private Session session = GlobalDataSession.getSession();

    public NgonNgu getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static NgonNguDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
