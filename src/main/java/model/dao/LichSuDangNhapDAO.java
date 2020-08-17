package model.dao;

import model.GlobalDataSession;
import model.dto.LichSuDangNhap;
import org.hibernate.Session;

public class LichSuDangNhapDAO extends DAO<LichSuDangNhap> {
    private LichSuDangNhapDAO() {
        super(LichSuDangNhap.class);
    }

    private static final LichSuDangNhapDAO instance = new LichSuDangNhapDAO();

    private Session session = GlobalDataSession.getSession();

    public LichSuDangNhap getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static LichSuDangNhapDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
