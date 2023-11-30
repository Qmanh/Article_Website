<%@include file="/common/taglib.jsp"%>
<c:url var="NewURL" value="/quan-tri/tai-khoan/role"/>
<c:url var="APIurl" value="/api/new"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Danh sách bài viết</title>
	</head>

	<body>
		<div class="main-content">
		<form action="<c:url value='/quan-tri/tai-khoan/role'/>" id="formSubmit" method="get">
			
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Trang chủ</a>
							</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty message}">
									<div class="alert alert-${alert}">
											${message}
									</div>
								</c:if>
								<div class="widget-box table-filter">
									<div class="table-btn-controls">
										<div class="pull-right tableTools-container">
											<div class="dt-buttons btn-overlap btn-group">
												<c:url var="createNewURL" value="/quan-tri/tai-khoan/role/chinh-sua"/>
												<a flag="info"
												   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
												   title='Thêm bài viết' href='${createNewURL}'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
												</a>
												<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
														class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
												</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th><input type="checkbox" id="checkAll"/></th>
														<th>Code Role</th>
														<th>Role Name</th>
														<th>Thao tác</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"/></td>
															<td>${item.code}</td>
															<td>${item.name}</td>
															<td>
																<c:url var="updateNewURL" value="/quan-tri/tai-khoan/role/chinh-sua">
																	<c:param name="id" value="${item.id}"/>
																</c:url>
																<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
																   title="Cập nhật bài viết" href='${updateNewURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>
											<input type="hidden" value="" id="page" name="page"/>
											<input type="hidden" value="" id="limit" name="limit"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form>
		</div>
		<!-- /.main-content -->
		<script>
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

			$(document).ready(function () {
				$("#btnDelete").prop("disabled", true);
				$("#checkAll").on('change', function () {
					var isChecked = $(this).prop("checked");
					$('tbody input[type=checkbox]').prop("checked", isChecked);

					// Enable or disable the "Delete" button based on the number of checked checkboxes
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
						return $(this).val();
					}).get();

					if (ids.length === 0) {
						$("#btnDelete").prop("disabled", true);
					} else {
						$("#btnDelete").prop("disabled", false);
					}
				});

				$('tbody input[type=checkbox]').on('change', function () {
					// Enable or disable the "Delete" button based on the number of checked checkboxes
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
						return $(this).val();
					}).get();

					if (ids.length === 0) {
						$("#btnDelete").prop("disabled", true);
					} else {
						$("#btnDelete").prop("disabled", false);
					}
				});
			});

			function warningBeforeDelete(){
				swal({
							title: "Are you sure?",
							text: "You will not be able to recover this data!",
							type: "warning",
							showCancelButton: true,
							confirmButtonClass: "btn-success",
							cancelButtonClass: "btn-danger",
							confirmButtonText: "Yes",
							cancelButtonText: "No",
							closeOnConfirm: false,
							closeOnCancel: false
						}).then(function(isConfirm) {
							if (isConfirm.value) {
								var ids = $('tbody input[type=checkbox]:checked').map(function () {
									return $(this).val();
								}).get();
								deleteRole(ids);
								swal("Deleted!", "Your data has been deleted.", "success");
							}else{
								swal("Cancelled", "Your data is safe :)", "error");
							}
						});

			}
			function deleteRole(data){
				$.ajax({
					url:'${APIurl}',
					type:'DELETE',
					contentType:'application/json',
					data: JSON.stringify(data),
					success: function (result){
						window.location.href = "${NewURL}?page=1&limit=2&message=delete_success";
					},
					error: function (error){
						window.location.href = "${NewURL}?page=1&limit=2&message=error_system";
					}
				});
			}

		</script>
	</body>

	</html>