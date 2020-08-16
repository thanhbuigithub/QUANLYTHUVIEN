package modules.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "nhan_vien", schema = "quanlythuvien", catalog = "")
public class NhanVien extends RecursiveTreeObject<NhanVien> {
    private int id;
    private SimpleStringProperty hoVaTen = new SimpleStringProperty();
    private ObjectProperty<Date> ngaySinh = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> gioiTinh = new SimpleObjectProperty<>();
    private SimpleStringProperty cmnd = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty sdt = new SimpleStringProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private ObjectProperty<Integer> chucDanh = new SimpleObjectProperty<>();

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
    @Column(name = "username")
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Basic
    @Column(name = "chuc_danh")
    public Integer getChucDanh() {
        return chucDanh.get();
    }

    public void setChucDanh(Integer chucDanh) {
        this.chucDanh.set(chucDanh);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NhanVien nhanVien = (NhanVien) o;
        return id == nhanVien.id &&
                Objects.equals(hoVaTen, nhanVien.hoVaTen) &&
                Objects.equals(ngaySinh, nhanVien.ngaySinh) &&
                Objects.equals(gioiTinh, nhanVien.gioiTinh) &&
                Objects.equals(cmnd, nhanVien.cmnd) &&
                Objects.equals(email, nhanVien.email) &&
                Objects.equals(sdt, nhanVien.sdt) &&
                Objects.equals(username, nhanVien.username) &&
                Objects.equals(password, nhanVien.password) &&
                Objects.equals(chucDanh, nhanVien.chucDanh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoVaTen, ngaySinh, gioiTinh, cmnd, email, sdt, username, password, chucDanh);
    }
}
