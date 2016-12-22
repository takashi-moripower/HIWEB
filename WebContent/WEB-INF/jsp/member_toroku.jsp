<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>

<script type="text/javascript">
	$(function() {

		var success = eval("${success}");
		if (success) {
			alert("登録が成功しました。");
			window.location.href = "index";
		}

		$(document).on('click', '#btn_toroku', function() {
			if (confirm('この内容で登録します。よろしいでしょうか？')) {
				$("form").submit();
			}
		});

	});

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
		        var prefNm = data.prefNm;
		        var cityNm = data.cityNm;
		        var addrOther = data.addrOther;
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
</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		<!-- フォーム　▼ -->
  <form:form class="form-horizontal" id="memberTorokuForm" name="memberTorokuForm" commandName="memberTorokuForm" action="submitMember" method="POST">
  	<input type="hidden" name="member.updateDt" value="${memberTorokuForm.member.updateDt}" />
    <fieldset>
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
  	<div class="panel panel-default">
      <!-- 基本情報(body) 　▼ -->
      <div class="panel-heading clearfix">
        <h4 class="panel-title pull-left" >基本情報</h4>
      </div>
      <div class="panel-body">
      	<input name="member.companyCd" type="hidden" value="${memberTorokuForm.member.companyCd}"/>
      <c:choose>
      	<c:when test="${user.gyomuSb == '9'}">
	        <div class="form-group  form-inline">
      		<c:choose>
	        	<c:when test="${memberTorokuForm.member.companyCd == null || memberTorokuForm.member.companyCd == ''}">
				<label class="col-md-3 control-label" for="textinput" required>得意先コード</label>
				<div class="col-md-5"><input name="member.customCd" type="tel" class="form-control input-md" size="06" maxlength="4" value="${memberTorokuForm.member.customCd}"></div>
	        	</c:when>
	        	<c:otherwise>
				<label class="col-md-3 control-label" >得意先コード</label>
				<div class="col-md-5  control-label">${memberTorokuForm.member.customCd}</div>
				<input name="member.customCd" type="hidden" value="${memberTorokuForm.member.customCd}"/>
	        	</c:otherwise>
        	</c:choose>
				<label class="col-md-3 control-label" for="textinput">得意先名称</label>
				<div class="col-md-5"><input name="member.companyNm" type="text" class="form-control input-md" size="30" maxlength="20" value="${memberTorokuForm.member.companyNm}"></div>
	        </div>
	        <div class="form-group  form-inline">
      		<c:choose>
	        	<c:when test="${memberTorokuForm.member.companyCd == null || memberTorokuForm.member.companyCd == ''}">
				<label class="col-md-3 control-label" for="textinput" required>事業所コード</label>
				<div class="col-md-5"><input name="member.officeCd" type="tel" class="form-control input-md" size="06" maxlength="2" value="${memberTorokuForm.member.officeCd}"></div>
	        	</c:when>
	        	<c:otherwise>
				<label class="col-md-3 control-label">事業所コード</label>
				<div class="col-md-5 control-label">${memberTorokuForm.member.officeCd}</div>
				<input name="member.officeCd" type="hidden" value="${memberTorokuForm.member.officeCd}"/>
	        	</c:otherwise>
        	</c:choose>
				<label class="col-md-3 control-label" for="textinput">事業所名称</label>
				<div class="col-md-5"><input name="member.officeNm" type="text" class="form-control input-md" size="30" maxlength="20" value="${memberTorokuForm.member.officeNm}"></div>
	        </div>
	        <div class="form-group  form-inline">
	            <label class="col-md-3 control-label" for="textinput">契約日</label>
	            <div class="col-md-5"><div class="input-group date">
	              <input type="text" class="form-control" size="12" name="member.keiyakuDayDis" maxlength="14" value="${memberTorokuForm.member.keiyakuDayDis}">
	              <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
			     </div></div>
	            <label class="col-md-3 control-label" for="textinput">契約期間</label>
	            <div class="col-md-5"><div class="input-group date">
	              <input type="text" class="form-control" size="12" name="member.keiyakuKgDayDis" maxlength="14" value="${memberTorokuForm.member.keiyakuKgDayDis}">
	              <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
			     </div></div>
	            <label class="col-md-3 control-label" for="textinput">利用端末数</label>
				<div class="col-md-5"><input type="tel" class="form-control input-md" size="06" maxlength="11" name="member.riyoTm" value="${memberTorokuForm.member.riyoTm}"></div>
	        </div>
	        <div class="form-group  form-inline">
      		<c:choose>
	        	<c:when test="${memberTorokuForm.member.companyCd == null || memberTorokuForm.member.companyCd == ''}">
		          <label class="col-md-3 control-label" for="radios" required>業務種別</label>
		          <div class="col-md-5">
		            <label class="radio-inline" for="radios-0">
		            <input type="radio" name="member.gyomuSb" id="radios-0" value="0" <c:if test="${memberTorokuForm.member.gyomuSb == '0' || (memberTorokuForm.member.gyomuSb != '0' && memberTorokuForm.member.gyomuSb != '1' && memberTorokuForm.member.gyomuSb != '9')}">checked="checked"</c:if>>
		            荷主 </label>
		            <label class="radio-inline" for="radios-1">
		            <input type="radio" name="member.gyomuSb" id="radios-1" value="1" <c:if test="${memberTorokuForm.member.gyomuSb == '1'}">checked="checked"</c:if>>
		            運送会社 </label>

		            <c:if test="${user.gyomuSb == '9'}">
		            <label class="radio-inline" for="radios-3">
		            <input type="radio" name="member.gyomuSb" id="radios-3" value="9" <c:if test="${memberTorokuForm.member.gyomuSb == '9'}">checked="checked"</c:if>>
		           管理者 </label>
		            </c:if>
		          </div>
	        	</c:when>
	        	<c:otherwise>
		          <label class="col-md-3 control-label" for="radios">業務種別</label>
		          <div class="col-md-5">
		          	<c:if test="${memberTorokuForm.member.gyomuSb == '0'}">
						<label class="radio-inline">荷主 </label>
		            </c:if>
		          	<c:if test="${memberTorokuForm.member.gyomuSb == '1'}">
						<label class="radio-inline">運送会社 </label>
		            </c:if>
		          	<c:if test="${memberTorokuForm.member.gyomuSb == '9'}">
						<label class="radio-inline">管理者 </label>
		            </c:if>
		          <input name="member.gyomuSb" type="hidden" value="${memberTorokuForm.member.gyomuSb}"/>
		          </div>
	        	</c:otherwise>
        	</c:choose>
			  <label class="col-md-3 control-label" for="textinput">料率</label>
			  <div class="col-md-5">
				<input name="member.ryoritu" type="tel" class="form-control input-md" size="15" maxlength="2" value="${memberTorokuForm.member.ryoritu}">
			  </div>
			  <label class="col-md-3 control-label" for="textinput">保険金額</label>
			  <div class="col-md-5">
				<input name="member.hokenMn" type="tel" class="form-control input-md" size="15" maxlength="11" value="${memberTorokuForm.member.hokenMn}">
			  </div>
	        </div>
        </c:when>
        <c:otherwise>
	        <div class="form-group  form-inline">
				<label class="col-md-3 control-label">得意先コード</label>
				<div class="col-md-5  control-label">${memberTorokuForm.member.customCd}</div>
				<input name="member.customCd" type="hidden" value="${memberTorokuForm.member.customCd}"/>
				<label class="col-md-3 control-label">得意先名称</label>
				<div class="col-md-5  control-label">${memberTorokuForm.member.companyNm}</div>
				<input name="member.companyNm" type="hidden" value="${memberTorokuForm.member.companyNm}"/>
	        </div>
	        <div class="form-group  form-inline">
				<label class="col-md-3 control-label">事業所コード</label>
				<div class="col-md-5 control-label">${memberTorokuForm.member.officeCd}</div>
				<input name="member.officeCd" type="hidden" value="${memberTorokuForm.member.officeCd}"/>
				<label class="col-md-3 control-label">事業所名称</label>
				<div class="col-md-5 control-label">${memberTorokuForm.member.officeNm}</div>
				<input name="member.officeNm" type="hidden" value="${memberTorokuForm.member.officeNm}"/>
	        </div>
	        <div class="form-group  form-inline">
	            <label class="col-md-3 control-label">契約日</label>
	            <div class="col-md-5 control-label">${memberTorokuForm.member.keiyakuDayDis}</div>
				<input name="member.keiyakuDay" type="hidden" value="${memberTorokuForm.member.keiyakuDay}"/>
	            <label class="col-md-3 control-label">契約期間</label>
	            <div class="col-md-5 control-label">${memberTorokuForm.member.keiyakuKgDayDis}</div>
				<input name="member.keiyakuKgDay" type="hidden" value="${memberTorokuForm.member.keiyakuKgDay}"/>
	            <label class="col-md-3 control-label">利用端末数</label>
	            <div class="col-md-5 control-label">${memberTorokuForm.member.riyoTm}</div>
				<input name="member.riyoTm" type="hidden" value="${memberTorokuForm.member.riyoTm}"/>
	        </div>
	        <div class="form-group  form-inline">
	          <label class="col-md-3 control-label">業務種別</label>
	          <c:if test="${user.gyomuSb == '0'}">
	          	<div class="col-md-5 control-label">荷主</div>
	          </c:if>
	          <c:if test="${user.gyomuSb == '1'}">
	          	<div class="col-md-5 control-label">運送会社</div>
	          </c:if>
	          <c:if test="${user.gyomuSb == '9'}">
	          	<div class="col-md-5 control-label">管理者 </div>
	          </c:if>
	          <input name="member.gyomuSb" type="hidden" value="${memberTorokuForm.member.gyomuSb}"/>
			  <label class="col-md-3 control-label">料率</label>
			  <div class="col-md-5 control-label">${memberTorokuForm.member.ryoritu}%</div>
				<input name="member.ryoritu" type="hidden" value="${memberTorokuForm.member.ryoritu}"/>
			  <label class="col-md-3 control-label">保険金額</label>
			  <div class="col-md-5 control-label"><fmt:formatNumber value="${memberTorokuForm.member.hokenMn}" pattern="#,#00"/>
			  	<input name="member.hokenMn" type="hidden" value="${memberTorokuForm.member.hokenMn}"/>
			  </div>
	        </div>
        </c:otherwise>
        </c:choose>
		<div class="form-group form-inline">
		  <label class="col-md-3 control-label" for="postCode">郵便番号</label>
		  <div class="col-md-2">
			<div class="input-group">
			<input type="tel" id="postCode" name="member.postCode" class="form-control input-md postCode" placeholder="000-0000" maxlength="8" value="${memberTorokuForm.member.postCode}"></div>
		  </div>
		  <div class="col-md-3">
			<a class="btn btn-info btn-sm" id="hisory-syuka-0" onclick="getPostCodeInfo(event)">住所</a>
		  </div>
		  <!-- 都道府県 -->
		  <label class="col-md-3 control-label" for="prefNm" required>都道府県</label>
		  <div class="col-md-5">

		  		<select path="member.prefNm" id="prefNm" name="member.prefNm" class="form-control">
		  			<option value="" ></option>
		  			<c:forEach items="${memberTorokuForm.prefList}" var="pref">
						<c:choose>
							<c:when test="${memberTorokuForm.member.prefNm eq pref.prefName}">
								<option value="${pref.prefName}" selected>${pref.prefName}</option>
					        </c:when>
					        <c:otherwise>
					            <option value="${pref.prefName}">${pref.prefName}</option>
					        </c:otherwise>
					    </c:choose>
				    </c:forEach>
		  		</select>
		  </div>
		  <!-- 表示遅延時間 -->
		  <c:if test="${user.gyomuSb == '9'}">
			  <label class="col-md-3 control-label" for="dispDl">表示遅延時間</label>
			  <div class="col-md-5">
				<input id="dispDl" name="member.dispDl" type="tel" class="form-control input-md" size="10" maxlength="11" value="${memberTorokuForm.member.dispDl}">
			  </div>
		  </c:if>

		</div>
		<div class="form-group form-inline">
		  <!-- 市区町村 -->
		  <label class="col-md-3 control-label" for="cityNm" required>市区町村</label>
		  <div class="col-md-5">
			<input id="cityNm" name="member.cityNm" type="text" class="form-control input-md" maxlength="40" size="15" value="${memberTorokuForm.member.cityNm}">
		  </div>
		  <!-- 番地 -->
		  <label class="col-md-3 control-label" for="addrOther">番地など</label>
		  <div class="col-md-5">
			<input id="addrOther" name="member.addrOther" type="text" class="form-control input-md" maxlength="60" size="20" value="${memberTorokuForm.member.addrOther}">
		  </div>
		  <!-- 責任者 -->
		  <label class="col-md-3 control-label" for="sekininNm">責任者</label>
		  <div class="col-md-5">
			<input id="sekininNm" name="member.sekininNm" type="text" class="form-control input-md" size="10"  maxlength="20" value="${memberTorokuForm.member.sekininNm}">
		  </div>

		</div>
		<div class="form-group form-inline">
		  <!-- TEL -->
		  <label class="col-md-3 control-label" for="companyTel">TEL</label>
		  <div class="col-md-5">
			<input id="companyTel" name="member.companyTel" type="tel" class="form-control input-md tel" maxlength="15" size="15" value="${memberTorokuForm.member.companyTel}">
		  </div>
		  <!-- FAX -->
		  <label class="col-md-3 control-label" for="companyFax">FAX</label>
		  <div class="col-md-5">
			<input id="companyFax" name="member.companyFax" type="tel" class="form-control input-md tel" maxlength="15" size="15" value="${memberTorokuForm.member.companyFax}">
		  </div>
		  <!-- EMAIL-->
		  <label class="col-md-3 control-label" for="companyEmail">E-MAIL</label>
		  <div class="col-md-4">
			<input id="companyEmail" name="member.companyEmail" type="tel" class="form-control input-md email" maxlength="60" size="60" style="width: 10.5em;" value="${memberTorokuForm.member.companyEmail}">
		  </div>
		</div>
      </div>
      <!-- 基本情報(body) 　▲ -->
    </div>
    <!-- 基本情報 　▲-->

	 <!-- 請求情報　▼-->
<div id="truck-panel1" class="panel panel-default">
      <div class="panel-heading clearfix">
        <h4 class="panel-title pull-left" >請求・支払い情報</h4>
      </div>
      <!-- 担当者情報(body) 　▼ -->
      <div class="panel-body">
<table width="446" align="center" class="table table-bordered">
  <tr>
    <th width="28" scope="col">担当者</th>
    <th width="82" scope="col">氏名</th>
    <th width="70" scope="col">部署</th>
    <th width="43" scope="col">役職</th>
    <th width="79" scope="col">TEL</th>
    <th width="104" scope="col">E-Mail</th>
    </tr>
  <tr>
    <td>経理</td>
    <td><input id="keiriNm" name="member.keiriNm" type="text" class="form-control" size="15" value="${memberTorokuForm.member.keiriNm}"></td>
    <td><input id="keiriBs" name="member.keiriBs" type="text" class="form-control" size="15" value="${memberTorokuForm.member.keiriBs}"></td>
    <td><input id="keiriYk" name="member.keiriYk" type="text" class="form-control" size="15" value="${memberTorokuForm.member.keiriYk}"></td>
    <td><input id="keiriTel" name="member.keiriTel" type="tel" class="form-control tel" size="15" value="${memberTorokuForm.member.keiriTel}"></td>
    <td style="width: 10.5em;"><input id="keiriEmail" name="member.keiriEmail" type="tel" class="form-control email" maxlength="60" size="60" value="${memberTorokuForm.member.keiriEmail}"></td>
    </tr>
</table>
		<div class="form-group form-inline">
		  <!-- 銀行コード-->
		  <label class="col-md-2 control-label" for="ginkouCd">銀行コード</label>
		  <div class="col-md-4">
			<input id="ginkouCd" name="member.ginkouCd" type="tel" class="form-control input-md" size="15" value="${memberTorokuForm.member.ginkouCd}">
		  </div>
		  <!-- 銀行-->
		  <label class="col-md-2 control-label" for="ginkouNm">銀行名</label>
		  <div class="col-md-4">
			<input id="ginkouNm" name="member.ginkouNm" type="text" class="form-control input-md" size="15" value="${memberTorokuForm.member.ginkouNm}">
		  </div>
		  <!-- 支店コード-->
		  <label class="col-md-2 control-label" for="sitenCd">支店コード</label>
		  <div class="col-md-4">
			<input id="sitenCd" name="member.sitenCd" type="tel" class="form-control input-md" size="15" value="${memberTorokuForm.member.sitenCd}">
		  </div>
		  <!-- 支店名 -->
		  <label class="col-md-2 control-label" for="sitenNm">支店名</label>
		  <div class="col-md-4">
			<input id="sitenNm" name="member.sitenNm" type="text" class="form-control input-md" size="15" value="${memberTorokuForm.member.sitenNm}">
		  </div>
		</div>
		<div class="form-group form-inline">
		  <!-- 口座種類 -->
		  <label class="col-md-2 control-label" for="kozaSr">口座種類</label>
		  <div class="col-md-4">
            <select id="kozaSr" name="member.kozaSr" class="form-control ">
              <option value=""></option>
              <option value="普通" <c:if test="${memberTorokuForm.member.kozaSr eq '普通'}">selected</c:if>>普通</option>
              <option value="当座" <c:if test="${memberTorokuForm.member.kozaSr eq '当座'}">selected</c:if>>当座</option>
            </select>
		  </div>
		  <!-- 口座番号 -->
		  <label class="col-md-2 control-label" for="kozaNo">口座番号</label>
		  <div class="col-md-4">
			<input id="kozaNo" name="member.kozaNo" type="tel" class="form-control input-md number" size="10" value="${memberTorokuForm.member.kozaNo}">
		  </div>
		  <label class="col-md-2 control-label" for="simbSb">締日種別</label>
		  <div class="col-md-4">
            <select id="simbSb" name="member.simbSb" class="form-control ">
              <option value=""></option>
              <option value="月末締め" <c:if test="${memberTorokuForm.member.simbSb eq '月末締め'}">selected</c:if>>月末締め</option>
              <option value="10日締め" <c:if test="${memberTorokuForm.member.simbSb eq '10日締め'}">selected</c:if>>10日締め</option>
              <option value="15日締め" <c:if test="${memberTorokuForm.member.simbSb eq '15日締め'}">selected</c:if>>15日締め</option>
              <option value="20日締め" <c:if test="${memberTorokuForm.member.simbSb eq '20日締め'}">selected</c:if>>20日締め</option>
            </select>
		  </div>
		  <label class="col-md-2 control-label" for="sihrSt">支払サイト</label>
		  <div class="col-md-4">
            <select id="sihrSt" name="member.sihrSt" class="form-control ">
              <option value=""></option>
              <option value="翌月末支払い" <c:if test="${memberTorokuForm.member.sihrSt eq '翌月末支払い'}">selected</c:if>>翌月末支払い</option>
              <option value="翌々月末支払い" <c:if test="${memberTorokuForm.member.sihrSt eq '翌々月末支払い'}">selected</c:if>>翌々月末支払い</option>
              <option value="月末支払い" <c:if test="${memberTorokuForm.member.sihrSt eq '月末支払い'}">selected</c:if>>月末支払い</option>
              <option value="翌月20日支払い" <c:if test="${memberTorokuForm.member.sihrSt eq '翌月20日支払い'}">selected</c:if>>翌月20日支払い</option>
            </select>
		  </div>
		</div>
	  </div>
	</div>
	　<!-- 請求情報 　▲-->

   <!-- 担当者情報 ▼ -->
	<div id="truck-panel1" class="panel panel-default">
      <div class="panel-heading clearfix">
        <h4 class="panel-title pull-left" >担当者情報</h4>
      </div>
      <!-- 担当者情報(body) 　▼ -->
      <div class="panel-body">
		<table width="446" align="center" class="table table-bordered">
		  <tr>
		    <th width="28" scope="col">ID</th>
		    <th width="82" scope="col">氏名</th>
		    <th width="70" scope="col">部署</th>
		    <th width="43" scope="col">役職</th>
		    <th width="79" scope="col">TEL(携帯）</th>
		    <th width="104" scope="col">E-Mail(ログインID）</th>
		  </tr>
		  <c:forEach var="user" items="${memberTorokuForm.userList}" varStatus="status">
			  <tr>
			  	<input type="hidden" name="userList[${status.index}].userId" value="${user.userId}" />
			    <td>${user.index}</td>
			    <td><input name="userList[${status.index}].username" type="text" class="form-control" size="15" value="${user.username}"></td>
			    <td><input name="userList[${status.index}].userBS" type="text" class="form-control" size="15" value="${user.userBS}"></td>
			    <td><input name="userList[${status.index}].userYK" type="text" class="form-control" size="15" value="${user.userYK}"></td>
			    <td><input name="userList[${status.index}].renrakuTel" type="tel" class="form-control tel" size="15" value="${user.renrakuTel}"></td>
			    <td style="width: 10.5em;"><input name="userList[${status.index}].loginId" type="tel" class="form-control email" maxlength="60" size="60" value="${user.loginId}"></td>
			    <input name="userList[${status.index}].updateDt" type="hidden" value="${user.updateDt}">
			  </tr>
		  </c:forEach>
		</table>
	  </div>
	</div>
    <!-- 担当者情報 ▲ -->

    <!-- ボタン 　▼-->
    <div class="row" style="padding:15px 15px 15px 15px;clear:left;">
      <a id="btn_toroku" class="btn btn-primary col-md-offset-17">登　　録</a>
      <a href="index" class="btn btn-default col-md-offset-1"> キャンセル </a>
    </div>
    <!-- ボタン 　▲-->

	</fieldset>
  </form:form>
  <!-- フォーム　▲-->

</div>
</body>
</html>
