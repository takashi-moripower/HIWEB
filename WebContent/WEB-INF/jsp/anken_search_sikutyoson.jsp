<!DOCTYPE html>
<html lang="en">
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

	<%@ include file="common/header.jsp"%>
	<script>
	$(function() {

		var MaxSikutyosonSentakuSu = "${maxSikutyosonSentakuSu}";
		var ankenSearchForm = eval(${ankenSearchForm.json});
    	if(ankenSearchForm) {
    		$("input[name='cityNameList']").val(ankenSearchForm.cityNameList);
    	}
	});
	</script>
</head>
<body>
	<div class="container">
	 <%@ include file="common/nav.jsp"%>
  <form:form name="ankenSearchForm" commandName="ankenSearchForm" class="form-horizontal" action="sikutyosonSentaku"
	method="POST">
	<input name="func" type="hidden" value="${func}"/>
	<input name="prefName" type="hidden" value="${prefName}"/>
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
    	<c:forEach items="${dispCategs}" var="entry">
    		<h4>${entry.key}</h4>
    		<div class="form-group form-inline" style="margin-left:30px;">
    			<c:forEach items="${entry.value}" var="city">
	    			<div class="col-md-5">
	    				<label class="checkbox-inline" for="checkboxes-${city.cityCd}">
	    					<input type="checkbox" name="cityNameList" id="checkboxes-${city.cityCd}" value="${city.cityName}">
				    		<c:if test="${city.kensu == '0'}">
		    					<a style="color:black">${city.cityDisp}(${city.kensu})</a>
				    		</c:if>
				    		<c:if test="${city.kensu != '0'}">
		    					<a style="font-weight: bold">${city.cityDisp}(${city.kensu})</a>
				    		</c:if>
	    				</label>
	    			</div>
    			</c:forEach>
    		</div>
    	</c:forEach>
    </fieldset>

	<hr>
    <!-- ボタン 　▼-->
    <div class="row" style="padding:15px 15px 15px 15px;clear:left;">
      <button type="submit" class="btn btn-primary col-md-offset-17" id="btn_confirm">選　択</button>
      <c:choose>
      	<c:when test="${func eq 'shuka'}">
      		<a href="init_todohuen?func=${func}" class="btn btn-default col-md-offset-1"> 前画面に戻る </a>
      	</c:when>
      	<c:otherwise>
      		<a href="initAnkenSearch" class="btn btn-default col-md-offset-1"> 前画面に戻る </a>
      	</c:otherwise>
      </c:choose>
    </div>
    <!-- ボタン 　▲-->
  </form:form>
</div>
</body>
</html>
