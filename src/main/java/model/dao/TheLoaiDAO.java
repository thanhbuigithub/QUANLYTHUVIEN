package model.dao;

import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.TheLoai;
import org.hibernate.Session;

public class TheLoaiDAO extends DAO<TheLoai> {
    private TheLoaiDAO() {
        super(TheLoai.class);
    }

    private static final TheLoaiDAO instance = new TheLoaiDAO();

    private Session session = GlobalDataSession.getSession();

    public TheLoai getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static TheLoaiDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
