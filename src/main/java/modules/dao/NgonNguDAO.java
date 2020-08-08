package modules.dao;

import controller.GlobalDataSession;
import modules.entities.NgonNgu;
import org.hibernate.Session;

import java.util.List;

public class NgonNguDAO extends AbstractDAO<NgonNgu>{
    private NgonNguDAO() {
        super(NgonNgu.class);
    }

    private static final NgonNguDAO instance = new NgonNguDAO();

    private Session session = GlobalDataSession.getSession();

    private List<NgonNgu> all = null;

    public NgonNgu getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static NgonNguDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<NgonNgu> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
