package modules.dao;

import controller.GlobalDataSession;
import modules.entities.ViTri;
import org.hibernate.Session;

import java.util.List;

public class ViTriDAO extends AbstractDAO<ViTri>{
    private ViTriDAO() {
        super(ViTri.class);
    }

    private static final ViTriDAO instance = new ViTriDAO();

    private Session session = GlobalDataSession.getSession();

    private List<ViTri> all = null;

    public ViTri getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static ViTriDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<ViTri> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
