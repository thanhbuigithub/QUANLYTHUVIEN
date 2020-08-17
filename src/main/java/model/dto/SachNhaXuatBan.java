package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sach_nha_xuat_ban", schema = "quanlythuvien", catalog = "")
@IdClass(SachNhaXuatBanPK.class)
public class SachNhaXuatBan {
    private int idSach;
    private int idNhaXuatBan;

    @Id
    @Column(name = "id_sach")
    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @Id
    @Column(name = "id_nha_xuat_ban")
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
        SachNhaXuatBan that = (SachNhaXuatBan) o;
        return idSach == that.idSach &&
                idNhaXuatBan == that.idNhaXuatBan;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSach, idNhaXuatBan);
    }
}
