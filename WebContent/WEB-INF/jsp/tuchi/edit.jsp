<!DOCTYPE html>
<%@page import="logistics.system.project.tuchi.Entity.TuchiEntity"%>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="../common/header.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/tuchi/edit.js"></script>

</head>
<body>
	<div class="container">
		<%@ include file="../common/nav.jsp"%>
	</div>
	<div class="container">
		<h1>通知</h1>
		<form method="post" class="form-horizontal" action="tuchi_post">
			<input type="hidden" name="tuchiId" value="${tuchi.tuchiId}" /> <input
				type="hidden" name="userId" value="${tuchi.userId}" />
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<th style="width: 16rem">タイトル</th>
						<td><input type="text" name="title" value="${tuchi.title}" /></td>
					</tr>
					<tr>
						<th>地域</th>
						<td><select name="prefCd">
								<option value="0000">全国</option>
								<c:forEach items="${prefList}" var="pref">
									<option value="${pref.prefCd}"
										<c:if test="${pref.prefCd == tuchi.prefCd}">selected</c:if>>${pref.prefName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<c:set var="cat" value="00" />
					<c:forEach items="${prefList}" var="pref">
						<tr pref_cd="${pref.prefCd}">
							<th>${pref.prefName}</th>
							<td>
							<div>
								<label for="city-all">
									<input type="checkbox" name="all-city" id="all-city"/>
									全域
								</label>
							</div>

							<c:forEach items="${cityData}" var="city">
									<c:if test="${city.prefCd == pref.prefCd }">
										<c:if test="${cat != city.dispCateg }">
											<p>${city.dispCateg}</p>
											<c:set var="cat" value="${city.dispCateg}" />
										</c:if>
										<label for="city-${city.cityCd}">
										<input type="checkbox" id="city-${city.cityCd}" name="city" value="${city.cityCd}" <c:if test="${city.selected}">checked</c:if>/>
										${city.cityDisp}
										</label>
							</c:if>
								</c:forEach></td>
						</tr>
					</c:forEach>
					<tr>
						<th>開始日</th>
						<td><input type="date" name="dateStart"
							value="${tuchi.dateStartText}" /></td>
					</tr>
					<tr>
						<th>終了日</th>
						<td><input type="date" name="dateEnd"
							value="${tuchi.dateEndText}" /></td>
					</tr>
					<tr>
						<th style="white-space: nowrap">トラックオプション</th>
						<td>
							<c:forEach items="${truckOp}" var="op" >
							<label for="truckOp-${op.opCd}">
							<input type="checkbox" id="op-${op.opCd}" name="truckOp"  value="${op.opCd}" <c:if test="${op.selected}">checked</c:if> />
							${op.opName}
							</label>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>車種</th>
						<td>
							<c:forEach items="${syasyu}" var="syasyu" >
							<label for="syasyu-${syasyu.syasyuCd}">
							<input
							type="checkbox"
							id="syasyu-${syasyu.syasyuCd}"
							name="syasyu"  value="${syasyu.syasyuCd}"
							<c:if test="${syasyu.selected}">checked</c:if>
							/>
							${syasyu.syasyuName}
							</label>
							</c:forEach>
						</td>
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
