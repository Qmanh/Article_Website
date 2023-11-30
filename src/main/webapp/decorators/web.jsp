<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>

	<link rel="icon" type="image/x-icon" href="<c:url value="/template/bloghome/assets/article.ico"/>" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="<c:url value="/template/bloghome/css/styles.css"/>" rel="stylesheet" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
	<script src="<c:url value="/template/bloghome/js/jquery.twbsPagination.js"/>" type="text/javascript"></script>

	<link href="<c:url value='/template/web/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" media="all"/>
	<link href="<c:url value='/template/web/css/small-business.css'/>" rel="stylesheet" type="text/css" media="all"/>


	<script src="<c:url value='/template/web/vendor/jquery/jquery.min.js'/>"></script>
	<script src="<c:url value='/template/web/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/template/paging/jquery.twbsPagination.js'/>" type="text/javascript"></script>
	<script src="<c:url value="/template/detailNew/js/scripts.js"/>"></script>


</head>
<body>

	<!-- Navigation -->
	<%@ include file="/common/web/header.jsp" %>

	<dec:body/>



</body>
</html>