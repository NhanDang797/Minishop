package com.controller;

import com.entity.DanhMucSanPham;
import com.entity.GioHang;
import com.entity.SanPham;
import com.service.DanhMucSanPhamService;
import com.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/chitiet")
@SessionAttributes("giohang")
public class ChiTietController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucSanPhamService danhMucSanPhamService;

    @GetMapping("/{masanpham}") /*PathVariable : tham số truyền vào*/
    public String Default(@PathVariable int masanpham , ModelMap modelMap , HttpSession httpSession){
        /*danh sach san pham*/
        SanPham sanPham =  sanPhamService.sanPhamTheoMa(masanpham);
        List<DanhMucSanPham > danhMucSanPhams  = danhMucSanPhamService.dsDanhMuc();

        /*session >> so luong */
        if (httpSession.getAttribute("giohang") != null){
            List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
            modelMap.addAttribute("soluongSP", gioHangs.size());
        }
        modelMap.addAttribute("sanPham", sanPham);
        modelMap.addAttribute("danhMucSanPham" , danhMucSanPhams);

        return "chitiet";
    }
}
