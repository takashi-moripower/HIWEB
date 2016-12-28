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
		<form method="post" class="form-horizontal" action="tuchi_post">
			<input type="hidden" name="tuchiId"
				value="<c:out value='${tuchi.tuchiId}' />" /> <input type="hidden"
				name="userId" value="<c:out value='${tuchi.userId}'/>" />

			<table class="table table-bordered ">
				<tbody>
					<tr>
						<th>タイトル</th>
						<td><input type="text" name="title"
							value="<c:out value="${tuchi.title}"/>" /></td>
					</tr>
					<tr>
						<th>地域</th>
						<td><select name="prefCd">
								<option value="">全国</option>
								<c:forEach var="item" items="${prefList}" varStatus="status">
									<option value="<c:out value='${item.prefCd}' />"
										<c:if test="${item.prefCd == tuchi.prefCd}"> selected </c:if>><c:out
											value="${item.prefName}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>開始日</th>
						<td><input type="date" name="dateStart"
							value="<c:out value='${tuchi.dateStartText}'/>" /></td>
					</tr>
					<tr>
						<th>終了日</th>
						<td><input type="date" name="dateEnd"
							value="<c:out value='${tuchi.dateEndText}'/>" /></td>
					</tr>
					<tr>
						<th>トラックオプション</th>
						<td><c:forEach var="item" items="${truckOp}"
								varStatus="status">
								<label for="truckOp-<c:out value="${item.opCd}"/>"
									class="checkbox-inline"><input
									id='truckOp-<c:out value="${item.opCd}"/>' type="checkbox"
									value="<c:out value="${item.opCd}" />"
									<c:if test="${item.value}">checked="checked"</c:if>
									name="truckOp" /> <c:out value="${item.opName}" /></label>
							</c:forEach></td>
					</tr>
					<tr>
						<th>車種</th>
						<td><c:forEach var="item" items="${syasyu}"
								varStatus="status">
								<label for="truckOp-<c:out value="${item.syasyuCd}"/>"
									class="checkbox-inline"><input
									id='syasyu-<c:out value="${item.syasyuCd}"/>' type="checkbox"
									value="<c:out value="${item.syasyuCd}" />"
									<c:if test="${item.value}">checked="checked"</c:if>
									name="syasyu" /> <c:out value="${item.syasyuName}" /></label>
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
