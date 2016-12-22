<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>

<script src="resources/js/customized-commponent.js"></script>
<script src="resources/js/ankentoroku.js"></script>

<script type="text/javascript">

	var addrTitle = Constants.ADDR_TITLE;

	var showArrElemId = Constants.SHOW_ARR_ELEM_ID;

	var contraTitle = Constants.CONTRA_TITLE;

	var showContraElemId = Constants.SHOW_CONTRA_ELEM_ID;

	var addrURL = Constants.AJAX_ADDRESS_URL;

	var contactURL = Constants.AJAX_CONTACT_URL;

	var addrKBN01 = Constants.ADDRESS_KBN_01;

	var addrKBN02 = Constants.ADDRESS_KBN_02;

	var rirekiTitle = Constants.RIREKI_TITLE;

	$(function() {

		var running = 0;

		$(document).on('click', '#btn_confirm', function(event) {
			if (running == 1){
				return false;
			}
			running = 1;
			ankenTorokuPostSubmit('<%=request.getContextPath()%>',['syuka-panel','nouhin-panel','truck-panel']);
		});

	});

	function getComponets(type, event) {
		var etarget = event.srcElement || event.target;
		if(type==1) {
			getItems(addrURL, addrKBN01, rirekiTitle, addrTitle, "pickup_addr_history", showArrElemId, etarget, "id");
		}
		if(type==2) {
			getItems(addrURL, addrKBN02, rirekiTitle, addrTitle, "recipient_addr_history", showArrElemId, etarget, "id");
		}
		if(type==3) {
			getItems(contactURL, addrKBN01, rirekiTitle, contraTitle, "pickup_contr_history", showContraElemId, etarget, "id");
		}
		if(type==4) {
			getItems(contactURL, addrKBN02, rirekiTitle, contraTitle, "recipient_contr_history", showContraElemId, etarget, "id");
		}
	};

	function inCancel() {
		if(confirm('メニュー画面に戻ります。よろしいですか？')) {
			//javascript:history.back();
			window.location.href = "<%=request.getContextPath()%>/index";
		}
	};

	function upCancel() {
		if(confirm('案件詳細画面に戻ります。よろしいですか？')) {
			var ankenNo = '${ankenTorokuForm.ankenNo}';
			ankenNo = ankenNo.replace(/-/g,'');
			window.location.href = "<%=request.getContextPath()%>/initAnkenDetail?ankenNo="+ankenNo+"&updateDt=${ankenTorokuForm.updateDt}";
		}
	};

	function getPostCodeInfo(event){
		var self = $(event.currentTarget);
		var formgroup = self.parents(".form-group.form-inline");
		var postCode = formgroup.find("#postCode").val().replace("-", "");
		if(isNaN(postCode)){
			alert(Constants.POST_CODE_FORMAT_TYPE);
			formgroup.find("#postCode").val("");
			return false;
		}

		if(postCode.length!=7){
			alert(Constants.POST_CODE_FORMAT_LENGTH);
			formgroup.find("#postCode").val("");
			return false;
		}

		var formatVal = postCode.substr(0, 3) + "-" + postCode.substr(3, 4);
		formgroup.find("#postCode").val(formatVal);
		$.ajax({
		    url:'getPostCode',
		    data:{
		       'postCode' : postCode
		    },
		    type:'post',
		    dataType:'json',
		    success:function(data) {
		        var prefNm = data == null ? "" : data.prefNm;
		        var cityNm = data == null ? "" :data.cityNm;
		        var addrOther = data == null ? "" :data.addrOther;
		        var panel = self.parents(".panel-body");

		        panel.find("#prefNm").find("[value='" + prefNm + "']").attr(
						"selected", true);
		        //panel.find("#prefNm").val(prefNm);
		        panel.find("#cityNm").val(cityNm);
		        panel.find("#addrOther").val(addrOther);
		    }
		});
	}
</script>

<style type="text/css">
	/* section */
	.section{margin:auto;overflow:hidden;}
	.section ul li{float:left;margin-right:14px;margin-bottom:13px;display:inline;width:142px;height:110px;overflow:hidden;position:relative;}
	.section ul li .photo{width:142px;height:110px;overflow:hidden;}
	.section .rsp{width:142px;height:110px;overflow:hidden;position: absolute;background:#000;top:0px;left:0px;}
	.section .text{position:absolute;width:142px;height:110px;left:-342px;top:0px;overflow:hidden; text-align: center; padding-top: 35px}
	.section .text .img_file{width:142px; height:42px; margin-top: -35px; opacity:0;}
</style>
</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		<!-- フォーム　▼ -->

		<!--<c:if test="${showErrorMessFlag}">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<spring:hasBindErrors name="ankenTorokuForm.syukaList">
				 <c:if test="${errors.fieldErrorCount > 0}">
			        <c:forEach items="${errors.fieldErrors}" var="error">
			            <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>
			            ${message}<br/>
			        </c:forEach>
		   		</c:if>
			</spring:hasBindErrors>
			<spring:hasBindErrors name="ankenTorokuForm.nohinList">
				 <c:if test="${errors.fieldErrorCount > 0}">
			        <c:forEach items="${errors.fieldErrors}" var="error">
			            <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>
			            ${message}<br/>
			        </c:forEach>
		   		</c:if>
			</spring:hasBindErrors>
			<spring:hasBindErrors name="ankenTorokuForm.truckList">
				 <c:if test="${errors.fieldErrorCount > 0}">
			        <c:forEach items="${errors.fieldErrors}" var="error">
			            <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>
			            ${message}<br/>
			        </c:forEach>
		   		</c:if>
			</spring:hasBindErrors>
		</div>
		</c:if>	-->

		<form:form id="ankenTorokuForm" name="ankenTorokuForm" commandName="ankenTorokuForm" action="ankenToroku"
			class="form-horizontal">

			<input type="hidden" id="updateFlag" name="updateFlag" value="${requestScope.updateFlag}"/>

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
				<div class="panel panel-default">
					<!-- 基本情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">基本情報</h4>
					</div>
					<div class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-2 control-label" for="textinput">案件番号</label>
							<form:label class="col-md-5 control-label" path="ankenNo">${ankenTorokuForm.ankenNo}</form:label>
							<form:hidden path="" id="ankenNoHidden" name="ankenNo" value="${ankenTorokuForm.ankenNo}"/>
							<form:hidden path="" id="ankenIdHidden" name="ankenId" value="${ankenTorokuForm.ankenId}"/>
							<label class="col-md-2 control-label" for="textinput">受注期限</label>
							<div class="col-md-6">
								<div class="input-group date">
									<input type="tel" class="form-control" name="orderDate" id="orderDate"
										value="${ankenTorokuForm.orderDate}" size="12"><span
										class="input-group-addon "><i
										class="glyphicon glyphicon-th"></i></span>
								</div>
							</div>
							<label class="col-md-3 control-label" for="textinput">案件ステータス</label>
							<form:label class="col-md-3 control-label" path="ankenStatusNm">${ankenTorokuForm.ankenStatusNm}</form:label>
							<form:hidden path="" id="ankenStatusHidden" name="ankenStatus" value="${ankenTorokuForm.ankenStatus}"/>
							<form:hidden path="" id="ankenStatusNmHidden" name="ankenStatusNm" value="${ankenTorokuForm.ankenStatusNm}"/>
						</div>
						<c:if test="${user.gyomuSb == '9'}">
						<div class="form-group  form-inline">
			    			<label class="col-md-2 control-label">荷主</label>
			    			<div class="col-md-5  control-label">
			    				<form:label id="ninushiCdLabel" class="col-md-7 control-label" path="seikyuKsCd">${ankenTorokuForm.seikyuKsCd}</form:label>
			    				<a href="javascript:;" class="btn btn-info btn-sm" id="" onclick="selectNinushi()">選択</a>
			    			</div>
			    			<form:hidden path="seikyuKsCd" id="seikyuKsCdHidden" name="seikyuKsCd"/>
			    			<form:hidden path="seikyuKsNm" id="seikyuKsNmHidden" name="seikyuKsNm"/>
			    			<form:hidden path="seikyuKsAddr" id="seikyuKsAddrHidden" name="seikyuKsAddr"/>
			    			<label class="col-md-2 control-label">荷主名称</label>
			    			<form:label id="ninushiNmLabel" class="col-md-6 control-label" path="seikyuKsNm">${ankenTorokuForm.seikyuKsNm}</form:label>
			    			<label class="col-md-2 control-label">荷主住所</label>
			    			<form:label id="ninushiAddrLabel" class="col-md-5 control-label" path="seikyuKsAddr">${ankenTorokuForm.seikyuKsAddr}</form:label>
			         	</div>
			       	 	<div class="form-group  form-inline">
			    			<label class="col-md-2 control-label" for="textinput">運送</label>
			    			<div class="col-md-5  control-label">
			    				<form:label id="unsoCdLabel" class="col-md-7 control-label" path="shihraiKsCd">${ankenTorokuForm.shihraiKsCd}</form:label>
			    				<a href="javascript:;" class="btn btn-info btn-sm" id="" onclick="selectUnso()">選択</a>
			    			</div>
			    			<form:hidden path="shihraiKsCd" id="shihraiKsCdHidden" name="shihraiKsCd"/>
			    			<form:hidden path="shihraiKsNm" id="shihraiKsNmHidden" name="shihraiKsNm"/>
			    			<form:hidden path="shihraiKsAddr" id="shihraiKsAddrHidden" name="shihraiKsAddr"/>
			    			<label class="col-md-2 control-label">運送会社</label>
			    			<form:label id="unsoNmLabel" class="col-md-6 control-label" path="shihraiKsNm">${ankenTorokuForm.shihraiKsNm}</form:label>
			    			<label class="col-md-2 control-label">運送住所</label>
			    			<form:label id="unsoAddrLabel" class="col-md-5 control-label" path="shihraiKsAddr">${ankenTorokuForm.shihraiKsAddr}</form:label>
			        	</div>
			        	</c:if>
			        	<c:if test="${user.gyomuSb == '0'}">
			        		<form:hidden path="" id="seikyuKsCdHidden" name="seikyuKsCd" value="${user.companyCd}"/>
			        	</c:if>
			        	<form:hidden path="" name="createId" value="${ankenTorokuForm.createId}"/>
						<form:hidden path="" name="createDt" value="${ankenTorokuForm.createDt}"/>
			        	<form:hidden path="" name="updateId" value="${ankenTorokuForm.updateId}"/>
						<form:hidden path="" name="updateDt" value="${ankenTorokuForm.updateDt}"/>
					</div>
					<!-- 基本情報(body) 　▲ -->
				</div>

				<!-- 基本情報 　▲-->

				<div class="row">
					<c:forEach var="item" items="${ankenTorokuForm.syukaList}"
						varStatus="status">
						<div class="col-md-12" id="syuka-panel">
							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<h4 class="panel-title pull-left">積地（集荷）</h4>
									<div class="pull-right">
										<a href="#" class="btn btn-info btn-sm" id="add-syuka1"><span
											class="glyphicon glyphicon-plus"></span> 追加</a> <a href="#"
											class="btn btn-warning btn-sm" id="delete-syuka1"><span
											class="glyphicon glyphicon-remove"></span> 削除</a>
									</div>
								</div>
								<!-- 運送情報(body) 　▼ -->

								<div class="panel-body" name="syukaList">
									<div class="form-group form-inline">
										<!-- 集荷日時 -->
										<label class="col-md-4 control-label" for="textinput" required>集荷日</label>
										<div class="col-md-8">
											<div class="input-group date">
												<input type="tel" class="form-control" size="12"
													id="syuka_date" name="syukaForm.syukaDay" value="${item.syukaDay}"><span
													class="input-group-addon "><i
													class="glyphicon glyphicon-th"></i></span>
											</div>
										</div>
										<label class="col-md-4 control-label" for="textinput" required>集荷時間</label>
										<div class="col-md-6">
											<input type="tel" class="form-control time" id="syuka_time" name="syukaForm.syukaTime" value="${item.syukaTime}" size="6">
										</div>
									</div>

									<!-- 集荷名称 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput" required>集荷先</label>
										<div class="col-md-18">
											<input name="syukaForm.addrNm" type="text" id="addrNm"
												class="form-control input-md" size="35" value="${item.addrNm}">
											<a class="btn btn-info btn-sm" id="hisory-nouhin-0"
												data-toggle="modal" data-target="#pickup_addr_history"
												onclick="getComponets(1,event);">履歴</a>
										</div>

									</div>

									<!-- 集荷場所 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">郵便番号</label>
										<div class="col-md-8">
											<div class="input-group">
												<input type="tel" name="syukaForm.postCode" id="postCode"
													class="form-control postCode" placeholder="000-0000" value="${item.postCode}"
													size="10">
											</div>
											<a class="btn btn-info btn-sm" id="hisory-syuka-0" onclick="getPostCodeInfo(event)">住所</a>
										</div>
										<!-- 都道府県 -->
										<label class="col-md-4 control-label" for="textinput" required>都道府県</label>
										<div class="col-md-8">
											<form:select path="syukaForm.prefNm" name="syukaForm.prefNm" id="prefNm" class="form-control">
												<form:option value="" label=""/>
												<c:forEach items="${ankenTorokuForm.prefList}" var="pref">
	        										<c:choose>
	        											<c:when test="${pref.prefName==item.prefNm}">
	           											<option value="${pref.prefName}" selected>${pref.prefName}</option>
												        </c:when>
												        <c:otherwise>
												            <option value="${pref.prefName}">${pref.prefName}</option>
												        </c:otherwise>
												    </c:choose>
											    </c:forEach>
												<!--<form:options items="${ankenTorokuForm.prefList}" itemValue="prefName" itemLabel="prefName"/>-->
											</form:select>
											<form:hidden path="" name="syukaForm.prefNm"/>
										</div>
									</div>
									<div class="form-group form-inline">
										<!-- 市区町村 -->
										<label class="col-md-4 control-label" for="textinput" required>市区町村</label>
										<div class="col-md-8">
											<input name="syukaForm.cityNm" type="text" id="cityNm"
												class="form-control input-md" size="15" value="${item.cityNm}">
										</div>
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">番地等</label>
										<div class="col-md-8">
											<input name="syukaForm.addrOther" type="text" id="addrOther"
												class="form-control input-md" size="15" value="${item.addrOther}">
										</div>
									</div>
									<!-- 担当者 -->
									<div class="form-group form-inline">
										<!-- 担当者 -->
										<label class="col-md-4 control-label" for="textinput">担当者名</label>
										<div class="col-md-8">
											<input name="syukaForm.tantoNm" type="text"
												class="form-control input-md" size="10" id="tantoNm" value="${item.tantoNm}">
											<a class="btn btn-info btn-sm" id="hisory-syuka-0"
												data-toggle="modal" data-target="#pickup_contr_history"
												onclick="getComponets(3,event);">履歴</a>
										</div>
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">担当TEL</label>
										<div class="col-md-8">
											<input name="syukaForm.tantoTel" type="tel" id="tantoTel"
												class="form-control input-md tel" size="15" value="${item.tantoTel}">
										</div>
									</div>
									<div class="form-group form-inline">
										<!-- 荷種 -->
										<label class="col-md-4 control-label" for="selectbasic">荷種</label>
										<div class="col-md-8">
											<form:select path="syukaForm.nisyuCd" id="nisyuCd" class="form-control">
												<c:forEach items="${ankenTorokuForm.nisyuList}" var="nisyu">
	        										<c:choose>
	        											<c:when test="${nisyu.nisyuCd==item.nisyuCd}">
	           											<option value="${nisyu.nisyuCd}" selected>${nisyu.nisyuNm}</option>
												        </c:when>
												        <c:otherwise>
												            <option value="${nisyu.nisyuCd}">${nisyu.nisyuNm}</option>
												        </c:otherwise>
												    </c:choose>
											    </c:forEach>

												<!--<form:options items="${ankenTorokuForm.nisyuList}" itemValue="nisyuCd" itemLabel="nisyuNm"/>-->
											</form:select>
											<form:hidden path="" name="syukaForm.nisyuCd"/>
											<form:hidden path="" name="syukaForm.nisyuNm"/>
										</div>
										<!-- 荷種 -->

										<!-- 荷姿 -->
										<label class="col-md-4 control-label" for="textinput" required>荷姿</label>
										<div class="col-md-8">
											<form:select path="syukaForm.nisugataCd" id="nisugataCd" class="form-control">
												<form:option value="" label=""/>
												<c:forEach items="${ankenTorokuForm.nisugateList}" var="nisugate">
	        										<c:choose>
	        											<c:when test="${nisugate.nisugataCd==item.nisugataCd}">
	           											<option value="${nisugate.nisugataCd}" selected>${nisugate.nisugataNm}</option>
												        </c:when>
												        <c:otherwise>
												            <option value="${nisugate.nisugataCd}">${nisugate.nisugataNm}</option>
												        </c:otherwise>
												    </c:choose>
											    </c:forEach>

												<!--<form:options items="${ankenTorokuForm.nisugateList}" itemValue="nisugataCd" itemLabel="nisugataNm"/>-->
											</form:select>
											<form:hidden path="" name="syukaForm.nisugataCd"/>
											<form:hidden path="" name="syukaForm.nisugataNm"/>
										</div>
									</div>
									<div class="form-group form-inline">
										<!-- 個数 -->
										<label class="col-md-4 control-label" for="textinput">個数</label>
										<div class="col-md-8">
											<input name="syukaForm.amount" type="tel"
												class="form-control input-md number" size="6" value="${item.amount}">
										</div>
										<!-- 重量 -->
										<label class="col-md-4 control-label" for="textinput">重量</label>
										<div class="col-md-8">
											<div class="input-group">
												<input type="tel" name="syukaForm.weight" id="weight"
													class="form-control number" size="6" placeholder="" value="${item.weight}">
												<span class="input-group-addon">ｔ</span>
											</div>
										</div>
									</div>
									<!--備考 -->
									<div class="form-group form-inline">
										<!-- 備考 -->
										<label class="col-md-4 control-label" for="textinput">備考</label>
										<div class="col-md-20">
											<textarea class="form-control" id="remarks" name="syukaForm.remarks"
												cols="55" rows="3">${item.remarks}</textarea>
										</div>
									</div>
								</div>
								<!-- 集荷情報(body) 　▲ -->
							</div>
						</div>
						</c:forEach>


						<c:forEach var="item" items="${ankenTorokuForm.nohinList}"
							varStatus="status">
						<div class="col-md-12" id="nouhin-panel">
							<!-- 納品情報 　▲-->
							<div class="panel panel-info">
								<div class="panel-heading clearfix">
									<h4 class="panel-title pull-left">納品（届先）</h4>
									<div class="pull-right">
										<a href="#" class="btn btn-info btn-sm" id="add-nouhin"><span
											class="glyphicon glyphicon-plus"></span> 追加</a> <a href="#"
											class="btn btn-warning btn-sm" id="delete-nouhin"><span
											class="glyphicon glyphicon-remove"></span> 削除</a>
									</div>
								</div>
								<!-- 運送情報(body) 　▼ -->
								<div class="panel-body">
									<div class="form-group form-inline">
										<!-- 集荷日時 -->
										<label class="col-md-4 control-label" for="textinput" required>納品日</label>
										<div class="col-md-8">
											<div class="input-group date">
												<input name="nohinForm.nohinDay" id="nouhin_date"
													type="tel" class="form-control" size="12" value="${item.nohinDay}">
												<span class="input-group-addon "><i
													class="glyphicon glyphicon-th"></i></span>
											</div>
										</div>
										<label class="col-md-4 control-label" for="textinput" required>納品時間</label>
										<div class="col-md-6">
											<input type="tel" class="form-control time" id="nohin_time" name="nohinForm.nohinTime" size="6" value="${item.nohinTime}">
										</div>
									</div>

									<!-- 納品名称 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput" required>納品先</label>
										<div class="col-md-18">
											<input name="nohinForm.addrNm" type="text" id="addrNm"
												class="form-control input-md" size="35" value="${item.addrNm}"> <a
												class="btn btn-info btn-sm" id="hisory-nouhin-0"
												data-toggle="modal" data-target="#recipient_addr_history"
												onclick="getComponets(2,event);">履歴</a>
										</div>
									</div>

									<!-- 納品場所 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">郵便番号</label>
										<div class="col-md-5">
											<div class="input-group">
												<input type="tel" name="nohinForm.postCode" id="postCode"
													class="form-control postCode" placeholder="000-0000" value="${item.postCode}">
											</div>
										</div>
										<div class="col-md-3">
											<a class="btn btn-info btn-sm" id="hisory-syuka" onclick="getPostCodeInfo(event)">住所</a>
										</div>
										<!-- 都道府県 -->
										<label class="col-md-4 control-label" for="textinput" required>都道府県</label>
										<div class="col-md-8">
											<form:select path="nohinForm.prefNm" name="nohinForm.prefNm" id="prefNm" class="form-control">
												<form:option value="" label=""/>
												<c:forEach items="${ankenTorokuForm.prefList}" var="pref">
	        										<c:choose>
	        											<c:when test="${pref.prefName==item.prefNm}">
	           											<option value="${pref.prefName}" selected>${pref.prefName}</option>
												        </c:when>
												        <c:otherwise>
												            <option value="${pref.prefName}">${pref.prefName}</option>
												        </c:otherwise>
												    </c:choose>
											    </c:forEach>

												<!--<form:options items="${ankenTorokuForm.prefList}" itemValue="prefName" itemLabel="prefName"/>-->
											</form:select>
											<form:hidden path="" name="nohinForm.prefNm"/>
										</div>
									</div>
									<div class="form-group form-inline">
										<!-- 市区町村 -->
										<label class="col-md-4 control-label" for="textinput" required>市区町村</label>
										<div class="col-md-8">
											<input name="nohinForm.cityNm" type="text" id="cityNm"
												class="form-control input-md" size="15" value="${item.cityNm}">
										</div>
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">番地等</label>
										<div class="col-md-8">
											<input name="nohinForm.addrOther" type="text" id="addrOther"
												class="form-control input-md" size="15" value="${item.addrOther}">
										</div>
									</div>
									<!-- 担当者 -->
									<div class="form-group form-inline">
										<!-- 担当者 -->
										<label class="col-md-4 control-label" for="textinput">担当者名</label>
										<div class="col-md-8">
											<input name="nohinForm.tantoNm" type="text"
												class="form-control input-md" size="10" id="tantoNm" value="${item.tantoNm}">
											<a class="btn btn-info btn-sm" id="hisory-nouhin-1"
												data-toggle="modal" data-target="#recipient_contr_history"
												onclick="getComponets(4,event);">履歴</a>
										</div>

										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">担当TEL</label>
										<div class="col-md-8">
											<input name="nohinForm.tantoTel" type="tel" id="tantoTel"
												class="form-control input-md tel" size="15" value="${item.tantoTel}">
										</div>
									</div>
									<div class="form-group form-inline">
										<!-- 荷種 -->
										<label class="col-md-4 control-label" for="selectbasic">荷種</label>
										<div class="col-md-8">
											<form:select path="nohinForm.nisyuCd" id="nisyuCd" class="form-control">
												<c:forEach items="${ankenTorokuForm.nisyuList}" var="nisyu">
	        										<c:choose>
	        											<c:when test="${nisyu.nisyuCd==item.nisyuCd}">
	           											<option value="${nisyu.nisyuCd}" selected>${nisyu.nisyuNm}</option>
												        </c:when>
												        <c:otherwise>
												            <option value="${nisyu.nisyuCd}">${nisyu.nisyuNm}</option>
												        </c:otherwise>
												    </c:choose>
											    </c:forEach>

												<!--<form:options items="${ankenTorokuForm.nisyuList}" itemValue="nisyuCd" itemLabel="nisyuNm"/>-->
											</form:select>
											<form:hidden path="" name="nohinForm.nisyuCd"/>
											<form:hidden path="" name="nohinForm.nisyuNm"/>
										</div>
										<!-- 荷種 -->
										<!-- 荷姿 -->
										<label class="col-md-4 control-label" for="textinput" required>荷姿</label>
										<div class="col-md-8">
											<form:select path="nohinForm.nisugataCd" id="nisugataCd" class="form-control">
												<form:option value="" label=""/>
												<c:forEach items="${ankenTorokuForm.nisugateList}" var="nisugate">
	        										<c:choose>
	        											<c:when test="${nisugate.nisugataCd==item.nisugataCd}">
	           											<option value="${nisugate.nisugataCd}" selected>${nisugate.nisugataNm}</option>
												        </c:when>
												        <c:otherwise>
												            <option value="${nisugate.nisugataCd}">${nisugate.nisugataNm}</option>
												        </c:otherwise>
												    </c:choose>
											    </c:forEach>

												<!--<form:options items="${ankenTorokuForm.nisugateList}" itemValue="nisugataCd" itemLabel="nisugataNm"/>-->
											</form:select>
											<form:hidden path="" name="nohinForm.nisugataCd"/>
											<form:hidden path="" name="nohinForm.nisugataNm"/>
										</div>
									</div>
									<div class="form-group form-inline">
										<!-- 個数 -->
										<label class="col-md-4 control-label" for="textinput">個数</label>
										<div class="col-md-8">
											<input name="nohinForm.amount" type="tel"
												class="form-control input-md number" size="6" value="${item.amount}">
										</div>
										<!-- 重量 -->
										<label class="col-md-4 control-label" for="textinput">重量</label>
										<div class="col-md-8">
											<div class="input-group">
												<input type="tel" name="nohinForm.weight" id="weight"
													class="form-control number" size="6" placeholder="" value="${item.weight}">
												<span class="input-group-addon">ｔ</span>
											</div>
										</div>
									</div>
									<!--備考 -->
									<div class="form-group form-inline">
										<!-- 備考 -->
										<label class="col-md-4 control-label" for="textinput">備考</label>
										<div class="col-md-20">
											<textarea class="form-control" name="nohinForm.remarks"
												cols="55" rows="3" id="remarks">${item.remarks}</textarea>
										</div>
									</div>
								</div>
								<!-- 運送情報(body) 　▲ -->
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- トラック情報 ▲ -->
				<c:forEach var="item" items="${ankenTorokuForm.truckList}"
					varStatus="status">
				<div id="truck-panel" class="panel panel-default">
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">トラック</h4>
						<div class="pull-right">
							<a href="#" class="btn btn-info btn-sm" id="add-truck-0"><span
								class="glyphicon glyphicon-plus"></span>追加</a> <a href="#"
								class="btn btn-warning btn-sm" id="delete-truck10"><span
								class="glyphicon glyphicon-remove"></span> 削除</a>
						</div>
					</div>
					<!-- トラック情報(body) 　▼ -->
					<div class="panel-body">
						<div class="form-group form-inline">
							<label class="col-md-1 control-label" for="textinput" required>予算</label>
							<div class="col-md-5">
								<div class="input-group">
									<input name="truckForm.yosanMn" type="tel" id="yosanMn"
										class="form-control input-md money" size="11" value="${item.yosanMn}">
									<span class="input-group-addon">円</span>
								</div>
							</div>
							<label class="col-md-3 control-label" for="radios">高速使用料</label>
							<div class="col-md-3">
								<select id="kosokuKbn" name="select_kaosoku"
									class="form-control ">

									<c:choose>
										<c:when test="${'0'==item.kosokuKbn || '込み'==item.kosokuKbn || item.kosokuKbn == null}">
											<option value="0" selected>込み</option>
											<option value="1">別料金</option>
 											<!-- <option value="${nisugateList.nisugataCd}" selected>${nisugateList.nisugataNm}</option> -->
								        </c:when>
								        <c:otherwise>
								            <option value="0">込み</option>
											<option value="1" selected>別料金</option>
								        </c:otherwise>
								    </c:choose>

									<!-- <option value="0" selected>込み</option>
									<option value="1">別料金</option> -->
								</select>
								<form:hidden path="" name="truckForm.kosokuKbn"/>
								<form:hidden path="" name="truckForm.kosokuKbnNm"/>
							</div>
							<label class="col-md-4 control-label" for="radios">燃料サーチャージ</label>
							<div class="col-md-3">
								<select id="nenryoscKbn" name="select_nenryo"
									class="form-control ">
									<c:choose>
										<c:when test="${'0'==item.nenryoscKbn || '込み'==item.nenryoscKbn || item.nenryoscKbn==null}">
											<option value="0" selected>込み</option>
											<option value="1">別料金</option>
 											<!-- <option value="${nisugateList.nisugataCd}" selected>${nisugateList.nisugataNm}</option> -->
								        </c:when>
								        <c:otherwise>
								            <option value="0">込み</option>
											<option value="1" selected>別料金</option>
								        </c:otherwise>
								    </c:choose>
									<!-- <option value="0">込み</option>
									<option value="1">別料金</option>-->
								</select>
								<form:hidden path="" name="truckForm.nenryoscKbn"/>
								<form:hidden path="" name="truckForm.nenryoscKbnNm"/>
							</div>
							<div class="col-md-4">
								<c:choose>
									<c:when test="${item.nenryoscKbn=='1' || item.nenryoscKbn=='別料金' || item.nenryoscKbn==null}">
										<div class="input-group" id="nenryo_val">
											<input type="tel" id="nenryoscMn" name="truckForm.nenryoscMn" class="form-control input-md money" size="11"
												value="${item.nenryoscMn}"> <span class="input-group-addon">円</span>
										</div>
									</c:when>
									<c:otherwise>
										<div class="input-group" id="nenryo_val" style="display: none">
											<input type="tel" id="nenryoscMn" name="truckForm.nenryoscMn" class="form-control input-md money" size="11"
												value="${item.nenryoscMn}"> <span class="input-group-addon">円</span>
										</div>
									</c:otherwise>
								</c:choose>
							</div>

						</div>
						<div class="form-group form-inline">
							<label class="col-md-1 control-label" for="selectbasic" required>車種</label>
							<div class="col-md-3">
								<select name="select_syasyu" id="syasyuCd" class="form-control">

									<option value="" label="-"/>
									<c:forEach items="${ankenTorokuForm.syasyuList}" var="syasyu">
      										<c:choose>
      											<c:when test="${syasyu.syasyuCd==item.syasyuCd}">
         											<option value="${syasyu.syasyuCd}" selected>${syasyu.syasyuName}</option>
									        </c:when>
									        <c:otherwise>
									            <option value="${syasyu.syasyuCd}">${syasyu.syasyuName}</option>
									        </c:otherwise>
									    </c:choose>
								    </c:forEach>

									<!-- <option value="0">-</option>
									<option value="1">2t</option>
									<option value="2">2t/S</option>
									<option value="3">2t/L</option>
									<option value="4">3t</option>
									<option value="5">4t</option>
									<option value="6">4t/W</option>
									<option value="7">4t/L</option>
									<option value="8">中型</option>
									<option value="9">10t</option>
									<option value="10">10t/W</option>
									<option value="11">13t</option> -->
								</select>
							</div>
							<form:hidden path="" name="truckForm.syasyuCd"/>
							<form:hidden path="" name="truckForm.syasyuNm"/>

							<label class="col-md-1 control-label" for="select_daisu" required>台数</label>
							<div class="col-md-2">
								<select name="select_daisu" id="daisu" class="form-control ">
									<option value="1" <c:if test="${item.daisu=='1'}">selected</c:if>>1</option>
									<option value="2" <c:if test="${item.daisu=='2'}">selected</c:if>>2</option>
									<option value="3" <c:if test="${item.daisu=='3'}">selected</c:if>>3</option>
									<option value="4" <c:if test="${item.daisu=='4'}">selected</c:if>>4</option>
									<option value="5" <c:if test="${item.daisu=='5'}">selected</c:if>>5</option>
									<option value="6" <c:if test="${item.daisu=='6'}">selected</c:if>>6</option>
									<option value="7" <c:if test="${item.daisu=='7'}">selected</c:if>>7</option>
									<option value="8" <c:if test="${item.daisu=='8'}">selected</c:if>>8</option>
									<option value="9" <c:if test="${item.daisu=='9'}">selected</c:if>>9</option>
									<option value="10" <c:if test="${item.daisu=='10'}">selected</c:if>>10</option>
								</select>
							</div>
							<form:hidden path="" name="truckForm.daisu"/>

							<label class="col-md-3 control-label" for="checkboxes">オプション</label>
							<div class="col-md-14">
								<c:set var="opList" value="${item.opList}" />
								<c:forEach items="${ankenTorokuForm.truckOpList}" var="truckOp" >
									<c:choose>
										<c:when test="${fn:contains(opList, truckOp.opCd) || fn:contains(opList, truckOp.opName)}">
 											<label class="checkbox-inline">
 												<input type="checkbox" name="checkboxes" value="${truckOp.opCd}" checked>
												${truckOp.opName}
											</label>
								        </c:when>
								        <c:otherwise>
								            <label class="checkbox-inline">
 												<input type="checkbox" name="checkboxes"  value="${truckOp.opCd}">
												${truckOp.opName}
											</label>
								        </c:otherwise>
								    </c:choose>
								</c:forEach>

								<!-- <label class="checkbox-inline" for="checkboxes-4"> <input
									type="checkbox" name="checkboxes" id="checkboxes-4" value="1">
									ゲート
								</label> <label class="checkbox-inline" for="checkboxes-5"> <input
									type="checkbox" name="checkboxes" id="checkboxes-5" value="2">
									ワイド
								</label> <label class="checkbox-inline" for="checkboxes-6"> <input
									type="checkbox" name="checkboxes" id="checkboxes-6" value="3">
									エア
								</label> <label class="checkbox-inline" for="checkboxes-1"> <input
									type="checkbox" name="checkboxes" id="checkboxes-1" value="4">
									箱
								</label> <label class="checkbox-inline" for="checkboxes-0"> <input
									type="checkbox" name="checkboxes" id="checkboxes-0" value="5">
									平
								</label> <label class="checkbox-inline" for="checkboxes-8"> <input
									type="checkbox" name="checkboxes" id="checkboxes-8" value="6">
									冷凍
								</label> <label class="checkbox-inline" for="checkboxes-7"> <input
									type="checkbox" name="checkboxes" id="checkboxes-7" value="7">
									冷蔵
								</label> <label class="checkbox-inline" for="checkboxes-3"> <input
									type="checkbox" name="checkboxes" id="checkboxes-3" value="8">
									ユニック
								</label> -->
							</div>
							<form:hidden path="" name="truckForm.opList"/>
							<form:hidden path="" name="truckForm.opNmList"/>
						</div>
						<c:if test="${user.gyomuSb != '0'}">
						<div class="form-group form-inline">
				          <label class="col-md-1 control-label" for="textinput">受注</label>
					          <div class="col-md-5">
					      			<div class="input-group">
					      			<input name="truckForm.orderMn" id="orderMn" value="${item.orderMn}" type="tel" class="form-control input-md money" size="11">
					      			<span class="input-group-addon">円</span></div>
					          </div>
				        </div>
				        </c:if>
					</div>
					<!-- トラック情報(body) 　▲ -->

				</div>
				</c:forEach>
				<!-- トラック情報 ▲ -->

				<!-- 補足情報(body)-->
				<div class="panel panel-default">
					<!-- 補足情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">補足情報</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-10">
								<div class="form-group form-inline">
									<label class="col-md-4 control-label" for="textinput">保険金額</label>
									<div class="col-md-10">
										<div class="input-group">
											<input name="hokenMn" type="tel" id="hokenMn"
												class="form-control input-md money" size="10" value="${ankenTorokuForm.hokenMn}"><span
												class="input-group-addon">円</span>
										</div>
									</div>
								</div>
								<!--注意事項 ▼-->
								<div class="form-group form-inline">
									<!-- 注意事項-->
									<label class="col-md-4 control-label" for="textinput">注意事項</label>
									<div class="col-md-10">
										<textarea class="form-control" id="tyuiJk" name="tyuiJk"
											cols="45" rows="6">${ankenTorokuForm.tyuiJk}</textarea>
									</div>
								</div>
								<!--注意事項 ▲-->
							</div>
							<div class="col-md-14 section">
								<input id="currPic" type="hidden" name="currPic">
								<div class="form-group form-inline">
									<label class="col-md-5 control-label" for="radios">画像情報</label>
								</div>
								<ul class="clearfix">
									<li>
										<div class="photo">
									<c:choose>
										<c:when test="${ankenTorokuForm.picNm1 != null && ankenTorokuForm.picNm1 != ''}">
											<img id="picImg1" src="${sessionScope.anken_pic_url_tmp}/${ankenTorokuForm.picTmpNm1}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg1" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
										</div>
										<div class="rsp"></div>
										<form:hidden path="" id="picNm1" name="picNm1" value="${ankenTorokuForm.picNm1}"/>
										<form:hidden path="" id="picTmpNm1" name="picTmpNm1" value="${ankenTorokuForm.picTmpNm1}"/>
										<form:hidden path="" id="picChg1" name="picChg1" value="${ankenTorokuForm.picChg1}"/>
										<div class="text"></div>
									</li>
									<li>
										<div class="photo">
									<c:choose>
										<c:when test="${ankenTorokuForm.picNm2 != null && ankenTorokuForm.picNm2 != ''}">
											<img id="picImg2" src="${sessionScope.anken_pic_url_tmp}/${ankenTorokuForm.picTmpNm2}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg2" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
										</div>
										<div class="rsp"></div>
										<form:hidden path="" id="picNm2" name="picNm2" value="${ankenTorokuForm.picNm2}"/>
										<form:hidden path="" id="picTmpNm2" name="picTmpNm2" value="${ankenTorokuForm.picTmpNm2}"/>
										<form:hidden path="" id="picChg2" name="picChg2" value="${ankenTorokuForm.picChg2}"/>
										<div class="text"></div>
									</li>
									<li>
										<div class="photo">
									<c:choose>
										<c:when test="${ankenTorokuForm.picNm3 != null && ankenTorokuForm.picNm3 != ''}">
											<img id="picImg3" src="${sessionScope.anken_pic_url_tmp}/${ankenTorokuForm.picTmpNm3}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg3" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
										</div>
										<div class="rsp"></div>
										<form:hidden path="" id="picNm3" name="picNm3" value="${ankenTorokuForm.picNm3}"/>
										<form:hidden path="" id="picTmpNm3" name="picTmpNm3" value="${ankenTorokuForm.picTmpNm3}"/>
										<form:hidden path="" id="picChg3" name="picChg3" value="${ankenTorokuForm.picChg3}"/>
										<div class="text"></div>
									</li>
								</ul>

								<div class="clear"></div>
							</div>

						</div>
					</div>
					<!-- 補足情報(body) 　▲ -->
				</div>
				<!-- 補足情報 　▲-->

				<!-- ボタン 　▼-->
				<div class="row" style="padding: 15px 15px 15px 15px; clear: left;">
					<button type="button" id="btn_confirm"
						class="btn btn-primary col-md-offset-17">確 認</button>

						<c:choose>
							<c:when test="${requestScope.updateFlag == true}">
								<a href="javascript:void(0);" onclick="upCancel()" class="btn btn-default col-md-offset-1"> キャンセル </a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="inCancel()" class="btn btn-default col-md-offset-1"> キャンセル </a>
							</c:otherwise>
						</c:choose>
				</div>
				<!-- ボタン 　▲-->

				<c:if test="${user.gyomuSb == '9'}">
				<!-- memberlist popup 　▼-->
					<div id="memListDialog" style="z-index:19891020;overflow:visible;position:fixed;pointer-events:auto;display:none">
						<iframe id="memIframe" name="memIframe" scrolling="auto" allowtransparency="true" frameborder="0" src="" style="width:100%; display:block"></iframe>
					</div>
					<script type="text/javascript">
						var medialog = {}, win = $(window), timer;
						medialog.elem = $('#memListDialog');
						medialog.style = function(options) {
							var dialogo = medialog.elem;
							var titHeight = dialogo.find(".dialog-title").outerHeight() || 0;
							var btnHeight = dialogo.find(".dialog-btn-nav").outerHeight() || 0;
							dialogo.css(options);
							dialogo.find('iframe').css({
								height: parseFloat(options.height) - titHeight - btnHeight
							});
						};

						function closeMemListDialog() {
							medialog.elem.hide();
							window.frames["memIframe"].document.write("");

							if (timer) {
								clearInterval(timer);
							}
						}

						function selectNinushi() {
							var opt = {
								url: '<%=request.getContextPath()%>/memberList?selType=0'
							};
							showDialog(opt);
						}

						function setNinushi(companyCd, customCd, compayNm, officeNm, prefNm, cityNm) {
							$("#ninushiCdLabel").html(companyCd);
							$("#seikyuKsCdHidden").val(companyCd);
							$("#seikyuKsNmHidden").val(compayNm + " " + officeNm);
							$("#seikyuKsAddrHidden").val(prefNm + cityNm);
							$("#ninushiNmLabel").html(compayNm + " " + officeNm);
							$("#ninushiAddrLabel").html(prefNm + cityNm);

							closeMemListDialog()
						}

						function selectUnso() {
							var opt = {
								url: '<%=request.getContextPath()%>/memberList?selType=1'
							};
							showDialog(opt);
						}

						function setUnso(companyCd, customCd, compayNm, officeNm, prefNm, cityNm) {
							$("#unsoCdLabel").html(companyCd);
							$("#shihraiKsCdHidden").val(companyCd);
							$("#shihraiKsNmHidden").val(compayNm + " " + officeNm);
							$("#shihraiKsAddrHidden").val(prefNm + cityNm);
							$("#unsoNmLabel").html(compayNm + " " + officeNm);
							$("#unsoAddrLabel").html(prefNm + cityNm);

							closeMemListDialog()
						}

						function showDialog(opt) {
							if (timer) {
								clearInterval(timer);
							}
							var dialogo = medialog.elem;
							dialogo.find('iframe').attr("src", "");
							dialogo.find('iframe').attr("src", opt.url);

							timer = self.setInterval(function(){
								medialog.style({
									top: 0,
									left: 0,
									width: win.width(),
									height: win.height()
								});
							}, 100);

							dialogo.show();
						}

					</script>
				<!-- memberlist popup 　▲-->
				</c:if>

			</fieldset>
		</form:form>
		<!-- フォーム　▲-->

		<iframe id="upl-iframe" name="upl-iframe" src="" style="display: none"></iframe>

		<script type="text/javascript">
			$(function(){
				$('.input-group.date').datepicker({
				  format : 'yyyy/mm/dd (D)',

				  startDate: "2015/5/19",
				  language: 'ja',
				  orientation: 'top left',
				  autoclose: true
				});

				$(".section ul li .rsp").hide();

				$(".section ul li").hover(function(){
					$(".section").find(".text").html('');
					var that = $(this);
					var picNm = that.find(":input[id^='picNm']").val();
					if (picNm == '') {
						that.find(".text").html('<a class="btn btn-small btn-default">アップロード</a><input type="file" name="picFile" class="btn btn-large img_file" onchange="uploadImg(this)"/>');
					} else {
						that.find(".text").html('<a class="btn btn-small btn-danger" onclick="delImg(this)">削除</a>');
					}
					that.find(".rsp").stop().fadeTo(500, 0.5);
					that.find(".text").stop().animate({left:'0'}, {duration: 500});
				},function(){
					var that = $(this);
					that.find(".rsp").stop().fadeTo(500, 0);
					that.find(".text").stop().animate({left:'100'}, {duration: "fast"});
					that.find(".text").animate({left:'-142'}, {duration: 0});
				});
				//initPics();
			});

			function showPics(idx) {
				var ankenId = '${ankenTorokuForm.ankenId}';
				var picNm = $("#picNm" + idx).val();
				var picTmpNm = $("#picTmpNm" + idx).val();
				var picChg = $("#picChg" + idx).val();
				if (picChg == 2) {
					$("#picImg" + idx).attr("src", '${sessionScope.anken_pic_url_tmp}/noPicture.jpg');
				} else if (picChg == 1) {
					$("#picImg" + idx).attr("src", '${sessionScope.anken_pic_url_tmp}/' + picTmpNm);
				} else {
					var updateFlag = $("#updateFlag").val();
					if (updateFlag == 'true') {
						$("#picImg" + idx).attr("src", '${sessionScope.anken_pic_url_tmp}/' + picTmpNm);
					} else {
						$("#picImg" + idx).attr("src", '${sessionScope.anken_pic_url_tmp}/noPicture.jpg');
					}
				}
			}

			var _picidx = 1;
			function initPics() {
				showPics(_picidx);
				if (_picidx < 3) {
					_picidx ++;
					setTimeout("initPics()", 200);
				}
			}

			function uploadImg(elem) {
				var hid = $(elem).parent().parent().find(":input[id^='picNm']").attr("id");
				var idx = hid.substring(hid.length - 1);
				$("#currPic").val(idx);

				$("#ankenTorokuForm").attr("enctype", "multipart/form-data");
				$("#ankenTorokuForm").attr("action", "ankenUploadPic");
				$("#ankenTorokuForm").attr("target", "upl-iframe");
				$("#ankenTorokuForm").submit();
			};

			function setImg(data) {
				$("#ankenTorokuForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#ankenTorokuForm").attr("action", "ankenToroku");
				$("#ankenTorokuForm").attr("target", "_self");

				if (data.resCd == '1') {
					var ankenId = '${ankenTorokuForm.ankenId}';
					var idx = data.idx;
					$("#picImg" + idx).attr("src", '${sessionScope.anken_pic_url_tmp}/' + data.picTmpNm);
					$("#picNm" + idx).val(data.picNm);
					$("#picTmpNm" + idx).val(data.picTmpNm);
					$("#picChg" + idx).val('1');
				} else {
					alert("アップロードが失敗しました。再度やり直してください。");
				}
			};

			function delImg(elem) {
				var hid = $(elem).parent().parent().find(":input[id^='picNm']").attr("id");
				var idx = hid.substring(hid.length - 1);
				$("#picImg" + idx).attr("src", '${sessionScope.anken_pic_url_tmp}/noPicture.jpg');
				$("#picNm" + idx).val('');
				$("#picChg" + idx).val('2');
			};
		</script>
	</div>
</body>
</html>
