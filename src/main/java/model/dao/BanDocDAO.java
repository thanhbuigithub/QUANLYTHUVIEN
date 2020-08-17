package model.dao;

import model.GlobalDataSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.BanDoc;
import org.hibernate.Session;

public class BanDocDAO extends DAO<BanDoc> {
    private BanDocDAO() {
        super(BanDoc.class);
    }

    private static final BanDocDAO instance = new BanDocDAO();

    private Session session = GlobalDataSession.getSession();

    public BanDoc getByID(Integer id){
        return all().parallelStream().filter(e->e.getId()==id).findAny().orElse(null);
    }

    public static BanDocDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
