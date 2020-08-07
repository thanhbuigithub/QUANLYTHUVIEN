package modules.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Sach {
    private int id;
    private String tenSach;
    private int namXuatBan;
    private int maNxb;
    private Integer maViTri;
    private String moTa;
    private Integer giaBia;
    private Integer soLuong;
    private int soTrang;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ten_sach")
    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    @Basic
    @Column(name = "nam_xuat_ban")
    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    @Basic
    @Column(name = "ma_nxb")
    public int getMaNxb() {
        return maNxb;
    }

    public void setMaNxb(int maNxb) {
        this.maNxb = maNxb;
    }

    @Basic
    @Column(name = "ma_vi_tri")
    public Integer getMaViTri() {
        return maViTri;
    }

    public void setMaViTri(Integer maViTri) {
        this.maViTri = maViTri;
    }

    @Basic
    @Column(name = "mo_ta")
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Basic
    @Column(name = "gia_bia")
    public Integer getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(Integer giaBia) {
        this.giaBia = giaBia;
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
    @Column(name = "so_trang")
    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sach sach = (Sach) o;
        return id == sach.id &&
                namXuatBan == sach.namXuatBan &&
                maNxb == sach.maNxb &&
                soTrang == sach.soTrang &&
                Objects.equals(tenSach, sach.tenSach) &&
                Objects.equals(maViTri, sach.maViTri) &&
                Objects.equals(moTa, sach.moTa) &&
                Objects.equals(giaBia, sach.giaBia) &&
                Objects.equals(soLuong, sach.soLuong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenSach, namXuatBan, maNxb, maViTri, moTa, giaBia, soLuong, soTrang);
    }

    @Override
    public String toString() {
        return id + " - " + tenSach;
    }
}
