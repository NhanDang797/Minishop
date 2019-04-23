package com.impl;

import com.entity.DanhMucSanPham;
import com.entity.SanPham;

import java.util.List;

public interface SanPhamImpl {
    List<SanPham> dsSamPhamLimit(int spBatDau);/*Hàm lấy ds sản phẩm theo giới hạn */
    SanPham sanPhamTheoMa(int maSanPham);
    List<SanPham> sanPhamTheoDanhMuc(int maDanhMuc);
    void XoaSanPhamTheoMa(int maSanPham);
    void ThemSanPham (SanPham sanPham);
    void CapNhatSanPham(SanPham sanPham);
}
