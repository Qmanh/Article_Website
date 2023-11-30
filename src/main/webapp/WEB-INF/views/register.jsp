<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Đăng ký</title>
</head>
<body>
<div class="registration-form">

  <form action="<c:url value="/dang-ky"/> " method="post">
    <c:if test="${not empty message}">
      <div class="alert alert-${alert}" style="text-align: center">
          ${message}
      </div>
    </c:if>
    <div class="form-icon">
      <span><i class="icon icon-user"></i></span>
    </div>
    <div class="form-group">
      <input type="email" class="form-control item" id="username" name="username" placeholder="Username">
    </div>
    <div class="form-group">
      <input type="text" class="form-control item" id="fullName" name="fullName" placeholder="Fullname">
    </div>
    <div class="form-group " >
      <input type="password" class="form-control item" id="password" name="password" placeholder="Password">
    </div>
    <div class="form-group">
      <input type="password" class="form-control item" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
    </div>


    <div class="form-group">
      <button type="submit" class="btn btn-block create-account">Đăng ký</button>
    </div>

  </form>
  <div class="social-media">
    <div class="form-group">
      <h3>Have already an account?&nbsp; <a href="<c:url value="/dang-nhap"/>">Sign in</a></h3>
    </div>
  </div>
</div>
<script>

</script>
</body>
</html>
