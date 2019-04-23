package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAUSANPHAM")
public class MauSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mamau")
	int maMau;
	
	@Column(name = "tenmau")
	String tenMau;

	public int getMaMau() {
		return maMau;
	}

	public void setMaMau(int maMau) {
		this.maMau = maMau;
	}

	public String getTenMau() {
		return tenMau;
	}

	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}
	
	
}
