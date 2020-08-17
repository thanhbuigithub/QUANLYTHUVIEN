package model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SachNhaXuatBanPK implements Serializable {
    private int idSach;
    private int idNhaXuatBan;

    @Column(name = "id_sach")
    @Id
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Column(name = "id_nha_xuat_ban")
    @Id
    public int getIdNhaXuatBan() {
        return idNhaXuatBan;
    }

    public void setIdNhaXuatBan(int idNhaXuatBan) {
        this.idNhaXuatBan = idNhaXuatBan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SachNhaXuatBanPK that = (SachNhaXuatBanPK) o;
        return idSach == that.idSach &&
                idNhaXuatBan == that.idNhaXuatBan;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idNhaXuatBan);
    }
}
