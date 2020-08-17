package model.dao;

import model.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.NhanVien;
import org.hibernate.Session;

public class NhanVienDAO extends DAO<NhanVien> {
    private NhanVienDAO() {
        super(NhanVien.class);
    }

    private static final NhanVienDAO instance = new NhanVienDAO();

    private Session session = GlobalDataSession.getSession();

    public ObservableList<NhanVien> all = FXCollections.observableArrayList();

    public NhanVien getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static NhanVienDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }

    public ObservableList<NhanVien> all(){
        if(all.isEmpty()){
            all.addAll(instance.getAll());
        }
        return all;
    }

    public void reload(){
        all.clear();
        all.addAll(instance.getAll());
    }
}
