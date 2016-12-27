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
		<ul>
		<li><a href="tuchi_add">新規追加</a></li>
		</ul>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Title</th>
					<th>tuchiId</th>
					<th>userId</th>
					<th>dateStart</th>
					<th>dateEnd</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<th><c:out value="${item.title}" /></th>
						<td><c:out value="${item.tuchiId}" /></td>
						<td><c:out value="${item.userId}" /></td>
						<td><c:out value="${item.dateStart}" /></td>
						<td><c:out value="${item.dateEnd}" /></td>
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
</html>
