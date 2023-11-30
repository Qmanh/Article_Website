<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="NewURL" value="/quan-tri/bai-viet/danh-sach"/>
<c:url var="APIurl" value="/api/new"/>
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua"/>

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

<%--          <form action="${APIurl}" method="post" class="form-horizontal" role="form" id="formSubmit" enctype="multipart/form-data" >--%>
            <form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
                <div class="form-group">
                    <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại:</label>
                    <div class="col-sm-9">
                        <form:select path="categoryCode" id="categoryCode">
                            <form:option value="" label="-- Chọn thể loại --"/>
                            <form:options items="${categories}"/>
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="title">Tên bài viết</label>
                    <div class="col-sm-9">
                        <form:input path="title" cssClass="col-xs-10 col-sm-5"/>
                    </div>
                </div>
                <c:if test="${empty model.id}">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="file">Ảnh đại diện</label>
                    <div class="col-sm-9">
                            <input type="file" class="col-xs-10 col-sm-5" id="file" name="thumbnail" formenctype="multipart/form-data"/>
                    </div>
                </div>
                </c:if>
                <div class="form-group">
                    <label for="shortDescription" class="col-sm-3 control-label no-padding-right">Mô tả ngắn:</label>
                    <div class="col-sm-9">
                        <form:textarea path="shortDescription" rows="5" cols="10" cssClass="form-control" id="shortDescription"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="content" class="col-sm-3 control-label no-padding-right">Nội dung:</label>
                    <div class="col-sm-9">
                        <form:textarea path="content" rows="5" cols="10" cssClass="form-control" id="content"/>
                    </div>
                </div>
                <form:hidden path="id" id="newId"/>
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <c:if test="${not empty model.id}">
                            <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                Cập nhật bài viết
                            </button>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                Thêm bài viết
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
    var editor = '';
    $(document).ready(function(){
        editor = CKEDITOR.replace( 'content')
        editor.on( 'change', function( evt ) {
            // getData() returns CKEditor's HTML content.
            console.log( 'Total bytes: ' + evt.editor.getData());
        });
    });

    $('#btnExit').click(function (e) {
        e.preventDefault();
        window.location.href = "${NewURL}?type=list&limit=2&page=1";
    })



    $('#btnAddOrUpdateNew').click(function (e) {
    e.preventDefault();
    var formData = $('#formSubmit').serializeArray();
    var dto={};
      $.each(formData, function (i, v) {
          dto[""+v.name+""] = v.value;
      });
      dto["content"]=editor.getData();
    var json=JSON.stringify(dto);
    var blob = new Blob([json], {
          type: 'application/json'
      });

      var id = $('#newId').val();
      console.log(id);

    if(id== ""){
        var data = new FormData();
        var file = $('#file')[0].files[0];
        data.append("test_file",file);
        data.append("test_json",blob);
        addNew(data);
    }else{
        updateNew(dto);
    }

  });
  function addNew(data) {
    $.ajax({
      url: '${APIurl}',
      type: 'POST',
      processData: false,
      contentType: false,
      data: data,
        header:{
            'Accept': 'application/json',
            'Content-Type': 'multipart/form-data',
        },
      success: function (result) {
        window.location.href = "${NewURL}?type=list&limit=2&page=1&&message=insert_success";
      },
      error: function (error) {
        window.location.href = "${NewURL}?type=list&limit=2&page=1&message=error_system";
      }
    });
  }
  function updateNew(data) {
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
              window.location.href = "${NewURL}?type=list&limit=2&page=1&message=error_system";
          }
      });
  }

</script>
</body>

</html>
