<%@page import="logistics.system.project.tuchi.form.TuchiEditForm"%>
<%@page import="logistics.system.project.utility.Constants"%>
<%@page import="logistics.system.project.common.Entity.CityEntity"%>
<%
	TuchiEditForm form = (TuchiEditForm) request.getAttribute("form");
	String prefCd = (String) request.getAttribute("prefCd");
%>


<div class="form-group form-inline city-selecter" pref_cd="<%=prefCd%>" style="display:none">
	<label class="col-md-3 control-label"> <%=Constants.getPrefName(prefCd)%>
	</label>
	<div class="col-md-21 panel panel-default">
		<div class="panel-body">
			<label> <input type="checkbox" name="all-city"
				id="all-city" />&nbsp;<%=Constants.getPrefName(prefCd)%><%= Constants.AREA_ALL %>
			</label>
		</div>
		<%
			String cat = "00";

			for (CityEntity c : Constants.getCityList()) {
				if (c.getPrefCd().equals(prefCd)) {
					if (!cat.equals(c.getDispCateg())) {
						cat = c.getDispCateg();
		%><p><%=c.getDispCateg()%></p>
		<%
			}
					String checked = (form.getCity().contains(c.getCityCd())) ? "checked" : "";
		%>
		<label for="city-<%=c.getCityCd()%>">&nbsp;<input type="checkbox"
			id="city-<%=c.getCityCd()%>" name="city" value="<%=c.getCityCd()%>"
			<%=checked%> /> <%=c.getCityDisp()%>
		</label>
		<%
			}
			}
		%>

	</div>
</div>