package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_tac_gia", schema = "quanlythuvien", catalog = "")
@IdClass(SachTacGiaPK.class)
public class SachTacGia {
    private int idSach;
    private int idTacGia;

    @Id
    @Column(name = "id_sach")
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Id
    @Column(name = "id_tac_gia")
    public int getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(int idTacGia) {
        this.idTacGia = idTacGia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachTacGia that = (SachTacGia) o;
        return idSach == that.idSach &&
                idTacGia == that.idTacGia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idTacGia);
    }
}
