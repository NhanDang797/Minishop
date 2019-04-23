<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<title>DangNhap Page</title>

    <jsp:include page="header.jsp" />
</head>

<body class="body-login ">
	<div class="body-flex-login">
		<div class="container-login">
			<div class="container-login-left">
				<div class="header-login" id="header-top-left">
					<span class="text-logo">Welcom</span><br /> 
					<span class="text-logo-small">Hãy tạo nên phong cách của bạn cùng minishop !</span>
				</div>
				
				<div class="header-bottom-left">
					<p>
						<img alt="icon_oval" src='<c:url value="/resources/images/icon_oval.png"/>'> 
							<span>Luôn cập nhật xu hướng thời trang mới nhất</span><br/>
					</p>
					<p>
						<img alt="icon_oval" src='<c:url value="/resources/images/icon_oval.png"/>'>
							<span>Giảm hơn 50% tất cả các mặt hàng cho khách VIP</span>
					</p>
					<p>
						<img alt="icon_oval" src='<c:url value="/resources/images/icon_oval.png"/>'>
						<span>Tận tình tư vấn để tạo nên phong cách cho bạn</span>
					</p>
				</div>
			</div>
			
			<div class="container-login-right">
				<div class="header-login " id="header-top-right">
					<span class="actived" id="dang-nhap">Đăng Nhập</span> / <span id="dang-ky">Đăng Ký</span>
                    <a href="/" style="float: right "><img style="height: 30px;" src="/resources/images/home.jpg"></a>
                </div>

				<div class="container-center-login-right">

					<div id="login-toggle"> <%--Đăng nhập--%>
						<h4 style="color: #F04646 ; font-style: italic ; text-align: center" id="ketqua"></h4>
						<input class="icon-email" type="text" placeholder=" Email" /><br/>
						<input class="icon-password" type="password" placeholder=" Mật Khẩu" /><br/>
						<button id="submit" class="meterial-primary-button" >ĐĂNG NHẬP</button>
					</div>
                    <%-- đăng ký sử dụng Ajax : không dngf controller--%>
					<div id="signup-toggle" style="display: none"> <%--Đăng ký--%>
                        <c:if test="${not empty msg}">
                            <div class="alert alert-${alert}">
                                    ${msg}
                            </div>
                        </c:if>
						<form action="" method="post">
							<input name="email" class="icon-email"  required placeholder=" Email" /><br/>
							<input name="matkhau" class="icon-password" type="password" required placeholder=" Mật Khẩu" /><br/>
							<input name="matkhauRe" class="icon-password" type="password" required placeholder="Nhập Lại Mật Khẩu" /><br/>
							<button class="meterial-primary-button" type="submit" >ĐĂNG KÝ</button>
						</form>
					</div>

				</div>

				<div class="container-social-icon">
					<img alt="icon_oval" src='<c:url value="/resources/images/icon_facebook.png"/>'>
					<img alt="icon_oval" src='<c:url value="/resources/images/icon_google.png"/>'>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>	