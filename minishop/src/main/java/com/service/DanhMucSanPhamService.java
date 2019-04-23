package com.service;

import com.dao.DanhMucSanPhamDAO;
import com.entity.DanhMucSanPham;
import com.impl.DanhMucImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucSanPhamService implements DanhMucImpl {

    @Autowired
    DanhMucSanPhamDAO danhMucSanPhamDAO;

    public List<DanhMucSanPham> dsDanhMuc() {
        return danhMucSanPhamDAO.dsDanhMuc();
    }
}
