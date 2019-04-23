package com.entity;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "SANPHAM")
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masanpham")
	int maSanPham;
	
	@Column(name = "tensanpham")
	String tenSanPham;
	
	@Column(name = "giatien")
	String giaTien;
	
	@Column(name = "mota")
	String moTa;
	
	@Column(name = "hinhsanpham")
    String hinhSanPham;

    @Column(name = "gianhcho")
    String gianhCho;

	@OneToOne() // bo cascade = CascadeType.ALL de khong them va bang DanhMucSanPham
	@JoinColumn(name="madanhmuc")
	DanhMucSanPham danhMucSanPham;

	@OneToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL) /*fetch = FetchType.EAGER : là tự tự động khi truy vấn bảng SanPham nó sẽ truy vấn luôn bảng chitiet ,FetchType.EAGER  : Khi nào cần gọi code truy vấn bên controller nó mới có , nếu không nó sẽ báo  lỗi "<failed to lazily initialize a collection of role>"  */
	@JoinColumn(name="masanpham")
	Set<ChiTietSanPham> chiTietSanPhams;
	
	@ManyToMany()
	/*@JoinTable : tự  động tạo ra 1 bảng trung gian mà không cần  phải tạo class khác . lưu ý : jointable được tạo ra khi bảng của nó không có các thuộc tính khác , chỉ có các thuộc tính của các bảng có khóa chính*/
	@JoinTable(name="chitietkhuyenmai",
				/*name : bang chitietkhuyenmai ,referencedColumnName : bang khuyenmai	 */
			joinColumns= {@JoinColumn(name="masanpham",referencedColumnName="masanpham")},
			inverseJoinColumns= {@JoinColumn(name="makhuyenmai",referencedColumnName="makhuyenmai")}
	)
	Set<KhuyenMai> dsKhuyenMai;
	
	
	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(String giaTien) {
		this.giaTien = giaTien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getHinhSanPham() {
		return hinhSanPham;
	}

	public void setHinhSanPham(String hinhSanPham) {
		this.hinhSanPham = hinhSanPham;
	}

	public DanhMucSanPham getDanhMucSanPham() {
		return danhMucSanPham;
	}

	public void setDanhMucSanPham(DanhMucSanPham danhMucSanPham) {
		this.danhMucSanPham = danhMucSanPham;
	}

	public Set<ChiTietSanPham> getChiTietSanPhams() {
		return chiTietSanPhams;
	}

	public void setChiTietSanPhams(Set<ChiTietSanPham> chiTietSanPhams) {
		this.chiTietSanPhams = chiTietSanPhams;
	}

	public Set<KhuyenMai> getDsKhuyenMai() {
		return dsKhuyenMai;
	}

	public void setDsKhuyenMai(Set<KhuyenMai> dsKhuyenMai) {
		this.dsKhuyenMai = dsKhuyenMai;
	}

    public String getGianhCho() {
        return gianhCho;
    }

    public void setGianhCho(String gianhCho) {
        this.gianhCho = gianhCho;
    }
}
