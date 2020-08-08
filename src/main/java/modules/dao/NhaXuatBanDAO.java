package modules.dao;

import controller.GlobalDataSession;
import modules.entities.Nxb;
import org.hibernate.Session;

import java.util.List;

public class NhaXuatBanDAO extends AbstractDAO<Nxb>{
    private NhaXuatBanDAO() {
        super(Nxb.class);
    }

    private static final NhaXuatBanDAO instance = new NhaXuatBanDAO();

    private Session session = GlobalDataSession.getSession();

    private List<Nxb> all = null;

    public Nxb getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static NhaXuatBanDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<Nxb> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
