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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("sanpham/")
public class SanPhamController {

    @Autowired
    DanhMucSanPhamService danhMucSanPhamService;

    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("{id}/{name}")
    public String Default(HttpSession httpSession, ModelMap modelMap , @PathVariable int id , @PathVariable String name){

        List<DanhMucSanPham> danhMucSanPhams  = danhMucSanPhamService.dsDanhMuc();
        modelMap.addAttribute("danhMucSanPham" , danhMucSanPhams);
        modelMap.addAttribute("name" , name);

        List<SanPham> listSanPham= sanPhamService.sanPhamTheoDanhMuc(id);
        modelMap.addAttribute("listSanPham" , listSanPham);

        if (httpSession.getAttribute("giohang")!= null ){
            List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");

            modelMap.addAttribute("soluongSP", gioHangs.size());
            modelMap.addAttribute("listGioHang",gioHangs);

        }


        return "sanpham";
    }

}
