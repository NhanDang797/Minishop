package com.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable // su dung cho multiID
public class ChiTietHoaDonID implements Serializable{

	int mahoadon;
	int machitietsanpham;
	
	public int getMahoadon() {
		return mahoadon;
	}
	public void setMahoadon(int mahoadon) {
		this.mahoadon = mahoadon;
	}

    public int getMachitietsanpham() {
        return machitietsanpham;
    }

    public void setMachitietsanpham(int machitietsanpham) {
        this.machitietsanpham = machitietsanpham;
    }
}
