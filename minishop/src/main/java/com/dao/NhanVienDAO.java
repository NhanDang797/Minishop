package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.NhanVien;
import com.impl.NhanVienImpl;
/*	Lỗi Proxy phát sinh ra khi sử dụng @Transactional và implements 
 * 	>>>> chỉ định class này sẽ được phụ thuộc vào proxy , giới hạn trong class này
 */
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NhanVienDAO implements NhanVienImpl{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public boolean KiemTraDangNhap(String email, String matkhau) {
		try {
			Session session = sessionFactory.getCurrentSession();
			NhanVien nv = (NhanVien) session.createQuery("from NhanVien  where email = '"+email+"' and matkhau = '"+matkhau+"'").getSingleResult();
			
			if (nv != null) {
				return true;
			}
			else {
				return false;
			}
		}	
		catch (Exception e) {
			return false;
		}
	}

    @Transactional
    public boolean ThemNhanVien(NhanVien nhanVien) {
        Session session = sessionFactory.getCurrentSession();
        int id = (Integer) session.save(nhanVien);
        if (id > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
