package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_nha_xuat_ban", schema = "quanlythuvien", catalog = "")
@IdClass(SachNhaXuatBanPK.class)
public class SachNhaXuatBan {
    private int sachId;
    private int nhaXuatBanId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachNhaXuatBan that = (SachNhaXuatBan) o;
        return sachId == that.sachId &&
                nhaXuatBanId == that.nhaXuatBanId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, nhaXuatBanId);
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
    @Column(name = "nha_xuat_ban_id")
    public int getNhaXuatBanId() {
        return nhaXuatBanId;
    }

    public void setNhaXuatBanId(int nhaXuatBanId) {
        this.nhaXuatBanId = nhaXuatBanId;
    }
}
