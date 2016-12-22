<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="common/header.jsp"%>

<script type="text/javascript">
	$(function() {
		$(document).on('click', '#btn_confirm',function(){
			$("#passChgForm").submit();
		});
	});
</script>

</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		
		<!-- フォーム　▼ -->
		<form:form commandName="changePasswordForm" action="savePassword" id="passChgForm" name="changePasswordForm"
			class="form-horizontal">
			<input type="hidden" name="updateDt" value="${updateDt}">
			
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
				<!-- 基本情報 　▼-->
				<div class="panel panel-default">
					<!-- 基本情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">基本情報</h4>
					</div>
					<div class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">氏名</label>
							<label class="col-md-5 control-label" for="textinput">${sessionScope.user.username}</label>

							<label class="col-md-3 control-label" for="textinput">部署</label>
							<label class="col-md-5 control-label" for="textinput">${sessionScope.user.userBS}</label>
						</div>
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">役職</label>
							<label class="col-md-5 control-label" for="textinput">${sessionScope.user.userYK}</label>
							
							<label class="col-md-3 control-label" for="textinput">TEL(携帯）</label>
							<label class="col-md-5 control-label" for="textinput">${sessionScope.user.renrakuTel}</label>
						</div>
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">E-Mail</label>
							<label class="col-md-5 control-label" for="textinput">${sessionScope.user.loginId}</label>
						</div>
					</div>
					<!-- 基本情報(body) 　▲ -->
				</div>
				<!-- 基本情報 　▲-->
				
				<!-- ﾊﾟｽﾜｰﾄﾞ変更 　▼-->
				<div class="panel panel-default">
					<!-- ﾊﾟｽﾜｰﾄﾞ変更(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">ﾊﾟｽﾜｰﾄﾞ変更</h4>
					</div>
					<div class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">古いパスワード</label>
							<div class="col-md-5">
								<input name="oldPassword" type="password" class="form-control input-md" size="30" value="${oldPassword }">
							</div>
						</div>

						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">新パスワード</label>
							<div class="col-md-5">
								<input name="password" type="password" class="form-control input-md" size="30" maxlength="15" value="${password }">
							</div>
						</div>
							
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">新パスワード確認</label>
							<div class="col-md-5">
								<input name="confirmPassword" type="password" class="form-control input-md" size="30" maxlength="15" value="${confirmPassword }">
							</div>
						</div>
					</div>
					<!-- ﾊﾟｽﾜｰﾄﾞ変更(body) 　▲ -->
				</div>
				<!-- ﾊﾟｽﾜｰﾄﾞ変更 　▲-->

				<!-- ボタン 　▼-->
				<div class="row" style="padding: 15px 15px 15px 15px; clear: left;">
					<a href="javascript:void(0);" class="btn btn-primary col-md-offset-17" id="btn_confirm">確 認
					</a> <a href="index" class="btn btn-default col-md-offset-1"> キャンセル </a>
				</div>
				<!-- ボタン 　▲-->
			</fieldset>
		</form:form>
		<!-- フォーム　▲-->
	</div>
</body>
</html>
