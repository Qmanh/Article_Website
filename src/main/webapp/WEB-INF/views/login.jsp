<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <div class="registration-form">
            <form action='j_spring_security_check' method="post" id="formLogin" >
                <div>
                    <h2 style="color:black; text-align: center">Đăng nhập</h2>
                    <c:if test="${param.incorrectAccount != null}">
                        <div class="alert alert-danger" >
                            Username or Password is incorrect
                        </div>
                    </c:if>

                    <c:if test="${param.accessDenied != null}">
                        <div class="alert alert-danger">
                            you Not authorize
                        </div>
                    </c:if>

                    <c:if test="${message != null}">
                        <div class="alert alert-danger">
                            ${message}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control item" id="inputEmail" placeholder="Tên Đăng nhập" name="j_username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control item" id="inputPassword" placeholder="Mật khẩu" name="j_password">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-block create-account">Đăng nhập</button>
                </div>
                <div class="form-group" style="text-align: right">
                    <a href="<c:url value='/reset-password'/> ">Forgot Password</a>

                </div>

            </form>
        <div class="social-media">
            <div class="form-group" style="text-align: center">
                <h3>Don't have an account?&nbsp;<a href="<c:url value="/dang-ky"/>">Sign up</a></h3>
            </div>
        </div>
        </div>
</body>
</html>
