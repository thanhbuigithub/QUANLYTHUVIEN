package modules;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "the_loai", schema = "quanlythuvien", catalog = "")
public class TheLoai {
    private int id;
    private String tenTheLoai;
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
    @Column(name = "ten_the_loai")
    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
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
        TheLoai theLoai = (TheLoai) o;
        return id == theLoai.id &&
                Objects.equals(tenTheLoai, theLoai.tenTheLoai) &&
                Objects.equals(moTa, theLoai.moTa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenTheLoai, moTa);
    }
}
