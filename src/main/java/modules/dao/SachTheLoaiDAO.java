package modules.dao;

import controller.GlobalDataSession;
import modules.entities.SachTheLoai;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SachTheLoaiDAO extends AbstractDAO<SachTheLoai>{
    private SachTheLoaiDAO() {
        super(SachTheLoai.class);
    }

    private static final SachTheLoaiDAO instance = new SachTheLoaiDAO();

    private Session session = GlobalDataSession.getSession();

    private List<SachTheLoai> all = null;

    public List<SachTheLoai> getByIDSach(Integer id){
        return all().parallelStream().filter(e->e.getIdSach()==id).collect(Collectors.toList());
    }

    public static SachTheLoaiDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<SachTheLoai> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
