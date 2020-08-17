package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_ngon_ngu", schema = "quanlythuvien", catalog = "")
@IdClass(SachNgonNguPK.class)
public class SachNgonNgu {
    private int idSach;
    private int idNgonNgu;

    @Id
    @Column(name = "id_sach")
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Id
    @Column(name = "id_ngon_ngu")
    public int getIdNgonNgu() {
        return idNgonNgu;
    }

    public void setIdNgonNgu(int idNgonNgu) {
        this.idNgonNgu = idNgonNgu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachNgonNgu that = (SachNgonNgu) o;
        return idSach == that.idSach &&
                idNgonNgu == that.idNgonNgu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idNgonNgu);
    }
}
