$(document).ready(function () {

    /* code đăng nhập sử dụng Ajax*/
    $("#submit").click(function () {
        var email_ = $(".icon-email").val();
        var matkhau_ = $(".icon-password").val();
        $.ajax({
            url:"/api/KiemTraDangNhap",
            type: 'GET',
            data:{
                email:email_,
                matkhau:matkhau_
                },
                success:function (value) {
                    if (value == "true"){
                        duongDanHienTai = window.location.href;
                        duongDan = duongDanHienTai.replace("dangnhap/","");
                        window.location = duongDan;
                    }
                    else {
                    $("#ketqua").text("Đăng Nhập Thất Bại !");
                }
            }
            
        })
    });

    /* ẩn hiện đăng nhập đăng ký*/

    $("#dang-nhap").click(function () {
        $(this).addClass("actived");
        $("#dang-ky").removeClass("actived");
        $("#signup-toggle").hide();
        $("#login-toggle").show();

    });

    $("#dang-ky").click(function () {
        $(this).addClass("actived");
        $("#dang-nhap").removeClass("actived");
        $("#login-toggle").hide();
        $("#signup-toggle").show();
    });

    /* xử lý thêm giỏ hàng*/
    $(".btn-giohang").click(function () {
        /*lấy ra thẻ cha là tr của button đưuọc nhấn >> tìm đến class mau >> lấy thuộc tính data-mamau*/
        var machitiet = $(this).attr("data-machitiet");
        var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
        var tenmau = $(this).closest("tr").find(".mau").text();
        var masize = $(this).closest("tr").find(".size").attr("data-masize");
        var tensize = $(this).closest("tr").find(".size").text();
        var soluong = $(this).closest("tr").find(".soluong").text();
        var masp = $(".tensp").attr("data-masp");
        var tensp = $(".tensp").text();
        var giatien = $(".giasp").attr("data-giasp");

        /*cú pháp ajax lồng
           > 1 : thêm thông tin vào giỏ hàng
           > 2 : lấy số lượng sản phẩm
        */
        $.ajax({
            url:"/api/ThemGioHang",
            type: 'GET',
            data:{
                maChiTiet : machitiet,
                maSanPham : masp,
                maMau : mamau,
                maSize : masize,
                soLuong : soluong,
                tenSanPham : tensp,
                giaTien : giatien,
                tenMau : tenmau,
                tenSize : tensize
            }
        }).done(function () {
            $.ajax({
                url:"/api/LaySoLuongGioHang",
                type: 'GET',
                success: function (value) {
                    $("#soluonggiohang").find("div").addClass("gio-hang");
                    $("#soluonggiohang").find("div").html("<span>"+value+"</span>");
                }
            });
        });
    });

    /*chuyển định dạng tiền tệ về .*/

    /* sửa lại phần tính tiền trong giỏ hàng*/
    TongTienGioHang();
    function TongTienGioHang(){
        var tongtiensp = 0;
        $(".giatien").each(function () {

            var a = ($(this).closest("tr").find(".giatien").attr("data-value")).replace(",", "");
            var b = a.replace(".","");
            var giatien = b.replace(".000","000");

            var soluong = $(this).closest("tr").find(".soluong-giohang").val();
            var tongtien = soluong * parseInt(giatien);

            var dinhdangtongtien = tongtien.toString().replace(tongtien.toString().substring((tongtien.toString().length -3),(tongtien.toString().length )),"");
            var format = parseInt(dinhdangtongtien).toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();

            $(this).text(format);

            tongtiensp += tongtien;
            var tongcong = tongtiensp.toString().replace(tongtiensp.toString().substring((tongtiensp.toString().length -3),(tongtiensp.toString().length )),"");
            var formattongcong = parseInt(tongcong).toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
            $("#tongtien").text(formattongcong);

        })
        var rows = $(".rowCount").length;
        if (rows == 0){
            $("#tongtien").text("0");
        }
    }

   /* xử lý giá tiền sản phẩm */
    $(".soluong-giohang").change(function () {

        /*Cập nhật số lượng giỏ hàng*/
        var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
        var masize = $(this).closest("tr").find(".size").attr("data-masize");
        var masp = $(".masp").attr("data-masp");
        var soluong = $(this).val();
        $.ajax({
            url:"/api/CapNhatSoLuongGioHang",
            type: 'GET',
            data:{
                maSanPham : masp,
                maMau : mamau,
                maSize : masize,
                soLuong : soluong,
            }
        })
        TongTienGioHang();
    });

    /*xóa giỏ hàng*/
    $(".xoa-giohang").click(function () {
        var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
        var masize = $(this).closest("tr").find(".size").attr("data-masize");
        var masp = $(".masp").attr("data-masp");
        var self = $(this);
        $.ajax({
            url:"/api/XoaGioHang",
            type: 'GET',
            data:{
                maSanPham : masp,
                maMau : mamau,
                maSize : masize,
            },
            success:function () {
                self.closest("tr").remove();
                TongTienGioHang();
            }
        });

    })

    /*phan trang >>> themsanpham.jsp*/

    $("body").on("click",".paging-item",function () {  /* đây là dạng viết theo kiểu đăng ký nguyên phần body phòng ngờ cho việc phát sinh động */
        $(".paging-item").removeClass("active");
        $(this).addClass("active");

        var sotrang = $(this).text();
        var spBatDau = (sotrang - 1) * 5;
        $.ajax({
            url:"/api/LaySanPhamLimit",
            type :"GET",
            data : {
                spBatDau : spBatDau
            },
            success:function (value) {
                var tbodySanPham = $("#table-sanpham").find("tbody");
                tbodySanPham.empty();
                tbodySanPham.append(value);
            }
        });
    })

    /*xoa san pham>>> themsanpham.jsp*/

    $('#check-all').click(function() {
        if ($(this).is(':checked')) {
            $("#table-sanpham input").each(function () {
                $(this).attr("checked",true);
            })
        }
        else {
            $("#table-sanpham input").each(function () {
                $(this).attr("checked",false);
            })
        }
    });

    $(".xoa-sanpham").click(function () {
        $("#table-sanpham > tbody input:checked").each(function () {
            var masp = $(this).val();
            var self = $(this);

            $.ajax({
                url:"/api/XoaSanPham",
                type :"GET",
                data : {
                    maSanPham :masp
                },
                success:function (value) {
                    self.closest("tr").remove();
                }
            })
        });

    });

    /*upload file */
    var files = [];
    var hinhSanPham = "";
    $("#hinhanh").change(function (event) {
        files = event.target.files; // lays ra tat cac file duoc sinh ra
        hinhSanPham = files[0].name;
        forms = new FormData();
        forms.append("filehinhanh",files[0]); // lay vi tri thu nhat

        $.ajax({
            url:"/api/UploadFile",
            type :"POST",
            data : forms,
            contentType :false, // khong theo dinh dang nao het
            processData : false, // dung xu ly the form
            enctype : "multipart/form-data", // do data la kieu byte nen can file ma hoa va can truyen theo dang post

        })
    })


    /*clone san pham */
    $("body").on("click",".btn-chitiet",function () {
        $(this).remove();
        var chitiet_clone = $("#chitietsanpham").clone().removeAttr("id"); // id dùng để hide >> remove this
        $("#containerchitiet").append(chitiet_clone);
    })

    /*Them san pham */

    $("#btnthemsanpham").click(function (event) {
        event.preventDefault(); // xoa reload lai trang trong the form
        var formdata = $("#formsanpham").serializeArray(); // ham se lay tat ca cac field trong form va chuyen thanh 1 mang
        json = {};
        arrayChiTiet = [];

        $.each(formdata, function(i, field){ // duyet  key-value trong mảng Json
            json[field.name] = field.value;

        });

        /*duyet cac phan tu lien quan den chitietsanpham de PUT vao mang json*/
        $("#containerchitiet > .chitietsanpham").each(function () {
            objectChiTiet= {};
            var mauSanPham = $(this).find("select[name='mauSanPham']").val();
            var sizeSanPham = $(this).find("select[name='sizeSanPham']").val();
            var soLuong = $(this).find("input[name='soLuong']").val();

            objectChiTiet["mauSanPham"] = mauSanPham;
            objectChiTiet["sizeSanPham"] = sizeSanPham;
            objectChiTiet["soLuong"] = soLuong;
            arrayChiTiet.push(objectChiTiet);
        });

        json["chiTietSanPhams"] = arrayChiTiet;
        json["hinhSanPham"] = hinhSanPham;
        //console.log(json)
        $.ajax({
            url:"/api/ThemSanPham",
            type :"POST",
            data: {
                dataJson :JSON.stringify(json)
            }
        })
    })

    /*update san pham : font-end*/
    var  masanpham;
    $("body").on("click",".capnhat-sanpham",function () {
        masanpham = $(this).attr("data-id");
        $("#btncapnhat").removeClass("hidden-item");
        $("#btnthoat").removeClass("hidden-item");
        $("#btnthemsanpham").addClass("hidden-item");
        $.ajax({
            url:"/api/LaySanPhamTheoMa",
            type :"POST",
            data: {
                maSanPham : masanpham
            },
            success:function (value) {
                //console.log(value);
                $("#tensp").val(value.tenSanPham);
                $("#giatien").val(value.giaTien);
                $("#moTa").val(value.moTa);
                if (value.gianhCho == "nam"){
                    $("#radNam").prop("checked",true);
                }
                if (value.gianhCho == "nu"){
                    $("#radNu").prop("checked",true);
                }
                $("#danhmuc").val(value.danhMucSanPham.maDanhMuc);
                //xu ly cho chi tiet san pham
                $("#containerchitiet").empty();
                var countchitiet = value.chiTietSanPhams.length;
                for (i = 0 ;i < countchitiet ;i++) {

                    var chitiet_clone = $("#chitietsanpham").clone().removeAttr("id");
                    if (i < countchitiet-1){  // remove các button themchitiet (ngoài dòng cuối cùng )
                        chitiet_clone.find(".btn-chitiet").remove();
                    }
                    chitiet_clone.find("#mausanpham").val(value.chiTietSanPhams[i].mauSanPham.maMau);
                    chitiet_clone.find("#sizesanpham").val(value.chiTietSanPhams[i].sizeSanPham.maSize);
                    chitiet_clone.find("#soluong").val(value.chiTietSanPhams[i].soLuong);
                    $("#containerchitiet").append(chitiet_clone);
                }
            }

        })
    })

    /*update san pham : back-end*/
    $("#btncapnhat").click(function () {
        event.preventDefault(); // xoa reload lai trang trong the form
        var formdata = $("#formsanpham").serializeArray(); // ham se lay tat ca cac field trong form va chuyen thanh 1 mang
        json = {};
        arrayChiTiet = [];

        $.each(formdata, function(i, field){ // duyet  key-value trong mảng Json
            json[field.name] = field.value;
        });

        /*duyet cac phan tu lien quan den chitietsanpham de PUT vao mang json*/
        $("#containerchitiet > .chitietsanpham").each(function () {
            objectChiTiet= {};
            var mauSanPham = $(this).find("select[name='mauSanPham']").val();
            var sizeSanPham = $(this).find("select[name='sizeSanPham']").val();
            var soLuong = $(this).find("input[name='soLuong']").val();

            objectChiTiet["mauSanPham"] = mauSanPham;
            objectChiTiet["sizeSanPham"] = sizeSanPham;
            objectChiTiet["soLuong"] = soLuong;
            arrayChiTiet.push(objectChiTiet);
        });

        json["chiTietSanPhams"] = arrayChiTiet;
        json["hinhSanPham"] = hinhSanPham;
        json["maSanPham"] = masanpham;

        console.log(json)
        $.ajax({
            url:"/api/CapNhatSanPham",
            type :"POST",
            data: {
                dataJson :JSON.stringify(json)
            }
        })
    })


    /*END READY*/
});









