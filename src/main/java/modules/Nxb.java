package modules;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Nxb {
    private int id;
    private String tenNxb;
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
    @Column(name = "ten_nxb")
    public String getTenNxb() {
        return tenNxb;
    }

    public void setTenNxb(String tenNxb) {
        this.tenNxb = tenNxb;
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
        Nxb nxb = (Nxb) o;
        return id == nxb.id &&
                Objects.equals(tenNxb, nxb.tenNxb) &&
                Objects.equals(moTa, nxb.moTa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenNxb, moTa);
    }
}
