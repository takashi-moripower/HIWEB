<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>

<script src="<c:url value="/resources/js/Constants.js"></c:url>"></script>
<script src="<c:url value="/resources/js/customized-commponent.js"></c:url>"></script>


<script type="text/javascript">
	<c:if test="${selType != null && selType != ''}">
	function doSelect(companyCd, customCd, compayNm, officeNm, prefNm, cityNm) {
		if (window.parent) {
			var parentWin = window.parent;
			<c:if test="${selType == '0'}">
			parentWin.setNinushi(companyCd, customCd, compayNm, officeNm, prefNm, cityNm);
			</c:if>
			<c:if test="${selType == '1'}">
			parentWin.setUnso(companyCd, customCd, compayNm, officeNm, prefNm, cityNm);
			</c:if>
			<c:if test="${selType == '2'}">
			parentWin.setNinushi(companyCd, customCd, compayNm, officeNm, prefNm, cityNm);
			</c:if>
		}
	}
	function doClear() {
		if (window.parent) {
			var parentWin = window.parent;
			<c:if test="${selType == '0'}">
			parentWin.setNinushi('', '', '', '', '', '');
			</c:if>
			<c:if test="${selType == '1'}">
			parentWin.setUnso('', '', '', '', '', '');
			</c:if>
			<c:if test="${selType == '2'}">
			parentWin.setNinushi('', '', '', '', '', '');
			</c:if>
		}
	}
	</c:if>

	function doSearch() {
		var form = document.forms['memberSearchForm'];
		form.submit();
	}

	function doUpdate(companyCd) {
		var form = document.forms['memberSearchForm'];
		form.action = '<%=request.getContextPath()%>/initUpdateMember';
		form.submit();
	}
</script>
</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>

		<!-- フォーム　▼ -->
		<form:form commandName="memberSearchForm" action="memberList" id="memberSearchForm" name="memberSearchForm"
			class="form-horizontal">
			<input type="hidden" name="companyCd" value="${companyCd}">
			<input type="hidden" name="selType" value="${selType}">
			<fieldset>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">検索条件</h4>
					</div>
					<!-- 検索条件(body) 　▼ -->
					<div id="search_detail" class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="radios">業務種別</label>
							<div class="col-md-6 control-label">
							<c:choose>
								<c:when test="${selType != null && selType != ''}">
									<c:choose>
										<c:when test="${selType == '0'}">
											<input type="hidden" name="gyomuSb" value="0">
											<label class="radio-inline" >荷主 </label>
										</c:when>
										<c:when test="${selType == '1'}">
											<input type="hidden" name="gyomuSb" value="1">
											<label class="radio-inline" >運送会社 </label>
										</c:when>
										<c:when test="${selType == '2'}">
											<input type="hidden" name="gyomuSb" value="0">
											<label class="radio-inline" >荷主、管理者 </label>
										</c:when>
									</c:choose>
								</c:when>
								<c:otherwise>
									<label class="radio-inline" for="radios-0">
										<input id="radios-0" name="gyomuSb0" type="checkbox" value="0" <c:if test="${memberSearchForm.gyomuSb0 == '0'}">checked</c:if>>
									荷主 </label>
									<label class="radio-inline" for="radios-1"><input id="radios-1" name="gyomuSb1" type="checkbox" value="1" <c:if test="${memberSearchForm.gyomuSb1 == '1'}">checked</c:if>> 運送会社 </label>
								</c:otherwise>
							</c:choose>
							</div>
							<!-- 都道府県 -->
							<label class="col-md-2 control-label" for="textinput">都道府県</label>
							<div class="col-md-4">
								<input name="prefNm" type="text" class="form-control input-md" size="10" value="${memberSearchForm.prefNm}">
							</div>
							<!-- 市区町村 -->
							<label class="col-md-2 control-label" for="textinput">市区町村</label>
							<div class="col-md-4">
								<input name="cityNm" type="text" class="form-control input-md" size="15" value="${memberSearchForm.cityNm}">
							</div>
						</div>
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label" for="textinput">キーワード</label>
							<div class="col-md-3 control-label">
								<input name="keyWord" type="text"
									class="form-control input-md" size="40" value="${memberSearchForm.keyWord}">
							</div>
						</div>
					</div>
				</div>

				<!-- ボタン 　▼-->
				<div class="row" style="padding: 5px 1px 1px 1px; clear: left;">
					<a href="javascript:;" class="btn btn-info col-md-offset-17" onclick="doSearch()" >検 索</a>

					<c:choose>
						<c:when test="${selType != null && selType != ''}">
							<a href="javascript:;"
								class="btn btn-default col-md-offset-1" onclick="parent.closeMemListDialog();"> 戻る </a>
						</c:when>
						<c:otherwise>
							<a href="index"
								class="btn btn-default col-md-offset-1"> 戻る </a>
						</c:otherwise>
					</c:choose>
				</div>

				<hr>
				<div class="row" style="padding: 5px 5px 5px 5px; clear: left;">
					<label>件数：${fn:length(memberList)}件</label>
					<c:if test="${selType != null && selType != ''}">
						<a href="javascript:;" class="btn btn-primary btn-sm col-md-offset-1" onclick="doClear()" >選択クリア</a>
					</c:if>
				</div>

				<table width="997" align="center"
					class="table table-bordered table-striped">
					<tr>
						<th width="44" scope="col">選択</th>
						<th width="79" scope="col">得意先<br>コード
						</th>
						<th width="193" scope="col">得意先<br>名称
						</th>
						<th width="82" scope="col">事業所<br>コード
						</th>
						<th width="222" scope="col">事業所<br>名称
						</th>
						<th width="82" scope="col">業務<br>種別
						</th>
						<th width="124" scope="col">契約日</th>
						<th width="116" scope="col">利用<br> 端末
						</th>
						<th width="273" scope="col">住所</th>
					</tr>
					<c:forEach var="member" items="${memberList}" varStatus="status">
					<tr>
						<c:choose>
							<c:when test="${selType != null && selType != ''}">
							<td><a class="btn btn-primary btn-sm"
								href="javascript:;" onclick="doSelect('${member.companyCd}', '${member.customCd}', '${member.companyNm}', '${member.officeNm}', '${member.prefNm}', '${member.cityNm}')">選択</a></td>
							</c:when>
							<c:otherwise>
							<td><a class="btn btn-primary btn-sm"
								href="<%=request.getContextPath()%>/initUpdateMember?companyCd=${member.companyCd}">選択</a></td>
							</c:otherwise>
						</c:choose>
						<td>${member.customCd}</td>
						<td>${member.companyNm}</td>
						<td>${member.officeCd}</td>
						<td>${member.officeNm}</td>
						<td>${member.gyomuSbNm}</td>
						<td>${member.keiyakuDay}</td>
						<td>${member.riyoTm}</td>
						<td><div align="left">${member.prefNm}${member.cityNm}</div></td>
					</tr>
					</c:forEach>
				</table>

				<!-- 担当者情報 ▲ -->


			</fieldset>
		</form:form>
		<!-- フォーム　▲-->
	</div>
</body>
</html>
