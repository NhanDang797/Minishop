package com.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="hoadon")
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahoadon")
	int maHoaDon;
	
	@Column(name = "tenkhachhang")
	String tenKhachHang;
	
	@Column(name = "sodt")
	String soDienThoai;
	
	@Column(name = "diachigiaohang")
	String diaChiGiaoHang;
	
	@Column(name = "tinhtrang")
	boolean tinhTrang;
	
	@Column(name = "ngaylap")
	String ngayLap;

	@Column(name = "hinhthucgiaohang")
	String hinhThucGiaoHang;

	@Column(name = "ghichu")
	String ghiChu;


	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name = "mahoadon")
	Set<ChiTietHoaDon> dsChiTietHoaDon;
	
	
	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChiGiaoHang() {
		return diaChiGiaoHang;
	}

	public void setDiaChiGiaoHang(String diaChiGiaoHang) {
		this.diaChiGiaoHang = diaChiGiaoHang;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Set<ChiTietHoaDon> getDsChiTietHoaDon() {
		return dsChiTietHoaDon;
	}

	public void setDsChiTietHoaDon(Set<ChiTietHoaDon> dsChiTietHoaDon) {
		this.dsChiTietHoaDon = dsChiTietHoaDon;
	}

    public String getHinhThucGiaoHang() {
        return hinhThucGiaoHang;
    }

    public void setHinhThucGiaoHang(String hinhThucGiaoHang) {
        this.hinhThucGiaoHang = hinhThucGiaoHang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
