package com.dao;


import com.entity.SizeSanPham;
import com.impl.SizeSanPhamImpl;
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
public class SizeSanPhamDAO implements SizeSanPhamImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<SizeSanPham> LayDanhSachSize() {
        Session session = sessionFactory.getCurrentSession();
        List<SizeSanPham> listSizeSanPham  = session.createQuery("FROM SizeSanPham").getResultList();

        return listSizeSanPham;
    }
}
