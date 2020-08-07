package modules.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ngon_ngu", schema = "quanlythuvien", catalog = "")
public class NgonNgu {
    private int id;
    private String tenNgonNgu;
    private String moTa;

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

    @Basic
    @Column(name = "mo_ta")
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NgonNgu ngonNgu = (NgonNgu) o;
        return id == ngonNgu.id &&
                Objects.equals(tenNgonNgu, ngonNgu.tenNgonNgu) &&
                Objects.equals(moTa, ngonNgu.moTa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenNgonNgu, moTa);
    }
}
