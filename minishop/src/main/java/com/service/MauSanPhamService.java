package com.service;

import com.dao.MauSanPhamDAO;
import com.entity.MauSanPham;
import com.impl.MauSanPhamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSanPhamService implements MauSanPhamImpl {

    @Autowired
    MauSanPhamDAO mauSanPhamDAO;

    public List<MauSanPham> LayDanhSachMau() {
        return mauSanPhamDAO.LayDanhSachMau();
    }
}
