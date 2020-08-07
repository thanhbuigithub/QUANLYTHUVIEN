package modules.entities;

import modules.dao.BanDocDAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "the_thu_vien", schema = "quanlythuvien", catalog = "")
public class TheThuVien {
    private int id;
    private Integer idBanDoc;
    private Date ngayTao;
    private Date thoiHanSuDung;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_ban_doc")
    public Integer getIdBanDoc() {
        return idBanDoc;
    }

    public void setIdBanDoc(Integer idBanDoc) {
        this.idBanDoc = idBanDoc;
    }

    @Basic
    @Column(name = "ngay_tao")
    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Basic
    @Column(name = "thoi_han_su_dung")
    public Date getThoiHanSuDung() {
        return thoiHanSuDung;
    }

    public void setThoiHanSuDung(Date thoiHanSuDung) {
        this.thoiHanSuDung = thoiHanSuDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheThuVien that = (TheThuVien) o;
        return id == that.id &&
                Objects.equals(idBanDoc, that.idBanDoc) &&
                Objects.equals(ngayTao, that.ngayTao) &&
                Objects.equals(thoiHanSuDung, that.thoiHanSuDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBanDoc, ngayTao, thoiHanSuDung);
    }

    @Override
    public String toString() {
        BanDoc banDoc = BanDocDAO.getInstance().getByID(idBanDoc);
        return id + " - " + banDoc.getHoVaTen();
    }
}
