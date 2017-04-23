<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="logistics.system.project.tuchi.Entity.TuchiEntity"%>
<%@page import="logistics.system.project.common.Entity.AreaEntity"%>
<%@page import="logistics.system.project.common.Entity.PrefEntity"%>
<%@page import="logistics.system.project.common.Entity.CityEntity"%>
<%@page import="logistics.system.project.common.Entity.TruckOpEntity"%>
<%@page import="logistics.system.project.common.Entity.SyasyuEntity"%>
<%@page import="logistics.system.project.common.Entity.MemberEntity"%>
<%@page import="logistics.system.project.tuchi.form.TuchiEditForm"%>
<%@page import="logistics.system.project.utility.Constants"%>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="../common/header.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/tuchi/edit.js"></script>
<script>

</script>
<%
	TuchiEditForm form = (TuchiEditForm) request.getAttribute("form");
	List<MemberEntity> ninushiList = (List<MemberEntity>) request.getAttribute("ninushiList");
%>

</head>
<body>
	<div class="container">
		<%@ include file="../common/nav.jsp"%>
	</div>
	<div class="container">

		<form method="post" class="form-horizontal" action="tuchi_edit"
			method="POST">
			<input type="hidden" name="tuchiId" value="${form.tuchiId}" /> <input
				type="hidden" name="userId" value="${form.userId}" />
			<fieldset>
				<%
					List<String> errors = (List<String>) request.getAttribute("errors");
					if (errors != null) {
				%>

				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul>
						<%
							for (String error : errors) {
						%>
						<li><%=error%></li>
						<%
							}
						%>
					</ul>
				</div>
				<%
					}
				%>

				<input name="func" type="hidden" value="ankenSearch"> <input
					id="prefName" name="prefName" type="hidden" value=""> <input
					id="panelClosed" name="panelClosed" type="hidden" value="">
				<!-- 検索条件　▼ -->
				<div class="panel panel-default">
					<!-- 検索条件(heading) 　▼ -->
					<div class="panel-heading">
						<h4 class="panel-title">通知条件</h4>
					</div>
					<!-- 検索条件(body) 　▼ -->
					<div id="search_detail" class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label">タイトル</label>
							<div class="col-md-8">
								<input type="text" name="title" value="${form.title}" />
							</div>
						</div>
						<div class="form-group  form-inline">
							<label class="col-md-3 control-label">送信先Email</label>
							<div class="col-md-8">
								<input type="email" name="email" value="${form.email}" />
							</div>
						</div>

						<div class="form-group  form-inline">
							<label class="col-md-3 control-label">荷主</label>
							<div class="col-md-8">
								<select name="ninusiCd">
									<option value="">指定なし</option>
									<%
										for (MemberEntity m : ninushiList) {
											String sel = (m.getCompanyCd().equals(form.getNinusiCd())) ? "selected" : "";
											String buf = String.format("<option value='%s' %s>%s</option>", m.getCompanyCd(), sel,
													m.getCompanyNm());
											out.println(buf);
										}
									%>
								</select>
							</div>
						</div>
						<div class="form-group form-inline">
							<label class="col-md-3 control-label">都道府県</label>
							<div class="col-md-21">
								<a class="pref-result"><%=form.getPrefName()%></a>
								<div class="pref-selecter panel panel-default">
									<div class="panel-body">
										<%
											for (AreaEntity a : Constants.getAreaList()) {
												out.println("<div class='row'><div class='col-md-2'>" + a.getAreaName() + "</div>");
												for (PrefEntity p : form.getPrefByArea(a.getAreaCd())) {
													List<String> pr = form.getPref();
													String checked = form.getPref().contains(p.getPrefCd()) ? "checked" : "";
													String buf = p.getPrefName();
													buf = String.format("<input type='checkbox' %s id='pref-%s' name='pref' value='%s' />", checked,
															p.getPrefCd(), p.getPrefCd()) + buf;
													buf = String.format("<label for='pref-%s' class='col-md-2'>", p.getPrefCd()) + buf + "</label>";
													out.println(buf);
												}
												out.println("</div>");
											}
										%>
									</div>
								</div>
							</div>
						</div>
						<%
							for (PrefEntity p : Constants.getPrefList()) {
								request.setAttribute("prefCd", p.getPrefCd());
						%>
						<jsp:include page="edit_city.jsp" />
						<%
							}
						%>
						<div class="form-group form-inline">
							<label class="col-md-3 control-label">車種</label>
							<div class="col-md-21">
								<%
									for (SyasyuEntity s : Constants.getSyasyuList()) {
										String checked = form.getSyasyu().contains(s.getSyasyuCd()) ? "checked" : "";
										String buf = s.getSyasyuName();
										buf = String.format("<input type='checkbox' id='syasyu-%s' name='syasyu' value='%s' %s/>",
												s.getSyasyuCd(), s.getSyasyuCd(), checked) + buf;
										buf = String.format("<label for='syasyu-%s'>", s.getSyasyuCd()) + buf + "&nbsp;</label>";
										out.println(buf);
									}
								%>
							</div>
						</div>
						<div class="form-group form-inline">
							<label class="col-md-3 control-label">集荷日付</label>
							<div class="col-md-21">
								<div class="input-group date">
									<input id="dateStart" name="dateStart" type="text"
										class="form-control" value="<%=form.getDateStart()%>"
										size="12"> <span class="input-group-addon "><i
										class="glyphicon glyphicon-th"></i></span>
								</div>
								<p class="form-control-static">～</p>
								<div class="input-group date">
									<input id="dateEnd" name="dateEnd" type="text"
										class="form-control" value="<%=form.getDateEnd()%>"
										size="12"> <span class="input-group-addon "><i
										class="glyphicon glyphicon-th"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group form-inline">
							<label class="col-md-3 control-label">オプション</label>
							<div class="col-md-21">
								<%
									for (TruckOpEntity t : Constants.getTruckOpList()) {
										String checked = form.getTruckOp().contains(t.getOpCd()) ? "checked" : "";
										String buf = t.getOpName();
										buf = String.format("<input type='checkbox' id='truckOp-%s' name='truckOp' value='%s' %s/>",
												t.getOpCd(), t.getOpCd(), checked) + buf;
										buf = String.format("<label for='truckOp-%s'>", t.getOpCd()) + buf + "&nbsp;</label>";
										out.println(buf);
									}
								%>
							</div>
						</div>
						<div class="form-group form-inline">
							<a href="tuchi_list"
								class="btn btn-default col-md-3 col-md-offset-18"> 一覧に戻る </a>
							<button type="submit" class="btn btn-info col-md-3">保存</button>
						</div>

					</div>
					<!-- 基本情報(body) 　▲ -->
				</div>
				<!-- 検索条件　▲-->
			</fieldset>
		</form>
		<!-- フォーム　▲-->


	</div>
	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>

</body>
</html>
