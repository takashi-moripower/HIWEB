<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="logistics.system.project.tuchi.Entity.TuchiEntity"%>
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
		<h1>通知</h1>
		<%
		List<String> errors = (List<String>)request.getAttribute("errors");
		if( errors != null ){
			%>
		<div class="panel panel-danger">
			<div class="panel-body text-danger">
				<ul>
				<% for(String error : errors){ %>
					<li><%= error %></li>
				<% } %>
				</ul>
			</div>
		</div>
		<%
		}
		%>
		<form method="post" class="form-horizontal" action="tuchi_edit">
			<input type="hidden" name="tuchiId" value="${form.tuchiId}" /> <input
				type="hidden" name="userId" value="${form.userId}" />
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<th style="width: 16rem">タイトル</th>
						<td><input type="text" name="title" value="${form.title}" /></td>
					</tr>
					<tr>
						<th>送信先Email</th>
						<td><input type="email" name="email"
							value="${form.email}" /></td>
					</tr>
					<tr>
						<th>荷主</th>
						<td>
							<select name="ninusiCd">
								<option value="">
								指定なし
								</option>
								<% for(MemberEntity m : ninushiList){
									String sel = (m.getCompanyCd().equals(form.getNinusiCd())) ? "selected" : "";
								%>
								<option value="<%= m.getCompanyCd() %>" <%=sel%>>
									<%= m.getCompanyNm() %>
								</option>
								<% } %>
							</select>
						</td>
					</tr>
					<tr>
						<th>地域</th>
						<td><select name="prefCd">
								<option value="00">全国</option>
								<%
									for (PrefEntity p : Constants.getPrefList()) {
										String sel = (p.getPrefCd().equals(form.getPrefCd())) ? "selected" : "";
								%>
								<option value="<%=p.getPrefCd()%>" <%=sel%>>
									<%=p.getPrefName()%>
								</option>
								<%
									}
								%>
						</select></td>
					</tr>
					<%
						for (PrefEntity p : Constants.getPrefList()) {
					%>
					<tr pref_cd="<%=p.getPrefCd()%>" style="display:hidden">
						<th><%=p.getPrefName()%></th>
						<td>
							<div>
								<label for="city-all"> <input type="checkbox"
									name="all-city" id="all-city" /> 全域
								</label>
							</div>
							<%
						 	String cat = "00";

						 	for (CityEntity c : Constants.getCityList()) {
							 	if (c.getPrefCd().equals(p.getPrefCd())) {
								 	if (!cat.equals(c.getDispCateg())) {
					 					cat = c.getDispCateg();
										%><p><%=c.getDispCateg()%></p> <%
								 	}
					 				String checked = (form.getCity().contains(c.getCityCd())) ? "checked" : "";
									 %>
										<label for="city-<%=c.getCityCd()%>">
										 	<input type="checkbox" id="city-<%=c.getCityCd()%>" name="city" value="<%=c.getCityCd()%>" <%=checked%> />
								 	 		<%=c.getCityDisp()%>
										</label>
									<%
							 	}
						 	}
						 %>
						</td>
					</tr>
					<%
						}
					%>
					<tr>
						<th>受注期限・開始</th>
						<td><input type="date" name="dateStart"
							value="${form.dateStart}" /></td>
					</tr>
					<tr>
						<th>受注期限・終了</th>
						<td><input type="date" name="dateEnd"
							value="${form.dateEnd}" /></td>
					</tr>
					<tr>
						<th style="white-space: nowrap">トラックオプション</th>
						<td>
						<%
						for(TruckOpEntity t: Constants.getTruckOpList()){
							String checked = form.getTruckOp().contains( t.getOpCd() ) ? "checked" : "";
						%>
							<label for="truckOp-<%=t.getOpCd() %>">
								<input type="checkbox" id="truckOp-<%=t.getOpCd() %>" name="truckOp" value="<%=t.getOpCd() %>" <%= checked %>/>
								<%= t.getOpName() %>
							</label>
						<%
						}
						%>
						</td>
					</tr>
					<tr>
						<th style="white-space: nowrap">車種</th>
						<td>
						<%
						for(SyasyuEntity s: Constants.getSyasyuList()){
							String checked = form.getSyasyu().contains( s.getSyasyuCd() ) ? "checked" : "";
						%>
							<label for="syasyu-<%=s.getSyasyuCd() %>">
								<input type="checkbox" id="syasyu-<%=s.getSyasyuCd() %>" name="syasyu" value="<%=s.getSyasyuCd() %>" <%= checked %>/>
								<%= s.getSyasyuName() %>
							</label>
						<%
						}
						%>
						</td>
					</tr>
				</tbody>
			</table>
			<button type="submit">submit</button>
		</form>
	</div>
	<footer>
		<div class="container">
			<p class="text-right">Copyright &#169; 2015 TRAIL Corporation</p>
		</div>
	</footer>

</body>
</html>
