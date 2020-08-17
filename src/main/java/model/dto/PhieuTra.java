package model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "phieu_tra", schema = "quanlythuvien", catalog = "")
public class PhieuTra extends RecursiveTreeObject<PhieuTra> {
    private int id;
    private PhieuMuon phieuMuon;
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
                Objects.equals(phieuMuon, phieuTra.phieuMuon) &&
                Objects.equals(ngayTra, phieuTra.ngayTra) &&
                Objects.equals(tinhTrang, phieuTra.tinhTrang) &&
                Objects.equals(boiThuong, phieuTra.boiThuong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phieuMuon, ngayTra, tinhTrang, boiThuong);
    }

    @OneToOne(cascade = CascadeType.ALL)
    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }
}
