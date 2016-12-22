<%
	//ログイン済みかチェック
    if(request.getSession().getAttribute("user") != null) {
		response.sendRedirect("index");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="WEB-INF/jsp/common/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

</head>

<body>
	<div class="container">
	 <%@ include file="WEB-INF/jsp/common/nav.jsp"%>

<form:form commandName="loginForm" class="form-horizontal" action="login"
	method="post">

	<c:if test="${showErrorMessFlag}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<form:errors path="*"></form:errors>
		<div>${errormessage}</div>
	</div>
	</c:if>

	<fieldset>
		<h3 class="col-md-offset-9" style="margin-top: 100px;">ログイン</h3>

		<!-- ユーザID -->
		<div class="form-group form-inline"
			style="margin-top: 30px; margin-bottom: 20px;">
			<!-- ユーザID -->
			<label class="col-md-2 control-label col-md-offset-8"
				for="inputuserId">ユーザID</label>
			<div class="col-md-8">
				<input id="userId" name="userId" type="tel" class="form-control input-md"
					size="15" value="${loginForm.userId }">
			</div>
		</div>
		<!-- パスワード -->
		<div class="form-group form-inline" style="margin-bottom: 20px;">
			<!-- パスワード -->
			<label class="col-md-2 control-label col-md-offset-8"
				for="inputPassword">パスワード</label>
			<div class="col-md-8">
				<input name="password" type="password" class="form-control input-md"
					size="15">
			</div>
		</div>
		<!-- パスワード -->
		<div class="form-group form-inline" style="margin-bottom: 200px;">
			<button type="submit" class="btn btn-primary col-md-offset-11">ログイン</button>
		</div>

	</fieldset>
</form:form>
<!-- フォーム　▲-->
<footer>
	<hr>
	<div class="container">
		<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
	</div>
</footer>
<script type="text/javascript">
	$("#userId").focus();
	$("#userId").select();
</script>
</body>
</html>
