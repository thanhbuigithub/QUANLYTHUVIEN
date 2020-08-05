package modules;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachTacGiaPK implements Serializable {
    private int idSach;
    private int idTacGia;

    @Column(name = "id_sach")
    @Id
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Column(name = "id_tac_gia")
    @Id
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
        SachTacGiaPK that = (SachTacGiaPK) o;
        return idSach == that.idSach &&
                idTacGia == that.idTacGia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idTacGia);
    }
}
