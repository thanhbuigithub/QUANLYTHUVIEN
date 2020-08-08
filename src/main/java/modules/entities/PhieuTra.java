package modules.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "phieu_tra", schema = "quanlythuvien")
public class PhieuTra extends RecursiveTreeObject<PhieuTra> {
    private int id;
    private ObjectProperty<Integer> idPhieuMuon = new SimpleObjectProperty<>();
    private ObjectProperty<Date> ngayTra = new SimpleObjectProperty<>();
    private StringProperty tinhTrang = new SimpleStringProperty();
    private StringProperty boiThuong = new SimpleStringProperty();

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
        return idPhieuMuon.get();
    }

    public void setIdPhieuMuon(Integer idPhieuMuon) {
        this.idPhieuMuon.set(idPhieuMuon);
    }

    @Basic
    @Column(name = "ngay_tra")
    public Date getNgayTra() {
        return ngayTra.get();
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra.set(ngayTra);
    }

    @Basic
    @Column(name = "tinh_trang")
    public String getTinhTrang() {
        return tinhTrang.get();
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang.setValue(tinhTrang);
    }

    @Basic
    @Column(name = "boi_thuong")
    public String getBoiThuong() {
        return boiThuong.get();
    }

    public void setBoiThuong(String boiThuong) {
        this.boiThuong.set(boiThuong);
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
