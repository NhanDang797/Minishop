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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")

public class TrangChuController{

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucSanPhamService danhMucSanPhamService;

	@GetMapping
	public String Default( HttpSession httpSession, ModelMap modelMap) {/*Quản  lý trạng thái Session thông qua HttpSession . Không nên dung @SessionAttribute  */

        if (httpSession.getAttribute("user") != null) {
            String email = (String) httpSession.getAttribute("user");
            String chuCaiDau = (email.substring(0, 1)).toUpperCase();
            modelMap.addAttribute("chuCaiDau", chuCaiDau);

        }

        List<SanPham> sanPhamList =  sanPhamService.dsSamPhamLimit(-1);
        modelMap.addAttribute("listSanPham",sanPhamList);

        /*dropdown menu*/
        List<DanhMucSanPham> danhMucSanPhams  = danhMucSanPhamService.dsDanhMuc();
        modelMap.addAttribute("danhMucSanPham" , danhMucSanPhams);

        if (httpSession.getAttribute("giohang")!= null ){
            List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");


            modelMap.addAttribute("soluongSP", gioHangs.size());
            modelMap.addAttribute("listGioHang",gioHangs);

        }

		return "trangchu";
	}

}
