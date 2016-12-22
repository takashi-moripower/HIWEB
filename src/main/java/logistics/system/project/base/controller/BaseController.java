package logistics.system.project.base.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;

public class BaseController  {
	
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected HttpSession session = null;
	protected UserEntity userSession = null;
	
	/**
	 * subclass implement check user
	 * @return
	 */
	public boolean checkUser(String ankenNo){
		return true;
	}
	
	public void setHeader(String[] headerArgs, HashMap<String, Object> results) {	
		
		String tabTitle= "", pageTitle = "", ownerName = "";
		
		if(headerArgs.length >= 1 && StringUtils.isNotEmpty(headerArgs[0])) {
			tabTitle = headerArgs[0];
		}
		
		if(headerArgs.length >= 2 && StringUtils.isNotEmpty(headerArgs[1])) {
			pageTitle = headerArgs[1];
		}
		
		if(headerArgs.length >= 3 && StringUtils.isNotEmpty(headerArgs[2])) {
			ownerName = headerArgs[2];
		}
						
		
		results.put(Constants.TAB_TITLE, tabTitle);
		results.put(Constants.PAGE_TITLE, pageTitle);
		results.put(Constants.OWNER_NAME, ownerName);
		results.put(Constants.SHOW_LOGOUT_FLAG, false);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public UserEntity getUserSession() {
		return userSession;
	}

	public void setUserSession(UserEntity userSession) {
		this.userSession = userSession;
	}
	
	
}
