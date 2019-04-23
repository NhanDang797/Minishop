package com.controller;

import com.entity.*;
import com.service.ChiTietHoaDonService;
import com.service.DanhMucSanPhamService;
import com.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("giohang/")
@SessionAttributes("giohang")
public class GioHangController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    ChiTietHoaDonService chiTietHoaDonService;

    @Autowired
    DanhMucSanPhamService danhMucSanPhamService;

    @GetMapping
    public String Default(HttpSession httpSession, ModelMap modelMap){


        /*dropdown menu*/
        List<DanhMucSanPham> danhMucSanPhams  = danhMucSanPhamService.dsDanhMuc();
        modelMap.addAttribute("danhMucSanPham" , danhMucSanPhams);

        if (httpSession.getAttribute("giohang")!= null ){
            List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");

            modelMap.addAttribute("soluongSP", gioHangs.size());
            modelMap.addAttribute("listGioHang",gioHangs);

        }
        return "giohang";
    }

    @PostMapping
    public String ThemHoaDon(HttpSession httpSession,@RequestParam  String tenKhachHang, @RequestParam  String soDienThoai, @RequestParam  String diaChiGiaoHang, @RequestParam  String hinhThucGiaoHang , @RequestParam  String ghiChu){

        if (httpSession.getAttribute("giohang")!= null ){
            List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
            /*1 : set giá trị cho bảng Hóa đơn*/
            HoaDon hoaDon = new HoaDon();
            hoaDon.setTenKhachHang(tenKhachHang);
            hoaDon.setSoDienThoai(soDienThoai);
            hoaDon.setDiaChiGiaoHang(diaChiGiaoHang);
            hoaDon.setHinhThucGiaoHang(hinhThucGiaoHang);
            hoaDon.setGhiChu(ghiChu);

            int idHoaDon = hoaDonService.ThemHoaDon(hoaDon);

            if (idHoaDon >0){

                /*2 : set giá trị cho bảng ChitiethoadonID và Chitiethoadon   >> duyệt trong giỏ hàng */
                for (GioHang gioHang: gioHangs) {
                    ChiTietHoaDonID chiTietHoaDonID = new ChiTietHoaDonID();

                    chiTietHoaDonID.setMachitietsanpham(gioHang.getMaChiTiet());
                    chiTietHoaDonID.setMahoadon(hoaDon.getMaHoaDon());

                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setChiTietHoaDonID(chiTietHoaDonID);
                    chiTietHoaDon.setGiaTien(gioHang.getGiaTien());
                    chiTietHoaDon.setSoLuong(gioHang.getSoLuong());

                    chiTietHoaDonService.ThemChiTietHoaDon(chiTietHoaDon); // thêm vào bảng chi tiết

                }

            }
        }

        return "giohang";
    }
}
