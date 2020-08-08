package modules.dao;

import controller.GlobalDataSession;
import modules.entities.Sach;
import org.hibernate.Session;

import java.util.List;

public class SachDAO extends AbstractDAO<Sach>{
    private SachDAO() {
        super(Sach.class);
    }

    private static final SachDAO instance = new SachDAO();

    private Session session = GlobalDataSession.getSession();

    public List<Sach> all = null;

    public Sach getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static SachDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<Sach> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
