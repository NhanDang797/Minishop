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
@Table(name = "DANHMUCSANPHAM")
public class DanhMucSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madanhmuc")
	int maDanhMuc;
	
	@Column(name = "tendanhmuc")
	String tenDanhMuc;
	
	@Column(name = "hinhdanhmuc")
	String hinhDanhMuc;	

	@OneToMany
	@JoinColumn(name="madanhmuc")
	Set<SanPham> sanPhams;
	
	public int getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public String getHinhDanhMuc() {
		return hinhDanhMuc;
	}

	public void setHinhDanhMuc(String hinhDanhMuc) {
		this.hinhDanhMuc = hinhDanhMuc;
	}

	public Set<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(Set<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}
	
	
}
