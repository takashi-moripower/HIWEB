<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="../common/header.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="../common/nav.jsp"%>
	</div>

	<div class="container">
	<h1>通知</h1>
		<nav class="navbar ">
			<div class="container-fluid">
				<div class="navbar-header">
					<ul class="nav navbar-nav">
						<li><a class="btn btn-default" href="tuchi_add">新規追加</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>user</th>
					<th>配信先</th>
					<th>都道府県</th>
					<th>開始</th>
					<th>終了</th>
					<th>action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${item.tuchiId}" /></td>
						<th><c:out value="${item.title}" /></th>
						<td><c:out value="${item.userNm}" /></td>
						<td><c:out value="${item.email}" /></td>
						<td><c:out value="${item.prefName}" /></td>
						<td><c:out value="${item.dateStartText}" /></td>
						<td><c:out value="${item.dateEndText}" /></td>
						<td><a class="link-edit"
							href='tuchi_edit?tuchiId=<c:out value="${item.tuchiId}" />'>edit</a>
							<a class="link-delete"
							href='tuchi_delete?tuchiId=<c:out value="${item.tuchiId}"/>'>delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>
</body>
<script>
	$(function() {
		$('.link-delete').on('click', function() {
			console.log('clicked');
			if (confirm('本当に削除しますか?')) {
				return true;
			}
			;
			return false;
		});
	})
</script>
</html>
