<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<img src="<c:url value="/template/bloghome/assets/article.ico"/>" alt="My Logo" style="max-width: 20px;max-height: 20px">&nbsp;
		<a class="navbar-brand" href="<c:url value='/trang-chu' />">Báo Thường Niên</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/trang-chu' />">Trang chủ
						<span class="sr-only">(current)</span>
				</a></li>
				<security:authorize access="isAnonymous()">
					<li class="nav-item"><a class="nav-link" href="<c:url value='/dang-nhap' />">Đăng nhập</a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/dang-ky' />">Đăng ký</a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="<c:url value='/trang-chu' />">Wellcome,<%=SecurityUtils.getPrincipal().getFullName()%></a>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/change-password' />">Đổi mật khẩu</a>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/thoat' />">Thoát</a>
				</security:authorize>

				</li>
			</ul>
		</div>
	</div>
</nav>
