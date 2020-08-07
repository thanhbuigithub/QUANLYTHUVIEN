package modules.dao;

import controller.GlobalDataSession;
import modules.entities.BanDoc;
import modules.entities.PhieuMuon;
import org.hibernate.Session;

import java.util.List;

public class BanDocDAO extends AbstractDAO<BanDoc>{
    private BanDocDAO() {
        super(BanDoc.class);
    }

    private static final BanDocDAO instance = new BanDocDAO();

    private Session session = GlobalDataSession.getSession();

    private List<BanDoc> all = null;

    public BanDoc getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static BanDocDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<BanDoc> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
