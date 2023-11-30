<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Báo thường niên</title>
    <!-- Favicon-->

</head>
<body>

<!-- Page header with logo and tagline-->
<!-- Page header with logo and tagline-->
<header class="py-2 bg-light border-bottom mb-1">
    <div class="container">
        <div class="text-center my-3">
            <h1 class="fw-bolder">BÁO THƯỜNG NIÊN</h1>
            <p class="lead mb-0">Trang tin tức đáng tin cậy và xác thực nhất</p>
        </div>
    </div>
</header>
<!-- Page content-->
<div class="container">
    <form action="<c:url value="/trang-chu"/> " method="get" id="formSubmit">
    <div class="row">

        <!-- Blog entries-->
        <div class="col-lg-8">
            <!-- Featured blog post-->
            <c:forEach var="item" items="${model.listResult}">

                <c:if test="${randomId.contains(item.id)}">
            <div class="card mb-4">
                <a href="<c:url value="/trang-chu/bai-viet?id=${item.id}"/>"><img class="card-img-top" src="data:image/jpg;image/png;base64,${item.thumbnail}" alt="hình ảnh" /></a>
                <div class="card-body">
                    <div class="small text-muted">${item.modifiedDate}</div>
                    <h2 class="card-title">${item.title}</h2>
                    <p class="card-text">${item.shortDescription}</p>
                    <a class="btn btn-primary" href="<c:url value="/trang-chu/bai-viet?id=${item.id}"/>">Read more →</a>
                </div>
            </div>
                </c:if>

            </c:forEach>
            <!-- Nested row for non-featured blog posts-->

            <div class="row">
                <c:forEach var="item" items="${model.listResult}">
                <c:if test="${not randomId.contains(item.id)}">
                <div class="col-lg-6">
                    <!-- Blog post-->

                    <div class="card mb-4">
                        <a href=""<c:url value="/trang-chu/bai-viet?id=${item.id}"/>""><img class="card-img-top" src="data:image/jpg;image/png;base64,${item.thumbnail}" alt="hình ảnh" style="height: 300px" /></a>
                        <div class="card-body">
                            <div class="small text-muted">${item.modifiedDate}</div>
                            <h2 class="card-title h4">${item.title}</h2>
                            <p class="card-text">${item.shortDescription}</p>
                            <a class="btn btn-primary" href="<c:url value="/trang-chu/bai-viet?id=${item.id}"/> ">Read more →</a>
                        </div>
                    </div>
                    <!-- Blog post-->
                </div>
                </c:if>
                </c:forEach>
            </div>

                <nav aria-label="Page navigation ">
                    <ul class="pagination justify-content-center my-4" id="pagination"></ul>
                </nav>

            <input type="hidden" value="" id="page" name="page"/>
            <input type="hidden" value="" id="limit" name="limit"/>
<%--            <input type="hidden" value="" id="type" name="type"/>--%>
<%--            <input type="hidden" value="" id="id" name="id"/>--%>
        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4  text-center">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                            <input class="form-control" type="text" name="keyword" id="keyword"
                                   value=""
                                   placeholder="Enter search term..." aria-label="Enter search term..." aria-describedby="button-search" />
                    </div>
                </div>
            </div>
            <div class="p-2 mr-2"></div>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                    <div class="row">
                        <c:forEach var="item" items="${categories}">
                            <div class="col-sm-6">
                                <ul class="list-unstyled mb-0">
                                    <li><a href="<c:url value="/trang-chu?page=1&limit=5&type=${item.key}"/> ">${item.value}</a></li>
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>

    </div>
    </form>
</div>
<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>


<script type="text/javascript">

    var currentPage = ${model.page};
    var totalPages = ${model.totalPage};
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
