package com.service;

import com.dao.HoaDonDAO;
import com.entity.HoaDon;
import com.impl.HoaDonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService implements HoaDonImpl {
    @Autowired
    HoaDonDAO hoaDonDAO;

    public int ThemHoaDon(HoaDon hoaDon) {
        return hoaDonDAO.ThemHoaDon(hoaDon);
    }
}
