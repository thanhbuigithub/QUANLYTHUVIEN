package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_ngon_ngu", schema = "quanlythuvien", catalog = "")
@IdClass(SachNgonNguPK.class)
public class SachNgonNgu {
    private int sachId;
    private int ngonNguId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachNgonNgu that = (SachNgonNgu) o;
        return sachId == that.sachId &&
                ngonNguId == that.ngonNguId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, ngonNguId);
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
    @Column(name = "ngon_ngu_id")
    public int getNgonNguId() {
        return ngonNguId;
    }

    public void setNgonNguId(int ngonNguId) {
        this.ngonNguId = ngonNguId;
    }
}
