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
		<div>
			<c:choose>
				<c:when test="${data != null and data != ''}">
					<c:out value="${data}" />
				</c:when>
				<c:otherwise>
				no data
		</c:otherwise>
			</c:choose>
		</div>
	</div>

	<ul>

	</ul>

	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>
</body>
</html>
