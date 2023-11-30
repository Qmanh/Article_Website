<%@ include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:url var="APIurl" value="/api/comment"/>
<c:url var="newURL" value="/trang-chu/bai-viet"/>
<c:url var="loginURL" value="/dang-nhap"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Blog Post - Start Bootstrap Template</title>
    <!-- Favicon-->

</head>
<body>
<div class="container mt-5">

    <div class="row">
        <div class="col-lg-12">
            <!-- Post content-->
            <article>
                <!-- Post header-->
                <header class="mb-4">
                    <!-- Post title-->
                    <h1 class="fw-bolder mb-1">${model.title}</h1>
                    <!-- Post meta content-->
                    <div class="text-muted fst-italic mb-2">Posted on ${model.modifiedDate} by ${model.createdBy}</div>
                    <!-- Post categories-->
                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">Web Design</a>
                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">Freebies</a>
                </header>
                <!-- Preview image figure-->
                <figure class="mb-4"><img class="img-fluid rounded" src="data:image/jpg;image/png;base64,${model.thumbnail}" alt="hình ảnh" /></figure>
                <!-- Post content-->
                <section class="mb-5">
                    ${model.content}
                </section>
            </article>
            <!-- Comments section-->

            <section class="mb-5">

                <div class="card bg-light">
                    <div class="card-body">
                        <!-- Comment form-->
                        <form  class="mb-4" id="commentSubmit" >
                            <textarea id="content" name="content" Class="form-control" rows="3" placeholder="Join the discussion and leave a comment!"></textarea>
                            <input type="hidden" id="newId" name="newId" value="${model.id}">
                            <button class="btn btn-danger" type="button" id="btnCommentSubmit">apply</button>
                        </form>
                        <form  action="<c:url value="/trang-chu/bai-viet?page="/> " method="get" id="formSubmit">
                        <!-- Comment with nested comments-->
                        <c:forEach var="comment" items="${comments.listResult}">
                        <div class="d-flex mb-4">
                            <!-- Parent comment-->
                            <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                <div class="ms-3">
                                    <div class="text-muted fst-italic mb-2">Time: ${comment.modifiedDate}</div>
                                    <div class="fw-bold">${comment.createdBy}*</div>
                                        ${comment.content}
                                </div>

                        </div>
                        </c:forEach>
                            <input type="hidden" id="id" name="id" value="${model.id}">
                            <input type="hidden" id="page" name="page" value="">
                        </form>
                    </div>
                </div>

            </section>

                <nav aria-label="Page navigation ">
                    <ul class="pagination justify-content-center my-4" id="pagination"></ul>
                </nav>

        </div>
</div>


</div>
    <!-- Footer -->
    <%@ include file="/common/web/footer.jsp" %>

<script>
    $('#btnCommentSubmit').click(function (e) {
        e.preventDefault();
        var formData= $('#commentSubmit').serializeArray();
        var dto={};
        var id = $('#id').val();
        $.each(formData, function (i, v) {
            dto[""+v.name+""] = v.value;
        });
        insertComment(dto);
    })
    function insertComment(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NewURL}?id=${model.id}";
            },
            error: function (error) {
                window.location.href = "${loginURL}";
            }
        });
    }

    var currentPage = ${comments.page};
    var totalPages = ${comments.totalPage};
    <%--var limit = ${model.limit};--%>
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if(currentPage != page){
                    $('#limit').val(5);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
</body>
</html>

