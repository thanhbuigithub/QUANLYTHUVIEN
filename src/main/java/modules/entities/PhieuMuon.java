package modules.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "phieu_muon", schema = "quanlythuvien")
public class PhieuMuon extends RecursiveTreeObject<PhieuMuon> {
    private int id;
    private ObjectProperty<Integer> idSach = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> idBanDoc = new SimpleObjectProperty<>();
    private ObjectProperty<Date> ngayMuon = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> thoiHanMuon = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> giaHan = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> idNhanVien = new SimpleObjectProperty<>();

    public PhieuMuon(){}

    public PhieuMuon(Integer idSach, Integer idTheThuVien, Integer idNhanVien)
    {
        this.idSach.set(idSach);
        this.idBanDoc.set(idTheThuVien);
        this.ngayMuon.set(new Date());
        this.thoiHanMuon.set(7);
        this.giaHan.set(0);
        this.idNhanVien.set(idNhanVien);
    }


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
        return idSach.get();
    }

    public void setIdSach(Integer idSach) {
        this.idSach.set(idSach);
    }

    @Basic
    @Column(name = "id_ban_doc")
    public Integer getIdBanDoc() {
        return idBanDoc.get();
    }

    public void setIdBanDoc(Integer idBanDoc) {
        this.idBanDoc.set(idBanDoc);
    }

    @Basic
    @Column(name = "ngay_muon")
    public Date getNgayMuon() {
        return ngayMuon.get();
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon.set(ngayMuon);
    }

    @Basic
    @Column(name = "thoi_han_muon")
    public Integer getThoiHanMuon() {
        return thoiHanMuon.get();
    }

    public void setThoiHanMuon(Integer thoiHanMuon) {
        this.thoiHanMuon.set(thoiHanMuon);
    }

    @Basic
    @Column(name = "gia_han")
    public Integer getGiaHan() {
        return giaHan.get();
    }

    public void setGiaHan(Integer giaHan) {
        this.giaHan.set(giaHan);
    }

    @Basic
    @Column(name = "id_nhan_vien")
    public Integer getIdNhanVien() {
        return idNhanVien.get();
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien.set(idNhanVien);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuMuon phieuMuon = (PhieuMuon) o;
        return id == phieuMuon.id &&
                Objects.equals(idSach, phieuMuon.idSach) &&
                Objects.equals(idBanDoc, phieuMuon.idTheThuVien) &&
                Objects.equals(ngayMuon, phieuMuon.ngayMuon) &&
                Objects.equals(thoiHanMuon, phieuMuon.thoiHanMuon) &&
                Objects.equals(giaHan, phieuMuon.giaHan) &&
                Objects.equals(idNhanVien, phieuMuon.idNhanVien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idSach, idBanDoc, ngayMuon, thoiHanMuon, giaHan, idNhanVien);
    }

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "id=" + id +
                ", idSach=" + idSach.get() +
                ", idTheThuVien=" + idBanDoc.get() +
                ", ngayMuon=" + ngayMuon.get() +
                ", thoiHanMuon=" + thoiHanMuon.get() +
                ", giaHan=" + giaHan.get() +
                ", idNhanVien=" + idNhanVien.get() +
                '}';
    }
}
