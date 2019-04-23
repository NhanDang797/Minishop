package com.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "makhuyenmai")
	int maKhuyenMai;
	
	@Column(name = "tenkhuyenmai")
	String tenKhuyenMai;
	
	@Column(name = "ngaybatdau")
	String ngayBatDau;
	
	@Column(name = "thoigianketthuc")
	String ngayKetThuc;
	
	@Column(name = "mota")
	String moTa;
	
	@Column(name = "hinhkhuyenmai")
	String hinhKhuyenMai;

	@Column(name = "giagiam")
	int giaGiam;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="chitietkhuyenmai",
				/*name : bang chitietkhuyenmai ,referencedColumnName : bang khuyenmai	 */
				joinColumns= {@JoinColumn(name="makhuyenmai",referencedColumnName="makhuyenmai")},
				inverseJoinColumns= {@JoinColumn(name="masanpham",referencedColumnName="masanpham")}
	)
	Set<SanPham> dsSanPham;
	
	public int getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(int maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(String ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getHinhKhuyenMai() {
		return hinhKhuyenMai;
	}

	public void setHinhKhuyenMai(String hinhKhuyenMai) {
		this.hinhKhuyenMai = hinhKhuyenMai;
	}

	public int getGiaGiam() {
		return giaGiam;
	}

	public void setGiaGiam(int giaGiam) {
		this.giaGiam = giaGiam;
	}

	public Set<SanPham> getDsSanPham() {
		return dsSanPham;
	}

	public void setDsSanPham(Set<SanPham> dsSanPham) {
		this.dsSanPham = dsSanPham;
	}
	
	
}
