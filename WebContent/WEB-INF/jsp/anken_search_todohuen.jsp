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

			var MaxTodohuenSentakuSu = "${maxTodohuenSentakuSu}";
			var ankenSearchForm = eval(${ankenSearchForm.json});
	    	if(ankenSearchForm) {
	    		$("input[name='prefNameList']").val(ankenSearchForm.prefNameList);
	    	}
		});
	</script>
</head>
<body>
	<div class="container">
	 <%@ include file="common/nav.jsp"%>
  <form:form name="ankenSearchForm" commandName="ankenSearchForm" class="form-horizontal" action="todohuenSentaku"
	method="post">
	<input name="func" type="hidden" value="${func}"/>
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
    	<!-- 北海道・東北 -->

    	<h4>北海道・東北</h4>
   		<div class="form-group form-inline" style="margin-left:100px;">
	    	<c:forEach var="pref" items="${prefs}">
	    		<c:if test="${pref.areaCd == '01' or pref.areaCd == '02'}">
		    		<div class="col-md-4">
		    			<label class="checkbox-inline" for="checkboxes-${pref.areaCd}-${pref.prefCd}">
				    		<input type="checkbox" name="prefNameList" id="checkboxes-${pref.areaCd}-${pref.prefCd}" value="${pref.prefName}">
				    		<c:choose>
				    			<c:when test="${func == 'shuka'}">
						    		<c:if test="${pref.kensu == '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}" style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}"  style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:when>
				    			<c:otherwise>
						    		<c:if test="${pref.kensu == '0'}">
					    				<a style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:otherwise>
				    		</c:choose>
			    		</label>
		    		</div>
	    		</c:if>
	    	</c:forEach>
	    </div>

		<!-- 関東 -->
    	<h4>関東</h4>
   		<div class="form-group form-inline" style="margin-left:100px;">
	    	<c:forEach var="pref" items="${prefs}">
	    		<c:if test="${pref.areaCd == '03'}">
		    		<div class="col-md-4">
		    			<label class="checkbox-inline" for="checkboxes-${pref.areaCd}-${pref.prefCd}">
				    		<input type="checkbox" name="prefNameList" id="checkboxes-${pref.areaCd}-${pref.prefCd}" value="${pref.prefName}">
				    		<c:choose>
				    			<c:when test="${func == 'shuka'}">
						    		<c:if test="${pref.kensu == '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}" style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}"  style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:when>
				    			<c:otherwise>
						    		<c:if test="${pref.kensu == '0'}">
					    				<a style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:otherwise>
				    		</c:choose>
			    		</label>
		    		</div>
	    		</c:if>
	    	</c:forEach>
	    </div>

      	<!-- 北陸・中部 -->
    	<h4>北陸・中部</h4>
   		<div class="form-group form-inline" style="margin-left:100px;">
	    	<c:forEach var="pref" items="${prefs}">
	    		<c:if test="${pref.areaCd == '04'}">
		    		<div class="col-md-4">
		    			<label class="checkbox-inline" for="checkboxes-${pref.areaCd}-${pref.prefCd}">
				    		<input type="checkbox" name="prefNameList" id="checkboxes-${pref.areaCd}-${pref.prefCd}" value="${pref.prefName}">
				    		<c:choose>
				    			<c:when test="${func == 'shuka'}">
						    		<c:if test="${pref.kensu == '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}" style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}"  style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:when>
				    			<c:otherwise>
						    		<c:if test="${pref.kensu == '0'}">
					    				<a style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:otherwise>
				    		</c:choose>
			    		</label>
		    		</div>
	    		</c:if>
	    	</c:forEach>
	    </div>

      	<!-- 近畿 -->
    	<h4>近畿</h4>
   		<div class="form-group form-inline" style="margin-left:100px;">
	    	<c:forEach var="pref" items="${prefs}">
	    		<c:if test="${pref.areaCd == '05'}">
		    		<div class="col-md-4">
		    			<label class="checkbox-inline" for="checkboxes-${pref.areaCd}-${pref.prefCd}">
				    		<input type="checkbox" name="prefNameList" id="checkboxes-${pref.areaCd}-${pref.prefCd}" value="${pref.prefName}">
				    		<c:choose>
				    			<c:when test="${func == 'shuka'}">
						    		<c:if test="${pref.kensu == '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}" style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}"  style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:when>
				    			<c:otherwise>
						    		<c:if test="${pref.kensu == '0'}">
					    				<a style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:otherwise>
				    		</c:choose>
			    		</label>
		    		</div>
	    		</c:if>
	    	</c:forEach>
	    </div>

      	<!-- 中国・四国 -->
    	<h4>中国・四国</h4>
   		<div class="form-group form-inline" style="margin-left:100px;">
	    	<c:forEach var="pref" items="${prefs}">
	    		<c:if test="${pref.areaCd == '06' or pref.areaCd == '07'}">
		    		<div class="col-md-4">
		    			<label class="checkbox-inline" for="checkboxes-${pref.areaCd}-${pref.prefCd}">
				    		<input type="checkbox" name="prefNameList" id="checkboxes-${pref.areaCd}-${pref.prefCd}" value="${pref.prefName}">
				    		<c:choose>
				    			<c:when test="${func == 'shuka'}">
						    		<c:if test="${pref.kensu == '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}" style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}"  style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:when>
				    			<c:otherwise>
						    		<c:if test="${pref.kensu == '0'}">
					    				<a style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:otherwise>
				    		</c:choose>
			    		</label>
		    		</div>
	    		</c:if>
	    	</c:forEach>
	    </div>

      	<!-- 九州・沖縄 -->
    	<h4>九州・沖縄</h4>
   		<div class="form-group form-inline" style="margin-left:100px;">
	    	<c:forEach var="pref" items="${prefs}">
	    		<c:if test="${pref.areaCd == '08' or pref.areaCd == '09'}">
		    		<div class="col-md-4">
		    			<label class="checkbox-inline" for="checkboxes-${pref.areaCd}-${pref.prefCd}">
				    		<input type="checkbox" name="prefNameList" id="checkboxes-${pref.areaCd}-${pref.prefCd}" value="${pref.prefName}">
				    		<c:choose>
				    			<c:when test="${func == 'shuka'}">
						    		<c:if test="${pref.kensu == '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}" style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a href="init_sikutyoson?func=shuka&prefCd=${pref.prefCd}"  style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:when>
				    			<c:otherwise>
						    		<c:if test="${pref.kensu == '0'}">
					    				<a style="color:black">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
						    		<c:if test="${pref.kensu != '0'}">
					    				<a style="font-weight: bold">${pref.prefName}(${pref.kensu})</a>
						    		</c:if>
				    			</c:otherwise>
				    		</c:choose>
			    		</label>
		    		</div>
	    		</c:if>
	    	</c:forEach>
	    </div>
    </fieldset>
    <hr>
    <!-- ボタン 　▼-->
    <div class="row" style="padding:15px 15px 15px 15px;clear:left;">
      <button type="submit" class="btn btn-primary col-md-offset-17" id="btn_confirm">選　択</button>
      <c:choose>
      	<c:when test="${func eq 'shuka'}">
      		<a href="index" class="btn btn-default col-md-offset-1"> 前画面に戻る </a>
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
