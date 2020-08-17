package model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachNgonNguPK implements Serializable {
    private int sachId;
    private int ngonNguId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachNgonNguPK that = (SachNgonNguPK) o;
        return sachId == that.sachId &&
                ngonNguId == that.ngonNguId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, ngonNguId);
    }

    @Column(name = "sach_id")
    @Id
    public int getSachId() {
        return sachId;
    }

    public void setSachId(int sachId) {
        this.sachId = sachId;
    }

    @Column(name = "ngon_ngu_id")
    @Id
    public int getNgonNguId() {
        return ngonNguId;
    }

    public void setNgonNguId(int ngonNguId) {
        this.ngonNguId = ngonNguId;
    }
}
