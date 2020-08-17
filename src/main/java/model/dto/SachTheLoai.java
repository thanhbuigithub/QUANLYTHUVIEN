package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_the_loai", schema = "quanlythuvien", catalog = "")
@IdClass(SachTheLoaiPK.class)
public class SachTheLoai {
    private int sachId;
    private int theLoaiId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachTheLoai that = (SachTheLoai) o;
        return sachId == that.sachId &&
                theLoaiId == that.theLoaiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, theLoaiId);
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
    @Column(name = "the_loai_id")
    public int getTheLoaiId() {
        return theLoaiId;
    }

    public void setTheLoaiId(int theLoaiId) {
        this.theLoaiId = theLoaiId;
    }
}
