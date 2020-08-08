package modules.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lich_su_dang_nhap", schema = "quanlythuvien", catalog = "")
public class LichSuDangNhap {
    private int id;
    private Integer idNhanVien;
    private Timestamp thoiGianDangNhap;
    private Timestamp thoiGianDangXuat;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_nhan_vien")
    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    @Basic
    @Column(name = "thoi_gian_dang_nhap")
    public Timestamp getThoiGianDangNhap() {
        return thoiGianDangNhap;
    }

    public void setThoiGianDangNhap(Timestamp thoiGianDangNhap) {
        this.thoiGianDangNhap = thoiGianDangNhap;
    }

    @Basic
    @Column(name = "thoi_gian_dang_xuat")
    public Timestamp getThoiGianDangXuat() {
        return thoiGianDangXuat;
    }

    public void setThoiGianDangXuat(Timestamp thoiGianDangXuat) {
        this.thoiGianDangXuat = thoiGianDangXuat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichSuDangNhap that = (LichSuDangNhap) o;
        return id == that.id &&
                Objects.equals(idNhanVien, that.idNhanVien) &&
                Objects.equals(thoiGianDangNhap, that.thoiGianDangNhap) &&
                Objects.equals(thoiGianDangXuat, that.thoiGianDangXuat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idNhanVien, thoiGianDangNhap, thoiGianDangXuat);
    }
}
