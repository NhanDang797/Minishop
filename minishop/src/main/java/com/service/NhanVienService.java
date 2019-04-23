package com.service;

import com.entity.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.NhanVienDAO;
import com.impl.NhanVienImpl;

@Service 
public class NhanVienService implements NhanVienImpl{
	@Autowired
	private NhanVienDAO nhanVienDAO;

	public boolean KiemTraDangNhap(String email, String matkhau) {
		boolean kiemtra = nhanVienDAO.KiemTraDangNhap(email, matkhau);
		return kiemtra;
	}

    public boolean ThemNhanVien(NhanVien nhanVien) {
        boolean ktThem = nhanVienDAO.ThemNhanVien(nhanVien);
		return ktThem;
    }
}
