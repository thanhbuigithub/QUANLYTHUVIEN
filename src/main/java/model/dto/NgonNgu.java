package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ngon_ngu", schema = "quanlythuvien", catalog = "")
public class NgonNgu {
    private int id;
    private String tenNgonNgu;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ten_ngon_ngu")
    public String getTenNgonNgu() {
        return tenNgonNgu;
    }

    public void setTenNgonNgu(String tenNgonNgu) {
        this.tenNgonNgu = tenNgonNgu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NgonNgu ngonNgu = (NgonNgu) o;
        return id == ngonNgu.id &&
                Objects.equals(tenNgonNgu, ngonNgu.tenNgonNgu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenNgonNgu);
    }
}
