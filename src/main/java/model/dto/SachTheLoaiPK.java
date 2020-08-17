package model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachTheLoaiPK implements Serializable {
    private int sachId;
    private int theLoaiId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachTheLoaiPK that = (SachTheLoaiPK) o;
        return sachId == that.sachId &&
                theLoaiId == that.theLoaiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, theLoaiId);
    }

    @Column(name = "sach_id")
    @Id
    public int getSachId() {
        return sachId;
    }

    public void setSachId(int sachId) {
        this.sachId = sachId;
    }

    @Column(name = "the_loai_id")
    @Id
    public int getTheLoaiId() {
        return theLoaiId;
    }

    public void setTheLoaiId(int theLoaiId) {
        this.theLoaiId = theLoaiId;
    }
}
