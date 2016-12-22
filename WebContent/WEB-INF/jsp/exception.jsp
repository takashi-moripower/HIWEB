<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>
<%@ page import="java.io.PrintWriter" %>
<title>exception.jsp</title>
</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		<!-- フォーム　▼ -->
  <form:form class="form-horizontal" id="" action=""
   method="POST">
    <fieldset>
		<h2 class="text-danger">エラー ページ</h2>
		<p class="text-danger">予期しないエラーが発生したため、処理を継続できません。<br>お手数ですが、システム管理者までご連絡ください。</p>
		<!-- ボタン 　▼-->
		<div class="row" style="padding: 15px 15px 15px 15px; clear: left;">
			</a> <a href="index" class="btn btn-default col-md-offset-12"> メニューに戻る </a>
		</div>

		</pre>
	</fieldset>
  </form:form>
  <!-- フォーム　▲-->

</div>
</body>
</html>
