package model.dao;

import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.NhaXuatBan;
import org.hibernate.Session;

public class NhaXuatBanDAO extends DAO<NhaXuatBan> {
    private NhaXuatBanDAO() {
        super(NhaXuatBan.class);
    }

    private static final NhaXuatBanDAO instance = new NhaXuatBanDAO();

    private Session session = GlobalDataSession.getSession();

    public NhaXuatBan getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static NhaXuatBanDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
