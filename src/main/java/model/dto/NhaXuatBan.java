package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nha_xuat_ban", schema = "quanlythuvien", catalog = "")
public class NhaXuatBan {
    private int id;
    private String tenNhaXuatBan;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ten_nha_xuat_ban")
    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NhaXuatBan that = (NhaXuatBan) o;
        return id == that.id &&
                Objects.equals(tenNhaXuatBan, that.tenNhaXuatBan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenNhaXuatBan);
    }
}
