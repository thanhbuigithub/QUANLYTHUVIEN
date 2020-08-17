package model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ban_doc", schema = "quanlythuvien", catalog = "")
public class BanDoc extends RecursiveTreeObject<BanDoc> {
    private int id;
    private String hoVaTen;
    private Date ngaySinh;
    private Integer gioiTinh;
    private String cmnd;
    private String email;
    private String sdt;
    private Date ngayTaoThe;
    private Integer thoiHanSuDung;

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
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Basic
    @Column(name = "ngay_sinh")
    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Basic
    @Column(name = "gioi_tinh")
    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Basic
    @Column(name = "cmnd")
    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "sdt")
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Basic
    @Column(name = "ngay_tao_the")
    public Date getNgayTaoThe() {
        return ngayTaoThe;
    }

    public void setNgayTaoThe(Date ngayTaoThe) {
        this.ngayTaoThe = ngayTaoThe;
    }

    @Basic
    @Column(name = "thoi_han_su_dung")
    public Integer getThoiHanSuDung() {
        return thoiHanSuDung;
    }

    public void setThoiHanSuDung(Integer thoiHanSuDung) {
        this.thoiHanSuDung = thoiHanSuDung;
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
                Objects.equals(ngayTaoThe, banDoc.ngayTaoThe) &&
                Objects.equals(thoiHanSuDung, banDoc.thoiHanSuDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoVaTen, ngaySinh, gioiTinh, cmnd, email, sdt, ngayTaoThe, thoiHanSuDung);
    }

    @Override
    public String toString() {
        return id + " - " + hoVaTen;
    }
}
