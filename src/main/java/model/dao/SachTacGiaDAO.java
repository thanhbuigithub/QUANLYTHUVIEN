package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.SachTacGia;
import model.dto.TacGia;
import org.hibernate.Session;

public class SachTacGiaDAO extends DAO<SachTacGia> {
    private SachTacGiaDAO() {
        super(SachTacGia.class);
    }

    private static final SachTacGiaDAO instance = new SachTacGiaDAO();

    private Session session = GlobalDataSession.getSession();

    public static SachTacGiaDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
