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
@Table(name = "CHITIETSANPHAM")
public class ChiTietSanPham {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machitietsanpham")
	int maChiTietSanPham;
	
	@Column(name = "soluong")
	int soLuong;
	
	@Column(name = "ngaynhap")
	String ngayNhap;

    @OneToOne
    @JoinColumn(name = "masanpham")
    private SanPham sanpham;
	
	@OneToOne
	@JoinColumn(name="masize")
	SizeSanPham  sizeSanPham;
	
	@OneToOne
	@JoinColumn(name="mamau")
	MauSanPham mauSanPham;

	public int getMaChiTietSanPham() {
		return maChiTietSanPham;
	}

	public void setMaChiTietSanPham(int maChiTietSanPham) {
		this.maChiTietSanPham = maChiTietSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public SanPham getSanpham() {
		return sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	public SizeSanPham getSizeSanPham() {
		return sizeSanPham;
	}

	public void setSizeSanPham(SizeSanPham sizeSanPham) {
		this.sizeSanPham = sizeSanPham;
	}

	public MauSanPham getMauSanPham() {
		return mauSanPham;
	}

	public void setMauSanPham(MauSanPham mauSanPham) {
		this.mauSanPham = mauSanPham;
	}
	
	
}
