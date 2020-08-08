package modules.dao;

import controller.GlobalDataSession;
import modules.entities.TacGia;
import org.hibernate.Session;

import java.util.List;

public class TacGiaDAO extends AbstractDAO<TacGia>{
    private TacGiaDAO() {
        super(TacGia.class);
    }

    private static final TacGiaDAO instance = new TacGiaDAO();

    private Session session = GlobalDataSession.getSession();

    private List<TacGia> all = null;

    public TacGia getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static TacGiaDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<TacGia> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
