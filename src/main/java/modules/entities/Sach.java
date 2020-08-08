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
    private String nhaXuatBan;
    private String ngonNgu;
    private String tacGia;
    private String theLoai;
    private Integer maViTri;
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
    @Column(name = "nha_xuat_ban")
    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @Basic
    @Column(name = "ngon_ngu")
    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    @Basic
    @Column(name = "tac_gia")
    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    @Basic
    @Column(name = "the_loai")
    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
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
                soTrang == sach.soTrang &&
                Objects.equals(tenSach, sach.tenSach) &&
                Objects.equals(nhaXuatBan, sach.nhaXuatBan) &&
                Objects.equals(ngonNgu, sach.ngonNgu) &&
                Objects.equals(tacGia, sach.tacGia) &&
                Objects.equals(theLoai, sach.theLoai) &&
                Objects.equals(maViTri, sach.maViTri) &&
                Objects.equals(giaBia, sach.giaBia) &&
                Objects.equals(soLuong, sach.soLuong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenSach, namXuatBan, nhaXuatBan, ngonNgu, tacGia, theLoai, maViTri, giaBia, soLuong, soTrang);
    }

    @Override
    public String toString() {
        return id + " - " + tenSach;
    }
}
