package model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sach extends RecursiveTreeObject<Sach> {
    private int id;
    private String tenSach;
    private int namXuatBan;
    private Set<NhaXuatBan> nhaXuatBan = new HashSet<>(0);
    private Set<NgonNgu> ngonNgu = new HashSet<>(0);
    private Set<TacGia> tacGia = new HashSet<>(0);
    private Set<TheLoai> theLoai = new HashSet<>(0);
    private ViTri viTri;
    private Integer giaBia;
    private Integer soLuong;
    private int soTrang;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ten_sach")
    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    @Basic
    @Column(name = "nam_xuat_ban")
    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    @Basic
    @Column(name = "gia_bia")
    public Integer getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(Integer giaBia) {
        this.giaBia = giaBia;
    }

    @Basic
    @Column(name = "so_luong")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Basic
    @Column(name = "so_trang")
    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sach sach = (Sach) o;
        return id == sach.id &&
                namXuatBan == sach.namXuatBan &&
                soTrang == sach.soTrang &&
                Objects.equals(tenSach, sach.tenSach) &&
                Objects.equals(viTri, sach.viTri) &&
                Objects.equals(giaBia, sach.giaBia) &&
                Objects.equals(soLuong, sach.soLuong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenSach, namXuatBan, viTri, giaBia, soLuong, soTrang);
    }

    @OneToOne(cascade = CascadeType.ALL)
    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sach_nha_xuat_ban", joinColumns = { @JoinColumn(name = "sach_id") }, inverseJoinColumns = { @JoinColumn(name = "nha_xuat_ban_id") })
    public Set<NhaXuatBan> getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(Set<NhaXuatBan> nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sach_ngon_ngu", joinColumns = { @JoinColumn(name = "sach_id") }, inverseJoinColumns = { @JoinColumn(name = "ngon_ngu_id") })
    public Set<NgonNgu> getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(Set<NgonNgu> ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sach_tac_gia", joinColumns = { @JoinColumn(name = "sach_id") }, inverseJoinColumns = { @JoinColumn(name = "tac_gia_id") })
    public Set<TacGia> getTacGia() {
        return tacGia;
    }

    public void setTacGia(Set<TacGia> tacGia) {
        this.tacGia = tacGia;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sach_the_loai", joinColumns = { @JoinColumn(name = "sach_id") }, inverseJoinColumns = { @JoinColumn(name = "the_loai_id") })
    public Set<TheLoai> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(Set<TheLoai> theLoai) {
        this.theLoai = theLoai;
    }

    @Override
    public String toString() {
        return id + " - " + tenSach;
    }
}
