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
		<!-- フォーム　▼ -->
		<form class="form-horizontal">
			<fieldset>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">案件検索</h4>
					</div>
					<div class="panel-body">
						<div class="form-group form-inline">
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<!-- Trigger the modal with a button -->
								<a class="btn btn-info btn-lg" href="init_todohuen?func=shuka">集荷地域</a>
								<!-- 地域選択Modal -->
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<a class="btn btn-info btn-lg"
									href="initAnkenSearch?prePage=index">詳細検索</a>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">受注案件管理</h4>
					</div>
					<div class="panel-body">
						<div class="form-group form-inline">
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<input id="ankenStatusList1" type="hidden"
									name="ankenStatusList"> <input id="ankenStatusList2"
									type="hidden" name="ankenStatusList"> <a
									class="btn btn-info btn-lg" href="certain">確定済案件</a> <span
									class="help-block">${kakuinZumiKensu}件（車番待<a
									href="syabanMinyuroku">${shabanMinyuRyokuKensu}件</a>）
								</span>
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<a class="btn btn-info btn-lg" href="initSeikyuAnkenList">請求対象検索</a>
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
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<%-- <a class="btn btn-info btn-lg"  href="<c:url value="trail/anken/toroku"></c:url>" style="margin-top:20px;padding:15px;">新規案件登録</a> --%>
								<a class="btn btn-info btn-lg" href="tuchi_list"
									style="margin-top: 20px; padding: 15px;">通知条件一覧</a>
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<a class="btn btn-info btn-lg"
									href="<c:url value="/tuchi_add"></c:url>"
									style="margin-top: 20px; padding: 15px;">新規作成</a>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">会員情報管理</h4>
					</div>
					<div class="panel-body">
						<div class="form-group form-inline">
							<div class="col-md-2"></div>
							<div class="col-md-5">
								<a class="btn btn-info btn-lg" href="initUpdateMember">会員情報管理</a>
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
						<object data="resources/html/news_unso.html" width="100%"></object>
					</div>
				</div>

			</fieldset>
		</form>
	</div>
	<!-- フォーム　▲-->
	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>
</body>
</html>
