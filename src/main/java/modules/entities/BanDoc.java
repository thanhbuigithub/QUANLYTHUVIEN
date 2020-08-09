package modules.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ban_doc", schema = "quanlythuvien")
public class BanDoc extends RecursiveTreeObject<BanDoc> {
    private int id;
    public SimpleStringProperty hoVaTen = new SimpleStringProperty();
    public ObjectProperty<Date> ngaySinh = new SimpleObjectProperty<>();
    public ObjectProperty<Integer> gioiTinh = new SimpleObjectProperty<>();
    public SimpleStringProperty cmnd = new SimpleStringProperty();
    public SimpleStringProperty email = new SimpleStringProperty();
    public SimpleStringProperty sdt = new SimpleStringProperty();
    public ObjectProperty<Date> thoiHanSuDungThe = new SimpleObjectProperty<>();

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ho_va_ten")
    public String getHoVaTen() {
        return hoVaTen.get();
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen.set(hoVaTen);
    }

    @Basic
    @Column(name = "ngay_sinh")
    public Date getNgaySinh() {
        return ngaySinh.get();
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    @Basic
    @Column(name = "gioi_tinh")
    public Integer getGioiTinh() {
        return gioiTinh.get();
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh.set(gioiTinh);
    }

    @Basic
    @Column(name = "cmnd")
    public String getCmnd() {
        return cmnd.get();
    }

    public void setCmnd(String cmnd) {
        this.cmnd.set(cmnd);
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Basic
    @Column(name = "sdt")
    public String getSdt() {
        return sdt.get();
    }

    public void setSdt(String sdt) {
        this.sdt.set(sdt);
    }

    @Basic
    @Column(name = "thoi_han_su_dung_the")
    public Date getThoiHanSuDungThe() {
        return thoiHanSuDungThe.get();
    }

    public void setThoiHanSuDungThe(Date thoiHanSuDungThe) {
        this.thoiHanSuDungThe.set(thoiHanSuDungThe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanDoc banDoc = (BanDoc) o;
        return id == banDoc.id &&
                Objects.equals(hoVaTen, banDoc.hoVaTen) &&
                Objects.equals(ngaySinh, banDoc.ngaySinh) &&
                Objects.equals(gioiTinh, banDoc.gioiTinh) &&
                Objects.equals(cmnd, banDoc.cmnd) &&
                Objects.equals(email, banDoc.email) &&
                Objects.equals(sdt, banDoc.sdt) &&
                Objects.equals(thoiHanSuDungThe, banDoc.thoiHanSuDungThe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoVaTen, ngaySinh, gioiTinh, cmnd, email, sdt, thoiHanSuDungThe);
    }

    @Override
    public String toString() {
        return id + " - " + hoVaTen;
    }
}
