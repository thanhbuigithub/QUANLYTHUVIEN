package model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachTacGiaPK implements Serializable {
    private int sachId;
    private int tacGiaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachTacGiaPK that = (SachTacGiaPK) o;
        return sachId == that.sachId &&
                tacGiaId == that.tacGiaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, tacGiaId);
    }

    @Column(name = "sach_id")
    @Id
    public int getSachId() {
        return sachId;
    }

    public void setSachId(int sachId) {
        this.sachId = sachId;
    }

    @Column(name = "tac_gia_id")
    @Id
    public int getTacGiaId() {
        return tacGiaId;
    }

    public void setTacGiaId(int tacGiaId) {
        this.tacGiaId = tacGiaId;
    }
}
