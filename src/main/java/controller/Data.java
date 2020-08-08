package controller;

import modules.dao.*;
import modules.entities.*;

import java.util.List;

public class Data {
    public static List<PhieuMuon> listPhieuMuon = PhieuMuonDAO.getInstance().getAll();

    public static List<PhieuTra> listPhieuTra = PhieuTraDAO.getInstance().getAll();

    public static List<Sach> listSach = SachDAO.getInstance().getAll();

    public static List<BanDoc> listBanDoc = BanDocDAO.getInstance().getAll();

    public static List<NhanVien> listNhanVien = NhanVienDAO.getInstance().getAll();
}
