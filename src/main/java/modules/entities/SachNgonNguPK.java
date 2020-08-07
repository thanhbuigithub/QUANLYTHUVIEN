package modules.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachNgonNguPK implements Serializable {
    private int idSach;
    private int idNgonNgu;

    @Column(name = "id_sach")
    @Id
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Column(name = "id_ngon_ngu")
    @Id
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
        SachNgonNguPK that = (SachNgonNguPK) o;
        return idSach == that.idSach &&
                idNgonNgu == that.idNgonNgu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idNgonNgu);
    }
}
