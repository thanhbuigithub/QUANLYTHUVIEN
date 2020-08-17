package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tac_gia", schema = "quanlythuvien", catalog = "")
public class TacGia {
    private int id;
    private String tenTacGia;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ten_tac_gia")
    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TacGia tacGia = (TacGia) o;
        return id == tacGia.id &&
                Objects.equals(tenTacGia, tacGia.tenTacGia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenTacGia);
    }
}
