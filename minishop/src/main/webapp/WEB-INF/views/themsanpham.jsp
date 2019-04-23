<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->

    <link href='<c:url value="/resources/template/css/bootstrap.min.css" /> ' rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href='<c:url value="/resources/template/css/style.css" /> ' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href='<c:url value="/resources/template/css/morris.css" /> ' type="text/css"/>
    <!-- Graph CSS -->
    <link href='<c:url value="/resources/template/css/font-awesome.css" /> ' rel="stylesheet">
    <!-- jQuery -->
    <script src='<c:url value="/resources/js/jquery-3.min.js"/>'></script>
    <!-- //jQuery -->
    <link rel="stylesheet" href='<c:url value="/resources/template/css/icon-font.min.css" /> ' type='text/css'/>
    <!-- //lined-icons -->
</head>

<body>
<div class="page-container">
    <!--/content-inner-->
    <div class="left-content " style="background: white">
        <div class="row">
            <div class="col-md-5 col-sm-12 " style="margin-top: 50px">
                <%--xu ly du lieu dong thong qua formdata--%>
                <form id="formsanpham">
                    <div class="form-group">
                        <label for="tensp">Tên Sản Phẩm:</label>
                        <input type="text" class="form-control" id="tensp" name="tenSanPham" placeholder="Tên Sản Phẩm">
                    </div>
                    <div class="form-group">
                        <label for="giatien">Giá Tiền:</label>
                        <input type="text" class="form-control" id="giatien" name="giaTien" placeholder="Giá Tiền">
                    </div>
                    <div class="form-group">
                        <label for="danhmuc">Danh Mục Sản Phẩm :</label>
                        <select class="form-control" id="danhmuc" name="danhMucSanPham">
                            <c:forEach var="l" items="${danhMucSanPham}">
                                <option value="${l.maDanhMuc}">${l.tenDanhMuc}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="moTa"> Mô Tả:</label>
                        <textarea class="form-control" rows="5" id="moTa" name="moTa" placeholder="Mô tả"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="hinhanh">Hình Ảnh:</label>
                        <input type="file" class="form-control" id="hinhanh" name="hinhSanPham">
                    </div>
                    <span>Dành Cho :</span>
                    <div class="radio-inline">
                        <label ><input type="radio" name="gianhCho" checked value="nam" id="radNam">Nam</label>
                    </div>
                    <div class="radio-inline">
                        <label ><input type="radio" name="gianhCho" value="nu" id="radNu">Nữ</label>
                    </div>
                </form>

                <%--End form--%>


                <div id="containerchitiet">
                    <div class="chitietsanpham" >
                        <p>Chi Tiết :</p>
                        <div class="form-group">

                            <label for="mausanpham">Màu Sản Phẩm :</label>
                            <select class="form-control" id="mausanpham" name="mauSanPham">
                                <c:forEach var="l" items="${listMauSanPham}">
                                    <option value="${l.maMau}">${l.tenMau}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="sizesanpham">Size Sản Phẩm :</label>
                            <select class="form-control" id="sizesanpham" name="sizeSanPham">
                                <c:forEach var="l" items="${listSizeSanPham}">
                                    <option value="${l.maSize}">${l.tenSize}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="soluong">Số Lượng:</label>
                            <input type="number" min="1" value="1" class="form-control" id="soluong" name="soLuong" placeholder="Số Lượng Sản Phẩm">
                        </div>
                        <button class="btn btn-warning btn-chitiet" style="margin-bottom: 10px ; float: right"> Thêm Chi Tiết</button>
                    </div>

                </div>



                <div class="chitietsanpham" id="chitietsanpham">
                    <p>Chi Tiết :</p>
                    <div class="form-group">

                        <label for="mausanpham">Màu Sản Phẩm :</label>
                        <select class="form-control" id="mausanpham" name="mauSanPham">
                            <c:forEach var="l" items="${listMauSanPham}">
                                <option value="${l.maMau}">${l.tenMau}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="sizesanpham">Size Sản Phẩm :</label>
                        <select class="form-control" id="sizesanpham" name="sizeSanPham">
                            <c:forEach var="l" items="${listSizeSanPham}">
                                <option value="${l.maSize}">${l.tenSize}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="soluong">Số Lượng:</label>
                        <input type="number" min="1" value="1" class="form-control" id="soluong" name="soLuong" placeholder="Số Lượng Sản Phẩm">
                    </div>
                    <button class="btn btn-warning btn-chitiet" style="margin-bottom: 10px ; float: right"> Thêm Chi Tiết</button>
                </div>

                <div style="margin-top: 65px;margin-bottom: 20px">

                    <button id="btnthemsanpham" class="btn btn-primary " >Thêm </button>
                    <button id="btncapnhat" class="btn btn-primary hidden-item" >Cập Nhật </button>
                    <button id="btnthoat" class="btn btn-primary hidden-item" >Thoát </button>
                </div>
            </div>


            <%--end div right--%>

            <div class="col-md-7 col-sm-12">

                <table class="table" id="table-sanpham">
                    <thead>
                    <tr>
                        <th>
                            <div class="checkbox">
                                <label><input type="checkbox" value="" id="check-all"></label>
                            </div>
                        </th>
                        <th>Tên Sản Phẩm</th>
                        <th>Giá Tiền</th>
                        <th>Danh Cho</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="l" items="${listsp}">
                        <tr>
                            <td>
                                <div class="checkbox">
                                    <label><input class="selectSanPham" type="checkbox" value="${l.maSanPham}"></label>
                                </div>
                            </td>
                            <td class="tensp" data-masp="${l.maSanPham}">${l.tenSanPham}</td>
                            <td class="giatiensp">${l.giaTien}</td>
                            <td class="gianchosp">${l.gianhCho}</td>
                            <td class="capnhat-sanpham" data-id="${l.maSanPham}"><button class="btn btn-warning btn-sm">Cập Nhật</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button class="btn btn-primary xoa-sanpham" >Xóa</button>
                <div class="text-center">
                    <ul class="pagination pagination-sm ">
                        <c:forEach var="i" begin="1" end="${page}">
                            <c:choose>
                                <c:when test="${i==1}">
                                    <li class="paging-item active"><a href="#">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="paging-item"><a href="#">${i}</a></li >
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </div> <%-- //div row--%>
    </div>
    <!--//content-inner-->
    <!--/sidebar-menu-->
    <div class="sidebar-menu">
        <header class="logo1">
            <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a>
        </header>
        <div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
        <div class="menu">
            <ul id="menu">
                <li><a href="index.html"><i class="fa fa-tachometer"></i> <span>Dashboard</span>
                    <div class="clearfix"></div>
                </a></li>


                <li id="menu-academico"><a href="inbox.html"><i class="fa fa-envelope nav_icon"></i><span>Inbox</span>
                    <div class="clearfix"></div>
                </a></li>
                <li><a href="gallery.html"><i class="fa fa-picture-o" aria-hidden="true"></i><span>Gallery</span>
                    <div class="clearfix"></div>
                </a></li>
                <li id="menu-academico"><a href="charts.html"><i class="fa fa-bar-chart"></i><span>Charts</span>
                    <div class="clearfix"></div>
                </a></li>
                <li id="menu-academico"><a href="#"><i class="fa fa-list-ul"
                                                       aria-hidden="true"></i><span> Short Codes</span>
                    <span class="fa fa-angle-right" style="float: right"></span>
                    <div class="clearfix"></div>
                </a>
                    <ul id="menu-academico-sub">
                        <li id="menu-academico-avaliacoes"><a href="icons.html">Icons</a></li>
                        <li id="menu-academico-avaliacoes"><a href="typography.html">Typography</a></li>
                        <li id="menu-academico-avaliacoes"><a href="faq.html">Faq</a></li>
                    </ul>
                </li>
                <li id="menu-academico"><a href="errorpage.html"><i class="fa fa-exclamation-triangle"
                                                                    aria-hidden="true"></i><span>Error
								Page</span>
                    <div class="clearfix"></div>
                </a></li>
                <li id="menu-academico"><a href="#"><i class="fa fa-cogs"
                                                       aria-hidden="true"></i><span> UI Components</span> <span
                        class="fa fa-angle-right" style="float: right"></span>
                    <div class="clearfix"></div>
                </a>
                    <ul id="menu-academico-sub">
                        <li id="menu-academico-avaliacoes"><a href="button.html">Buttons</a></li>
                        <li id="menu-academico-avaliacoes"><a href="grid.html">Grids</a></li>
                    </ul>
                </li>
                <li><a href="tabels.html"><i class="fa fa-table"></i> <span>Tables</span>
                    <div class="clearfix"></div>
                </a></li>
                <li><a href="maps.html"><i class="fa fa-map-marker" aria-hidden="true"></i> <span>Maps</span>
                    <div class="clearfix"></div>
                </a></li>
                <li id="menu-academico"><a href="#"><i class="fa fa-file-text-o"></i> <span>Pages</span> <span
                        class="fa fa-angle-right"
                        style="float: right"></span>
                    <div class="clearfix"></div>
                </a>
                    <ul id="menu-academico-sub">
                        <li class="menu-academico-boletim"><a href="calendar.html">Calendar</a></li>
                        <li class="menu-academico-avaliacoes"><a href="signin.html">Sign In</a></li>
                        <li class="menu-academico-avaliacoes"><a href="signup.html">Sign Up</a></li>

                    </ul>
                </li>
                <li><a href="#"><i class="fa fa-check-square-o nav_icon"></i><span>Forms</span> <span
                        class="fa fa-angle-right"
                        style="float: right"></span>
                    <div class="clearfix"></div>
                </a>
                    <ul>
                        <li><a href="input.html"> Input</a></li>
                        <li><a href="validation.html">Validation</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<script>
    var toggle = true;

    $(".sidebar-icon").click(function () {
        if (toggle) {
            $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
            $("#menu span").css({"position": "absolute"});
        }
        else {
            $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
            setTimeout(function () {
                $("#menu span").css({"position": "relative"});
            }, 400);
        }

        toggle = !toggle;
    });
</script>
<!--js -->
<script src='<c:url value="/resources/template/js/jquery.nicescroll.js"/>'></script>
<script src='<c:url value="/resources/template/js/scripts.js"/>'></script>

<!-- Bootstrap Core JavaScript -->
<script  src='<c:url value="/resources/template/js/bootstrap.min.js"/>'></script>
<!-- /Bootstrap Core JavaScript -->
<!-- morris JavaScript -->
<script  src='<c:url value="/resources/template/js/raphael-min.js"/>'></script>
<script  src='<c:url value="/resources/js/custom.js"/>'></script>
<%--<script>
    $(document).ready(function () {
        //BOX BUTTON SHOW AND CLOSE
        jQuery('.small-graph-box').hover(function () {
            jQuery(this).find('.box-button').fadeIn('fast');
        }, function () {
            jQuery(this).find('.box-button').fadeOut('fast');
        });
        jQuery('.small-graph-box .box-close').click(function () {
            jQuery(this).closest('.small-graph-box').fadeOut(200);
            return false;
        });

        //CHARTS
        function gd(year, day, month) {
            return new Date(year, month - 1, day).getTime();
        }

        graphArea2 = Morris.Area({
            element: 'hero-area',
            padding: 10,
            behaveLikeLine: true,
            gridEnabled: false,
            gridLineColor: '#dddddd',
            axes: true,
            resize: true,
            smooth: true,
            pointSize: 0,
            lineWidth: 0,
            fillOpacity: 0.85,
            data: [
                {period: '2014 Q1', iphone: 2668, ipad: null, itouch: 2649},
                {period: '2014 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
                {period: '2014 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
                {period: '2014 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
                {period: '2015 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
                {period: '2015 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
                {period: '2015 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
                {period: '2015 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
                {period: '2016 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
                {period: '2016 Q2', iphone: 8442, ipad: 5723, itouch: 1801}
            ],
            lineColors: ['#ff4a43', '#a2d200', '#22beef'],
            xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        });


    });
</script>--%>
</body>

</html>