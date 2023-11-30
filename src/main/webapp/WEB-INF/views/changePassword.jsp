<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thay đổi mật khẩu</title>
</head>
<body>
    <div class="registration-form">
            <form action='<c:url value='/change-password'/> ' method="post" id="formLogin" >
                <div>
                    <h2 style="color:black; text-align: center">Thay đổi mật khẩu</h2>

                    <c:if test="${message != null}">
                        <div class="alert alert-${alert}">
                            ${message}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control item" id="inputOldPassword" placeholder="Mật khẩu cũ" name="inputOldPassword">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control item" id="inputNewPassword" placeholder="Mật khẩu mới" name="inputNewPassword">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-block create-account">Đổi mật khẩu</button>
                </div>

            </form>
        <div class="social-media">
            <div class="form-group" style="text-align: center">
                <h3><a href="<c:url value="/trang-chu"/>">Trở lại</a></h3>
            </div>
        </div>
        </div>
</body>
</html>
