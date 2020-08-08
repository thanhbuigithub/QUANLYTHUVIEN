package modules.dao;

import controller.GlobalDataSession;
import modules.entities.SachNgonNgu;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SachNgonNguDAO extends AbstractDAO<SachNgonNgu>{
    private SachNgonNguDAO() {
        super(SachNgonNgu.class);
    }

    private static final SachNgonNguDAO instance = new SachNgonNguDAO();

    private Session session = GlobalDataSession.getSession();

    private List<SachNgonNgu> all = null;

    public List<SachNgonNgu> getByIDSach(Integer id){
        return all().parallelStream().filter(e->e.getIdSach()==id).collect(Collectors.toList());
    }

    public static SachNgonNguDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<SachNgonNgu> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
