package model.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tham_so", schema = "quanlythuvien", catalog = "")
public class ThamSo {
    private int id;
    private Integer thoiHanMuonMacDinh;
    private Integer soLanGiaHanToiDa;
    private Integer thoiHanSuDungTheMacDinh;
    private Integer chucDanhMacDinh;
    private Integer soCuonSachDuocMuonToiDa;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "thoi_han_muon_mac_dinh")
    public Integer getThoiHanMuonMacDinh() {
        return thoiHanMuonMacDinh;
    }

    public void setThoiHanMuonMacDinh(Integer thoiHanMuonMacDinh) {
        this.thoiHanMuonMacDinh = thoiHanMuonMacDinh;
    }

    @Basic
    @Column(name = "so_lan_gia_han_toi_da")
    public Integer getSoLanGiaHanToiDa() {
        return soLanGiaHanToiDa;
    }

    public void setSoLanGiaHanToiDa(Integer soLanGiaHanToiDa) {
        this.soLanGiaHanToiDa = soLanGiaHanToiDa;
    }

    @Basic
    @Column(name = "thoi_han_su_dung_the_mac_dinh")
    public Integer getThoiHanSuDungTheMacDinh() {
        return thoiHanSuDungTheMacDinh;
    }

    public void setThoiHanSuDungTheMacDinh(Integer thoiHanSuDungTheMacDinh) {
        this.thoiHanSuDungTheMacDinh = thoiHanSuDungTheMacDinh;
    }

    @Basic
    @Column(name = "chuc_danh_mac_dinh")
    public Integer getChucDanhMacDinh() {
        return chucDanhMacDinh;
    }

    public void setChucDanhMacDinh(Integer chucDanhMacDinh) {
        this.chucDanhMacDinh = chucDanhMacDinh;
    }

    @Basic
    @Column(name = "so_cuon_sach_duoc_muon_toi_da")
    public Integer getSoCuonSachDuocMuonToiDa() {
        return soCuonSachDuocMuonToiDa;
    }

    public void setSoCuonSachDuocMuonToiDa(Integer soCuonSachDuocMuonToiDa) {
        this.soCuonSachDuocMuonToiDa = soCuonSachDuocMuonToiDa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThamSo thamSo = (ThamSo) o;
        return id == thamSo.id &&
                Objects.equals(thoiHanMuonMacDinh, thamSo.thoiHanMuonMacDinh) &&
                Objects.equals(soLanGiaHanToiDa, thamSo.soLanGiaHanToiDa) &&
                Objects.equals(thoiHanSuDungTheMacDinh, thamSo.thoiHanSuDungTheMacDinh) &&
                Objects.equals(chucDanhMacDinh, thamSo.chucDanhMacDinh) &&
                Objects.equals(soCuonSachDuocMuonToiDa, thamSo.soCuonSachDuocMuonToiDa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thoiHanMuonMacDinh, soLanGiaHanToiDa, thoiHanSuDungTheMacDinh, chucDanhMacDinh, soCuonSachDuocMuonToiDa);
    }
}
