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
		<ul>
			<li><a href="tuchi_debug">debug</a></li>
			<li><a href="tuchi_list">list</a></li>
			<li><a href="tuchi_add">add</a></li>
		</ul>
	</div>

	<div class="container">
		<%@ include file="../common/nav.jsp"%>

		<form:form class="form-horizontal">
			<fieldset>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">案件管理</h4>
					</div>
					<div class="panel-body">
						<div class="form-group form-inline">
							<div class="col-md-1"></div>
							<div class="col-md-5">
								<%-- <a class="btn btn-info btn-lg"  href="<c:url value="trail/anken/toroku"></c:url>" style="margin-top:20px;padding:15px;">新規案件登録</a> --%>
								<a class="btn btn-info btn-lg" href="createcase"
									style="margin-top: 20px; padding: 15px;">新規案件登録</a>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-5">
								<a class="btn btn-info btn-lg"
									href="<c:url value="/initAnkenSearch?prePage=index"></c:url>"
									style="margin-top: 20px; padding: 15px;">詳細検索</a>
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
								<a class="btn btn-info btn-lg" href="initSeikyuAnkenList"
									style="margin-top: 20px; padding: 15px;">請求対象検索</a>
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
									href="<%=request.getContextPath()%>/initRegisterMember"
									style="margin-top: 20px; padding: 15px;">企業登録</a>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-5">
								<a class="btn btn-info btn-lg"
									href="<%=request.getContextPath()%>/memberList"
									style="margin-top: 20px; padding: 15px;">企業検索</a>
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
						<h4 class="panel-title">お知らせ</h4>
					</div>
					<div class="panel-body">
						<IFRAME src="news_top.html" frameborder="0" width="100%"></IFRAME>
					</div>
				</div>

			</fieldset>
		</form:form>
	</div>
	<!-- フォーム　▲-->
	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>
</body>
</html>
