<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="logistics.system.project.utility.Constants"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="../common/header.jsp"%>
<style>
.btn.btn-xs {
	padding: 0 10px;
}

.table-tuchi-list thead th {
	white-space: nowrap;
}

table.table-tuchi-list tr td {
	padding:.5rem;
	white-space: nowrap;
}

.table-tuchi-list td.area {
	white-space: normal;
}
<%boolean isAdmin = Constants.GYOMU_SB_TRAIL.equals(request.getAttribute("GyomuSb")); %>
<%if (!isAdmin) {%>
.table-tuchi-list th.user-nm, .table-tuchi-list td.user-nm {
	display: none;
}
<%}%>
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/nav.jsp"%>
	</div>

	<div class="container">
		<h1>通知条件一覧</h1>

		<table class="table table-bordered table-striped table-tuchi-list">
			<thead>
				<tr>
					<th>Id</th>
					<th>タイトル</th>
					<th class="user-nm">登録者</th>
					<th>荷主</th>
					<th>配信先</th>
					<th>地域</th>
					<th>開始</th>
					<th>終了</th>
					<th>発送数</th>
					<th>発送数<wbr>(今日)
					</th>
					<th>action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${item.tuchiId}" /></td>
						<td><c:out value="${item.title}" /></td>
						<td class="user-nm"><c:out value="${item.userNm}" /></td>
						<td><c:out value="${item.unsoNm}" /></td>
						<td><c:out value="${item.email}" /></td>
						<td class="area"><c:out value="${item.area}" /></td>
						<td><c:out value="${item.dateStartText}" /></td>
						<td><c:out value="${item.dateEndText}" /></td>
						<td class="text-right"><c:out value="${item.mailCount}" /></td>
						<td class="text-right"><c:out value="${item.mailCountDay}" /></td>
						<td><a class="link-edit btn btn-info btn-xs"
							href='tuchi_edit?tuchiId=<c:out value="${item.tuchiId}" />'>編集</a>
							<a class="link-delete  btn btn-warning btn-xs"
							href='tuchi_delete?tuchiId=<c:out value="${item.tuchiId}"/>'>削除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="container-fluid text-right">
			<a class="btn btn-default" href="tuchi_add">新規追加</a> <a
				class="btn btn-default" href="topPage">前の画面に戻る</a>
		</div>
		<br>
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
