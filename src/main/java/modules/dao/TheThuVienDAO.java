package modules.dao;

import controller.GlobalDataSession;
import modules.entities.BanDoc;
import modules.entities.PhieuMuon;
import modules.entities.TheThuVien;
import org.hibernate.Session;

import java.util.List;

public class TheThuVienDAO extends AbstractDAO<TheThuVien>{
    private TheThuVienDAO() {
        super(TheThuVien.class);
    }

    private static final TheThuVienDAO instance = new TheThuVienDAO();

    private Session session = GlobalDataSession.getSession();

    public List<TheThuVien> all = null;

    public TheThuVien getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static TheThuVienDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<TheThuVien> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
