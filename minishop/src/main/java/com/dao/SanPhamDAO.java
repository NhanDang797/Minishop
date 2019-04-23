package com.dao;

import com.entity.*;
import com.impl.SanPhamImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)

public class SanPhamDAO implements SanPhamImpl {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<SanPham> dsSamPhamLimit(int spBatDau) {
        Session session = sessionFactory.getCurrentSession();
        List<SanPham> dsSanPham;
        if(spBatDau < 0){
            dsSanPham = (List<SanPham>) session.createQuery("FROM SanPham").getResultList();
        }
        else {
            dsSanPham = (List<SanPham>) session.createQuery( " FROM SanPham ").setFirstResult(spBatDau).setMaxResults(5).getResultList();
        }
        return dsSanPham;

    }


    /* Đây là hàm truy vấn 4 bảng : dựa vào các relationship mà nó có thể tự động lấy ra hết tất cả các trường liên quan
     *  >>> lấy ra bằng các Set đã đặt trong đối tượng
     * */
    @Transactional
    public SanPham sanPhamTheoMa(int maSanPham) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "FROM SanPham sp where sp.maSanPham = " + maSanPham;

        SanPham sanPham =(SanPham) session.createQuery( sql).getSingleResult();

        return sanPham;
    }

    @Transactional
    public List<SanPham> sanPhamTheoDanhMuc(int maDanhMuc) {
        Session session = sessionFactory.getCurrentSession();
        List<SanPham> listSanPham = session.createQuery("FROM SanPham sp where sp.danhMucSanPham.maDanhMuc = " + maDanhMuc).getResultList();
        return listSanPham;
    }

    @Transactional
    public void XoaSanPhamTheoMa(int maSanPham) {

        /* Quy tắc xóa : xóa khóa ngoại trước
        * 1 : xóa bảng chitietsanpham
        * 2 : xóa bảng chitiethoadon
        * 3 : xóa bảng chitietkhuyenmai
        * 4 : xóa khóa chính bang sanpham
        *
        *  ***** note : do dùng embedid nen khong dung dược các hàm xóa tự động của hibernate nen phai xóa bằng tay
        * */
        Session session = sessionFactory.getCurrentSession();
        SanPham sanPham = session.get(SanPham.class,maSanPham);// lay ra masp

        Set<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhams();
        for (ChiTietSanPham chiTietSanPham: chiTietSanPhams ) {
            session.createQuery("delete ChiTietHoaDon WHERE machitietsanpham =" +chiTietSanPham.getMaChiTietSanPham()).executeUpdate();
        }

       // session.createQuery("delete ChiTietKhuyenMai WHERE maSanPham ="+ maSanPham ).executeUpdate();
        session.createQuery("delete ChiTietSanPham WHERE maSanPham ="+ maSanPham ).executeUpdate();
        session.createQuery("delete SanPham WHERE maSanPham ="+ maSanPham ).executeUpdate();

    }

    @Transactional
    public void ThemSanPham(SanPham sanPham) {
        Session session = sessionFactory.getCurrentSession();
        session.save(sanPham);
    }

    @Transactional
    public void CapNhatSanPham(SanPham sanPham) {
        Session session = sessionFactory.getCurrentSession();
        session.update(sanPham);
    }


}
