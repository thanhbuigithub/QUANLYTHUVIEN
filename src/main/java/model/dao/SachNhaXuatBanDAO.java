package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GlobalDataSession;
import model.dto.BanDoc;
import model.dto.NhaXuatBan;
import model.dto.SachNhaXuatBan;
import org.hibernate.Session;

public class SachNhaXuatBanDAO extends DAO<SachNhaXuatBan> {
    private SachNhaXuatBanDAO() {
        super(SachNhaXuatBan.class);
    }

    private static final SachNhaXuatBanDAO instance = new SachNhaXuatBanDAO();

    private Session session = GlobalDataSession.getSession();

    public static SachNhaXuatBanDAO getInstance() {
        instance.session = GlobalDataSession.getSession();
        return instance;
    }
}
