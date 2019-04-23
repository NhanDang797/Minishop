package com.entity;

import java.util.Set;

public class SanPhamJSON {

    int maSanPham;
    String tenSanPham;
    String giaTien;
    String moTa;
    String hinhSanPham;
    String gianhCho;
    DanhMucSanPham danhMucSanPham;
    Set<ChiTietSanPham> chiTietSanPhams;
    Set<KhuyenMai> dsKhuyenMai;

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String getGianhCho() {
        return gianhCho;
    }

    public void setGianhCho(String gianhCho) {
        this.gianhCho = gianhCho;
    }

    public DanhMucSanPham getDanhMucSanPham() {
        return danhMucSanPham;
    }

    public void setDanhMucSanPham(DanhMucSanPham danhMucSanPham) {
        this.danhMucSanPham = danhMucSanPham;
    }

    public Set<ChiTietSanPham> getChiTietSanPhams() {
        return chiTietSanPhams;
    }

    public void setChiTietSanPhams(Set<ChiTietSanPham> chiTietSanPhams) {
        this.chiTietSanPhams = chiTietSanPhams;
    }

    public Set<KhuyenMai> getDsKhuyenMai() {
        return dsKhuyenMai;
    }

    public void setDsKhuyenMai(Set<KhuyenMai> dsKhuyenMai) {
        this.dsKhuyenMai = dsKhuyenMai;
    }
}
