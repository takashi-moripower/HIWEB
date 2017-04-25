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

  <form:form class="form-horizontal">
    <fieldset>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">案件登録</h4>
      </div>
      <div class="panel-body">
          <div class="form-group form-inline">
            <div class="col-md-1"></div>
            <div class="col-md-5">
		      <a class="btn btn-info btn-lg"  href="createcase" >新規案件登録</a>
			</div>
            <div class="col-md-1"></div>
		  </div>
      </div>
    </div>

	<div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">案件検索</h4>
      </div>
      <div class="panel-body">
          <div class="form-group form-inline">
            <div class="col-md-1"></div>
            <div class="col-md-5">
		      <a class="btn btn-info btn-lg"  href="uncertain" >未確定案件</a>
			  <span class="help-block">${ninushiKensuEntity.mikakuteiKensu}件（期日過ぎ<a href="uncertain?exceed=true">${ninushiKensuEntity.sugiKensu}件</a>）</span>
			</div>
            <div class="col-md-1"></div>
            <div class="col-md-5">
		      <a class="btn btn-info btn-lg"  href="certain" >確定済案件</a>
			  <span class="help-block">${kakuinZumiKensu}件</span>
			</div>
            <div class="col-md-1"></div>
            <div class="col-md-5">
		      <a class="btn btn-info btn-lg"  href="initAnkenSearch" >詳細検索</a>
			</div>
		  </div>
      </div>
    </div>

	<div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">登録情報管理</h4>
      </div>
      <div class="panel-body">
          <div class="form-group form-inline">
            <div class="col-md-1"></div>
            <div class="col-md-5">
		          <a class="btn btn-info btn-lg"  href="initUpdateMember">会員情報管理</a>
    			  </div>
            <div class="col-md-1"></div>
            <div class="col-md-5">
		          <a class="btn btn-info btn-lg"  href="<%=request.getContextPath()%>/changePassword" >ﾊﾟｽﾜｰﾄﾞ変更</a>
    			  </div>
		  </div>
      </div>
    </div>

	<div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">お知らせ</h4>
      </div>
      <div class="panel-body">
		<object data="resources/html/news_ninushi.html" width="100%"></object>
      </div>
    </div>

    </fieldset>
  </form:form >
  <!-- フォーム　▲-->
</div>
   <footer>
      <div class="container">
        <p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
    </div>
   </footer>
</body>
</html>
