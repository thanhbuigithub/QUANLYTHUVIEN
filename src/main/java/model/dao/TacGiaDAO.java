package model.dao;

import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.TacGia;
import org.hibernate.Session;

public class TacGiaDAO extends DAO<TacGia> {
    private TacGiaDAO() {
        super(TacGia.class);
    }

    private static final TacGiaDAO instance = new TacGiaDAO();

    private Session session = GlobalDataSession.getSession();

    public TacGia getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static TacGiaDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
