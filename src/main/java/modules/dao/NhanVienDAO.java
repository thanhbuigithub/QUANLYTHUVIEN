package modules.dao;

import controller.GlobalDataSession;
import modules.entities.BanDoc;
import modules.entities.NhanVien;
import modules.entities.PhieuMuon;
import org.hibernate.Session;

import java.util.List;

public class NhanVienDAO extends AbstractDAO<NhanVien>{
    private NhanVienDAO() {
        super(NhanVien.class);
    }

    private static final NhanVienDAO instance = new NhanVienDAO();

    private Session session = GlobalDataSession.getSession();

    public List<NhanVien> all = null;

    public NhanVien getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static NhanVienDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<NhanVien> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
