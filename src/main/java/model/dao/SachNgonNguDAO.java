package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.NgonNgu;
import model.dto.Sach;
import model.dto.SachNgonNgu;
import org.hibernate.Session;

public class SachNgonNguDAO extends DAO<SachNgonNgu> {
    private SachNgonNguDAO() {
        super(SachNgonNgu.class);
    }

    private static final SachNgonNguDAO instance = new SachNgonNguDAO();

    private Session session = GlobalDataSession.getSession();

    public static SachNgonNguDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
