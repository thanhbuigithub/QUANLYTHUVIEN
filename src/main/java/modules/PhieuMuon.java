package modules;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "phieu_muon", schema = "quanlythuvien", catalog = "")
public class PhieuMuon {
    private int id;
    private Integer idSach;
    private Integer idTheThuVien;
    private Date ngayMuon;
    private Integer thoiHanMuon;
    private Integer giaHan;
    private Integer idNhanVien;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_sach")
    public Integer getIdSach() {
        return idSach;
    }

    public void setIdSach(Integer idSach) {
        this.idSach = idSach;
    }

    @Basic
    @Column(name = "id_the_thu_vien")
    public Integer getIdTheThuVien() {
        return idTheThuVien;
    }

    public void setIdTheThuVien(Integer idTheThuVien) {
        this.idTheThuVien = idTheThuVien;
    }

    @Basic
    @Column(name = "ngay_muon")
    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    @Basic
    @Column(name = "thoi_han_muon")
    public Integer getThoiHanMuon() {
        return thoiHanMuon;
    }

    public void setThoiHanMuon(Integer thoiHanMuon) {
        this.thoiHanMuon = thoiHanMuon;
    }

    @Basic
    @Column(name = "gia_han")
    public Integer getGiaHan() {
        return giaHan;
    }

    public void setGiaHan(Integer giaHan) {
        this.giaHan = giaHan;
    }

    @Basic
    @Column(name = "id_nhan_vien")
    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuMuon phieuMuon = (PhieuMuon) o;
        return id == phieuMuon.id &&
                Objects.equals(idSach, phieuMuon.idSach) &&
                Objects.equals(idTheThuVien, phieuMuon.idTheThuVien) &&
                Objects.equals(ngayMuon, phieuMuon.ngayMuon) &&
                Objects.equals(thoiHanMuon, phieuMuon.thoiHanMuon) &&
                Objects.equals(giaHan, phieuMuon.giaHan) &&
                Objects.equals(idNhanVien, phieuMuon.idNhanVien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idSach, idTheThuVien, ngayMuon, thoiHanMuon, giaHan, idNhanVien);
    }
}
