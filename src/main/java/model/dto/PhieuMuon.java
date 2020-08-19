package model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "phieu_muon", schema = "quanlythuvien", catalog = "")
public class PhieuMuon extends RecursiveTreeObject<PhieuMuon> {
    private int id;
    private Sach sach;
    private BanDoc banDoc;
    private Integer soLuong;
    private Date ngayMuon;
    private Integer thoiHanMuon;
    private Integer giaHan;
    private NhanVien nhanVien;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "so_luong")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuMuon phieuMuon = (PhieuMuon) o;
        return id == phieuMuon.id &&
                Objects.equals(sach, phieuMuon.sach) &&
                Objects.equals(banDoc, phieuMuon.banDoc) &&
                Objects.equals(soLuong, phieuMuon.soLuong) &&
                Objects.equals(ngayMuon, phieuMuon.ngayMuon) &&
                Objects.equals(thoiHanMuon, phieuMuon.thoiHanMuon) &&
                Objects.equals(giaHan, phieuMuon.giaHan) &&
                Objects.equals(nhanVien, phieuMuon.nhanVien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sach, banDoc, soLuong, ngayMuon, thoiHanMuon, giaHan, nhanVien);
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public BanDoc getBanDoc() {
        return banDoc;
    }

    public void setBanDoc(BanDoc banDoc) {
        this.banDoc = banDoc;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", sach=" + sach +
                ", banDoc=" + banDoc;
    }
}
