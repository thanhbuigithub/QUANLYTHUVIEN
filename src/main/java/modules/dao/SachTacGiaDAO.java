package modules.dao;

import controller.GlobalDataSession;
import modules.entities.SachTacGia;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SachTacGiaDAO extends AbstractDAO<SachTacGia>{
    private SachTacGiaDAO() {
        super(SachTacGia.class);
    }

    private static final SachTacGiaDAO instance = new SachTacGiaDAO();

    private Session session = GlobalDataSession.getSession();

    private List<SachTacGia> all = null;

    public List<SachTacGia> getByIDSach(Integer id){
        return all().parallelStream().filter(e->e.getIdSach()==id).collect(Collectors.toList());
    }

    public static SachTacGiaDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<SachTacGia> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
