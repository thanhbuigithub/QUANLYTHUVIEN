package modules;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachTheLoaiPK implements Serializable {
    private int idSach;
    private int idTheLoai;

    @Column(name = "id_sach")
    @Id
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Column(name = "id_the_loai")
    @Id
    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachTheLoaiPK that = (SachTheLoaiPK) o;
        return idSach == that.idSach &&
                idTheLoai == that.idTheLoai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idTheLoai);
    }
}
