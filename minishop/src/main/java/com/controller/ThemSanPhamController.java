package com.controller;

import com.entity.DanhMucSanPham;
import com.entity.MauSanPham;
import com.entity.SanPham;
import com.entity.SizeSanPham;
import com.service.DanhMucSanPhamService;
import com.service.MauSanPhamService;
import com.service.SanPhamService;
import com.service.SizeSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ThemSanPham")
public class ThemSanPhamController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucSanPhamService danhMucSanPhamService;

    @Autowired
    MauSanPhamService mauSanPhamService;

    @Autowired
    SizeSanPhamService sizeSanPhamService;



    @GetMapping
    public String Default(ModelMap modelMap){

        List<SanPham> listSanPhams = sanPhamService.dsSamPhamLimit(0);
        modelMap.addAttribute("listsp" ,listSanPhams);

        // phan trang san pham
        List<SanPham> allSanPham = sanPhamService.dsSamPhamLimit(-1);
        double tongsoPage =  Math.ceil((double) allSanPham.size() / 5); // lam tron len 1 so
        modelMap.addAttribute("page",tongsoPage);

        //load danhmuc san pham
        List<DanhMucSanPham> danhMucSanPhams  = danhMucSanPhamService.dsDanhMuc();
        modelMap.addAttribute("danhMucSanPham" , danhMucSanPhams);

        // load danh sach  mau và size san pham
        List<MauSanPham> listMauSanPham = mauSanPhamService.LayDanhSachMau();
        modelMap.addAttribute("listMauSanPham" , listMauSanPham);

        List<SizeSanPham> listSizeSanPham = sizeSanPhamService.LayDanhSachSize();
        modelMap.addAttribute("listSizeSanPham" , listSizeSanPham);

        return "themsanpham";
    }
}
