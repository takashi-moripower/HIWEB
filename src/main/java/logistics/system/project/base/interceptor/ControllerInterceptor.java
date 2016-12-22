package logistics.system.project.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.controller.AnkenDetailController;
import logistics.system.project.utility.ComUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ControllerInterceptor extends HandlerInterceptorAdapter {

	@Value("#{configProperties['auth_problem']}")
	private String auth_problem;

	@Value("#{configProperties['session_timeout']}")
	private String session_timeout;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			if(method.getBean() instanceof BaseController) {
				BaseController bc = (BaseController)method.getBean();
				bc.setRequest(request);
				bc.setResponse(response);
				HttpSession session = request.getSession();
				bc.setSession(session);

				if (session.getAttribute("user") != null) {
					bc.setUserSession((UserEntity)session.getAttribute("user"));
					if (bc instanceof AnkenDetailController) {
						String ankenNo = request.getParameter("ankenNo");
						if (!bc.checkUser(ankenNo)) {

							response.addHeader("Pragma", "No-cache");
							response.addHeader("Cache-Control", "no-cache");
							response.setDateHeader("Expires",-10);

							if (ComUtils.isAjax(request)) {
								String jsonRes ="{success:'0'}";
								response.getWriter().write(jsonRes);
							} else {
								String referer = request.getHeader("Referer");

								if (StringUtils.isBlank(referer) || referer.indexOf("login") >= 0) {
									referer = "index";
								}
								if (referer.indexOf("ankenDetailZyutyu") >= 0 || referer.indexOf("initAnkenDetail") >=0 ) {
									referer = "ankenList?pageNo=1";
								}
								response.setContentType("text/html; charset=UTF-8");
								String scriptStr ="<script>alert('" + this.auth_problem + "');"
										+ "document.location.replace('"+referer+"');</script>";
								response.getWriter().write(scriptStr);
							}
							return false;
						}
					}
				}
				else {
					String requestUrl = request.getRequestURI();
					String path = request.getServletPath();
					if(requestUrl.endsWith("login")) {
						return true;
					}
					else {

						if (requestUrl.matches(".*(initAnkenDetail).*")) {
							session.setAttribute("backUrl", path + "?" + request.getQueryString());
						}

						response.addHeader("Pragma", "No-cache");
						response.addHeader("Cache-Control", "no-cache");
						response.setDateHeader("Expires",-10);
						response.setContentType("text/html; charset=UTF-8");

						String scriptStr ="<script>document.location.replace('login.jsp');</script>";
						response.getWriter().write(scriptStr);
						//response.sendRedirect("../login.jsp");
						return false;
					}
				}
			}

		}


		return super.preHandle(request, response, handler);
	}
}
