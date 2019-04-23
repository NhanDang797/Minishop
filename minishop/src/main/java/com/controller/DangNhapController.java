package com.controller;

import com.entity.NhanVien;
import com.service.NhanVienService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("dangnhap/")
public class DangNhapController {
    @Autowired
    NhanVienService nhanVienService;
	
	@GetMapping
	public String Default() {

		return "dangnhap";
	}

	@PostMapping
    public String DangKy(@RequestParam String email,@RequestParam String matkhau,@RequestParam String matkhauRe , ModelMap modelMap){
        boolean checkMail = validate(email);

        if (checkMail){
            if (matkhau.equals(matkhauRe)){
                NhanVien nhanVien = new NhanVien();
                nhanVien.setEmail(email);
                nhanVien.setTenDangNhap(email);
                nhanVien.setMatKhau(matkhau);

                boolean ktThem = nhanVienService.ThemNhanVien(nhanVien);
                if (ktThem){
                    modelMap.addAttribute("msg","Đăng Ký Tài Khoản Thành Công !");
                    modelMap.addAttribute("alert","info");
                }
                else{
                    modelMap.addAttribute("msg","Đăng Ký Tài Khoản Thất Bại !");
                    modelMap.addAttribute("alert","danger");
                }
            }
            else {
                modelMap.addAttribute("msg","Xác nhận mật khẩu không hợp lệ !");
                modelMap.addAttribute("alert","danger");
            }
        }
        else {
            modelMap.addAttribute("msg","Email Không hợp lệ !");
            modelMap.addAttribute("alert","danger");
        }
	    return "dangnhap";
    }
    /*bắt lỗi email*/
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
