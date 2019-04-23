package com.impl;

import com.entity.NhanVien;

public interface NhanVienImpl {
	boolean KiemTraDangNhap(String email, String matkhau);
	boolean ThemNhanVien(NhanVien nhanVien);
}
