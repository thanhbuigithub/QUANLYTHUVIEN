package modules;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "phieu_tra", schema = "quanlythuvien", catalog = "")
public class PhieuTra {
    private int id;
    private Integer idPhieuMuon;
    private Date ngayTra;
    private String tinhTrang;
    private String boiThuong;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_phieu_muon")
    public Integer getIdPhieuMuon() {
        return idPhieuMuon;
    }

    public void setIdPhieuMuon(Integer idPhieuMuon) {
        this.idPhieuMuon = idPhieuMuon;
    }

    @Basic
    @Column(name = "ngay_tra")
    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    @Basic
    @Column(name = "tinh_trang")
    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Basic
    @Column(name = "boi_thuong")
    public String getBoiThuong() {
        return boiThuong;
    }

    public void setBoiThuong(String boiThuong) {
        this.boiThuong = boiThuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuTra phieuTra = (PhieuTra) o;
        return id == phieuTra.id &&
                Objects.equals(idPhieuMuon, phieuTra.idPhieuMuon) &&
                Objects.equals(ngayTra, phieuTra.ngayTra) &&
                Objects.equals(tinhTrang, phieuTra.tinhTrang) &&
                Objects.equals(boiThuong, phieuTra.boiThuong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPhieuMuon, ngayTra, tinhTrang, boiThuong);
    }
}
