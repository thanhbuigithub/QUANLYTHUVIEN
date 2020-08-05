package modules;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "nhan_vien", schema = "quanlythuvien", catalog = "")
public class NhanVien {
    private int id;
    private String hoVaTen;
    private Date ngaySinh;
    private Integer gioiTinh;
    private String cmnd;
    private String email;
    private String sdt;
    private String username;
    private String password;
    private Integer chucDanh;

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "chuc_danh")
    public Integer getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(Integer chucDanh) {
        this.chucDanh = chucDanh;
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
