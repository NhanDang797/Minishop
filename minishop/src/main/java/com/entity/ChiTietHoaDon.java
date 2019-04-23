package com.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CHITIETHOADON")  
public class ChiTietHoaDon {
	@EmbeddedId
	ChiTietHoaDonID chiTietHoaDonID;
	
	@Column(name = "soluong")
	int soLuong;
	
	@Column(name = "giatien")
	String giaTien;

	public ChiTietHoaDonID getChiTietHoaDonID() {
		return chiTietHoaDonID;
	}

	public void setChiTietHoaDonID(ChiTietHoaDonID chiTietHoaDonID) {
		this.chiTietHoaDonID = chiTietHoaDonID;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(String giaTien) {
		this.giaTien = giaTien;
	}
}
