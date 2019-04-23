package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int maNhanVien;
	
	@Column(name = "hoten")
	String hoTen;
	
	@Column(name = "diachi")
	String diaChi;
	
	@Column(name = "gioitinh")
	byte gioiTinh;
	
	@Column(name = "cmnd")
	String cmnd;
	
	@Column(name = "sodienthoai")
	String soDienThoai;
	
	@Column(name = "email")
	String email;
	 
	@Column(name = "tendangnhap")
	String tenDangNhap;
		
	@Column(name = "matkhau")
	String matKhau;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="machucvu")
	ChucVu chucVu; // thay thế cho khóa ngoại 

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public byte getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(byte gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	
	
	
}
