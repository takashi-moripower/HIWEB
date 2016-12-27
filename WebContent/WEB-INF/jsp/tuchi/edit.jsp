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
		<form method="post" class="form-horizontal">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<th>title</th>
						<td><input type="text" name="title"  value="<c:out value="${tuchi.title}"/>"/></td>
					</tr>
					<tr>
						<th>dateStart</th>
						<td><input type="date" name="dateStart" /></td>
					</tr>
					<tr>
						<th>dateEnd</th>
						<td><input type="date" name="dateEnd" /></td>
					</tr>
					<tr>
						<th>truckOp</th>
						<td><c:forEach var="item" items="${truckOp}"
								varStatus="status">
								<label for="truckOp-<c:out value="${item.opCd}"/>"
									class="checkbox-inline"><input
									id='truckOp-<c:out value="${item.opCd}"/>' type="checkbox"
									value="<c:out value="${item.opCd}" />" <c:if test="${item.value}">checked="checked"</c:if> name="truckOp" /> <c:out
										value="${item.opName}" /></label>
							</c:forEach></td>
					</tr>
				</tbody>
			</table>
			<button type="submit">submit</button>
		</form>
	</div>
	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>
</body>
</html>
