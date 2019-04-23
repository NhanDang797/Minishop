<%--
  Created by IntelliJ IDEA.
  User: nhandang
  Date: 18-Jan-19
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiet Page</title>
    <jsp:include page="header.jsp"/>

</head>
<body>
<div class="header " style=" background: black !important; height: 60px;">
    <nav class="navbar navbar-default nav-none ">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/"><img src='<c:url value="/resources/images/icon_yame_shop.png"/>'></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav nav-center">
                    <li class="active"><a href="/">TRANG CHỦ</a></li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown" role="button" aria-haspopup="true"
                                            aria-expanded="false">Sản Phẩm<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:forEach var="l" items="${danhMucSanPham}">
                                <li class="list-group-item"><a href='<c:url value="/sanpham/${l.maDanhMuc}/${l.tenDanhMuc}"/>'>${l.tenDanhMuc}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li><a href="#">DỊCH VỤ</a></li>
                    <li><a href="#">LIÊN HỆ</a></li>

                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${chuCaiDau != null}">
                            <li>
                                <a class="user" href="dangnhap/"> <span style="text-align: center">${chuCaiDau}</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/dangnhap/">ĐĂNG NHẬP</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li id="soluonggiohang">
                        <a href="/giohang/">
                            <img src='<c:url value="/resources/images/ic_shopping_cart_white_24dp_1x.png"/>'>
                            <c:if test="${soluongSP > 0}">
                                <div class="gio-hang"><span style="color: white">${soluongSP}</span></div>
                            </c:if>
                            <c:if test="${soluongSP <= 0 || soluongSP == null}">
                                <div><span style="color: white">${soluongSP}</span></div>
                            </c:if>

                        </a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

</div>
<!-- End Header -->
<div class="container product-detail" >
    <div class="row" >
        <div class="col-sm-2 col-md-2">
            <%--<h3>Loại Sản Phẩm</h3>
            <ul class="list-group">
                <c:forEach var="l" items="${danhMucSanPham}">
                    <li class="list-group-item"><a href="#">${l.tenDanhMuc}</a></li>
                </c:forEach>
            </ul>--%>
        </div>

        <div class="col-sm-10 col-md-10">

            <div class="title-product" style="margin-top: 0px !important;" >
                <span>${name}</span>
                <div class="row">
                    <c:forEach var="l" items="${listSanPham}">

                        <div class="col-md-4 col-sm-6 wow fadeInUp " data-wow-duration="0.25s"  data-wow-delay="0.5s">
                            <a href="/chitiet/${l.maSanPham}" style="text-decoration: none">
                                <div class=" product">
                                    <img src='<c:url value="/resources/images/sanpham/${l.hinhSanPham}"/>'> <br/>
                                    <span class="product-name">${l.tenSanPham}</span><br/>
                                    <span class="product-price">${l.giaTien} VNĐ</span>
                                </div>
                            </a>
                        </div>

                    </c:forEach>
                </div>
                <!--  End product  -->
            </div>

        </div>
    </div>
</div><div class="footer container-fluid">
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <p><span class="title-footer">THÔNG TIN SHOP</span></p>
            <span class= "detail-footer">Yame là thương hiệu thời trang đầy uy tín, luôn đảm bảo chất lượng tốt nhất cho khách hàng.</span>
            <h3 class= "detail-footer" style="font-style: italic;color: gray;font-size: 20px">Copyright © The <span style="text-decoration: underline;">Yame-Shop</span> Company .</h3>

        </div>
        <div class="col-sm-4 col-md-4">
            <p><span class="title-footer">LIÊN HỆ</span></p>
            <span class= "detail-footer">Điện Biên Phủ, Phường 25, Bình Thạnh, Hồ Chí Minh, Việt Nam</span><br/>
            <span class= "detail-footer">Email : abc12345@gmail.com</span><br/>
            <span class= "detail-footer">SĐT : 0123456789 - 0123456987</span>
        </div>
        <div class="col-sm-4 col-md-4">
            <p><span class="title-footer">GÓP Ý</span></p>
            <form method="post">
                <input name ="tenNhanVien" class="input-text" type="text" placeholder="Email" >
                <textarea name ="tuoiNhanVien" class="input-texterea" rows="4" cols="40" placeholder="Nội dung"></textarea>
                <button class="btn btn-info btn-dongy" type="button">ĐỒNG Ý</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>