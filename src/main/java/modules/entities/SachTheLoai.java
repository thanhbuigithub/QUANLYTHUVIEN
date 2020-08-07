package modules.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_the_loai", schema = "quanlythuvien", catalog = "")
@IdClass(SachTheLoaiPK.class)
public class SachTheLoai {
    private int idSach;
    private int idTheLoai;

    @Id
    @Column(name = "id_sach")
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Id
    @Column(name = "id_the_loai")
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
        SachTheLoai that = (SachTheLoai) o;
        return idSach == that.idSach &&
                idTheLoai == that.idTheLoai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idTheLoai);
    }
}
