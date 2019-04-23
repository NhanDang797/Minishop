package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIZESANPHAM")
public class SizeSanPham {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masize")
	int maSize;
	
	@Column(name = "size")
	String tenSize;

	public int getMaSize() {
		return maSize;
	}

	public void setMaSize(int maSize) {
		this.maSize = maSize;
	}

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }
}
