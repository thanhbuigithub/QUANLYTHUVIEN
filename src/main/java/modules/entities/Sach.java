package modules.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Sach extends RecursiveTreeObject<Sach> {
    private int id;
    public SimpleStringProperty tenSach = new SimpleStringProperty();
    public ObjectProperty<Integer> namXuatBan = new SimpleObjectProperty<>();
    public SimpleStringProperty nhaXuatBan = new SimpleStringProperty();
    public SimpleStringProperty ngonNgu = new SimpleStringProperty();
    public SimpleStringProperty tacGia = new SimpleStringProperty();
    public SimpleStringProperty theLoai = new SimpleStringProperty();
    public ObjectProperty<Integer> maViTri = new SimpleObjectProperty<>();
    public ObjectProperty<Integer> giaBia = new SimpleObjectProperty<>();
    public ObjectProperty<Integer> soLuong = new SimpleObjectProperty<>();
    public ObjectProperty<Integer> soTrang = new SimpleObjectProperty<>();

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
        return tenSach.get();
    }

    public void setTenSach(String tenSach) {
        this.tenSach.set(tenSach);
    }

    @Basic
    @Column(name = "nam_xuat_ban")
    public Integer getNamXuatBan() {
        return namXuatBan.get();
    }

    public void setNamXuatBan(Integer namXuatBan) {
        this.namXuatBan.set(namXuatBan);
    }

    @Basic
    @Column(name = "nha_xuat_ban")
    public String getNhaXuatBan() {
        return nhaXuatBan.get();
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan.set(nhaXuatBan);
    }

    @Basic
    @Column(name = "ngon_ngu")
    public String getNgonNgu() {
        return ngonNgu.get();
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu.set(ngonNgu);
    }

    @Basic
    @Column(name = "tac_gia")
    public String getTacGia() {
        return tacGia.get();
    }

    public void setTacGia(String tacGia) {
        this.tacGia.set(tacGia);
    }

    @Basic
    @Column(name = "the_loai")
    public String getTheLoai() {
        return theLoai.get();
    }

    public void setTheLoai(String theLoai) {
        this.theLoai.set(theLoai);
    }

    @Basic
    @Column(name = "ma_vi_tri")
    public Integer getMaViTri() {
        return maViTri.get();
    }

    public void setMaViTri(Integer maViTri) {
        this.maViTri.set(maViTri);
    }

    @Basic
    @Column(name = "gia_bia")
    public Integer getGiaBia() {
        return giaBia.get();
    }

    public void setGiaBia(Integer giaBia) {
        this.giaBia.set(giaBia);
    }

    @Basic
    @Column(name = "so_luong")
    public Integer getSoLuong() {
        return soLuong.get();
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong.set(soLuong);
    }

    @Basic
    @Column(name = "so_trang")
    public Integer getSoTrang() {
        return soTrang.get();
    }

    public void setSoTrang(Integer soTrang) {
        this.soTrang.set(soTrang);
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
