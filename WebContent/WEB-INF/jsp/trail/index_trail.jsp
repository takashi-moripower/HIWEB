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
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">案件管理</h4>
			</div>
			<div class="panel-body">
				<div class="form-group form-inline">
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<%-- <a class="btn btn-info btn-lg"  href="<c:url value="trail/anken/toroku"></c:url>" style="margin-top:20px;padding:15px;">新規案件登録</a> --%>
						<a class="btn btn-info btn-lg" href="createcase">新規案件登録</a>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<a class="btn btn-info btn-lg"
							href="<c:url value="/initAnkenSearch?prePage=index"></c:url>">詳細検索</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">案件通知</h4>
			</div>
			<div class="panel-body">
				<div class="form-group form-inline">
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<%-- <a class="btn btn-info btn-lg"  href="<c:url value="trail/anken/toroku"></c:url>" style="margin-top:20px;padding:15px;">新規案件登録</a> --%>
						<a class="btn btn-info btn-lg" href="tuchi_list">通知条件一覧</a>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<a class="btn btn-info btn-lg"
							href="<c:url value="/tuchi_add"></c:url>">新規作成</a>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">請求管理</h4>
			</div>
			<div class="panel-body">
				<div class="form-group form-inline">
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<a class="btn btn-info btn-lg" href="initSeikyuAnkenList">請求対象検索</a>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">企業管理</h4>
			</div>
			<div class="panel-body">
				<div class="form-group form-inline">
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<a class="btn btn-info btn-lg"
							href="<%=request.getContextPath()%>/initRegisterMember">企業登録</a>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<a class="btn btn-info btn-lg"
							href="<%=request.getContextPath()%>/memberList">企業検索</a>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<a class="btn btn-info btn-lg"
							href="<%=request.getContextPath()%>/changePassword">ﾊﾟｽﾜｰﾄﾞ変更</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">デバッグ用</h4>
			</div>
			<div class="panel-body">
				<div class="form-group form-inline">
					<div class="col-md-5 col-md-offset-1">
						<a class="btn btn-info btn-lg"
							href="<%=request.getContextPath()%>/tuchi_debug">debug</a>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<a class="btn btn-info btn-lg"
							href="<%=request.getContextPath()%>/tuchi_debug_search">通知検索</a>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<a class="btn btn-info btn-lg"
							href="<%=request.getContextPath()%>/tuchi_debug_send">通知発送</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">管理用</h4>
			</div>
			<div class="panel-body">
				<div class="col-md-5 col-md-offset-1">
					<c:choose>
						<c:when test="${data != null and data != ''}">
							<a class="btn btn-default btn-disabled btn-lg"
								>url 設定</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-danger btn-lg"
								href="<%=request.getContextPath()%>/tuchi_init_base_url">url 設定</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">お知らせ(運送)</h4>
			</div>
			<div class="panel-body">
				<object data="resources/html/news_unso.html" width="100%"></object>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">お知らせ(荷主)</h4>
			</div>
			<div class="panel-body">
				<object data="resources/html/news_ninushi.html" width="100%"></object>
			</div>
		</div>
	</div>
	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>
</body>
</html>
