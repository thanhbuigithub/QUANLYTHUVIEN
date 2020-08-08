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
    public ObjectProperty<Integer> maNxb = new SimpleObjectProperty<>();
    public ObjectProperty<Integer> maViTri = new SimpleObjectProperty<>();
    public SimpleStringProperty moTa = new SimpleStringProperty();
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
    @Column(name = "ma_nxb")
    public Integer getMaNxb() {
        return maNxb.get();
    }

    public void setMaNxb(Integer maNxb) {
        this.maNxb.set(maNxb);
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
    @Column(name = "mo_ta")
    public String getMoTa() {
        return moTa.get();
    }

    public void setMoTa(String moTa) {
        this.moTa.set(moTa);
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
