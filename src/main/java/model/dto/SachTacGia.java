package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_tac_gia", schema = "quanlythuvien", catalog = "")
@IdClass(SachTacGiaPK.class)
public class SachTacGia {
    private int sachId;
    private int tacGiaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachTacGia that = (SachTacGia) o;
        return sachId == that.sachId &&
                tacGiaId == that.tacGiaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, tacGiaId);
    }

    @Id
    @Column(name = "sach_id")
    public int getSachId() {
        return sachId;
    }

    public void setSachId(int sachId) {
        this.sachId = sachId;
    }

    @Id
    @Column(name = "tac_gia_id")
    public int getTacGiaId() {
        return tacGiaId;
    }

    public void setTacGiaId(int tacGiaId) {
        this.tacGiaId = tacGiaId;
    }
}
