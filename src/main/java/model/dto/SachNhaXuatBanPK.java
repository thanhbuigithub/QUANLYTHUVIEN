package model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachNhaXuatBanPK implements Serializable {
    private int sachId;
    private int nhaXuatBanId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachNhaXuatBanPK that = (SachNhaXuatBanPK) o;
        return sachId == that.sachId &&
                nhaXuatBanId == that.nhaXuatBanId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sachId, nhaXuatBanId);
    }

    @Column(name = "sach_id")
    @Id
    public int getSachId() {
        return sachId;
    }

    public void setSachId(int sachId) {
        this.sachId = sachId;
    }

    @Column(name = "nha_xuat_ban_id")
    @Id
    public int getNhaXuatBanId() {
        return nhaXuatBanId;
    }

    public void setNhaXuatBanId(int nhaXuatBanId) {
        this.nhaXuatBanId = nhaXuatBanId;
    }
}
