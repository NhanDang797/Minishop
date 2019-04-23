package com.dao;

import com.entity.ChiTietHoaDon;
import com.entity.ChiTietHoaDonID;
import com.impl.ChiTietHoaDonImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ChiTietHoaDonDAO implements ChiTietHoaDonImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        ChiTietHoaDonID id = (ChiTietHoaDonID) session.save(chiTietHoaDon);
        if (id != null){
            return true;
        }
        else {
            return false;
        }
    }
}
