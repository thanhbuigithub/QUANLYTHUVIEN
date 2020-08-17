package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.SachTheLoai;
import org.hibernate.Session;

public class SachTheLoaiDAO extends DAO<SachTheLoai> {
    private SachTheLoaiDAO() {
        super(SachTheLoai.class);
    }

    private static final SachTheLoaiDAO instance = new SachTheLoaiDAO();

    private Session session = GlobalDataSession.getSession();

    public static SachTheLoaiDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
