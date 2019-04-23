package com.dao;

import com.entity.DanhMucSanPham;
import com.impl.DanhMucImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DanhMucSanPhamDAO implements DanhMucImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<DanhMucSanPham> dsDanhMuc() {
        Session session = sessionFactory.getCurrentSession();
        List<DanhMucSanPham> danhMucSanPhams = session.createQuery("FROM DanhMucSanPham").getResultList();
        return danhMucSanPhams;

    }
}
