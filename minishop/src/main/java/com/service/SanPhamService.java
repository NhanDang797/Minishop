package com.service;

import com.dao.SanPhamDAO;
import com.entity.SanPham;
import com.impl.SanPhamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService implements SanPhamImpl {

    @Autowired
    SanPhamDAO sanPhamDAO;

    public List<SanPham> dsSamPhamLimit(int spBatDau) {
        return sanPhamDAO.dsSamPhamLimit(spBatDau);
    }

    public SanPham sanPhamTheoMa(int maSanPham) {
        return sanPhamDAO.sanPhamTheoMa(maSanPham);
    }

    public List<SanPham> sanPhamTheoDanhMuc(int maDanhMuc) {
        return sanPhamDAO.sanPhamTheoDanhMuc(maDanhMuc);
    }

    public void XoaSanPhamTheoMa(int maSanPham) {
         sanPhamDAO.XoaSanPhamTheoMa(maSanPham);
    }

    public void ThemSanPham(SanPham sanPham) {
         sanPhamDAO.ThemSanPham(sanPham);
    }

    public void CapNhatSanPham(SanPham sanPham) {
            sanPhamDAO.CapNhatSanPham(sanPham);
    }


}
