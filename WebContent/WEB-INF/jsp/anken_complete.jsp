<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>

<script src="../resources/js/Constants.js"></script>
<script src="../resources/js/customized-commponent.js"></script>

</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		<!-- フォーム　▼ -->

		<!-- フォーム　▼ -->


		<form:form class="form-horizontal">
			<fieldset>
			
				<c:choose>	
					<c:when test="${requestScope.updateFlag == true}">
						<h4 class="col-md-offset-6" style="margin-top: 100px;">案件情報を更新しました。</h4>

						<div class="form-group form-inline">
							<label class="col-md-12 control-label col-md-offset-6"
								for="textinput">以下の案件を更新しました。（トラック台数分）</label>
						</div>
			        </c:when>
			        <c:otherwise>
			            <h4 class="col-md-offset-6" style="margin-top: 100px;">案件情報を登録しました。</h4>

						<div class="form-group form-inline">
							<label class="col-md-12 control-label col-md-offset-6"
								for="textinput">以下の案件番号が採番されました（トラック台数分）</label>
						</div>
			        </c:otherwise>
			    </c:choose>	
				<!--<h4 class="col-md-offset-6" style="margin-top: 100px;">案件情報を登録しました。</h4>

				<div class="form-group form-inline">
					<label class="col-md-12 control-label col-md-offset-6"
						for="textinput">以下の案件番号が採番されました（トラック台数分）</label>
				</div> -->

				<c:forEach var="item" items="${result.ankenNoList}"
					varStatus="status">
					<div class="form-group form-inline">
						<div class="col-md-12 control-label col-md-offset-7">
							${item}</div>
					</div>
				</c:forEach>

				<div class="form-group form-inline" style="margin-bottom: 150px;">
				</div>
				<div class="form-group form-inline" style="">
					<a href="<%=request.getContextPath()%>/createcase"
						class="btn btn-primary col-md-offset-11">続けて登録</a> <a
						href="<%=request.getContextPath()%>/index"  class="btn btn-info col-md-offset-1">戻る</a>
				</div>

			</fieldset>
		</form:form>
	</div>
</body>
</html>
