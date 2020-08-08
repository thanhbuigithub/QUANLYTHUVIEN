package modules.dao;

import controller.GlobalDataSession;
import modules.entities.TheLoai;
import org.hibernate.Session;

import java.util.List;

public class TheLoaiDAO extends AbstractDAO<TheLoai>{
    private TheLoaiDAO() {
        super(TheLoai.class);
    }

    private static final TheLoaiDAO instance = new TheLoaiDAO();

    private Session session = GlobalDataSession.getSession();

    private List<TheLoai> all = null;

    public TheLoai getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static TheLoaiDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public List<TheLoai> all(){
        if(all==null)
            return instance.getAll();
        else return all;
    }
}
