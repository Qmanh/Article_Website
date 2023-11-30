<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="NewURL" value="/quan-tri/tai-khoan/danh-sach"/>
<c:url var="APIurl" value="/api/user"/>
<c:url var="editNewURL" value="/quan-tri/tai-khoan/chinh-sua"/>

<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>

                <li>
                    <a href="#">Forms</a>
                </li>
                <li class="active">Form Elements</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>

                    <form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="username"> userName</label>
                            <div class="col-sm-9">
                                <form:input path="username" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="fullName" > fullName</label>
                            <div class="col-sm-9">
                                <form:input path="fullName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="status" > Status</label>
                            <div class="col-sm-9">
                                <form:input path="status" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="roleName" > Role</label>
                            <div class="col-sm-9">
                                <form:input path="roleName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>

                        <form:hidden path="password" value="${model.password}" />
                        <form:hidden path="id" id="newId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110" ></i>
                                            Cập nhật thể loại
                                    </button>
                                </c:if>

                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110" ></i>
                                        Thêm thể loại
                                    </button>
                                </c:if>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset" id="btnExit">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Hủy
                                </button>
                            </div>
                        </div>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $('#btnExit').click(function (e) {
        e.preventDefault();
        window.location.href = "${NewURL}?type=list&limit=2&page=1";
    })

    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        var id = $('#newId').val();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
            updateUser(data);
        console.log(formData);
    });
    function updateUser(data){
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editNewURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
                window.location.href = "${editNewURL}?id="+error.id+"&message=error_system";
            }
        });
    }

</script>
</body>
</html>
