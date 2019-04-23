package com.controller;

import com.entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.NhanVienService;
import com.service.SanPhamService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*Là nơi chứa các đường dẫn đưuọc gọi bởi Ajax*/
@Controller
@RequestMapping("api/")
@SessionAttributes({"user","giohang"})
public class ApiController {

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("KiemTraDangNhap")
    @ResponseBody
    public String KiemTraDangNhap(@RequestParam String email, @RequestParam String matkhau, ModelMap map) {
        boolean kiemtra = nhanVienService.KiemTraDangNhap(email, matkhau);
        if (kiemtra){
            map.addAttribute("user",email); /*Lưu session*/
        }
        return (kiemtra+"").trim();

    }

    /*tạo session Giohang : thêm sản phẩm vào giỏ hàng*/
    @GetMapping("ThemGioHang")
    @ResponseBody
    public void ThemGioHang(HttpSession httpSession, @RequestParam int maChiTiet, @RequestParam int maSanPham,@RequestParam int maMau,@RequestParam int maSize,@RequestParam String tenSanPham,@RequestParam String giaTien,@RequestParam String tenMau,@RequestParam String tenSize ,@RequestParam int soLuong ){ //*So luong nay de kiem tra trong kho*//*

        if (httpSession.getAttribute("giohang") == null){
            List<GioHang> gioHangs = new ArrayList<GioHang>();
            GioHang gioHang = new GioHang();

            gioHang.setMaChiTiet(maChiTiet);
            gioHang.setMaSanPham(maSanPham);
            gioHang.setMaMau(maMau);
            gioHang.setMaSize(maSize);
            gioHang.setTenSanPham(tenSanPham);
            gioHang.setTenMau(tenMau);
            gioHang.setTenSize(tenSize);
            gioHang.setGiaTien(giaTien);
            gioHang.setSoLuong(1);

            gioHangs.add(gioHang);

            httpSession.setAttribute("giohang", gioHangs); //*thuc hien luu session */

        }
        else {
            List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");

            int vitri = KiemTraTonTaiGioHang(listGioHang,httpSession, maSanPham,maSize, maMau);
            if (vitri ==  -1){

                List<GioHang> gioHangs = new ArrayList<GioHang>();
                GioHang gioHang = new GioHang();

                gioHang.setMaChiTiet(maChiTiet);
                gioHang.setMaSanPham(maSanPham);
                gioHang.setMaMau(maMau);
                gioHang.setMaSize(maSize);
                gioHang.setTenSanPham(tenSanPham);
                gioHang.setTenMau(tenMau);
                gioHang.setTenSize(tenSize);
                gioHang.setGiaTien(giaTien);
                gioHang.setSoLuong(1);

                listGioHang.add(gioHang);
            }
            else {
                int soLuongMoi = listGioHang.get(vitri).getSoLuong()+ 1 ;
                listGioHang.get(vitri).setSoLuong(soLuongMoi);
            }

        }

    }

    private int KiemTraTonTaiGioHang(List<GioHang> listGioHang,HttpSession httpSession,int masp , int masize , int mamau ){

        for (int i = 0 ; i< listGioHang.size() ;i++){
            if (listGioHang.get(i).getMaSanPham() == masp && listGioHang.get(i).getMaSize() == masize && listGioHang.get(i).getMaMau() == mamau){
                return i;
            }
        }
        return -1;
    }

    /*lấy số lượng trong giỏ hàng*/
    @GetMapping("LaySoLuongGioHang")
    @ResponseBody
    public String LaySoLuongGioHang( HttpSession httpSession){
        if (httpSession.getAttribute("giohang") !=null){
            List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
            return listGioHang.size()+"";
        }
        return "";
    }

    /*Cập nhật số lượng giỏ hàng*/
    @GetMapping("CapNhatSoLuongGioHang")
    @ResponseBody
    public void CapNhatSoLuongGioHang(HttpSession httpSession ,@RequestParam int soLuong, @RequestParam int maSanPham,@RequestParam int maMau,@RequestParam int maSize){
        if (httpSession.getAttribute("giohang") != null ){
            List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
            int vitri = KiemTraTonTaiGioHang(listGioHang,httpSession, maSanPham,maSize, maMau);

            listGioHang.get(vitri).setSoLuong(soLuong);

        }
    }

    /*xóa giỏ hàng*/
    @GetMapping("XoaGioHang")
    @ResponseBody
    public void XoaGioHang(HttpSession httpSession , @RequestParam int maSanPham,@RequestParam int maMau,@RequestParam int maSize){
        if (httpSession.getAttribute("giohang") != null ){
            List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
            int vitri = KiemTraTonTaiGioHang(listGioHang,httpSession, maSanPham,maSize, maMau);
            listGioHang.remove(vitri);

        }
    }

    /*phan trang san pham*/
    @GetMapping(value = "LaySanPhamLimit",produces = "text/plain ;charset=utf-8")
    @ResponseBody
    public String LaySanPhamLimit(@RequestParam int spBatDau){
        List<SanPham> listSanPham =  sanPhamService.dsSamPhamLimit(spBatDau);
        String html = "";
        for (SanPham sp : listSanPham ) {
            html += "<tr>";
            html += "<td> <div class='checkbox'> <label><input class='selectSanPham' type='checkbox' value='"+sp.getMaSanPham()+"'></label> </div> </td>";
            html += "<td class='tensp' data-masp='"+sp.getMaSanPham()+"'>"+sp.getTenSanPham()+"</td>";
            html += "<td class='giatiensp'>"+sp.getGiaTien()+"</td>";
            html += "<td class='gianhchosp'>"+sp.getGianhCho()+"</td>";
            html += "<td class='capnhat-sanpham' data-id='"+sp.getMaSanPham()+"'><button class='btn btn-warning btn-sm'>Cập Nhật</button></td>";
            html += "</tr>";
        }
        return html;

    }

    /*Xoa san pham */
    @GetMapping("XoaSanPham")
    @ResponseBody
    public String XoaSanPhamTheoMa(@RequestParam int maSanPham){
        sanPhamService.XoaSanPhamTheoMa(maSanPham);
        return "true";
    }

    /*UploadFile*/
    @Autowired
    ServletContext servletContext; // dung de lay duong dan trong thu muc.

    @PostMapping("UploadFile")
    @ResponseBody
    public String UploadFile(MultipartHttpServletRequest request){

        String path = servletContext.getRealPath("/resources/images/sanpham/");

        Iterator<String> listNames = request.getFileNames(); // tra ra 1 list file name
        MultipartFile multipartFile = request.getFile(listNames.next()); // lay ra 1 file

        File fileSave = new File(path + multipartFile.getOriginalFilename()); // multipartFile.getOriginalFilename() lay ra tern file va phan mo rong cua file
        try {
            multipartFile.transferTo(fileSave);// chep file vao noi luu tru
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }

    @PostMapping("ThemSanPham")
    @ResponseBody
    public void ThemSanPham(@RequestParam String dataJson) {
        ObjectMapper objectMapper = new ObjectMapper(); // 1 doi tuong cho phep mapped cac node trong chuoi JSON

        JsonNode jsonObject; // đại diện cho các node trong json

        try {
            jsonObject = objectMapper.readTree(dataJson); // doi tuong json cha

            SanPham sanPham = new SanPham();

            String tenSanPham = jsonObject.get("tenSanPham").asText();
            String giaTien = jsonObject.get("giaTien").asText();
            String moTa = jsonObject.get("moTa").asText();
            String gianhCho = jsonObject.get("gianhCho").asText();
            String hinhSanPham = jsonObject.get("hinhSanPham").asText();

            DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
            danhMucSanPham.setMaDanhMuc(jsonObject.get("danhMucSanPham").asInt());

            JsonNode jsonChiTiet = jsonObject.get("chiTietSanPhams"); // lay node cua json cha

            Set<ChiTietSanPham> listChiTiet = new HashSet<ChiTietSanPham>();

            for (JsonNode objectChiTiet : jsonChiTiet) {

                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

                MauSanPham mauSanPham = new MauSanPham();
                mauSanPham.setMaMau(objectChiTiet.get("mauSanPham").asInt());

                SizeSanPham sizeSanPham= new SizeSanPham();
                sizeSanPham.setMaSize(objectChiTiet.get("sizeSanPham").asInt());

                chiTietSanPham.setMauSanPham(mauSanPham);
                chiTietSanPham.setSizeSanPham(sizeSanPham);
                chiTietSanPham.setSoLuong(objectChiTiet.get("soLuong").asInt());

                listChiTiet.add(chiTietSanPham);

            }
            /*set value for SanPham*/
            sanPham.setTenSanPham(tenSanPham);
            sanPham.setGiaTien(giaTien);
            sanPham.setMoTa(moTa);
            sanPham.setGianhCho(gianhCho);
            sanPham.setHinhSanPham(hinhSanPham);
            sanPham.setChiTietSanPhams(listChiTiet);
            sanPham.setDanhMucSanPham(danhMucSanPham);

            sanPhamService.ThemSanPham(sanPham);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping(value = "LaySanPhamTheoMa",produces = "application/json;charset=utf-8")
    @ResponseBody
    public SanPhamJSON LaySanPhamTheoMa(@RequestParam int maSanPham) {
          /*
        * Luu ý khi làm việc với JSON : không sử dụng với các class có mối quan hệ nhiều
        * >>> phai tạo ra class có các thuộc tính giống với class entity
        * */

        SanPhamJSON sanPhamJSON = new SanPhamJSON();

        SanPham sanPham = sanPhamService.sanPhamTheoMa(maSanPham);

        sanPhamJSON.setMaSanPham(sanPham.getMaSanPham());
        sanPhamJSON.setTenSanPham(sanPham.getTenSanPham());
        sanPhamJSON.setGiaTien(sanPham.getGiaTien());
        sanPhamJSON.setGianhCho(sanPham.getGianhCho());
        sanPhamJSON.setHinhSanPham(sanPham.getHinhSanPham());
        sanPhamJSON.setMoTa(sanPham.getMoTa());

        DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
        danhMucSanPham.setMaDanhMuc(sanPham.getDanhMucSanPham().getMaDanhMuc());
        danhMucSanPham.setTenDanhMuc(sanPham.getDanhMucSanPham().getTenDanhMuc());

        Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
        for (ChiTietSanPham value : sanPham.getChiTietSanPhams()) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

            MauSanPham mauSanPham = new MauSanPham();
            mauSanPham.setMaMau(value.getMauSanPham().getMaMau());
            mauSanPham.setTenMau(value.getMauSanPham().getTenMau());

            SizeSanPham sizeSanPham = new SizeSanPham();
            sizeSanPham.setMaSize(value.getSizeSanPham().getMaSize());
            sizeSanPham.setTenSize(value.getSizeSanPham().getTenSize());

            chiTietSanPham.setMaChiTietSanPham(value.getMaChiTietSanPham());
            chiTietSanPham.setMauSanPham(mauSanPham);
            chiTietSanPham.setSizeSanPham(sizeSanPham);
            chiTietSanPham.setSoLuong(value.getSoLuong());

            chiTietSanPhams.add(chiTietSanPham);
        }

        sanPhamJSON.setDanhMucSanPham(danhMucSanPham);
        sanPhamJSON.setChiTietSanPhams(chiTietSanPhams);

        return sanPhamJSON;
    }

    /*Cập nhật sản phẩm*/
    @PostMapping("CapNhatSanPham")
    @ResponseBody
    public void CapNhatSanPham(@RequestParam String dataJson) {
        ObjectMapper objectMapper = new ObjectMapper(); // 1 doi tuong cho phep mapped cac node trong chuoi JSON

        JsonNode jsonObject; // đại diện cho các node trong json

        try {
            jsonObject = objectMapper.readTree(dataJson); // doi tuong json cha

            SanPham sanPham = new SanPham();

            int maSanPham = jsonObject.get("maSanPham").asInt();
            String tenSanPham = jsonObject.get("tenSanPham").asText();
            String giaTien = jsonObject.get("giaTien").asText();
            String moTa = jsonObject.get("moTa").asText();
            String gianhCho = jsonObject.get("gianhCho").asText();
            String hinhSanPham = jsonObject.get("hinhSanPham").asText();

            DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
            danhMucSanPham.setMaDanhMuc(jsonObject.get("danhMucSanPham").asInt());

            JsonNode jsonChiTiet = jsonObject.get("chiTietSanPhams"); // lay node cua json cha

            Set<ChiTietSanPham> listChiTiet = new HashSet<ChiTietSanPham>();

            for (JsonNode objectChiTiet : jsonChiTiet) {

                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

                MauSanPham mauSanPham = new MauSanPham();
                mauSanPham.setMaMau(objectChiTiet.get("mauSanPham").asInt());

                SizeSanPham sizeSanPham= new SizeSanPham();
                sizeSanPham.setMaSize(objectChiTiet.get("sizeSanPham").asInt());

                chiTietSanPham.setMauSanPham(mauSanPham);
                chiTietSanPham.setSizeSanPham(sizeSanPham);
                chiTietSanPham.setSoLuong(objectChiTiet.get("soLuong").asInt());

                listChiTiet.add(chiTietSanPham);

            }
            /*set value for SanPham*/
            sanPham.setMaSanPham(maSanPham);
            sanPham.setTenSanPham(tenSanPham);
            sanPham.setGiaTien(giaTien);
            sanPham.setMoTa(moTa);
            sanPham.setGianhCho(gianhCho);
            sanPham.setHinhSanPham(hinhSanPham);
            sanPham.setChiTietSanPhams(listChiTiet);
            sanPham.setDanhMucSanPham(danhMucSanPham);

            System.out.println(sanPham.getMaSanPham());

            sanPhamService.CapNhatSanPham(sanPham);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
