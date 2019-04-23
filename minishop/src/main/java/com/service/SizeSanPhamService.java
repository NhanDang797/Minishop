package com.service;

import com.dao.SizeSanPhamDAO;
import com.entity.SizeSanPham;
import com.impl.SizeSanPhamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SizeSanPhamService implements SizeSanPhamImpl {

    @Autowired
    SizeSanPhamDAO sizeSanPhamDAO;

    public List<SizeSanPham> LayDanhSachSize() {
        return sizeSanPhamDAO.LayDanhSachSize();
    }
}
