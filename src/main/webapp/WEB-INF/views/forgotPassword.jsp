<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
    <div class="registration-form">
            <form action='<c:url value="/reset-password"/>' method="post" id="formLogin" >
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" style="text-align: center">
                            ${message}
                    </div>
                </c:if>
                <div class="form-group">
                    <h2 style="color:black; text-align: center">Reset Password</h2>
                </div>
                <div class="form-group">
                    <b>Enter your user account's verified email address and we will send you a password reset.</b>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control item" id="username" placeholder="Nhập Email" name="username">
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-block create-account">Reset</button>
                </div>

            </form>
        <div class="social-media">
            <div class="form-group" style="text-align: center">
               <h3><a href="<c:url value="/dang-nhap"/>">SIGN IN</a></h3>
            </div>
        </div>
        </div>
</body>
</html>
