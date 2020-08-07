package modules.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vi_tri", schema = "quanlythuvien", catalog = "")
public class ViTri {
    private int id;
    private String khu;
    private String ke;
    private Integer ngan;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "khu")
    public String getKhu() {
        return khu;
    }

    public void setKhu(String khu) {
        this.khu = khu;
    }

    @Basic
    @Column(name = "ke")
    public String getKe() {
        return ke;
    }

    public void setKe(String ke) {
        this.ke = ke;
    }

    @Basic
    @Column(name = "ngan")
    public Integer getNgan() {
        return ngan;
    }

    public void setNgan(Integer ngan) {
        this.ngan = ngan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViTri viTri = (ViTri) o;
        return id == viTri.id &&
                Objects.equals(khu, viTri.khu) &&
                Objects.equals(ke, viTri.ke) &&
                Objects.equals(ngan, viTri.ngan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, khu, ke, ngan);
    }
}
