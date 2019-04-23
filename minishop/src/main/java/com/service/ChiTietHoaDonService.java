package com.service;

import com.dao.ChiTietHoaDonDAO;
import com.entity.ChiTietHoaDon;
import com.impl.ChiTietHoaDonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImpl {

    @Autowired
    ChiTietHoaDonDAO chiTietHoaDonDAO;
    public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        return chiTietHoaDonDAO.ThemChiTietHoaDon(chiTietHoaDon);
    }
}
